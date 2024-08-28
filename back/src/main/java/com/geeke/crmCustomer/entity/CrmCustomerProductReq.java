package com.geeke.crmCustomer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.geeke.crmProInfo.entity.CrmProducts;

/**
 * 客户管理Entity
 * @author
 * @version
 */

public class CrmCustomerProductReq extends DataEntity<CrmCustomerProductReq> {

    private static final long serialVersionUID = 1713619331797369420L;

    private String cusId; // 客户Id

    private CrmProducts crmProducts;

    // 构造方法
    public CrmCustomerProductReq() {
        super();
    }

    public CrmCustomerProductReq(String id) {
        super(id);
    }

    // 生成get和set方法

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
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

    public CrmProducts getCrmProducts() {
        return crmProducts;
    }

    public void setCrmProducts(CrmProducts crmProducts) {
        this.crmProducts = crmProducts;
    }
}
