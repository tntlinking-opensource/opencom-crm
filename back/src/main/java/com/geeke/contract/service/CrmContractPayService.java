package com.geeke.contract.service;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.persistence.DataEntity;
import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.contract.dao.CrmContractDao;
import com.geeke.contract.dao.CrmContractPayDao;
import com.geeke.contract.entity.CrmContractPay;
import com.geeke.crmBusiness.entity.CrmOpportunityPay;
import com.geeke.sys.utils.SessionUtils;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
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
 * 还款信息Service
 * @author
 * @version
 */

@Service("crmContractPayService")
@Transactional(readOnly = false)
public class CrmContractPayService extends CrudService<CrmContractPayDao, CrmContractPay> {
    @Autowired
    private CrmContractPayDao crmContractPayDao;

    public int deleteByContractId(String contractId) {
        String updateBy = null;
        JSONObject userObj = SessionUtils.getUserJson();
        if (userObj != null && StringUtils.isNotBlank(userObj.getString("id"))) {
            updateBy = userObj.getString("name") + "(" + userObj.getString("loginName") + ")";
        }
        String updateDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return crmContractPayDao.deleteByContractId(contractId, DataEntity.DEL_FLAG_DELETE, updateBy, updateDate);
    }
}
