package com.geeke.crmInvoice.enums;

/**
 * 发票类型
 */
public enum InvoiceType {
    ZZSPT("增值税普通发票（不可抵扣税金）", "1"),
    ZZSZY("增值税专用发票（可抵扣税）", "2"),
    ;
    String status;

    String value;


    InvoiceType(String status, String value) {
        this.status = status;
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
