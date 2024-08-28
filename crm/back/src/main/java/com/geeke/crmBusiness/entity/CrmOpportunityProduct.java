package com.geeke.crmBusiness.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.geeke.crmBusiness.entity.CrmOpportunity;
import com.geeke.crmProInfo.entity.CrmProducts;
import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

/**
 * 商机明细Entity
 * @author
 * @version
 */

public class CrmOpportunityProduct extends DataEntity<CrmOpportunityProduct> {

    private static final long serialVersionUID = 1720018472291172392L;

    private CrmOpportunity opp = new CrmOpportunity(); // 商机Id

    private CrmProducts product = new CrmProducts(); // 产品Id

    private String sigleNum; // 数量

    private BigDecimal discount; // 折扣

    @Getter
    private String pdcCode; // 产品分类

    @Getter
    private String pdcName; // 产品分类

    @Getter
    private String pdType; // 产品类型

    // 构造方法
    public CrmOpportunityProduct() {
        super();
    }

    public CrmOpportunityProduct(String id) {
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

    @Length(min = 1, max = 64, message = "产品Id长度必须介于 1 和 64 之间")
    public CrmProducts getProduct() {
        return product;
    }

    public void setProduct(CrmProducts product) {
        this.product = product;
    }

    @Length(min = 1, max = 21, message = "数量长度必须介于 1 和 21 之间")
    public String getSigleNum() {
        return sigleNum;
    }

    public void setSigleNum(String sigleNum) {
        this.sigleNum = sigleNum;
    }

    @NotNull(message = "折扣不能为空")
    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public void setPdcCode(String pdcCode) {
        this.pdcCode = pdcCode;
    }

    public void setPdcName(String pdcName) {
        this.pdcName = pdcName;
    }

    public void setPdType(String pdType) {
        this.pdType = pdType;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_opportunity_product";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1720018472291172392";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "商机管理-产品";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
