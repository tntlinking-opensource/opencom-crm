package com.geeke.crmBusiness.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.contract.entity.CrmContract;
import com.geeke.crmBusiness.entity.CrmOpportunityCot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商机管理-合同DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmOpportunityCotDao extends CrudDao<CrmOpportunityCot> {
    void deleteByUnityCot(@Param("oppId") String oppId, @Param("delFlagDelete") String delFlagDelete,
                          @Param("updateBy") String updateBy, @Param("updateDate") String updateDate);

    CrmContract getByCotId(@Param("oppId") String oppId);

}
