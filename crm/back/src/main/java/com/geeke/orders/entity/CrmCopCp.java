package com.geeke.orders.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 订单管理Entity
 * @author
 * @version
 */

public class CrmCopCp extends DataEntity<CrmCopCp> {

    private static final long serialVersionUID = 1683007003934706550L;

    private String pdNo;

    private String pdName;

    private String pdClassifyId;

    private String pdTypeId;

    private String pdGuidePrice;

    private String sigleNum;

    private String discount;

    private String sumAount;

    public String getPdNo() {
        return pdNo;
    }

    public void setPdNo(String pdNo) {
        this.pdNo = pdNo;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getPdClassifyId() {
        return pdClassifyId;
    }

    public void setPdClassifyId(String pdClassifyId) {
        this.pdClassifyId = pdClassifyId;
    }

    public String getPdTypeId() {
        return pdTypeId;
    }

    public void setPdTypeId(String pdTypeId) {
        this.pdTypeId = pdTypeId;
    }

    public String getPdGuidePrice() {
        return pdGuidePrice;
    }

    public void setPdGuidePrice(String pdGuidePrice) {
        this.pdGuidePrice = pdGuidePrice;
    }

    public String getSigleNum() {
        return sigleNum;
    }

    public void setSigleNum(String sigleNum) {
        this.sigleNum = sigleNum;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getSumAount() {
        return sumAount;
    }

    public void setSumAount(String sumAount) {
        this.sumAount = sumAount;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_orders";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1683007003934706550";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "订单管理";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
