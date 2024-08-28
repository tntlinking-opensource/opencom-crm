package com.geeke.myClue.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.myClue.entity.CrmClues;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 我的线索DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmCluesDao extends CrudDao<CrmClues> {
    int physicsDelete(@Param("entitys")List<CrmClues> entitys);
}
