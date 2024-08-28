package com.geeke.crmBusiness.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.geeke.crmBusiness.entity.CrmOpportunity;
import com.google.common.collect.Lists;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * 商机管理-合同Entity
 * @author
 * @version
 */

public class CrmOpportunityCot extends DataEntity<CrmOpportunityCot> {

    private static final long serialVersionUID = 1721468006573719571L;

    private CrmOpportunity opp; // 商机Id

    private String cotId; // 合同Id

    // 构造方法
    public CrmOpportunityCot() {
        super();
    }

    public CrmOpportunityCot(String id) {
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

    @Length(min = 1, max = 64, message = "合同Id长度必须介于 1 和 64 之间")
    public String getCotId() {
        return cotId;
    }

    public void setCotId(String cotId) {
        this.cotId = cotId;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_opportunity_cot";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1721468006573719571";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "商机管理-合同";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
