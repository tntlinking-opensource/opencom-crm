package com.geeke.crmCustomer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.TreeEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 客户管理Entity
 * @author
 * @version
 */

public class CrmCustomer extends TreeEntity<CrmCustomer> {

    private static final long serialVersionUID = 1709759135450792386L;

    private String cusName; // 客户名称

    private String cusType; // 客户类型

    private String cusSource; // 客户来源

    private String cusContacts; // 联系人

    private String cusMobile; // 联系方式

    private String cusRegion; // 所在区域

    private String cusAddress; // 详细地址

    private String cusResponsible; // 负责人

    private String cusStage; // 客户阶段

    // 构造方法
    public CrmCustomer() {
        super();
    }

    public CrmCustomer(String id) {
        super(id);
    }

    // 生成get和set方法

    @Length(min = 1, max = 300, message = "客户名称长度必须介于 1 和 300 之间")
    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    @Length(min = 1, max = 60, message = "客户类型长度必须介于 1 和 60 之间")
    public String getCusType() {
        return cusType;
    }

    public void setCusType(String cusType) {
        this.cusType = cusType;
    }

    @Length(min = 1, max = 60, message = "客户来源长度必须介于 1 和 60 之间")
    public String getCusSource() {
        return cusSource;
    }

    public void setCusSource(String cusSource) {
        this.cusSource = cusSource;
    }

    @Length(min = 0, max = 50, message = "联系人长度必须介于 0 和 50 之间")
    public String getCusContacts() {
        return cusContacts;
    }

    public void setCusContacts(String cusContacts) {
        this.cusContacts = cusContacts;
    }

    @Length(min = 0, max = 30, message = "联系方式长度必须介于 0 和 30 之间")
    public String getCusMobile() {
        return cusMobile;
    }

    public void setCusMobile(String cusMobile) {
        this.cusMobile = cusMobile;
    }

    @Length(min = 0, max = 100, message = "所在区域长度必须介于 0 和 100 之间")
    public String getCusRegion() {
        return cusRegion;
    }

    public void setCusRegion(String cusRegion) {
        this.cusRegion = cusRegion;
    }

    @Length(min = 0, max = 100, message = "详细地址长度必须介于 0 和 100 之间")
    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    @Length(min = 1, max = 64, message = "负责人长度必须介于 1 和 64 之间")
    public String getCusResponsible() {
        return cusResponsible;
    }

    public void setCusResponsible(String cusResponsible) {
        this.cusResponsible = cusResponsible;
    }

    @Length(min = 1, max = 255, message = "客户阶段长度必须介于 1 和 255 之间")
    public String getCusStage() {
        return cusStage;
    }

    public void setCusStage(String cusStage) {
        this.cusStage = cusStage;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_customer";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1709759135450792386";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "客户管理";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
