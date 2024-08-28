package com.geeke.paymentBack.entity;

import com.geeke.common.persistence.DataEntity;
import com.geeke.contract.entity.CrmContractPay;

import java.util.List;

/**
 * 回款信息Entity
 * @author
 * @version
 */

public class PaymentBack extends DataEntity<PaymentBack> {

    private static final long serialVersionUID = 1713619331797368913L;

    private String contractId; // 合同id

    private String order; // 期数

    private String payDate; // 计划还款日期

    private String payRate; // 计划还款比例

    private String payAmount; // 计划还款金额

    private String paymentBackDate; // 实际回款日期

    private String paymentBackAmount; // 实际回款金额

    private String remark; // 备注

    // 非数据库字段
    private String paymentBackPlanNo; // 回款计划编号

    private String contractCode; // 关联合同编码

    private String customerName; // 客户名称

    private String opportunityName; // 商机名称

    private String opportunityId;//商机id

    private String contractName; // 合同名称

    private String contractAmount; // 合同金额

    private String beginDate; // 开始日期

    private String endDate; // 结束日期

    private String oddPaymentBackAmount; // 剩余回款金额

    private String newPaymentBackDate; //最新回款日期

    private String newPaymentBackAmount; // 最新回款金额

    private String paymentBackStatus; // 回款状态

    private String oddPayAmount; // 剩余还款金额

    private String totalPayAmount; // 总计还款金额

    private List<PaymentBack> paymentBackList; // 还款信息

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getPayRate() {
        return payRate;
    }

    public void setPayRate(String payRate) {
        this.payRate = payRate;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getPaymentBackDate() {
        return paymentBackDate;
    }

    public void setPaymentBackDate(String paymentBackDate) {
        this.paymentBackDate = paymentBackDate;
    }

    public String getPaymentBackAmount() {
        return paymentBackAmount;
    }

    public void setPaymentBackAmount(String paymentBackAmount) {
        this.paymentBackAmount = paymentBackAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPaymentBackPlanNo() {
        return paymentBackPlanNo;
    }

    public void setPaymentBackPlanNo(String paymentBackPlanNo) {
        this.paymentBackPlanNo = paymentBackPlanNo;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOpportunityName() {
        return opportunityName;
    }

    public void setOpportunityName(String opportunityName) {
        this.opportunityName = opportunityName;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(String contractAmount) {
        this.contractAmount = contractAmount;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOddPaymentBackAmount() {
        return oddPaymentBackAmount;
    }

    public void setOddPaymentBackAmount(String oddPaymentBackAmount) {
        this.oddPaymentBackAmount = oddPaymentBackAmount;
    }

    public String getNewPaymentBackDate() {
        return newPaymentBackDate;
    }

    public void setNewPaymentBackDate(String newPaymentBackDate) {
        this.newPaymentBackDate = newPaymentBackDate;
    }

    public String getNewPaymentBackAmount() {
        return newPaymentBackAmount;
    }

    public void setNewPaymentBackAmount(String newPaymentBackAmount) {
        this.newPaymentBackAmount = newPaymentBackAmount;
    }

    public String getPaymentBackStatus() {
        return paymentBackStatus;
    }

    public void setPaymentBackStatus(String paymentBackStatus) {
        this.paymentBackStatus = paymentBackStatus;
    }

    public String getOddPayAmount() {
        return oddPayAmount;
    }

    public void setOddPayAmount(String oddPayAmount) {
        this.oddPayAmount = oddPayAmount;
    }

    public List<PaymentBack> getPaymentBackList() {
        return paymentBackList;
    }

    public void setPaymentBackList(List<PaymentBack> paymentBackList) {
        this.paymentBackList = paymentBackList;
    }

    public String getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(String totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }
}
