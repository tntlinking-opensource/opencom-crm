package com.geeke.crmCustomer.entity;

import java.math.BigDecimal;

/**
 * 客户管理Entity
 * @author
 * @version
 */

public class CrmCustomerOppLine {
    private String crmCustomerOppId;

    private String cusId;

    private String oppId;
    private String oppName;

    private BigDecimal oppPay;

    private String oppSource;

    //产品数量
    private String productsNum;

    //是否已发送报价单
    private String isSendPriceSheet;

    private String remarks;

    private CrmCustomer customer; // 客户
    private String oppStage; // 销售阶段
    private String oppContacts; // 联系人
    private String oppDepartment; // 所属部门
    private String oppDepartmentId; // 所属部门
    private String oppResponsible; // 负责人
    private String id;
    private String productCount;

    private String updateDate;
    private String oppUpdateDate;

    public CrmCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(CrmCustomer customer) {
        this.customer = customer;
    }

    public String getOppStage() {
        return oppStage;
    }

    public void setOppStage(String oppStage) {
        this.oppStage = oppStage;
    }

    public String getOppContacts() {
        return oppContacts;
    }

    public void setOppContacts(String oppContacts) {
        this.oppContacts = oppContacts;
    }

    public String getOppDepartment() {
        return oppDepartment;
    }

    public void setOppDepartment(String oppDepartment) {
        this.oppDepartment = oppDepartment;
    }

    public String getOppDepartmentId() {
        return oppDepartmentId;
    }

    public void setOppDepartmentId(String oppDepartmentId) {
        this.oppDepartmentId = oppDepartmentId;
    }

    public String getOppResponsible() {
        return oppResponsible;
    }

    public void setOppResponsible(String oppResponsible) {
        this.oppResponsible = oppResponsible;
    }

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

    public String getOppUpdateDate() {
        return oppUpdateDate;
    }

    public void setOppUpdateDate(String oppUpdateDate) {
        this.oppUpdateDate = oppUpdateDate;
    }

    public String getCrmCustomerOppId() {
        return crmCustomerOppId;
    }

    public void setCrmCustomerOppId(String crmCustomerOppId) {
        this.crmCustomerOppId = crmCustomerOppId;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getOppId() {
        return oppId;
    }

    public void setOppId(String oppId) {
        this.oppId = oppId;
    }

    public CrmCustomerOppLine() {
    }

    public String getOppName() {
        return oppName;
    }

    public void setOppName(String oppName) {
        this.oppName = oppName;
    }

    public BigDecimal getOppPay() {
        return oppPay;
    }

    public void setOppPay(BigDecimal oppPay) {
        this.oppPay = oppPay;
    }

    public String getOppSource() {
        return oppSource;
    }

    public void setOppSource(String oppSource) {
        this.oppSource = oppSource;
    }

    public String getProductsNum() {
        return productsNum;
    }

    public void setProductsNum(String productsNum) {
        this.productsNum = productsNum;
    }

    public String getIsSendPriceSheet() {
        return isSendPriceSheet;
    }

    public void setIsSendPriceSheet(String isSendPriceSheet) {
        this.isSendPriceSheet = isSendPriceSheet;
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }
}
