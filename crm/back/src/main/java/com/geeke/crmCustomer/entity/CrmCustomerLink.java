package com.geeke.crmCustomer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.geeke.contacts.entity.CrmContacts;
import com.geeke.crmBusiness.entity.CrmOpportunity;

import javax.validation.constraints.NotNull;

/**
 * 客户管理Entity
 * @author
 * @version
 */

public class CrmCustomerLink extends DataEntity<CrmCustomerLink> {


    private static final long serialVersionUID = 1721468006573719620L;

//    private CrmOpportunity opp; // 商机Id
    private CrmCustomer cus;
    private CrmContacts link; // 联系人Id

    // 构造方法
    public CrmCustomerLink() {
        super();
    }

    public CrmCustomerLink(String id) {
        super(id);
    }

    // 生成get和set方法

    public CrmCustomer getCus() {
        return cus;
    }

    public void setCus(CrmCustomer cus) {
        this.cus = cus;
    }

    @NotNull(message = "商机Id不能为空")


    @NotNull(message = "联系人Id不能为空")
    public CrmContacts getLink() {
        return link;
    }

    public void setLink(CrmContacts link) {
        this.link = link;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_opportunity_link";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1721468006573719620";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "商机管理-联系人";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
