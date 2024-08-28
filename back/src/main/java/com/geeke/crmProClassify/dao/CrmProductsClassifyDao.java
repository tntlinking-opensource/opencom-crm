package com.geeke.crmProClassify.dao;

import com.geeke.common.persistence.TreeDao;
import com.geeke.crmProClassify.entity.CrmProductsClassify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 产品分类DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmProductsClassifyDao extends TreeDao<CrmProductsClassify> {}
