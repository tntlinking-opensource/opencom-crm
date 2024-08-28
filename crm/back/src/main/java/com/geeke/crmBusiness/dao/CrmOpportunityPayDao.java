package com.geeke.crmBusiness.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.crmBusiness.entity.CrmOpportunityPay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商机明细-回款DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmOpportunityPayDao extends CrudDao<CrmOpportunityPay> {
    void deleteByUnityPay(@Param("oppId") String oppId, @Param("delFlagDelete") String delFlagDelete,
                          @Param("updateBy") String updateBy, @Param("updateDate") String updateDate);
}
