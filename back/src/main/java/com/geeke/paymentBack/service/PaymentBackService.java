package com.geeke.paymentBack.service;

import com.geeke.common.data.Page;
import com.geeke.common.data.Parameter;
import com.geeke.common.service.CrudService;
import com.geeke.contract.dao.CrmContractDao;
import com.geeke.contract.entity.CrmContract;
import com.geeke.contract.service.CrmContractService;
import com.geeke.paymentBack.dao.PaymentBackDao;
import com.geeke.paymentBack.entity.PaymentBack;
import com.geeke.paymentBack.entity.QueryCondition;
import com.geeke.paymentBack.enums.PayBackStatus;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 回款信息Service
 * @author
 * @version
 */

@Service("paymentBackService")
@Transactional(readOnly = true)
public class PaymentBackService extends CrudService<PaymentBackDao, PaymentBack> {

    @Autowired
    private CrmContractService crmContractService;

    public PaymentBack getByContractId(String contractId){
        PaymentBack paymentBack = new PaymentBack();
        CrmContract contract = crmContractService.get(contractId);
        crmContractService.entityToDto(contract, true);
        if (contract == null) {
            return null;
        }
        BeanUtils.copyProperties(contract, paymentBack);
        paymentBack = entityListToDtoList(Lists.newArrayList(contract)).get(0);
        // 组装回款信息
        Parameter contractIdParam = new Parameter("contract_id", "=", contract.getId());
        List<PaymentBack> paymentBackList = super.listAll(Lists.newArrayList(contractIdParam), "a.order");
        String totalPayAmount = Double.toString(paymentBackList.stream().filter(item -> item != null && item.getPaymentBackAmount() != null).mapToDouble(p -> Double.parseDouble(p.getPaymentBackAmount())).sum());
        paymentBack.setTotalPayAmount(totalPayAmount);
        paymentBack.setPaymentBackList(paymentBackList);
        return paymentBack;
    }

    public Page<PaymentBack> listPage(QueryCondition condition) {
        Parameter parameter = new Parameter("contract_status","=","2");
        condition.getSearchParams().getParams().add(parameter);
        Page<CrmContract> crmContractPage = crmContractService.listPage(condition.getSearchParams().getParams(), condition.getSearchParams().getOffset(), condition.getSearchParams().getLimit(), condition.getSearchParams().getOrderby());
        crmContractService.entityListToDtoList(crmContractPage.getRows(), false);
        List<PaymentBack> paymentBacks = entityListToDtoList(crmContractPage.getRows());

        String backStatus = condition.getBackStatus();
        String backPlanNo = condition.getBackPlanNo();
        if(backStatus != null && !Objects.equals("", backStatus)) {
            paymentBacks = paymentBacks.stream().filter(item -> backStatus.equals(item.getPaymentBackStatus())).collect(Collectors.toList());
        }
        if(backPlanNo != null && !Objects.equals("", backPlanNo)) {
            paymentBacks = paymentBacks.stream().filter(item -> (item.getPaymentBackPlanNo().contains(backPlanNo))).collect(Collectors.toList());
        }
        return new Page<>(crmContractPage.getTotal(), paymentBacks);
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
                PaymentBack paymentBackTemp = dao.getByContractId(contract.getId());
                String oddPaymentBackAmount = dao.getOddPaymentBackAmount(contract.getId());
                BeanUtils.copyProperties(contract, paymentBack);
                paymentBack.setContractId(contract.getId());
                paymentBack.setId(null);
                paymentBack.setOpportunityId(contract.getOpportunityId());
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
                if("0.0".equals(oddPaymentBackAmount)){
                    paymentBack.setPaymentBackStatus(PayBackStatus.COMPLETE.getValue());
                } else if(payDate.contains(".") && payDate.compareTo(year+"."+month+"."+day) > 0) {
                    paymentBack.setPaymentBackStatus(PayBackStatus.OVERDUE.getValue());
                } else {
                    paymentBack.setPaymentBackStatus(PayBackStatus.IN_PROCESS.getValue());
                }
                paymentBacks.add(paymentBack);
            }
        }
        return paymentBacks;
    }
}
