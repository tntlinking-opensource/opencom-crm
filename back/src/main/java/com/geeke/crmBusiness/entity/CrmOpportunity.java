package com.geeke.crmBusiness.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.geeke.crmCustomer.entity.CrmCustomer;
import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

/**
 * 商机管理Entity
 * @author
 * @version
 */

public class CrmOpportunity extends DataEntity<CrmOpportunity> {

    private static final long serialVersionUID = 1709759135450792726L;

    private CrmCustomer customer; // 客户

    private String oppName; // 商机名称

    private String oppSource; // 商机来源

    private String oppStage; // 销售阶段

    private String oppContacts; // 联系人

    private BigDecimal oppPay; // 销售金额

    private String oppDepartment; // 所属部门
    private String oppDepartmentId; // 所属部门

    private String oppResponsible; // 负责人
    @Getter
    private String oppRespondepartId; // 负责人

    @Getter
    private String oppQuotation; // 发送报价单

    // 构造方法
    public CrmOpportunity() {
        super();
    }

    public CrmOpportunity(String id) {
        super(id);
    }

    // 生成get和set方法

    @NotNull(message = "客户不能为空")
    public CrmCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(CrmCustomer customer) {
        this.customer = customer;
    }

    @Length(min = 1, max = 300, message = "商机名称长度必须介于 1 和 300 之间")
    public String getOppName() {
        return oppName;
    }

    public void setOppName(String oppName) {
        this.oppName = oppName;
    }

    @Length(min = 1, max = 100, message = "商机来源长度必须介于 1 和 100 之间")
    public String getOppSource() {
        return oppSource;
    }

    public void setOppSource(String oppSource) {
        this.oppSource = oppSource;
    }

    @Length(min = 1, max = 100, message = "销售阶段长度必须介于 1 和 100 之间")
    public String getOppStage() {
        return oppStage;
    }

    public void setOppStage(String oppStage) {
        this.oppStage = oppStage;
    }

    @Length(min = 1, max = 50, message = "联系人长度必须介于 1 和 50 之间")
    public String getOppContacts() {
        return oppContacts;
    }

    public void setOppContacts(String oppContacts) {
        this.oppContacts = oppContacts;
    }

    @NotNull(message = "销售金额不能为空")
    public BigDecimal getOppPay() {
        return oppPay;
    }

    public void setOppPay(BigDecimal oppPay) {
        this.oppPay = oppPay;
    }

    @Length(min = 0, max = 100, message = "所属部门长度必须介于 0 和 100 之间")
    public String getOppDepartment() {
        return oppDepartment;
    }

    public void setOppDepartment(String oppDepartment) {
        this.oppDepartment = oppDepartment;
    }
    @Length(min = 0, max = 100, message = "所属部门长度必须介于 0 和 100 之间")
    public String getOppDepartmentId() {
        return oppDepartmentId;
    }

    public void setOppDepartmentId(String oppDepartmentId) {
        this.oppDepartmentId = oppDepartmentId;
    }
    @Length(min = 0, max = 64, message = "负责人长度必须介于 0 和 64 之间")
    public String getOppResponsible() {
        return oppResponsible;
    }

    public void setOppResponsible(String oppResponsible) {
        this.oppResponsible = oppResponsible;
    }

    public void setOppQuotation(String oppQuotation) {
        this.oppQuotation = oppQuotation;
    }

    public void setOppRespondepartId(String oppRespondepartId) {
        this.oppRespondepartId = oppRespondepartId;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_opportunity";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1709759135450792726";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "商机管理";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
