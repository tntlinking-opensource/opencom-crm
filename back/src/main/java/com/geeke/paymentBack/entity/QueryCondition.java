package com.geeke.paymentBack.entity;

import com.geeke.common.controller.SearchParams;

/**
 * 查询条件 *
 */
public class QueryCondition {

    // 查询条件
    private SearchParams searchParams;

    // 回款状态
    private String backStatus;

    // 计划回款编号
    private String backPlanNo;

    public SearchParams getSearchParams() {
        return searchParams;
    }

    public void setSearchParams(SearchParams searchParams) {
        this.searchParams = searchParams;
    }

    public String getBackStatus() {
        return backStatus;
    }

    public void setBackStatus(String backStatus) {
        this.backStatus = backStatus;
    }

    public String getBackPlanNo() {
        return backPlanNo;
    }

    public void setBackPlanNo(String backPlanNo) {
        this.backPlanNo = backPlanNo;
    }
}
