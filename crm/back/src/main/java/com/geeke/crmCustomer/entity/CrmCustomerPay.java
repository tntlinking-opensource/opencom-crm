package com.geeke.crmCustomer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;

/**
 * 客户管理Entity
 * @author
 * @version
 */

public class CrmCustomerPay extends DataEntity<CrmCustomerPay> {

    private static final long serialVersionUID = 1713619331797369420L;

    private String cusId; // 客户Id

    private String payId;

    // 构造方法
    public CrmCustomerPay() {
        super();
    }

    public CrmCustomerPay(String id) {
        super(id);
    }

    // 生成get和set方法

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

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_customer_pay";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1713619331797369420";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "客户管理-沟通记录";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
