package com.geeke.crmCustomer.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.crmCustomer.entity.CrmCustomerCnt;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户管理DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmCustomerCntDao extends CrudDao<CrmCustomerCnt> {
    CrmCustomerCnt getCustomerCntByCntId(String cntId);
}
