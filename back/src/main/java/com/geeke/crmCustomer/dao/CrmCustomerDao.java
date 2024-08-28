package com.geeke.crmCustomer.dao;

import com.geeke.common.persistence.TreeDao;
import com.geeke.crmCommunicate.entity.CrmCommunicate;
import com.geeke.crmCustomer.entity.CrmCustomer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 客户管理DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmCustomerDao extends TreeDao<CrmCustomer> {

    List<CrmCommunicate> queryCon(String cusId);
}
