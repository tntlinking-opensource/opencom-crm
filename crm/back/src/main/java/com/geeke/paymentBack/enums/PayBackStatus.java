package com.geeke.paymentBack.enums;

public enum PayBackStatus {

    IN_PROCESS("1", "还款中"),
    OVERDUE("2", "已逾期"),
    COMPLETE("3", "已完成");

    private String type;

    private String value;

    PayBackStatus(String type, String value) {
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
