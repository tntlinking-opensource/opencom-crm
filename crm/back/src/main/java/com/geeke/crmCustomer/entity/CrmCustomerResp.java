package com.geeke.crmCustomer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.TreeEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 客户管理Entity
 * @author
 * @version
 */

public class CrmCustomerResp extends TreeEntity<CrmCustomerResp> {

    private CrmCustomer crmCustomer;

    private String cntPname;

    private String cntDate;

    public CrmCustomer getCrmCustomer() {
        return crmCustomer;
    }

    public void setCrmCustomer(CrmCustomer crmCustomer) {
        this.crmCustomer = crmCustomer;
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

    // 构造方法
    public CrmCustomerResp() {
        super();
    }

    public CrmCustomerResp(String id) {
        super(id);
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
