package com.geeke.myClue.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.google.common.collect.Lists;
import java.util.List;
import org.hibernate.validator.constraints.Length;

/**
 * 我的线索Entity
 * @author
 * @version
 */

public class MyClueCrmCommunicate extends DataEntity<MyClueCrmCommunicate> {

    private static final long serialVersionUID = 1720018472291172356L;

    private String cntType; // 沟通类型

    private String cntPname; // 沟通人姓名

    private String cntDate; // 沟通时间

    private String cntContent; // 沟通内容

    private String cutNextDate; // 下次沟通时间

    private String cutRemind; // 跟进提醒

    private String cutRemindType; // 跟进提醒时间类别

    private String cntMyclueId; // 我的线索id

    // 构造方法
    public MyClueCrmCommunicate() {
        super();
    }

    public MyClueCrmCommunicate(String id) {
        super(id);
    }

    // 生成get和set方法

    @Length(min = 1, max = 100, message = "沟通类型长度必须介于 1 和 100 之间")
    public String getCntType() {
        return cntType;
    }

    public void setCntType(String cntType) {
        this.cntType = cntType;
    }

    @Length(min = 1, max = 100, message = "沟通人姓名长度必须介于 1 和 100 之间")
    public String getCntPname() {
        return cntPname;
    }

    public void setCntPname(String cntPname) {
        this.cntPname = cntPname;
    }

    @Length(min = 1, max = 24, message = "沟通时间长度必须介于 1 和 24 之间")
    public String getCntDate() {
        return cntDate;
    }

    public void setCntDate(String cntDate) {
        this.cntDate = cntDate;
    }

    @Length(min = 0, max = 255, message = "沟通内容长度必须介于 0 和 255 之间")
    public String getCntContent() {
        return cntContent;
    }

    public void setCntContent(String cntContent) {
        this.cntContent = cntContent;
    }

    @Length(min = 0, max = 100, message = "下次沟通时间长度必须介于 0 和 100 之间")
    public String getCutNextDate() {
        return cutNextDate;
    }

    public void setCutNextDate(String cutNextDate) {
        this.cutNextDate = cutNextDate;
    }

    @Length(min = 0, max = 100, message = "跟进提醒长度必须介于 0 和 100 之间")
    public String getCutRemind() {
        return cutRemind;
    }

    public void setCutRemind(String cutRemind) {
        this.cutRemind = cutRemind;
    }

    @Length(min = 0, max = 100, message = "跟进提醒时间类别长度必须介于 0 和 100 之间")
    public String getCutRemindType() {
        return cutRemindType;
    }

    public void setCutRemindType(String cutRemindType) {
        this.cutRemindType = cutRemindType;
    }

    @Length(min = 0, max = 100, message = "我的线索id长度必须介于 0 和 100 之间")
    public String getCntMyclueId() {
        return cntMyclueId;
    }

    public void setCntMyclueId(String cntMyclueId) {
        this.cntMyclueId = cntMyclueId;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_communicate";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1720018472291172356";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "沟通";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
