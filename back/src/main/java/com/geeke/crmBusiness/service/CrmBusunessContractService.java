package com.geeke.crmBusiness.service;

import cn.hutool.json.JSONUtil;
import com.geeke.camundaex.entity.ActResult;
import com.geeke.common.data.Parameter;
import com.geeke.common.persistence.DataEntity;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.contract.entity.CrmContract;
import com.geeke.contract.entity.CrmContractPay;
import com.geeke.contract.enums.ContractStatus;
import com.geeke.contract.enums.PayType;
import com.geeke.contract.service.CrmContractPayService;
import com.geeke.crmBusiness.dao.CrmBusunessContractDao;
import com.geeke.crmBusiness.dao.CrmOpportunityDao;
import com.geeke.crmBusiness.dao.CrmOpportunityPayDao;
import com.geeke.crmBusiness.entity.CrmOpportunity;
import com.geeke.crmBusiness.entity.CrmOpportunityPay;
import com.geeke.crmCustomer.dao.CrmCustomerDao;
import com.geeke.crmCustomer.entity.CrmCustomer;
import com.geeke.org.dao.DepartmentDao;
import com.geeke.org.entity.Department;
import com.geeke.paymentBack.dao.PaymentBackDao;
import com.geeke.paymentBack.entity.PaymentBack;
import com.geeke.sys.entity.SysFile;
import com.geeke.sys.service.SysFileService;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.SessionUtils;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.geeke.wf.entity.WfDraft;
import com.geeke.wf.persistence.WfDataEntity;
import com.geeke.wf.service.BizInProcService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 合同管理Service
 * @author
 * @version
 */

@Service("crmBusunessContractService")
@Transactional(readOnly = false)
public class CrmBusunessContractService extends BizInProcService<CrmBusunessContractDao, CrmContract> {
    @Autowired
    private CrmContractPayService crmContractPayService;

    @Autowired
    private CrmBusunessContractDao crmContractDao;

    @Autowired
    private CrmCustomerDao crmCustomerDao;
    @Autowired
    private CrmOpportunityDao crmOpportunityDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private SysFileService sysFileService;
    @Autowired
    private PaymentBackDao paymentBackDao;
    @Autowired
    private CrmOpportunityCotService opportunityCotService;

    @Autowired
    private CrmOpportunityPayService crmOpportunityPayService;
    /**
     * 获取表单对象的class
     * @return
     */
    @Override
    protected Class<CrmContract> getBizClass() {
        return CrmContract.class;
    }



    /**
     * 保存合同
     * @param contract
     */
    @Transactional
    public CrmContract saveContract(CrmContract contract) {
        CrmContract crmContract = new CrmContract();
        BeanUtils.copyProperties(contract, crmContract);
        // 校验
        saveVerify(contract);
        // 保存合同
        crmContract.setContractStatus(ContractStatus.SAVE.getValue());
        crmContract.setUserId(SessionUtils.getUser().getId());
        crmContract.setDeptId(SessionUtils.getUser().getDepartment().getId());
        if (StringUtils.isBlank(contract.getId())){
            super.save(crmContract);
        }else{
            super.doInsert(crmContract);
        }
        return crmContract;

    }

    @Transactional
    public CrmContract saveContractWithAttachment(CrmContract contract, MultipartFile[] attachments) {
        CrmContract crmContract = saveContract(contract);
        saveAttachment(attachments, crmContract);
        return crmContract;
    }

    /**
     * 删除合同
     * @param crmContract
     */
    public int deleteContract(CrmContract crmContract) {
        deleteVerify(crmContract);
        // delete contract
        super.delete(crmContract);
        // delete payList
        return crmContractPayService.deleteByContractId(crmContract.getId());
    }
    @Override
    public int saveDraft(WfDraft wfDraft, CrmContract contract, MultipartFile[] attachments) throws IOException {
        CrmContract crmContract = null;
        if (StringUtils.isNotBlank(contract.getOpporStatus())){
            crmContract = saveContract(contract);
            opportunityCotService.saveContract(crmContract);
        }
        saveAttachment(attachments,crmContract);
        super.saveDraft(wfDraft, crmContract, attachments);
        return 0;
    }

    @Override
    public ActResult beforeCreate(CrmContract crmContract) {
        // 提交流程前先删除草稿 仅限于展示的合同
        int deletes = this.deleteContractByOppId(crmContract.getOpportunityId());
        savePayList(crmContract);
        opportunityCotService.saveContract(crmContract);
        return new ActResult(ActResult.EnumCodes.Done, "回调成功");
    }

    private int deleteContractByOppId(String opportunityId) {

        return crmContractDao.deleteContractByOppId(opportunityId);
    }

    @Override
    public ActResult afterCreate(CrmContract crmContract) {
        // 创建流程后设置状态
        String opportunityId = crmContract.getOpportunityId();
        CrmOpportunity opportunity = crmOpportunityDao.get(opportunityId);
        if (crmContract.getProcStatus().getValue().equals(WfDataEntity.PROC_FLAG_COMPLETED)) {
            crmContract.setContractStatus(ContractStatus.APPROVE.getValue());
            opportunity.setOppStage("4");
            // 审批通过后，生成回款记录
            bulkInsertPaymentBack(crmContract);
        } else {
            crmContract.setContractStatus(ContractStatus.IN_PROGRESS.getValue());
            opportunity.setOppStage("3");
        }
        opportunity.setOldUpdateDate(opportunity.getUpdateDate());
        crmOpportunityDao.update(opportunity);

        return new ActResult(ActResult.EnumCodes.Done, "回调成功");
    }

    @Override
    public ActResult afterAgree(CrmContract crmContract) {
        // 通过后流程后设置状态
        if (crmContract.getProcStatus().getValue().equals(WfDataEntity.PROC_FLAG_COMPLETED)) {
            crmContract.setContractStatus(ContractStatus.APPROVE.getValue());

            // 审批通过后，生成回款记录
            bulkInsertPaymentBack(crmContract);
            // 修改商机状态
            String opportunityId = crmContract.getOpportunityId();
            CrmOpportunity opportunity = new CrmOpportunity(opportunityId);
            opportunity.setOppStage("4");
            crmOpportunityDao.update(opportunity);
        }
        return new ActResult(ActResult.EnumCodes.Done, "回调成功");
    }

    @Override
    public ActResult afterBack(CrmContract crmContract, boolean toStart) {
        // 退回后流程后设置状态
        if (toStart) {
            crmContract.setContractStatus(ContractStatus.REJECT.getValue());
        }
        return new ActResult(ActResult.EnumCodes.Done, "回调成功");
    }



    @Transactional
    public void savePayList(CrmContract crmContract) {
        // 保存还款信息
        List<CrmContractPay> payList = crmContract.getPayList();
        if (CollectionUtils.isEmpty(payList)) {
            return;
        }
        payList.forEach(item -> item.setContractId(crmContract.getId()));
        Parameter contractIdParam = new Parameter("contract_id", "=", crmContract.getId());

        List<CrmContractPay> crmContractPays = crmContractPayService.listAll(Lists.newArrayList(contractIdParam), "contract_id");


        if (!crmContractPays.isEmpty()) {
            crmContractPayService.bulkDelete(crmContractPays);
        }
        List<String> listPayIds =  crmContractPayService.bulkInsert(payList);

        // 生成商机回款记录
        CrmOpportunity opportunity = new CrmOpportunity();
        opportunity.setId(crmContract.getOpportunityId());
        // 新增合同回款信息
        List<CrmOpportunityPay> list = new ArrayList<>();
        listPayIds.forEach(items->{
            CrmOpportunityPay pay = new CrmOpportunityPay();
            pay.setOpp(opportunity);
            pay.setPayId(items);
            list.add(pay);
        });
        crmOpportunityPayService.bulkInsert(list);
    }


    public void entityToDto(CrmContract crmContract, boolean withPayList) {
        if (crmContract == null) {
            return;
        }
        // deptName
        Department department = departmentDao.get(crmContract.getDeptId());
        crmContract.setDeptName(department == null ? "" : department.getName());
        // set customerName
        CrmCustomer customer = crmCustomerDao.get(crmContract.getCustomerId());
        crmContract.setCustomerName(customer == null ? "" : customer.getCusName());
        // set opportunityName
        CrmOpportunity opportunity = crmOpportunityDao.get(crmContract.getOpportunityId());
        crmContract.setOpportunityName(opportunity == null ? "" : opportunity.getOppName());
        // set payTypeName
        crmContract.setPayTypeName(PayType.getTypeByValue(crmContract.getPayType()));
        // set payList
        if (withPayList) {
            Parameter contractIdParam = new Parameter("contract_id", "=", crmContract.getId());
            List<CrmContractPay> crmContractPays = crmContractPayService.listAll(Lists.newArrayList(contractIdParam), "a.order");
            crmContract.setPayList(crmContractPays);
        }

    }

    public void entityListToDtoList(List<CrmContract> contractList, boolean withPayList) {
        if (CollectionUtils.isEmpty(contractList)) {
            return;
        }
        contractList.forEach(item -> entityToDto(item, withPayList));
    }

    /**
     * 编辑合同校验
     * @param contract
     */
    public void saveVerify(CrmContract contract) {
        // if edit, skip verify
        if (contract == null || StringUtils.isNullOrEmpty(contract.getId())) {
            return;
        }
        CrmContract crmContract = super.get(contract.getId());
        if (crmContract == null) {
            return;
        }
        // 合同状态
        String contractStatus = crmContract.getContractStatus();
        if (!contractStatus.equals(ContractStatus.SAVE.getValue()) && !contractStatus.equals(ContractStatus.REJECT.getValue())) {
            throw new CommonJsonException(ResultUtil.warningJson(ErrorEnum.E_50001, "当前状态无法编辑合同"));
        }
    }

    /**
     * 删除校验
     * @param crmContract
     */
    public void deleteVerify(CrmContract crmContract) {
        // if edit, skip verify
        if (crmContract == null || StringUtils.isNullOrEmpty(crmContract.getId())) {
            throw new CommonJsonException(ResultUtil.warningJson(ErrorEnum.E_50001, "数据错误"));
        }
        CrmContract oldContract = super.get(crmContract.getId());
        // 意味着没有数据
        if ((oldContract) == null){
            return;
        }
        // 合同状态
        String contractStatus = oldContract.getContractStatus();
        if (!contractStatus.equals(ContractStatus.SAVE.getValue()) && !contractStatus.equals(ContractStatus.REJECT.getValue())) {
            throw new CommonJsonException(ResultUtil.warningJson(ErrorEnum.E_50001, "当前状态无法删除合同"));
        }
    }

    /**
     * 保存附件
     * @param attachments
     * @param crmContract
     * @return
     */
    public void saveAttachment(MultipartFile[] attachments, CrmContract crmContract) {

        try {
            // delete old
            if (StringUtils.isNotEmpty(crmContract.getId())) {
                List<String> ids = JSONUtil.toList(crmContract.getAttachment(), String.class);
                sysFileService.delete((String[]) ids.toArray());
            }

            if(ArrayUtils.isEmpty(attachments)) {
                crmContract.setAttachment(null);
            } else {
                List<SysFile> sysFiles = sysFileService.changeAndSaveSysFileList(attachments, crmContract.getId());
                crmContract.setAttachment(sysFiles.stream().map(SysFile::getId).collect(Collectors.toList()).toString());
            }
            crmContract.preUpdate();
            crmContractDao.update(crmContract);
        } catch (Exception e) {
            logger.error("Failed to upload attachment");
        }
        return;
    }



    /**
     * 根据回款编号（合同签署日期+合同编号）查询合同信息*
     * @param paymentBackPlanNo 回款编号
     * @return 合同信息
     */
    public CrmContract getByPaymentBackPlanNo(String paymentBackPlanNo){
        CrmContract contract = dao.getByPaymentBackPlanNo(paymentBackPlanNo);
        entityToDto(contract, false);
        return contract;
    }

    /**
     * 批量生成回款记录 *
     * @param crmContract 合同信息
     */
    @Transactional
    public void bulkInsertPaymentBack(CrmContract crmContract){
        Parameter contractIdParam = new Parameter("contract_id", "=", crmContract.getId());
        List<CrmContractPay> crmContractPays = crmContractPayService.listAll(Lists.newArrayList(contractIdParam), "a.order");
        List<PaymentBack> paymentBackList = new ArrayList<>();
        PaymentBack paymentBack = new PaymentBack();
        if(CollectionUtils.isNotEmpty(crmContractPays)) {
            for(CrmContractPay pay : crmContractPays){
                BeanUtils.copyProperties(pay, paymentBack);
                paymentBackList.add(paymentBack);
            }
            paymentBackDao.bulkInsert(paymentBackList);
        }
    }

    public List<CrmContractPay> getPayList(String id) {
        Parameter contractIdParam = new Parameter("contract_id", "=", id);
        return crmContractPayService.listAll(Lists.newArrayList(contractIdParam), "a.order");
    }
}
