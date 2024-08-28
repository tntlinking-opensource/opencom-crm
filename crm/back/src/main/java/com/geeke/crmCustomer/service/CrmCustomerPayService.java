package com.geeke.crmCustomer.service;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.common.data.PageRequest;
import com.geeke.common.data.Parameter;
import com.geeke.common.service.CrudService;
import com.geeke.contract.entity.CrmContract;
import com.geeke.contract.entity.CrmContractPay;
import com.geeke.contract.service.CrmContractPayService;
import com.geeke.contract.service.CrmContractService;
import com.geeke.crmCustomer.dao.CrmCustomerPayDao;
import com.geeke.crmCustomer.entity.CrmCustomerPay;
import com.geeke.crmCustomer.entity.CrmCustomerPayLine;
import com.geeke.paymentBack.dao.PaymentBackDao;
import com.geeke.paymentBack.entity.PaymentBack;
import com.geeke.paymentBack.enums.PayBackStatus;
import com.geeke.paymentBack.service.PaymentBackService;
import com.geeke.utils.RemoteResultResolver;
import com.geeke.utils.StringUtils;
import com.google.common.collect.Lists;
import com.newtouch.cloud.common.dependency.remote.rest.DynamicRestTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 客户管理Service
 * @author
 * @version
 */

@Service("crmCustomerPayService")
@Transactional(readOnly = true)
public class CrmCustomerPayService extends CrudService<CrmCustomerPayDao, CrmCustomerPay> {

    @Autowired
    private DynamicRestTemplate restTemplate;

    @Autowired
    private CrmCustomerPayDao crmCustomerPayDao;

    @Autowired
    private CrmContractService crmContractService;

    @Autowired
    private PaymentBackService paymentBackService;

    @Autowired
    private PaymentBackDao paymentBackDao;
    @Autowired
    private CrmContractPayService crmContractPayService;

    @Override
    public Page<CrmCustomerPay> listPage(List<Parameter> parameters, int offset, int limit, String orderby) {
        Page<CrmCustomerPay> result = super.listPage(parameters, offset, limit, orderby);

        //查看是否多次调用
        int flag = RemoteResultResolver.getRemoteFlag();
        //组装数据
        if (flag <= 1 && Objects.nonNull(result.getRows())) {
            //设置Header
            HttpHeaders headers = new HttpHeaders();
            headers.add(RemoteResultResolver.REMOTE_FLAG, String.valueOf(flag++));

            SearchParams crmCommunicateParam = RemoteResultResolver.param(result.getRows(), "payId", "id");
            JSONObject crmCommunicateList = restTemplate.post(
                "crm",
                "/crmCommunicate/crmCommunicate/listAll",
                JSONObject.class,
                crmCommunicateParam,
                headers
            );
            Integer crmCommunicateCode = Objects.requireNonNull(crmCommunicateList.getInteger("code"));
            if (crmCommunicateCode == 100) {
                List<Map<String, Object>> pay = crmCommunicateList.getObject("data", ArrayList.class);
                RemoteResultResolver.resultResolver(result.getRows(), pay, "pay", "payId");
            }
        }

        return result;
    }

    @Override
    public List<CrmCustomerPay> listAll(List<Parameter> parameters, String orderby) {
        PageRequest pageRequest = new PageRequest(parameters, orderby);

        List<CrmCustomerPay> result = dao.listAll(pageRequest);

        //查看是否多次调用
        int flag = RemoteResultResolver.getRemoteFlag();
        //组装数据
        if (flag <= 1 && Objects.nonNull(result)) {
            //设置Header
            HttpHeaders headers = new HttpHeaders();
            headers.add(RemoteResultResolver.REMOTE_FLAG, String.valueOf(flag++));

            SearchParams crmCommunicateParam = RemoteResultResolver.param(result, "payId", "id");
            JSONObject crmCommunicateList = restTemplate.post(
                "crm",
                "/crmCommunicate/crmCommunicate/listAll",
                JSONObject.class,
                crmCommunicateParam,
                headers
            );
            Integer crmCommunicateCode = Objects.requireNonNull(crmCommunicateList.getInteger("code"));
            if (crmCommunicateCode == 100) {
                List<Map<String, Object>> pay = crmCommunicateList.getObject("data", ArrayList.class);
                RemoteResultResolver.resultResolver(result, pay, "pay", "payId");
            }
        }

        return result;
    }



    public Page<CrmCustomerPayLine> crmCustomerPayLinelistPage(List<Parameter> parameters, int offset, int limit, String orderby) {
        Page<CrmCustomerPay> page = super.listPage(parameters, offset, limit, orderby);
        List<CrmCustomerPayLine> crmCustomerPayList = new ArrayList<>();
        if (page.getRows() != null) {
            for (CrmCustomerPay crmCustomerPay : page.getRows()) {
                String payId = crmCustomerPay.getPayId();
//                CrmPayortunity crmPayortunity = crmPayortunityService.get(payId);
                CrmCustomerPayLine crmCustomerPayLine = new CrmCustomerPayLine();
//                crmCustomerPayLine.setCustomer(crmPayortunity.getCustomer());
//                crmCustomerPayLine.setPayContacts(crmPayortunity.getPayContacts());
//                crmCustomerPayLine.setPayName(crmPayortunity.getPayName());
//                crmCustomerPayLine.setPayPay(crmPayortunity.getPayPay());
//                crmCustomerPayLine.setPayDepartment(crmPayortunity.getPayDepartment());
//                crmCustomerPayLine.setPayDepartmentId(crmPayortunity.getPayDepartmentId());
//                crmCustomerPayLine.setPayResponsible(crmPayortunity.getPayResponsible());
//                crmCustomerPayLine.setPayStage(crmPayortunity.getPayStage());
//                crmCustomerPayLine.setPaySource(crmPayortunity.getPaySource());
//                crmCustomerPayLine.setCusId(crmCustomerPay.getCusId());
//                crmCustomerPayLine.setPayId(payId);
//                crmCustomerPayLine.setId(crmCustomerPay.getId());
//                crmCustomerPayLine.setUpdateDate(crmPayortunity.getUpdateDate());
//                crmCustomerPayLine.setPayUpdateDate(crmPayortunity.getUpdateDate());
                crmCustomerPayList.add(crmCustomerPayLine);
            }
        }
        Page<CrmCustomerPayLine> crmCustomerPayListPage = new Page<>(page.getTotal(), crmCustomerPayList);

        return crmCustomerPayListPage;
    }

    public CrmCustomerPayLine getCustomerPayLineByCrmCustomerPayId(String crmCustomerPayId) {

        CrmCustomerPay crmCustomerPay = this.get(crmCustomerPayId);
        String payId = crmCustomerPay.getPayId();
//        CrmPayortunity crmPayortunity = crmPayortunityService.get(payId);
        CrmCustomerPayLine crmCustomerPayLine = new CrmCustomerPayLine();
//        crmCustomerPayLine.setCustomer(crmPayortunity.getCustomer());
//        crmCustomerPayLine.setPayContacts(crmPayortunity.getPayContacts());
//        crmCustomerPayLine.setPayName(crmPayortunity.getPayName());
//        crmCustomerPayLine.setPayPay(crmPayortunity.getPayPay());
//        crmCustomerPayLine.setPayDepartment(crmPayortunity.getPayDepartment());
//        crmCustomerPayLine.setPayDepartmentId(crmPayortunity.getPayDepartmentId());
//        crmCustomerPayLine.setPayResponsible(crmPayortunity.getPayResponsible());
//        crmCustomerPayLine.setPayStage(crmPayortunity.getPayStage());
//        crmCustomerPayLine.setPaySource(crmPayortunity.getPaySource());
//        crmCustomerPayLine.setCusId(crmCustomerPay.getCusId());
//        crmCustomerPayLine.setPayId(payId);
//        crmCustomerPayLine.setId(crmCustomerPay.getId());
//        crmCustomerPayLine.setUpdateDate(crmPayortunity.getUpdateDate());
//        crmCustomerPayLine.setPayUpdateDate(crmPayortunity.getUpdateDate());
        return crmCustomerPayLine;
    }


    public CrmCustomerPay getCrmCustomerPayByPayId(String payId) {
        return crmCustomerPayDao.getCrmCustomerPayByPayId(payId);
    }


    @Transactional
    public PaymentBack getPayInfo(String sjId){
        PaymentBack paymentBack = new PaymentBack();
//        CrmContract contract = crmOpportunityCotDao.getByCotId(sjId);
        CrmContract contract = crmContractService.get(sjId);
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



    @Transactional
    public String savePayBacks(List<PaymentBack> entitys) {
        List<PaymentBack> insertIds = Lists.newArrayList();
        List<PaymentBack> updateIds = Lists.newArrayList();

        CrmContract contract = crmContractService.get(entitys.get(0).getContractId());
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

}
