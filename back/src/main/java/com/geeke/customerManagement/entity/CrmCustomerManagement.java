package com.geeke.customerManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.google.common.collect.Lists;
import java.util.List;
import org.hibernate.validator.constraints.Length;

/**
 * 客户名称查询Entity
 * @author
 * @version
 */

public class CrmCustomerManagement extends DataEntity<CrmCustomerManagement> {

    private static final long serialVersionUID = 1683007003934704611L;

    private String customerName; // 客户名称

    private String customerRpb; // 客户负责人

    // 构造方法
    public CrmCustomerManagement() {
        super();
    }

    public CrmCustomerManagement(String id) {
        super(id);
    }

    // 生成get和set方法

    @Length(min = 1, max = 300, message = "客户名称长度必须介于 1 和 300 之间")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Length(min = 1, max = 20, message = "客户负责人长度必须介于 1 和 20 之间")
    public String getCustomerRpb() {
        return customerRpb;
    }

    public void setCustomerRpb(String customerRpb) {
        this.customerRpb = customerRpb;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_customer_management";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1683007003934704611";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "客户名称查询";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return false;
    }
}
