
package com.geeke.crmInvoice.entity;


import com.geeke.contract.entity.CrmContract;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

import java.util.List;

import com.google.common.collect.Lists;


import com.fasterxml.jackson.annotation.JsonIgnore;



import com.geeke.wf.persistence.WfDataEntity;
/**
 * 发票管理Entity
 * @author
 * @version
 */
public class CrmInvoice extends WfDataEntity<CrmInvoice> {

	private static final long serialVersionUID = 1740772218121937029L;

	private CrmContract contract;      // 合同id

	private String contractName;     //合同名称

	private String cusName;		//客户名称

  	private String signComp ;		// 签约方公司

    private String contractUnit ;		// 合同单位

    private String parCustUnit ;		// 票面客户单位

  	private String itemName ;		// 项目名称

	private String itemCode ;		// 项目编号

	private String invoiceType ;		// 发票类型

	private String taxNum ;		// 税号

	private String invoiceAddressTel ;		// 地址、电话

	private String invoiceBankAccount ;		// 开户银行名称及账户

	private BigDecimal invoiceAmount ;		// 发票金额

	private String taxRate ;		// 税率

	private BigDecimal taxes ;		// 税金

	private String excludTax ;		// 不含税价

	private String status ;		// 申请状态

	private String invoicePrint ;		// 票面打印内容

	private String invoiceRemark ;		// 发票票面备注

	private String applicantDesc ;		// 申请人说明

	private String companyName ;		// 公司名称

	private String address ;		// 公司地址

	private String recipient ;		// 收件人

	private String phone ;		// 联系电话

	private Integer postalCode ;		// 邮编

	private String invoiceContent ;		// 发票内容

	private String invoiceKind; //发票种类

	private String proc_inst;		//流程实例id

	public String getProc_inst() {
		return proc_inst;
	}

	public void setProc_inst(String proc_inst) {
		this.proc_inst = proc_inst;
	}

	public String getProc_status() {
		return proc_status;
	}

	public void setProc_status(String proc_status) {
		this.proc_status = proc_status;
	}

	private String proc_status;     //流程状态


	private Integer del_flag;      //是否删除


	// 构造方法
	public CrmInvoice() {
		super();
	}

	public CrmInvoice(String id){
		super(id);
	}


	// 生成get和set方法

    public CrmContract getContract() {
        return contract;
    }

    public void setContract(CrmContract contract) {
        this.contract = contract;
    }

	@Length(min=0, max=30, message="签约方公司长度必须介于 0 和 30 之间")
	public String getSignComp() {
		return signComp;
	}

	public void setSignComp(String signComp) {
		this.signComp = signComp;
	}



	@Length(min=0, max=30, message="合同单位长度必须介于 0 和 30 之间")
	public String getContractUnit() {
		return contractUnit;
	}

	public void setContractUnit(String contractUnit) {
		this.contractUnit = contractUnit;
	}

	@Length(min=0, max=30, message="票面客户单位长度必须介于 0 和 30 之间")
	public String getParCustUnit() {
		return parCustUnit;
	}

	public void setParCustUnit(String parCustUnit) {
		this.parCustUnit = parCustUnit;
	}

	@Length(min=0, max=30, message="项目名称长度必须介于 0 和 30 之间")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Length(min=0, max=30, message="项目编号长度必须介于 0 和 30 之间")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	@Length(min=0, max=10, message="发票类型长度必须介于 0 和 10 之间")
	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	@Length(min=0, max=30, message="税号长度必须介于 0 和 30 之间")
	public String getTaxNum() {
		return taxNum;
	}

	public void setTaxNum(String taxNum) {
		this.taxNum = taxNum;
	}

	@Length(min=0, max=100, message="地址、电话长度必须介于 0 和 100 之间")
	public String getInvoiceAddressTel() {
		return invoiceAddressTel;
	}

	public void setInvoiceAddressTel(String invoiceAddressTel) {
		this.invoiceAddressTel = invoiceAddressTel;
	}

	@Length(min=0, max=100, message="开户银行名称 及账户 长度必须介于 0 和 100 之间")
	public String getInvoiceBankAccount() {
		return invoiceBankAccount;
	}

	public void setInvoiceBankAccount(String invoiceBankAccount) {
		this.invoiceBankAccount = invoiceBankAccount;
	}

	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	@Length(min=0, max=3, message="税率长度必须介于 0 和 3 之间")
	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getTaxes() {
		return taxes;
	}

	public void setTaxes(BigDecimal taxes) {
		this.taxes = taxes;
	}

	@Length(min=0, max=20, message="不含税价长度必须介于 0 和 20 之间")
	public String getExcludTax() {
		return excludTax;
	}

	public void setExcludTax(String excludTax) {
		this.excludTax = excludTax;
	}

	@Length(min=0, max=10, message="申请状态长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Length(min=0, max=200, message="票面打印内容长度必须介于 0 和 200 之间")
	public String getInvoicePrint() {
		return invoicePrint;
	}

	public void setInvoicePrint(String invoicePrint) {
		this.invoicePrint = invoicePrint;
	}

	@Length(min=0, max=200, message="发票票面备注长度必须介于 0 和 200 之间")
	public String getInvoiceRemark() {
		return invoiceRemark;
	}

	public void setInvoiceRemark(String invoiceRemark) {
		this.invoiceRemark = invoiceRemark;
	}

	@Length(min=0, max=200, message="申请人说明长度必须介于 0 和 200 之间")
	public String getApplicantDesc() {
		return applicantDesc;
	}

	public void setApplicantDesc(String applicantDesc) {
		this.applicantDesc = applicantDesc;
	}

	@Length(min=0, max=50, message="公司名称长度必须介于 0 和 50 之间")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Length(min=0, max=100, message="公司地址长度必须介于 0 和 100 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Length(min=0, max=50, message="收件人长度必须介于 0 和 50 之间")
	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	@Length(min=0, max=30, message="联系电话长度必须介于 0 和 30 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	@Length(min=0, max=200, message="发票内容长度必须介于 0 和 200 之间")
	public String getInvoiceContent() {
		return invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	public String getInvoiceKind() {
		return invoiceKind;
	}

	public void setInvoiceKind(String invoiceKind) {
		this.invoiceKind = invoiceKind;
	}

	public Integer getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(Integer del_flag) {
		this.del_flag = del_flag;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	/**
     * 获取实体对应表名
    */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "crm_invoice";
    }

    /**
     * 获取实体对应Id
    */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1740772218121937029";
    }

    /**
     * 获取实体对应描述
    */
    @Override
    @JsonIgnore
    public String getBusTableComments() {
        return "发票管理";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {

            return false;

    }
}
