package com.geeke.myClue.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 我的线索Entity
 * @author
 * @version
 */

public class MyCrmCluePool extends DataEntity<MyCrmCluePool> {

    private static final long serialVersionUID = 1709759135450792838L;

    private String cplName; // 线索池名称

    private String cplResponsible; // 线索池负责人

    private String cplRuleType; // 分配规则 手动、自动

    private String cplMemberClaimRules; // 成员领取规则

    private String cplMemberClaimLimit; // 分配领取上限

    private String cplAutoWhere1; // 自动分配规则1

    private String cplAutoWhere2; // 自动分配规则2

    private String cplAutoNum; // 自动分配数量

    private String cplRecycleDay; // 自动回收天

    private String cplRecycleStatus; // 自动回收状态

    // 构造方法
    public MyCrmCluePool() {
        super();
    }

    public MyCrmCluePool(String id) {
        super(id);
    }

    // 生成get和set方法

    @Length(min = 1, max = 300, message = "线索池名称长度必须介于 1 和 300 之间")
    public String getCplName() {
        return cplName;
    }

    public void setCplName(String cplName) {
        this.cplName = cplName;
    }

    @Length(min = 1, max = 300, message = "线索池负责人长度必须介于 1 和 300 之间")
    public String getCplResponsible() {
        return cplResponsible;
    }

    public void setCplResponsible(String cplResponsible) {
        this.cplResponsible = cplResponsible;
    }

    @Length(min = 1, max = 60, message = "分配规则 手动、自动长度必须介于 1 和 60 之间")
    public String getCplRuleType() {
        return cplRuleType;
    }

    public void setCplRuleType(String cplRuleType) {
        this.cplRuleType = cplRuleType;
    }

    @Length(min = 0, max = 2, message = "成员领取规则长度必须介于 0 和 2 之间")
    public String getCplMemberClaimRules() {
        return cplMemberClaimRules;
    }

    public void setCplMemberClaimRules(String cplMemberClaimRules) {
        this.cplMemberClaimRules = cplMemberClaimRules;
    }

    @Length(min = 0, max = 21, message = "分配领取上限长度必须介于 0 和 21 之间")
    public String getCplMemberClaimLimit() {
        return cplMemberClaimLimit;
    }

    public void setCplMemberClaimLimit(String cplMemberClaimLimit) {
        this.cplMemberClaimLimit = cplMemberClaimLimit;
    }

    @Length(min = 0, max = 300, message = "自动分配规则1长度必须介于 0 和 300 之间")
    public String getCplAutoWhere1() {
        return cplAutoWhere1;
    }

    public void setCplAutoWhere1(String cplAutoWhere1) {
        this.cplAutoWhere1 = cplAutoWhere1;
    }

    @Length(min = 0, max = 300, message = "自动分配规则2长度必须介于 0 和 300 之间")
    public String getCplAutoWhere2() {
        return cplAutoWhere2;
    }

    public void setCplAutoWhere2(String cplAutoWhere2) {
        this.cplAutoWhere2 = cplAutoWhere2;
    }

    @Length(min = 0, max = 300, message = "自动分配数量长度必须介于 0 和 300 之间")
    public String getCplAutoNum() {
        return cplAutoNum;
    }

    public void setCplAutoNum(String cplAutoNum) {
        this.cplAutoNum = cplAutoNum;
    }

    @Length(min = 1, max = 60, message = "自动回收天长度必须介于 1 和 60 之间")
    public String getCplRecycleDay() {
        return cplRecycleDay;
    }

    public void setCplRecycleDay(String cplRecycleDay) {
        this.cplRecycleDay = cplRecycleDay;
    }

    @Length(min = 1, max = 60, message = "自动回收状态长度必须介于 1 和 60 之间")
    public String getCplRecycleStatus() {
        return cplRecycleStatus;
    }

    public void setCplRecycleStatus(String cplRecycleStatus) {
        this.cplRecycleStatus = cplRecycleStatus;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_clue_pool";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1709759135450792838";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "线索池管理";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
