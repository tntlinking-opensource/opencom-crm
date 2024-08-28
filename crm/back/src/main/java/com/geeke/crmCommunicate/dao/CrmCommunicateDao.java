package com.geeke.crmCommunicate.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.crmCommunicate.entity.CrmCommunicate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 沟通记录DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmCommunicateDao extends CrudDao<CrmCommunicate> {}
