package com.geeke.customerManagement.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.customerManagement.entity.CrmCustomerManagement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 客户名称查询DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmCustomerManagementDao extends CrudDao<CrmCustomerManagement> {}
