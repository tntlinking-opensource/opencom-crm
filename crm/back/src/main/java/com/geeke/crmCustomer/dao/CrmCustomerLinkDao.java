package com.geeke.crmCustomer.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.crmCustomer.entity.CrmCustomerLink;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户管理DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmCustomerLinkDao extends CrudDao<CrmCustomerLink> {}
