package com.geeke.crmCustomer.entity;

import java.math.BigDecimal;

/**
 * 客户管理Entity
 *
 * @author
 */

public class CrmCustomerProductsLine {
    private String id;
    private String cusId;
    private String productsId;
    private String pdNo;

    private String pdName;

    private String pdClassifyId;

    private BigDecimal pdGuidePrice;

    private String pdTypeId;

    private String remarks;
    private String updateDate;

    public String getUpdateDate() {
        return updateDate;
    }

    public void setOldUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public CrmCustomerProductsLine() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public BigDecimal getPdGuidePrice() {
        return pdGuidePrice;
    }

    public void setPdGuidePrice(BigDecimal pdGuidePrice) {
        this.pdGuidePrice = pdGuidePrice;
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
}
