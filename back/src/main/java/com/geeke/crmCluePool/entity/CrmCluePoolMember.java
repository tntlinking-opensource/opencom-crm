package com.geeke.crmCluePool.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.geeke.crmCluePool.entity.CrmCluePool;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * 线索池管理Entity
 * @author
 * @version
 */

public class CrmCluePoolMember extends DataEntity<CrmCluePoolMember> {

    private static final long serialVersionUID = 1709759135450792435L;

    private CrmCluePool cpl; // 商机Id

    private String memberId; // 联系人Id

    // 构造方法
    public CrmCluePoolMember() {
        super();
    }

    public CrmCluePoolMember(String id) {
        super(id);
    }

    // 生成get和set方法

    @NotNull(message = "商机Id不能为空")
    public CrmCluePool getCpl() {
        return cpl;
    }

    public void setCpl(CrmCluePool cpl) {
        this.cpl = cpl;
    }

    @Length(min = 1, max = 64, message = "联系人Id长度必须介于 1 和 64 之间")
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_clue_pool_member";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1709759135450792435";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "线索池管理-成员";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
