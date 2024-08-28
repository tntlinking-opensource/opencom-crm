package com.geeke.crmCustomer.entity;

/**
 * 客户管理Entity
 * @author
 * @version
 */

public class CrmCustomerCotLine  {
    private String id;
    private String cotId;
    private String cusId;

    private String updateDate;
    private String cotUpdateDate;

   private String contractCode;

    private String contractName;

    //商机名称
    private String oppName;

    //合同金额
    private String contractMoney;

    private String sginDate;

    private String contractStatus;

    private String  createBy;

    private String createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCotUpdateDate() {
        return cotUpdateDate;
    }

    public void setCotUpdateDate(String cotUpdateDate) {
        this.cotUpdateDate = cotUpdateDate;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCotId() {
        return cotId;
    }

    public void setCotId(String cotId) {
        this.cotId = cotId;
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

    public String getOppName() {
        return oppName;
    }

    public void setOppName(String oppName) {
        this.oppName = oppName;
    }

    public String getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(String contractMoney) {
        this.contractMoney = contractMoney;
    }

    public String getSginDate() {
        return sginDate;
    }

    public void setSginDate(String sginDate) {
        this.sginDate = sginDate;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
