package com.geeke.myClue.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.myClue.entity.MyCrmCluePool;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 我的线索DAO接口
 * @author
 * @version
 */
@Mapper
public interface MyCrmCluePoolDao extends CrudDao<MyCrmCluePool> {}
