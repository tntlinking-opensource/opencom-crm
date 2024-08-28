package com.geeke.crmProInfo.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.crmProInfo.entity.CrmProducts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 产品管理DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmProductsDao extends CrudDao<CrmProducts> {}
