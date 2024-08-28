package com.geeke.crmCustomer.entity;

/**
 * 客户管理Entity
 * @author
 * @version
 */

public class CrmCustomerPayInsert  {

    private String id;
    private String payId;
    private String cusId;
    private String updateDate;
    private String payUpdateDate;

    // 构造方法

    public CrmCustomerPayInsert() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

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
}
