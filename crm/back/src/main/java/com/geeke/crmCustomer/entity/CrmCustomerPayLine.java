package com.geeke.crmCustomer.entity;

/**
 * 客户管理Entity
 *
 * @author
 */

public class CrmCustomerPayLine {

    private String crmCustomerPayId;

    private String cusId;

    private String payId;

    //回款计划编号
    private String payNum;

    //关联合同编号
    private String contractCode;

    //合同名称
    private String contractName;

    //合同金额
    private String contractpPay;

    //结束日期
    private String endDate;

    //剩余回款金额
    private String remainingPay;

    //最新回款日期
    private String latestDate ;

    //最新回款日期
    private String latestPay ;

    //回款状态
    private String payStatus ;


    private String id;
//    private String payId;

    private String updateDate;
    private String payUpdateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getPayId() {
//        return payId;
//    }
//
//    public void setPayId(String payId) {
//        this.payId = payId;
//    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getPayUpdateDate() {
        return payUpdateDate;
    }

    public void setPayUpdateDate(String payUpdateDate) {
        this.payUpdateDate = payUpdateDate;
    }

    public CrmCustomerPayLine() {
    }

    public String getCrmCustomerPayId() {
        return crmCustomerPayId;
    }

    public void setCrmCustomerPayId(String crmCustomerPayId) {
        this.crmCustomerPayId = crmCustomerPayId;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getPayNum() {
        return payNum;
    }

    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractpPay() {
        return contractpPay;
    }

    public void setContractpPay(String contractpPay) {
        this.contractpPay = contractpPay;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRemainingPay() {
        return remainingPay;
    }

    public void setRemainingPay(String remainingPay) {
        this.remainingPay = remainingPay;
    }

    public String getLatestDate() {
        return latestDate;
    }

    public void setLatestDate(String latestDate) {
        this.latestDate = latestDate;
    }

    public String getLatestPay() {
        return latestPay;
    }

    public void setLatestPay(String latestPay) {
        this.latestPay = latestPay;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }
}
