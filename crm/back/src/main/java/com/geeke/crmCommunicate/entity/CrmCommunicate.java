package com.geeke.crmCommunicate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.google.common.collect.Lists;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * 沟通记录Entity
 * @author
 * @version
 */

public class CrmCommunicate extends DataEntity<CrmCommunicate> {

    private static final long serialVersionUID = 1709759135450792293L;

    private String cntTypeId; // 沟通类型

    private String cntPname; // 沟通人姓名

    private String cntDate; // 沟通时间

    private String cntContent; // 沟通内容

    // 构造方法
    public CrmCommunicate() {
        super();
    }

    public CrmCommunicate(String id) {
        super(id);
    }

    // 生成get和set方法

    @Length(min = 1, max = 100, message = "沟通类型长度必须介于 1 和 100 之间")
    public String getCntTypeId() {
        return cntTypeId;
    }

    public void setCntTypeId(String cntTypeId) {
        this.cntTypeId = cntTypeId;
    }

    @Length(min = 1, max = 100, message = "沟通人姓名长度必须介于 1 和 100 之间")
    public String getCntPname() {
        return cntPname;
    }

    public void setCntPname(String cntPname) {
        this.cntPname = cntPname;
    }

    @NotNull(message = "沟通时间不能为空")
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
        return "1709759135450792293";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "沟通记录";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
