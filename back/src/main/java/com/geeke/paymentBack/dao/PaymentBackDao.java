package com.geeke.paymentBack.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.paymentBack.entity.PaymentBack;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 回款信息DAO接口
 * @author
 * @version
 */
@Mapper
public interface PaymentBackDao extends CrudDao<PaymentBack> {

    PaymentBack getByContractId(@Param("contractId") String contractId);

    String getOddPaymentBackAmount(@Param("contractId") String contractId);

}
