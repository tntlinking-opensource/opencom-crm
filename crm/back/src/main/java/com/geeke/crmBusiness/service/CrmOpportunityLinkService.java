package com.geeke.crmBusiness.service;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.IUser;
import com.geeke.common.constants.ActionConstants;
import com.geeke.common.data.PageRequest;
import com.geeke.common.data.Parameter;
import com.geeke.common.persistence.BaseEntity;
import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.contacts.dao.CrmContactsDao;
import com.geeke.contacts.entity.CrmContacts;
import com.geeke.contacts.service.CrmContactsService;
import com.geeke.crmBusiness.dao.CrmOpportunityLinkDao;
import com.geeke.crmBusiness.entity.CrmOpportunity;
import com.geeke.crmBusiness.entity.CrmOpportunityLink;
import com.geeke.sys.utils.SessionUtils;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商机明细-联系人Service
 * @author
 * @version
 */

@Service("crmOpportunityLinkService")
@Transactional(readOnly = true)
public class CrmOpportunityLinkService extends CrudService<CrmOpportunityLinkDao, CrmOpportunityLink> {
    @Autowired
    private CrmContactsDao crmContactsDao;
    @Autowired
    private CrmOpportunityLinkDao linkDao;
    @Transactional
    public CrmOpportunityLink saveCrmContacts(CrmContacts entity) {
        CrmOpportunityLink link = new CrmOpportunityLink();

        // 为空则创建
        if (StringUtils.isBlank(entity.getId())){
            // 判断手机号是否重复
            String phoneNumber = entity.getPhoneNumber();
            Parameter contractIdParam = new Parameter("phone_number", "=", phoneNumber);
            PageRequest pageRequest = new PageRequest(0, 1, Lists.newArrayList(contractIdParam), "phone_number");

            List<CrmContacts> crmContacts = crmContactsDao.listAll(pageRequest);
            if (!crmContacts.isEmpty()) {
                throw new CommonJsonException(ResultUtil.warningJson(ErrorEnum.E_50001, "填写手机号已存在，无需重复添加"));
            }
            entity.preInsert();
            crmContactsDao.insert(entity);
            // 再保存到商机明细-联系人
            CrmOpportunity opportunity = new CrmOpportunity();
            opportunity.setId(entity.getBsoy());
            link.setOpp(opportunity);
            link.setLink(entity);
            super.save(link);
            return link;
        }else{
            entity.preUpdate();
            crmContactsDao.update(entity);
        }
        return  link;
    }

    @Transactional
    public int deleteCrmContacts(CrmContacts entity) {

        entity.preUpdate();
        entity.setDelFlag(BaseEntity.DEL_FLAG_DELETE);
        crmContactsDao.delete(entity);

        String updateBy = null;
        JSONObject userObj = SessionUtils.getUserJson();
        if (userObj != null && StringUtils.isNotBlank(userObj.getString("id"))){
            updateBy = userObj.getString("name") + "(" + userObj.getString("loginName") + ")";
        }
        String updateDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        linkDao.deleteLinkById(entity.getId(),BaseEntity.DEL_FLAG_DELETE,updateBy,updateDate);

        return 0;
    }

    public List<CrmContacts> getByTacts(String opporId) {
        return linkDao.getByTacts(opporId);
    }
}
