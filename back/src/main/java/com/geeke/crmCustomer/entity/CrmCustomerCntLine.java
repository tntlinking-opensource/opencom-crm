package com.geeke.crmCustomer.entity;

/**
 * 客户管理Entity
 *
 * @author
 */

public class CrmCustomerCntLine {

    private String id;
    private String cntId;
    private String cusId;
    private String cntType;
    private String cntPname;
    private String cntDate;
    private String cntContent;
    private String cutNextDate;
    private String cutRemindType;
    private String cutRemind;

    private String createBy;

    private String createDate;

    private String updateBy;

    private String updateDate;
    private String cntUpdateDate;

    public String getCntUpdateDate() {
        return cntUpdateDate;
    }

    public void setCntUpdateDate(String cntUpdateDate) {
        this.cntUpdateDate = cntUpdateDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCntId() {
        return cntId;
    }

    public void setCntId(String cntId) {
        this.cntId = cntId;
    }

    public CrmCustomerCntLine() {
    }

    public String getCutRemind() {
        return cutRemind;
    }

    public void setCutRemind(String cutRemind) {
        this.cutRemind = cutRemind;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCntType() {
        return cntType;
    }

    public void setCntType(String cntType) {
        this.cntType = cntType;
    }

    public String getCntPname() {
        return cntPname;
    }

    public void setCntPname(String cntPname) {
        this.cntPname = cntPname;
    }

    public String getCntDate() {
        return cntDate;
    }

    public void setCntDate(String cntDate) {
        this.cntDate = cntDate;
    }

    public String getCntContent() {
        return cntContent;
    }

    public void setCntContent(String cntContent) {
        this.cntContent = cntContent;
    }

    public String getCutNextDate() {
        return cutNextDate;
    }

    public void setCutNextDate(String cutNextDate) {
        this.cutNextDate = cutNextDate;
    }

    public String getCutRemindType() {
        return cutRemindType;
    }

    public void setCutRemindType(String cutRemindType) {
        this.cutRemindType = cutRemindType;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }
}
