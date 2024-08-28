package com.geeke.crmCustomer.entity;

import java.math.BigDecimal;

/**
 * 客户管理Entity
 * @author
 * @version
 */

public class CrmCustomerProductsInsert  {
    private String id;
    private String pdNo;
    private String pdName;

    private String pdClassifyId;
    private String pdClassifyName;
    private BigDecimal pdGuidePrice;
    private BigDecimal pdCostPrice;
    private Integer pdTotal ;
    private String pdTypeId;
    private String remarks;
    private String cusId;
    private String productsId;

    private String updateDate;

    public String getUpdateDate() {
        return updateDate;
    }

    public void setOldUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public CrmCustomerProductsInsert() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getPdClassifyName() {
        return pdClassifyName;
    }

    public void setPdClassifyName(String pdClassifyName) {
        this.pdClassifyName = pdClassifyName;
    }

    public BigDecimal getPdGuidePrice() {
        return pdGuidePrice;
    }

    public void setPdGuidePrice(BigDecimal pdGuidePrice) {
        this.pdGuidePrice = pdGuidePrice;
    }

    public BigDecimal getPdCostPrice() {
        return pdCostPrice;
    }

    public void setPdCostPrice(BigDecimal pdCostPrice) {
        this.pdCostPrice = pdCostPrice;
    }

    public Integer getPdTotal() {
        return pdTotal;
    }

    public void setPdTotal(Integer pdTotal) {
        this.pdTotal = pdTotal;
    }

    public String getPdTypeId() {
        return pdTypeId;
    }

    public void setPdTypeId(String pdTypeId) {
        this.pdTypeId = pdTypeId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getProductsId() {
        return productsId;
    }

    public void setProductsId(String productsId) {
        this.productsId = productsId;
    }
}
