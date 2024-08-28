package com.geeke.contacts.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.contacts.entity.CrmContacts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 联系人DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmContactsDao extends CrudDao<CrmContacts> {}
