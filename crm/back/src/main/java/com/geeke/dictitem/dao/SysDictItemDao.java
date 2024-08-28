package com.geeke.dictitem.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.dictitem.entity.SysDictItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 码表查询DAO接口
 * @author
 * @version
 */
@Mapper
public interface SysDictItemDao extends CrudDao<SysDictItem> {}
