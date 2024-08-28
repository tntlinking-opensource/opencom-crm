package com.geeke.crmProClassify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.TreeEntity;
import com.google.common.collect.Lists;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * 产品分类Entity
 * @author
 * @version
 */

public class CrmProductsClassify extends TreeEntity<CrmProductsClassify> {

    private static final long serialVersionUID = 1709759135450792260L;

    private String pdcCode; // 节点编号

    private String pdcName; // 节点名称

    // 构造方法
    public CrmProductsClassify() {
        super();
    }

    public CrmProductsClassify(String id) {
        super(id);
    }

    // 生成get和set方法

    @NotNull(message = "节点编号不能为空")
    public String getPdcCode() {
        return pdcCode;
    }

    public void setPdcCode(String pdcCode) {
        this.pdcCode = pdcCode;
    }

    @Length(min = 1, max = 100, message = "节点名称长度必须介于 1 和 100 之间")
    public String getPdcName() {
        return pdcName;
    }

    public void setPdcName(String pdcName) {
        this.pdcName = pdcName;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_products_classify";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1709759135450792260";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "产品分类";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
