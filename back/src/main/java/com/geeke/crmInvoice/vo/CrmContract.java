package com.geeke.crmInvoice.vo;

import lombok.Data;

/**
 * 合同管理Entity
 * @author
 * @version
 */
@Data
public class CrmContract {
    private String id;  //合同id

    private String cusName; // 客户名称

    private String deptName; //部门名称

    private String contractCode; // 合同编码

    private String contractName; // 合同名称

    private String contractAmount; // 合同金额


}
