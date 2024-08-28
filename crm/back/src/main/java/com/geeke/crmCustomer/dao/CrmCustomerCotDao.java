package com.geeke.crmCustomer.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.crmCustomer.entity.CrmCustomerCot;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 客户管理DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmCustomerCotDao extends CrudDao<CrmCustomerCot> {
    CrmCustomerCot getCrmCustomerCotByCotId(String cotId);

    List<CrmCustomerCot> getCrmCustomerCotsByCusId(String cusId);
}
