package com.geeke.contract.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.google.common.collect.Lists;
import java.util.List;
import org.hibernate.validator.constraints.Length;

/**
 * 还款信息Entity
 * @author
 * @version
 */

public class CrmContractPay extends DataEntity<CrmContractPay> {

    private static final long serialVersionUID = 1713619331797368913L;

    private String contractId; // 合同id

    private String order; // 期数

    private String payDate; // 还款日期

    private String payRate; // 还款比例

    private String payAmount; // 还款金额

    private String remark; // 备注

    // 构造方法
    public CrmContractPay() {
        super();
    }

    public CrmContractPay(String id) {
        super(id);
    }

    // 生成get和set方法

    @Length(min = 1, max = 21, message = "合同id长度必须介于 1 和 21 之间")
    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    @Length(min = 0, max = 10, message = "期数长度必须介于 0 和 10 之间")
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Length(min = 0, max = 64, message = "还款日期长度必须介于 0 和 64 之间")
    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    @Length(min = 0, max = 10, message = "还款比例长度必须介于 0 和 10 之间")
    public String getPayRate() {
        return payRate;
    }

    public void setPayRate(String payRate) {
        this.payRate = payRate;
    }

    @Length(min = 0, max = 50, message = "还款金额长度必须介于 0 和 50 之间")
    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    @Length(min = 0, max = 200, message = "备注长度必须介于 0 和 200 之间")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_contract_pay";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1713619331797368913";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "合同还款信息";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
