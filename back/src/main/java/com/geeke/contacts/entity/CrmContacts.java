package com.geeke.contacts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.google.common.collect.Lists;
import java.util.List;
import org.hibernate.validator.constraints.Length;

/**
 * 联系人Entity
 * @author
 * @version
 */

public class CrmContacts extends DataEntity<CrmContacts> {

    private static final long serialVersionUID = 1641034040897358325L;

    private String contactsName; // 联系人姓名

    private String customName; // 客户名称

    private String customNameid;

    public String getCustomNameid() {
        return customNameid;
    }

    public void setCustomNameid(String customNameid) {
        this.customNameid = customNameid;
    }

    public String getBsoyid() {
        return bsoyid;
    }

    public void setBsoyid(String bsoyid) {
        this.bsoyid = bsoyid;
    }

    private String bsoy; // 商机

    private String bsoyid;

    private String sex; // 性别

    private String borndate; // 出生日期

    private String phoneNumber; // 手机号

    private String email; // 邮箱

    private String contactPhone; // 电话

    private String qnumber; // QQ

    private String wenumber; // 微信

    private String wbnumber; // 微博

    private String region; // 所在区域

    private String address; // 详细地址

    private String depart; // 所属部门

    private String workPost; // 职位

    private String notes; // 备注

    // 构造方法
    public CrmContacts() {
        super();
    }

    public CrmContacts(String id) {
        super(id);
    }

    // 生成get和set方法

    @Length(min = 1, max = 120, message = "联系人姓名长度必须介于 1 和 120 之间")
    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    @Length(min = 0, max = 120, message = "客户名称长度必须介于 0 和 120 之间")
    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    @Length(min = 0, max = 80, message = "商机长度必须介于 0 和 80 之间")
    public String getBsoy() {
        return bsoy;
    }

    public void setBsoy(String bsoy) {
        this.bsoy = bsoy;
    }

    @Length(min = 0, max = 10, message = "性别长度必须介于 0 和 10 之间")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Length(min = 0, max = 10, message = "出生日期长度必须介于 0 和 10 之间")
    public String getBorndate() {
        return borndate;
    }

    public void setBorndate(String borndate) {
        this.borndate = borndate;
    }

    @Length(min = 1, max = 120, message = "手机号长度必须介于 1 和 120 之间")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Length(min = 0, max = 32, message = "邮箱长度必须介于 0 和 32 之间")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min = 0, max = 20, message = "电话长度必须介于 0 和 20 之间")
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Length(min = 0, max = 30, message = "QQ长度必须介于 0 和 30 之间")
    public String getQnumber() {
        return qnumber;
    }

    public void setQnumber(String qnumber) {
        this.qnumber = qnumber;
    }

    @Length(min = 0, max = 30, message = "微信长度必须介于 0 和 30 之间")
    public String getWenumber() {
        return wenumber;
    }

    public void setWenumber(String wenumber) {
        this.wenumber = wenumber;
    }

    @Length(min = 0, max = 30, message = "微博长度必须介于 0 和 30 之间")
    public String getWbnumber() {
        return wbnumber;
    }

    public void setWbnumber(String wbnumber) {
        this.wbnumber = wbnumber;
    }

    @Length(min = 0, max = 30, message = "所在区域长度必须介于 0 和 30 之间")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Length(min = 0, max = 50, message = "详细地址长度必须介于 0 和 50 之间")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Length(min = 0, max = 80, message = "所属部门长度必须介于 0 和 80 之间")
    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    @Length(min = 0, max = 60, message = "职位长度必须介于 0 和 60 之间")
    public String getWorkPost() {
        return workPost;
    }

    public void setWorkPost(String workPost) {
        this.workPost = workPost;
    }

    @Length(min = 0, max = 300, message = "备注长度必须介于 0 和 300 之间")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_contacts";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1641034040897358325";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "联系人";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
