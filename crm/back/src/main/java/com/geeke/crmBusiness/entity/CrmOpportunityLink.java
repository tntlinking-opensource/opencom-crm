package com.geeke.crmBusiness.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.geeke.contacts.entity.CrmContacts;
import com.geeke.crmBusiness.entity.CrmOpportunity;
import com.google.common.collect.Lists;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * 商机明细-联系人Entity
 * @author
 * @version
 */

public class CrmOpportunityLink extends DataEntity<CrmOpportunityLink> {

    private static final long serialVersionUID = 1721468006573719620L;

    private CrmOpportunity opp; // 商机Id

    private CrmContacts link; // 联系人Id

    // 构造方法
    public CrmOpportunityLink() {
        super();
    }

    public CrmOpportunityLink(String id) {
        super(id);
    }

    // 生成get和set方法

    @NotNull(message = "商机Id不能为空")
    public CrmOpportunity getOpp() {
        return opp;
    }

    public void setOpp(CrmOpportunity opp) {
        this.opp = opp;
    }

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
