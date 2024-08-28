package com.geeke.contract.enums;

public enum ContractStatus {
    SAVE("已保存", "0"),
    IN_PROGRESS("审批中", "1"),
    APPROVE("审批通过", "2"),
    REJECT("审批不通过", "9")
    ;
    String status;

    String value;

    ContractStatus(String status, String value) {
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
