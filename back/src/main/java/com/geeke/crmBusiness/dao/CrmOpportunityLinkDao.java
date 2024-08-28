package com.geeke.crmBusiness.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.contacts.entity.CrmContacts;
import com.geeke.crmBusiness.entity.CrmOpportunityLink;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商机明细-联系人DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmOpportunityLinkDao extends CrudDao<CrmOpportunityLink> {
    int saveCrmContacts(CrmContacts entity);

    void deleteLinkById(@Param("linkId") String linkId,@Param("delFlagDelete") String delFlagDelete,
                        @Param("updateBy") String updateBy, @Param("updateDate")String updateDate);

    List<CrmContacts> getByTacts(@Param("opporId") String opporId);
}
