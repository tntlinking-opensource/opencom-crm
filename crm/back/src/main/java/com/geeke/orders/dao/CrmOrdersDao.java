package com.geeke.orders.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.crmCluePool.entity.CrmUserls;
import com.geeke.orders.entity.CrmCopCp;
import com.geeke.orders.entity.CrmOrders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单管理DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmOrdersDao extends CrudDao<CrmOrders> {

    public int getseleddcpcount(String bsoyName,Integer pageNum, Integer pageSize);

    public List<CrmCopCp> getseleddcp(String bsoyName, Integer pageNum, Integer pageSize);
}
