package com.geeke.crmCustomer.entity;

/**
 * 客户管理Entity
 * @author
 * @version
 */

public class CrmCustomerLinkLine {

    private String CrmCustomerLinkId;
    private String cusId;
    private String linkId;
    private String contactsName;
    private String bsoy;
    private String depart;
    private String workPost;
    private String contactPhone;
    private String phoneNumber;
    private String email;
    private String region;
    private String address;
    private String notes;

    public String getCrmCustomerLinkId() {
        return CrmCustomerLinkId;
    }

    public void setCrmCustomerLinkId(String crmCustomerLinkId) {
        CrmCustomerLinkId = crmCustomerLinkId;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public CrmCustomerLinkLine() {
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getBsoy() {
        return bsoy;
    }

    public void setBsoy(String bsoy) {
        this.bsoy = bsoy;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getWorkPost() {
        return workPost;
    }

    public void setWorkPost(String workPost) {
        this.workPost = workPost;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
