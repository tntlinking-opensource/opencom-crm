package com.geeke.orgdepartment.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.orgdepartment.entity.OrgDepartment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 部门查询DAO接口
 * @author
 * @version
 */
@Mapper
public interface OrgDepartmentDao extends CrudDao<OrgDepartment> {}
