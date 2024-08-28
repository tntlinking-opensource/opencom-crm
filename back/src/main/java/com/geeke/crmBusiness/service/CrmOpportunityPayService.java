package com.geeke.crmBusiness.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geeke.common.data.Parameter;
import com.geeke.common.persistence.DataEntity;
import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.contract.entity.CrmContract;
import com.geeke.contract.entity.CrmContractPay;
import com.geeke.contract.service.CrmContractPayService;
import com.geeke.contract.service.CrmContractService;
import com.geeke.crmBusiness.dao.CrmOpportunityCotDao;
import com.geeke.crmBusiness.dao.CrmOpportunityPayDao;
import com.geeke.crmBusiness.entity.CrmOpportunity;
import com.geeke.crmBusiness.entity.CrmOpportunityPay;
import com.geeke.notice.entity.NoticeSend;
import com.geeke.notice.service.NoticeSendService;
import com.geeke.paymentBack.dao.PaymentBackDao;
import com.geeke.paymentBack.entity.PaymentBack;
import com.geeke.paymentBack.enums.PayBackStatus;
import com.geeke.paymentBack.service.PaymentBackService;
import com.geeke.sys.utils.SessionUtils;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商机明细-回款Service
 * @author
 * @version
 */

@Service("crmOpportunityPayService")
@Transactional(readOnly = true)
public class CrmOpportunityPayService extends CrudService<CrmOpportunityPayDao, CrmOpportunityPay> {

    @Autowired
    private CrmOpportunityPayDao crmOpportunityPayDao;
    @Autowired
    private CrmOpportunityCotDao crmOpportunityCotDao;
    @Autowired
    private CrmContractPayService crmContractPayService;
    @Autowired
    private CrmContractService crmContractService;
    @Autowired
    private PaymentBackService paymentBackService;
    @Autowired
    private PaymentBackDao paymentBackDao;


    @Transactional
    public int deletePay(CrmOpportunityPay entity) {
        int rows;
        String updateBy = null;
        JSONObject userObj = SessionUtils.getUserJson();
        if (userObj != null && StringUtils.isNotBlank(userObj.getString("id"))) {
            updateBy = userObj.getString("name") + "(" + userObj.getString("loginName") + ")";
        }
        String updateDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String oppId = entity.getOpp().getId();

        CrmContract contract = crmOpportunityCotDao.getByCotId(oppId);

        // 删除商机明细-回款
        crmOpportunityPayDao.deleteByUnityPay(oppId,DataEntity.DEL_FLAG_DELETE, updateBy, updateDate);
        // 删除商机明细合同
        crmOpportunityCotDao.deleteByUnityCot(oppId,DataEntity.DEL_FLAG_DELETE, updateBy, updateDate);

        // 删除合同 \合同回款
        contract.setDelFlag(DataEntity.DEL_FLAG_DELETE);
        rows = crmContractService.deleteContract(contract);
        return rows;
    }

    @Transactional
    public PaymentBack getPayInfo(String sjId){
        PaymentBack paymentBack = new PaymentBack();
        CrmContract contract = crmOpportunityCotDao.getByCotId(sjId);
        crmContractService.entityToDto(contract, true);
        if (contract == null) {
            return null;
        }
        BeanUtils.copyProperties(contract, paymentBack);
        paymentBack = this.entityListToDtoList(Lists.newArrayList(contract)).get(0);
        // 组装回款信息
        Parameter contractIdParam = new Parameter("contract_id", "=", contract.getId());
        List<PaymentBack> paymentBackList = paymentBackService.listAll(Lists.newArrayList(contractIdParam), "a.order");
        // 分期列表
        List<CrmContractPay> crmContractPays = crmContractPayService.listAll(Lists.newArrayList(contractIdParam), "a.order");

        for (int i = 0; i < crmContractPays.size(); i++) {
            PaymentBack paymentBack1 = new PaymentBack();
            if (paymentBackList.size() < i+1) {
                paymentBack1.setOrder(crmContractPays.get(i).getOrder());
                paymentBack1.setPayDate(crmContractPays.get(i).getPayDate());
                paymentBack1.setPayAmount(crmContractPays.get(i).getPayAmount());
                paymentBack1.setPayRate(crmContractPays.get(i).getPayRate());
                paymentBackList.add(paymentBack1);
            }else{
                if (!Objects.equals(paymentBackList.get(i).getOrder(), crmContractPays.get(i).getOrder())){
                    paymentBack1.setOrder(crmContractPays.get(i).getOrder());
                    paymentBack1.setPayDate(crmContractPays.get(i).getPayDate());
                    paymentBack1.setPayAmount(crmContractPays.get(i).getPayAmount());
                    paymentBack1.setPayRate(crmContractPays.get(i).getPayRate());
                    paymentBackList.add(paymentBack1);
                }
            }
        }

        String totalPayAmount = Double.toString(paymentBackList.stream().filter(item -> item != null && item.getPaymentBackAmount() != null).mapToDouble(p -> Double.parseDouble(p.getPaymentBackAmount())).sum());
        String contractAmount = paymentBack.getContractAmount();
        double doubleValue = new BigDecimal(contractAmount).subtract(new BigDecimal(totalPayAmount).setScale(2, RoundingMode.HALF_UP)).doubleValue();
        paymentBack.setTotalPayAmount(totalPayAmount);
        paymentBack.setPaymentBackList(paymentBackList);
        paymentBack.setOddPayAmount(doubleValue+"");
        return paymentBack;
    }

    @Transactional
    public String savePayBacks(List<PaymentBack> entitys) {
        List<PaymentBack> insertIds = Lists.newArrayList();
        List<PaymentBack> updateIds = Lists.newArrayList();

        CrmContract contract = crmOpportunityCotDao.getByCotId(entitys.get(0).getOpportunityName());
        String contractId = contract.getId();

        int insertSize = 0;
        int updateSize = 0;
        for (int i = 0; i < entitys.size(); i++) {
            entitys.get(i).setContractId(contractId);
            if (StringUtils.isNotBlank(entitys.get(i).getId())) {
                updateIds.add(entitys.get(i));
            }else{
                insertIds.add(entitys.get(i));
            }
        }
        try{
            if (!insertIds.isEmpty()) {
                insertSize = paymentBackService.bulkInsert(insertIds).size();
            }
            if (!updateIds.isEmpty()){
                updateSize = paymentBackService.bulkUpdate(updateIds).size();
            }
        }catch (Exception e) {
            return e.getMessage();
        }
        return "回款信息保存"+insertSize+",修改"+updateSize;

    }
    /**
     * 组装回款列表信息*
     * @param contractList 组装信息
     * @return 组装结果
     */
    public List<PaymentBack> entityListToDtoList(List<CrmContract> contractList) {
        List<PaymentBack> paymentBacks = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(contractList)) {
            for(CrmContract contract : contractList){
                PaymentBack paymentBack = new PaymentBack();
                PaymentBack paymentBackTemp = paymentBackDao.getByContractId(contract.getId());
                String oddPaymentBackAmount = paymentBackDao.getOddPaymentBackAmount(contract.getId());
                oddPaymentBackAmount = new BigDecimal(oddPaymentBackAmount).setScale(2,RoundingMode.HALF_UP).toString();
                BeanUtils.copyProperties(contract, paymentBack);
                paymentBack.setContractId(contract.getId());
                paymentBack.setId(null);
                paymentBack.setPaymentBackPlanNo(contract.getSignDate() + contract.getContractCode());
                paymentBack.setNewPaymentBackDate(paymentBackTemp != null ? paymentBackTemp.getPaymentBackDate() : null);
                paymentBack.setNewPaymentBackAmount(paymentBackTemp != null ? paymentBackTemp.getPaymentBackAmount() : null);
                paymentBack.setOddPaymentBackAmount(oddPaymentBackAmount);
                // 组装还款状态
                Calendar calendar = Calendar.getInstance();
                Integer year = calendar.get (Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1;
                int day = calendar.get (Calendar.DATE);
                String payDate = paymentBackTemp != null ? paymentBackTemp.getPayDate() : "";
                if("0.00".equals(oddPaymentBackAmount)){
                    paymentBack.setPaymentBackStatus(PayBackStatus.COMPLETE.getValue());
                } else if(payDate.contains("-") && payDate.compareTo(year+"-"+month+"-"+day) > 0) {
                    paymentBack.setPaymentBackStatus(PayBackStatus.OVERDUE.getValue());
                } else {
                    paymentBack.setPaymentBackStatus(PayBackStatus.IN_PROCESS.getValue());
                }
                paymentBacks.add(paymentBack);
            }
        }
        return paymentBacks;
    }

    public void deleteByOpportId(String opportunityId) {
        String updateBy = null;
        JSONObject userObj = SessionUtils.getUserJson();
        if (userObj != null && StringUtils.isNotBlank(userObj.getString("id"))) {
            updateBy = userObj.getString("name") + "(" + userObj.getString("loginName") + ")";
        }
        String updateDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        crmOpportunityPayDao.deleteByUnityPay(opportunityId, DataEntity.DEL_FLAG_DELETE, updateBy, updateDate);

    }
}
