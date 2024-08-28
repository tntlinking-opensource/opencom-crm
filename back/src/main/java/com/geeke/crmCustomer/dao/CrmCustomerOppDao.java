package com.geeke.crmCustomer.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.crmCustomer.entity.CrmCustomerOpp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 客户管理DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmCustomerOppDao extends CrudDao<CrmCustomerOpp> {
    CrmCustomerOpp getCrmCustomerOppByOppId(String oppId);

    List<CrmCustomerOpp> getCrmCustomerOppsByCusId(String cusId);
}
