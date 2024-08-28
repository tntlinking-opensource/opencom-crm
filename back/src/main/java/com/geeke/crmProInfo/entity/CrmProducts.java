package com.geeke.crmProInfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.geeke.crmProClassify.entity.CrmProductsClassify;
import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * 产品管理Entity
 * @author
 * @version
 */

public class CrmProducts extends DataEntity<CrmProducts> {

    private static final long serialVersionUID = 1709759135450792274L;

    private String pdNo; // 产品编号

    private String pdName; // 产品名称

    private CrmProductsClassify pdClassify; // 产品分类

    private BigDecimal pdGuidePrice; // 指导价

    private BigDecimal pdCostPrice; // 成本价

    private Integer pdTotal; // 库存数量

    private String pdTypeId; // 产品类型

    private String pdStatus; // 状态（0：启用；1：禁用）

    // 构造方法
    public CrmProducts() {
        super();
    }

    public CrmProducts(String id) {
        super(id);
    }

    // 生成get和set方法

    @NotNull(message = "产品编号不能为空")
    public String getPdNo() {
        return pdNo;
    }

    public void setPdNo(String pdNo) {
        this.pdNo = pdNo;
    }

    @Length(min = 1, max = 255, message = "产品名称长度必须介于 1 和 255 之间")
    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    @NotNull(message = "产品分类不能为空")
    public CrmProductsClassify getPdClassify() {
        return pdClassify;
    }

    public void setPdClassify(CrmProductsClassify pdClassify) {
        this.pdClassify = pdClassify;
    }

    @NotNull(message = "指导价不能为空")
    public BigDecimal getPdGuidePrice() {
        return pdGuidePrice;
    }

    public void setPdGuidePrice(BigDecimal pdGuidePrice) {
        this.pdGuidePrice = pdGuidePrice;
    }

    @NotNull(message = "成本价不能为空")
    public BigDecimal getPdCostPrice() {
        return pdCostPrice;
    }

    public void setPdCostPrice(BigDecimal pdCostPrice) {
        this.pdCostPrice = pdCostPrice;
    }

    @NotNull(message = "库存数量不能为空")
    public Integer getPdTotal() {
        return pdTotal;
    }

    public void setPdTotal(Integer pdTotal) {
        this.pdTotal = pdTotal;
    }

    @Length(min = 1, max = 255, message = "产品类型长度必须介于 1 和 255 之间")
    public String getPdTypeId() {
        return pdTypeId;
    }

    public void setPdTypeId(String pdTypeId) {
        this.pdTypeId = pdTypeId;
    }

    @NotNull(message = "状态（0：启用；1：禁用）不能为空")
    public String getPdStatus() {
        return pdStatus;
    }

    public void setPdStatus(String pdStatus) {
        this.pdStatus = pdStatus;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_products";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1709759135450792274";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "产品";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
