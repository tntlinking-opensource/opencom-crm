package com.geeke.crmCustomer.entity;

/**
 * 客户管理Entity
 * @author
 * @version
 */

public class CrmCustomerCotInsert{
    private String id;
    private String cotId;
    private String cusId;

    private String updateDate;
    private String cotUpdateDate;
    public CrmCustomerCotInsert() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCotId() {
        return cotId;
    }

    public void setCotId(String cotId) {
        this.cotId = cotId;
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

    public String getCotUpdateDate() {
        return cotUpdateDate;
    }

    public void setCotUpdateDate(String cotUpdateDate) {
        this.cotUpdateDate = cotUpdateDate;
    }
}