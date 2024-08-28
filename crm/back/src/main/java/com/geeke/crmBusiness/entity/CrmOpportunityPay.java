
package com.geeke.crmBusiness.entity;


import com.geeke.crmBusiness.entity.CrmOpportunity;

import javax.validation.constraints.NotNull;


import java.util.List;

import com.google.common.collect.Lists;


import com.fasterxml.jackson.annotation.JsonIgnore;



import com.geeke.common.persistence.DataEntity;


/**
 * 商机明细-回款Entity
 * @author 
 * @version 
 */
 
public class CrmOpportunityPay extends DataEntity<CrmOpportunityPay> {


	private static final long serialVersionUID = 1721468006573719601L;

	private CrmOpportunity opp;      // 商机Id

	private String payId;      // 回款Id
	
	// 构造方法
	public CrmOpportunityPay() {
		super();
	}

	public CrmOpportunityPay(String id){
		super(id);
	}

	// 生成get和set方法
			
	@NotNull(message="商机Id不能为空")
    public CrmOpportunity getOpp() {
        return opp;
    }

    public void setOpp(CrmOpportunity opp) {
        this.opp = opp;
    }

	@NotNull(message="回款Id不能为空")
    public String  getPayId() {
        return payId;
    }

    public void setPayId( String payId) {
        this.payId = payId;
    }

	/**
     * 获取实体对应表名
    */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_opportunity_pay";
    }
    
    /**
     * 获取实体对应Id
    */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1721468006573719601";
    }
    
    /**
     * 获取实体对应描述
    */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "商机管理-回款";
    }
    
    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        
            return true;
        
    }
}
