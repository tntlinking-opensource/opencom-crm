package com.geeke.crmCluePool.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.crmCluePool.entity.CrmCluePoolMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 线索池管理DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmCluePoolMemberDao extends CrudDao<CrmCluePoolMember> {}
