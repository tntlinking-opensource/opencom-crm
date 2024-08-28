package com.geeke.orders.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.google.common.collect.Lists;
import java.util.List;
import org.hibernate.validator.constraints.Length;

/**
 * 订单管理Entity
 * @author
 * @version
 */

public class CrmOrders extends DataEntity<CrmOrders> {

    private static final long serialVersionUID = 1683007003934706550L;

    private String oedersCode; // 订单编号

    private String customName; // 客户名称

    private String bsoyName; // 商机名称

    private String contractCode; // 合同编号

    private String contractName; // 合同名称

    private String ordersMacount; // 订单金额

    private String ordersProduct; // 订单产品

    private String ordersHead; // 订单负责人

    private String wbnumber; // 附件

    private String consignee; // 收货人

    private String consigneePhone; // 收货人电话

    private String consigneeAddr; // 收货人地址

    private String notes; // 备注

    // 构造方法
    public CrmOrders() {
        super();
    }

    public CrmOrders(String id) {
        super(id);
    }

    // 生成get和set方法

    @Length(min = 1, max = 120, message = "订单编号长度必须介于 1 和 120 之间")
    public String getOedersCode() {
        return oedersCode;
    }

    public void setOedersCode(String oedersCode) {
        this.oedersCode = oedersCode;
    }

    @Length(min = 0, max = 120, message = "客户名称长度必须介于 0 和 120 之间")
    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    @Length(min = 0, max = 80, message = "商机名称长度必须介于 0 和 80 之间")
    public String getBsoyName() {
        return bsoyName;
    }

    public void setBsoyName(String bsoyName) {
        this.bsoyName = bsoyName;
    }

    @Length(min = 0, max = 10, message = "合同编号长度必须介于 0 和 10 之间")
    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    @Length(min = 0, max = 10, message = "合同名称长度必须介于 0 和 10 之间")
    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    @Length(min = 0, max = 120, message = "订单金额长度必须介于 0 和 120 之间")
    public String getOrdersMacount() {
        return ordersMacount;
    }

    public void setOrdersMacount(String ordersMacount) {
        this.ordersMacount = ordersMacount;
    }

    @Length(min = 0, max = 32, message = "订单产品长度必须介于 0 和 32 之间")
    public String getOrdersProduct() {
        return ordersProduct;
    }

    public void setOrdersProduct(String ordersProduct) {
        this.ordersProduct = ordersProduct;
    }

    @Length(min = 0, max = 20, message = "订单负责人长度必须介于 0 和 20 之间")
    public String getOrdersHead() {
        return ordersHead;
    }

    public void setOrdersHead(String ordersHead) {
        this.ordersHead = ordersHead;
    }

    public String getWbnumber() {
        return wbnumber;
    }

    public void setWbnumber(String wbnumber) {
        this.wbnumber = wbnumber;
    }

    @Length(min = 0, max = 30, message = "收货人长度必须介于 0 和 30 之间")
    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    @Length(min = 0, max = 30, message = "收货人电话长度必须介于 0 和 30 之间")
    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    @Length(min = 0, max = 30, message = "收货人地址长度必须介于 0 和 30 之间")
    public String getConsigneeAddr() {
        return consigneeAddr;
    }

    public void setConsigneeAddr(String consigneeAddr) {
        this.consigneeAddr = consigneeAddr;
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
        return "crm_orders";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1683007003934706550";
    }

    /**
     * 获取实体对应描述
     */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "订单管理";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
