package com.geeke.businessopportunity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.google.common.collect.Lists;
import java.util.List;
import org.hibernate.validator.constraints.Length;

/**
 * 商机查询Entity
 * @author
 * @version
 */

public class CrmBusinessOpportunity extends DataEntity<CrmBusinessOpportunity> {

    private static final long serialVersionUID = 1683007003934705124L;

    private String businessName; // 商机名称

    private String customerName; // 客户名称

    // 构造方法
    public CrmBusinessOpportunity() {
        super();
    }

    public CrmBusinessOpportunity(String id) {
        super(id);
    }

    // 生成get和set方法

    @Length(min = 1, max = 300, message = "商机名称长度必须介于 1 和 300 之间")
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @Length(min = 1, max = 300, message = "客户名称长度必须介于 1 和 300 之间")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_business_opportunity";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1683007003934705124";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "商机";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return false;
    }
}
