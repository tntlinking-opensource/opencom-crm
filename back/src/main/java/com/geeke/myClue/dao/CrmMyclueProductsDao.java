package com.geeke.myClue.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.myClue.entity.CrmMyclueProducts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 我的线索DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmMyclueProductsDao extends CrudDao<CrmMyclueProducts> {}
