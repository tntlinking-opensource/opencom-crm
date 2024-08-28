package com.geeke.contract.enums;

import com.geeke.utils.StringUtils;

public enum PayType {
    CASH("现金支付", "1"),
    BANK_CARD("银行卡转账", "2"),
    ALIPAY("支付宝转账", "3"),
    WECHAT("微信转账", "4"),
    OTHER("其他方式", "5"),

    ;

    public static String getTypeByValue(String value) {
        if (StringUtils.isNullOrEmpty(value)) {
            return "";
        }
        PayType[] values = PayType.values();
        for (PayType payType : values) {
            if (payType.value.equalsIgnoreCase(value)) {
                return payType.type;
            }
        }
        return "";
    }

    private String type;

    private String value;

    PayType(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
