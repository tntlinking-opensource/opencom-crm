package com.geeke.crmCustomer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;

/**
 * 客户管理Entity
 * @author
 * @version
 */

public class CrmCustomerCot extends DataEntity<CrmCustomerCot> {

    private static final long serialVersionUID = 1713619331797369420L;

    private String cusId; // 客户Id

    private String cotId;

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCotId() {
        return cotId;
    }

    public void setCotId(String cotId) {
        this.cotId = cotId;
    }

    // 构造方法
    public CrmCustomerCot() {
        super();
    }

    public CrmCustomerCot(String id) {
        super(id);
    }

    // 生成get和set方法

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
