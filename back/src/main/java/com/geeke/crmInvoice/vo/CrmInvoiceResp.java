package com.geeke.crmInvoice.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.contract.entity.CrmContract;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public class CrmInvoiceResp {


    private CrmContract contract;      // 合同id

    private String cusName;     //客户名称

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

    private String proc_inst;		//流程实例id

    private String proc_status;     //流程状态

    private String creator ;		// 创建人

    private String createTime ;		// 创建时间

    private String updator ;		// 修改人

    private String updateTime ;		// 修改时间

    private Integer del_flag;      //是否删除
}
