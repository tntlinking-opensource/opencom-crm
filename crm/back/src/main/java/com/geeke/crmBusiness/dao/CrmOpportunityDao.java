package com.geeke.crmBusiness.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.crmBusiness.entity.CrmOpportunity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商机管理DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmOpportunityDao extends CrudDao<CrmOpportunity> {}
