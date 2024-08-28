package com.geeke.crmInvoice.enums;

public enum InvoiceStatus {

    CG("草稿", "0"),
    DSH("待审核", "1"),
    YKJ("已开具", "2"),
    SHBTG("审核不通过", "9"),
    ;
    String status;

    String value;

    InvoiceStatus(String status, String value) {
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
