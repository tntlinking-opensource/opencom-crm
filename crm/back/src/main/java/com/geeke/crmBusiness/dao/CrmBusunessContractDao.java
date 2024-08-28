package com.geeke.crmBusiness.dao;

import com.geeke.contract.entity.CrmContract;
import com.geeke.wf.persistence.WfCrudDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 合同管理DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmBusunessContractDao extends WfCrudDao<CrmContract> {

    /**
     * 根据回款编号（合同签署日期+合同编号）查询合同信息*
     * @param paymentBackPlanNo 回款编号
     * @return 合同信息
     */
    CrmContract getByPaymentBackPlanNo(@Param("paymentBackPlanNo") String paymentBackPlanNo);

    int deleteContractByOppId(@Param("oppId") String opportunityId);
}
