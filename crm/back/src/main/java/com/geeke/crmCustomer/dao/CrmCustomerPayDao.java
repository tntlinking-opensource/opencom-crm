package com.geeke.crmCustomer.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.crmCustomer.entity.CrmCustomerPay;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户管理DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmCustomerPayDao extends CrudDao<CrmCustomerPay> {
    CrmCustomerPay getCrmCustomerPayByPayId(String payId);
}
