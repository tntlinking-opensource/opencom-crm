package com.geeke.myClue.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.geeke.myClue.entity.MyCrmCluePool;
import com.google.common.collect.Lists;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * 我的线索Entity
 * @author
 * @version
 */

public class CrmClues extends DataEntity<CrmClues> {

    private static final long serialVersionUID = 1709759135450792837L;

    private MyCrmCluePool cluesPool; // 所属线索池

    private String cluesResponsible; // 线索负责人

    private String cluesState; // 线索状态 公海 个人 回收

    private String cluesName; // 线索名称

    private String cluesSources; // 线索来源

    private String cluesContacts; // 线索联系人

    private String phoneNumber; // 手机号

    private String email; // 邮箱

    private String company; // 公司

    private String department; // 所属部门

    private String position; // 职位

    private String region; // 所在区域

    private String gender; // 性别（0：男；1：女；9：未知）

    private String birthDate; // 出生日期

    private String wechat; // 微信

    private String microblog; // 微博

    private String qq; // QQ

    private String addr; // 详细地址

    private String telephone; // 电话

    private List<MyCrmCluePool> myCrmCluePoolList = Lists.newArrayList(); // 子表列表

    // 构造方法
    public CrmClues() {
        super();
    }

    public CrmClues(String id) {
        super(id);
    }

    // 生成get和set方法

    @NotNull(message = "所属线索池不能为空")
    public MyCrmCluePool getCluesPool() {
        return cluesPool;
    }

    public void setCluesPool(MyCrmCluePool cluesPool) {
        this.cluesPool = cluesPool;
    }

    @Length(min = 1, max = 50, message = "线索负责人长度必须介于 1 和 50 之间")
    public String getCluesResponsible() {
        return cluesResponsible;
    }

    public void setCluesResponsible(String cluesResponsible) {
        this.cluesResponsible = cluesResponsible;
    }

    @Length(min = 1, max = 50, message = "线索状态 公海 个人 回收长度必须介于 1 和 50 之间")
    public String getCluesState() {
        return cluesState;
    }

    public void setCluesState(String cluesState) {
        this.cluesState = cluesState;
    }

    @Length(min = 1, max = 255, message = "线索名称长度必须介于 1 和 255 之间")
    public String getCluesName() {
        return cluesName;
    }

    public void setCluesName(String cluesName) {
        this.cluesName = cluesName;
    }

    @Length(min = 1, max = 60, message = "线索来源长度必须介于 1 和 60 之间")
    public String getCluesSources() {
        return cluesSources;
    }

    public void setCluesSources(String cluesSources) {
        this.cluesSources = cluesSources;
    }

    @Length(min = 1, max = 50, message = "线索联系人长度必须介于 1 和 50 之间")
    public String getCluesContacts() {
        return cluesContacts;
    }

    public void setCluesContacts(String cluesContacts) {
        this.cluesContacts = cluesContacts;
    }

    @Length(min = 1, max = 15, message = "手机号长度必须介于 1 和 15 之间")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Length(min = 0, max = 60, message = "邮箱长度必须介于 0 和 60 之间")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min = 0, max = 100, message = "公司长度必须介于 0 和 100 之间")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Length(min = 0, max = 100, message = "所属部门长度必须介于 0 和 100 之间")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Length(min = 0, max = 60, message = "职位长度必须介于 0 和 60 之间")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Length(min = 0, max = 100, message = "所在区域长度必须介于 0 和 100 之间")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Length(min = 0, max = 60, message = "性别（0：男；1：女；9：未知）长度必须介于 0 和 60 之间")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Length(min = 0, max = 60, message = "出生日期长度必须介于 0 和 60 之间")
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Length(min = 0, max = 60, message = "微信长度必须介于 0 和 60 之间")
    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    @Length(min = 0, max = 60, message = "微博长度必须介于 0 和 60 之间")
    public String getMicroblog() {
        return microblog;
    }

    public void setMicroblog(String microblog) {
        this.microblog = microblog;
    }

    @Length(min = 0, max = 60, message = "QQ长度必须介于 0 和 60 之间")
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Length(min = 0, max = 200, message = "详细地址长度必须介于 0 和 200 之间")
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Length(min = 0, max = 30, message = "电话长度必须介于 0 和 30 之间")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<MyCrmCluePool> getMyCrmCluePoolList() {
        return myCrmCluePoolList;
    }

    public void setMyCrmCluePoolList(List<MyCrmCluePool> myCrmCluePoolList) {
        this.myCrmCluePoolList = myCrmCluePoolList;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_clues";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1709759135450792837";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "线索";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
