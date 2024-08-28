package com.geeke.dictitem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.google.common.collect.Lists;
import java.util.List;
import org.hibernate.validator.constraints.Length;

/**
 * 码表查询Entity
 * @author
 * @version
 */

public class SysDictItem extends DataEntity<SysDictItem> {

    private static final long serialVersionUID = 1683007003934705134L;

    private String dictTypeId; // 字典类

    private String name; // 名称

    private String value; // 字典项值

    // 构造方法
    public SysDictItem() {
        super();
    }

    public SysDictItem(String id) {
        super(id);
    }

    // 生成get和set方法

    @Length(min = 1, max = 21, message = "字典类长度必须介于 1 和 21 之间")
    public String getDictTypeId() {
        return dictTypeId;
    }

    public void setDictTypeId(String dictTypeId) {
        this.dictTypeId = dictTypeId;
    }

    @Length(min = 1, max = 128, message = "名称长度必须介于 1 和 128 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 1, max = 128, message = "字典项值长度必须介于 1 和 128 之间")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "sys_dict_item";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1683007003934705134";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "字典项";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return false;
    }
}
