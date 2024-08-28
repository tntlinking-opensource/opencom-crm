package com.geeke.crmCustomer.entity;

import java.math.BigDecimal;

/**
 * 客户管理Entity
 * @author
 * @version
 */

public class CrmCustomerOppInsert  {

    private CrmCustomer customer; // 客户
    private String oppName; // 商机名称
    private String oppSource; // 商机来源
    private String oppStage; // 销售阶段
    private String oppContacts; // 联系人
    private BigDecimal oppPay; // 销售金额
    private String oppDepartment; // 所属部门
    private String oppDepartmentId; // 所属部门
    private String oppResponsible; // 负责人

    private String id;
    private String oppId;
    private String cusId;
    private String updateDate;
    private String oppUpdateDate;


    public CrmCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(CrmCustomer customer) {
        this.customer = customer;
    }

    public String getOppName() {
        return oppName;
    }

    public void setOppName(String oppName) {
        this.oppName = oppName;
    }

    public String getOppSource() {
        return oppSource;
    }

    public void setOppSource(String oppSource) {
        this.oppSource = oppSource;
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

    public BigDecimal getOppPay() {
        return oppPay;
    }

    public void setOppPay(BigDecimal oppPay) {
        this.oppPay = oppPay;
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

    public CrmCustomerOppInsert() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOppId() {
        return oppId;
    }

    public void setOppId(String oppId) {
        this.oppId = oppId;
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

    public String getOppUpdateDate() {
        return oppUpdateDate;
    }

    public void setOppUpdateDate(String oppUpdateDate) {
        this.oppUpdateDate = oppUpdateDate;
    }
}
