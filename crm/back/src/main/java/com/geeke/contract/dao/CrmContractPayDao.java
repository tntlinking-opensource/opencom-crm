package com.geeke.contract.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.contract.entity.CrmContractPay;
import com.geeke.crmBusiness.entity.CrmOpportunityPay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 还款信息DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmContractPayDao extends CrudDao<CrmContractPay> {

    int deleteByContractId(@Param("contractId") String contractId, @Param("deleteFlag") String deleteFlag, @Param("updateBy")String updateBy, @Param("updateDate")String updateDate);
}
