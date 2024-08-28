package com.geeke.crmInvoice.enums;

/**
 * 发票种类
 */
public enum InvoiceKind {
    ZZSZP6("增值税专用发票6%", "1"),
    ZZSZP5("增值税专用发票5%", "2"),
    ZZSZP9("增值税专用发票9%", "3"),
    ZZSZP13("增值税专用发票13%", "4"),
    ZZSZP16("增值税专用发票16%或者17%", "5"),

    ZZSPP6("增值税普通发票6%", "6"),
    ZZSPP5("增值税普通发票5%", "7"),
    ZZSPP9("增值税普通发票9%", "8"),
    ZZSPP13("增值税普通发票13%", "9"),
    ZZSPP16("增值税普通发票16%或者17%", "10"),
    ;
    String status;

    String value;



    InvoiceKind(String status, String value) {
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
