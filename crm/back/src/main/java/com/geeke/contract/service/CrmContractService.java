package com.geeke.contract.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.geeke.admin.dao.UserDao;
import com.geeke.admin.entity.UserRole;
import com.geeke.businessopportunity.dao.CrmBusinessOpportunityDao;
import com.geeke.businessopportunity.entity.CrmBusinessOpportunity;
import com.geeke.camundaex.entity.ActResult;
import com.geeke.common.constants.ActionConstants;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.common.data.Parameter;
import com.geeke.common.persistence.DataEntity;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.contract.dao.CrmContractDao;
import com.geeke.contract.dao.CrmContractPayDao;
import com.geeke.contract.entity.CrmContract;
import com.geeke.contract.entity.CrmContractPay;
import com.geeke.contract.enums.ContractStatus;
import com.geeke.contract.enums.PayType;
import com.geeke.crmBusiness.dao.CrmOpportunityDao;
import com.geeke.crmBusiness.entity.CrmOpportunity;
import com.geeke.crmCustomer.dao.CrmCustomerDao;
import com.geeke.crmCustomer.entity.CrmCustomer;
import com.geeke.customerManagement.dao.CrmCustomerManagementDao;
import com.geeke.customerManagement.entity.CrmCustomerManagement;
import com.geeke.org.dao.DepartmentDao;
import com.geeke.org.entity.Department;
import com.geeke.sys.entity.SysFile;
import com.geeke.sys.service.SysFileService;
import com.geeke.paymentBack.dao.PaymentBackDao;
import com.geeke.paymentBack.entity.PaymentBack;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.SessionUtils;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.geeke.wf.entity.WfDraft;
import com.geeke.wf.persistence.WfDataEntity;
import com.geeke.wf.service.BizInProcService;
import com.geeke.wf.service.NoneBizInProcService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 合同管理Service
 * @author
 * @version
 */

@Service("crmContractService")
@Transactional(readOnly = false)
public class CrmContractService extends BizInProcService<CrmContractDao, CrmContract> {
    @Autowired
    private CrmContractPayService crmContractPayService;
    @Autowired
    private CrmContractPayDao crmContractPayDao;

    @Autowired
    private CrmContractDao crmContractDao;

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
        // 校验
        saveVerify(contract);
        // 保存合同
        contract.setUserId(SessionUtils.getUser().getId());
        contract.setDeptId(SessionUtils.getUser().getDepartment().getId());
        super.save(contract);
        // 保存还款信息
        savePayList(contract);

        return contract;

    }


    /**
     * 删除合同
     * @param crmContract
     */
    public int deleteContract(CrmContract crmContract) {
        deleteVerify(crmContract);
        CrmContract oldContract = crmContractDao.get(crmContract.getId());
        // delete contract
        super.delete(crmContract);
        // delete payList
        crmContractPayService.deleteByContractId(crmContract.getId());
        return 1;
    }

    @Override
    public ActResult afterCreate(CrmContract crmContract) {
        // 校验
        saveVerify(crmContract);

        crmContract.setUserId(SessionUtils.getUser().getId());
        crmContract.setDeptId(SessionUtils.getUser().getDepartment().getId());
        // 创建流程后设置状态
        if (crmContract.getProcStatus().getValue().equals(WfDataEntity.PROC_FLAG_COMPLETED)) {
            crmContract.setContractStatus(ContractStatus.APPROVE.getValue());
        } else {
            crmContract.setContractStatus(ContractStatus.IN_PROGRESS.getValue());
        }
        // 保存还款信息
        savePayList(crmContract);
        return new ActResult(ActResult.EnumCodes.Done, "回调成功");
    }

    @Override
    public ActResult afterAgree(CrmContract crmContract) {
        // 通过后流程后设置状态
        if (crmContract.getProcStatus().getValue().equals(WfDataEntity.PROC_FLAG_COMPLETED)) {
            crmContract.setContractStatus(ContractStatus.APPROVE.getValue());

            // 审批通过后，生成回款记录
            bulkInsertPaymentBack(crmContract);
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



    public void savePayList(CrmContract crmContract) {
        // 保存还款信息
        List<CrmContractPay> payList = crmContract.getPayList();
        if (CollectionUtils.isEmpty(payList)) {
            return;
        }
        if (!StringUtils.isNullOrEmpty(crmContract.getId())) {
            crmContractPayService.deleteByContractId(crmContract.getId());
        }
        payList.forEach(item -> {
            item.preInsert();
            item.setContractId(crmContract.getId());
        });
        crmContractPayDao.bulkInsert(payList);
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
        if(crmContract == null) {
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
        // 合同状态
        String contractStatus = oldContract.getContractStatus();
        if (!contractStatus.equals(ContractStatus.SAVE.getValue()) && !contractStatus.equals(ContractStatus.REJECT.getValue())) {
            throw new CommonJsonException(ResultUtil.warningJson(ErrorEnum.E_50001, "当前状态无法删除合同"));
        }
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
}
