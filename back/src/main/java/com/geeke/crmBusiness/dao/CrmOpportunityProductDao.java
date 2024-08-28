package com.geeke.crmBusiness.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.crmBusiness.entity.CrmOpportunityProduct;
import com.geeke.crmProInfo.entity.CrmProducts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商机明细DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmOpportunityProductDao extends CrudDao<CrmOpportunityProduct> {

   List<CrmProducts> queryProductByOpp (String oppId);
}
