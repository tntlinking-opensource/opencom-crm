package com.geeke.contacts.service;

import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.contacts.dao.CrmContactsDao;
import com.geeke.contacts.entity.CrmContacts;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 联系人Service
 * @author
 * @version
 */

@Service("crmContactsService")
@Transactional(readOnly = true)
public class CrmContactsService extends CrudService<CrmContactsDao, CrmContacts> {

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public CrmContacts save(CrmContacts crmContacts) {
        // 约束条件处理
        Map<String, String> colMaps = Maps.newHashMap();

        // 手机号

        colMaps.clear();

        colMaps.put("phone_number", "phoneNumber");

        if (exists(dao, crmContacts, colMaps)) {
            throw new CommonJsonException(ResultUtil.warningJson(ErrorEnum.E_50001, "手机号已存在"));
        }

        CrmContacts crmContactsTemp = super.save(crmContacts);

        return crmContactsTemp;
    }
}
