package com.geeke.myClue.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * 我的线索Entity
 * @author
 * @version
 */

public class CrmMyclueProducts extends DataEntity<CrmMyclueProducts> {

    private static final long serialVersionUID = 1720018472291172414L;

    private String pdNo; // 产品编号

    private String pdName; // 产品名称

    private String pdClassifyId; // 产品分类

    private BigDecimal pdGuidePrice; // 指导价

    private BigDecimal pdCostPrice; // 成本价

    private Integer pdTotal; // 库存数量

    private String pdTypeId; // 产品类型

    private String pdStatus = "0"; // 状态（0：启用；1：禁用）

    private String pdZname; // 产品名称真

    private String pdClassifyName; // 产品分类名称

    private String pdTypeName; // pd_type_name

    private String cntMyclueId; // 我的线索id

    // 构造方法
    public CrmMyclueProducts() {
        super();
    }

    public CrmMyclueProducts(String id) {
        super(id);
    }

    // 生成get和set方法

    @Length(min = 1, max = 255, message = "产品编号长度必须介于 1 和 255 之间")
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

    @Length(min = 1, max = 255, message = "产品分类长度必须介于 1 和 255 之间")
    public String getPdClassifyId() {
        return pdClassifyId;
    }

    public void setPdClassifyId(String pdClassifyId) {
        this.pdClassifyId = pdClassifyId;
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

    @Length(min = 1, max = 1, message = "状态（0：启用；1：禁用）长度必须介于 1 和 1 之间")
    public String getPdStatus() {
        return pdStatus;
    }

    public void setPdStatus(String pdStatus) {
        this.pdStatus = pdStatus;
    }

    @Length(min = 0, max = 100, message = "产品名称真长度必须介于 0 和 100 之间")
    public String getPdZname() {
        return pdZname;
    }

    public void setPdZname(String pdZname) {
        this.pdZname = pdZname;
    }

    @Length(min = 0, max = 100, message = "产品分类名称长度必须介于 0 和 100 之间")
    public String getPdClassifyName() {
        return pdClassifyName;
    }

    public void setPdClassifyName(String pdClassifyName) {
        this.pdClassifyName = pdClassifyName;
    }

    @Length(min = 0, max = 100, message = "pd_type_name长度必须介于 0 和 100 之间")
    public String getPdTypeName() {
        return pdTypeName;
    }

    public void setPdTypeName(String pdTypeName) {
        this.pdTypeName = pdTypeName;
    }

    @Length(min = 0, max = 100, message = "我的线索id长度必须介于 0 和 100 之间")
    public String getCntMyclueId() {
        return cntMyclueId;
    }

    public void setCntMyclueId(String cntMyclueId) {
        this.cntMyclueId = cntMyclueId;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_myclue_products";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1720018472291172414";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "我的线索意向产品";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
