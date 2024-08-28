package com.geeke.contract.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.sys.entity.DictItem;
import com.geeke.wf.persistence.WfDataEntity;
import com.google.common.collect.Lists;
import java.util.List;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

/**
 * 合同管理Entity
 * @author
 * @version
 */
public class CrmContract extends WfDataEntity<CrmContract> {

    private static final long serialVersionUID = 1713619331797368881L;

    private String deptId; // 部门id

    private String userId; // 创建用户id

    private String customerId; // 客户id

    private String opportunityId; // 商机id

    private String contractCode; // 合同编码

    private String contractName; // 合同名称

    private String contractAmount; // 合同金额

    private String signDate; // 签约日期

    private String beginDate; // 开始日期

    private String endDate; // 结束日期

    private String partyA; // 甲方

    private String partyB; // 乙方

    private String payType; // 支付方式

    private String remark; // 备注

    private String attachment; // 附件

    private String contractStatus; // 合同状态

    // 非数据库字段
    private String deptName;

    private String customerName;

    private String opportunityName;

    private String payTypeName;

    private List<CrmContractPay> payList;

    // 非数据库字段   判定商机管理合同
    @Getter
    private String opporStatus;

    // 构造方法
    public CrmContract() {
        super();
    }

    public CrmContract(String id) {
        super(id);
    }

    // 生成get和set方法

    @Length(min = 1, max = 21, message = "部门id长度必须介于 1 和 21 之间")
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Length(min = 1, max = 21, message = "创建用户id长度必须介于 1 和 21 之间")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Length(min = 1, max = 21, message = "客户id长度必须介于 1 和 21 之间")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Length(min = 1, max = 21, message = "商机id长度必须介于 1 和 21 之间")
    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }

    @Length(min = 0, max = 255, message = "合同编码长度必须介于 0 和 255 之间")
    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    @Length(min = 0, max = 255, message = "合同名称长度必须介于 0 和 255 之间")
    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    @Length(min = 0, max = 24, message = "签约日期长度必须介于 0 和 24 之间")
    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    @Length(min = 0, max = 24, message = "开始日期长度必须介于 0 和 24 之间")
    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    @Length(min = 0, max = 24, message = "结束日期长度必须介于 0 和 24 之间")
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Length(min = 0, max = 50, message = "甲方长度必须介于 0 和 50 之间")
    public String getPartyA() {
        return partyA;
    }

    public void setPartyA(String partyA) {
        this.partyA = partyA;
    }

    @Length(min = 0, max = 50, message = "乙方长度必须介于 0 和 50 之间")
    public String getPartyB() {
        return partyB;
    }

    public void setPartyB(String partyB) {
        this.partyB = partyB;
    }

    @Length(min = 0, max = 50, message = "支付方式长度必须介于 0 和 50 之间")
    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Length(min = 0, max = 200, message = "备注长度必须介于 0 和 200 之间")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Length(min = 0, max = 64, message = "附件长度必须介于 0 和 64 之间")
    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Length(min = 0, max = 2, message = "合同状态长度必须介于 0 和 2 之间")
    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(String contractAmount) {
        this.contractAmount = contractAmount;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_contract";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1713619331797368881";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "合同";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOpportunityName() {
        return opportunityName;
    }

    public void setOpportunityName(String opportunityName) {
        this.opportunityName = opportunityName;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    public List<CrmContractPay> getPayList() {
        return payList;
    }

    public void setPayList(List<CrmContractPay> payList) {
        this.payList = payList;
    }

    public void setOpporStatus(String opporStatus) {
        this.opporStatus = opporStatus;
    }

}
