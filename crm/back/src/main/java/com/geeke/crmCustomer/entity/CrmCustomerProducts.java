package com.geeke.crmCustomer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;

/**
 * 客户管理Entity
 * @author
 * @version
 */

public class CrmCustomerProducts extends DataEntity<CrmCustomerProducts> {

    private static final long serialVersionUID = 1713619331797369420L;

    private String cusId; // 客户Id

    private String productId;

    // 构造方法
    public CrmCustomerProducts() {
        super();
    }

    public CrmCustomerProducts(String id) {
        super(id);
    }

    // 生成get和set方法

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_customer_product";
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
