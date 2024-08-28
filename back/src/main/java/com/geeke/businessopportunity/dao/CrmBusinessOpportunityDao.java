package com.geeke.businessopportunity.dao;

import com.geeke.businessopportunity.entity.CrmBusinessOpportunity;
import com.geeke.common.persistence.CrudDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商机查询DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmBusinessOpportunityDao extends CrudDao<CrmBusinessOpportunity> {}
