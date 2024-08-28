package com.geeke.crmBusiness.service;

import cn.hutool.json.JSONUtil;
import com.geeke.common.constants.ActionConstants;
import com.geeke.common.data.Parameter;
import com.geeke.common.service.CrudService;
import com.geeke.contract.dao.CrmContractDao;
import com.geeke.contract.entity.CrmContract;
import com.geeke.crmBusiness.dao.CrmOpportunityCotDao;
import com.geeke.crmBusiness.entity.CrmOpportunity;
import com.geeke.crmBusiness.entity.CrmOpportunityCot;
import com.geeke.sys.entity.SysFile;
import com.geeke.sys.service.SysFileService;
import com.geeke.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商机管理-合同Service
 *
 * @author
 */

@Service("crmOpportunityCotService")
@Transactional(readOnly = true)
public class CrmOpportunityCotService extends CrudService<CrmOpportunityCotDao, CrmOpportunityCot> {
    @Autowired
    private CrmOpportunityCotDao crmOpportunityCotDao;

    @Transactional
    public CrmContract saveContract(CrmContract crmContract) {
        // 保存商机管理-合同
        AtomicReference<CrmOpportunityCot> crmOpportunityCot = new AtomicReference<>(new CrmOpportunityCot());
        CrmOpportunity opportunity = new CrmOpportunity();
        opportunity.setId(crmContract.getOpportunityId());

        // 先查出是否具有商机明细-合同
        List<Parameter> list = new ArrayList<>();
        Parameter parameter = new Parameter("cot_id", "=", crmContract.getId());
        list.add(parameter);
        List<CrmOpportunityCot> crmOpportunityCots = super.listAll(list, "cot_id");
        if (crmOpportunityCots.size() != 0) {
            crmOpportunityCots.forEach(item -> {
                crmOpportunityCot.set(item);
                crmOpportunityCot.get().setId(item.getId());
            });
        } else {
            crmOpportunityCot.get().setCotId(crmContract.getId());
            crmOpportunityCot.get().setOpp(opportunity);
            crmOpportunityCot.get().setRemarks(crmContract.getRemark());
        }
        this.save(crmOpportunityCot.get());
        return crmContract;

    }

    @Transactional
    public CrmContract saveContractWithAttachment(CrmContract contract, MultipartFile[] attachments, String[] deleteIds) {
        CrmContract crmContract = saveContract(contract);
        return contract;
    }
    @Transactional
    public CrmOpportunityCot save(CrmOpportunityCot entity) {
        if (StringUtils.isBlank(entity.getId())) {
            entity.preInsert();
            doInsert(entity);
            this.saveAction(this.createAction(ActionConstants.ACTION_CREATED, entity));
        } else {
            entity.preUpdate();
            doUpdate(entity);
            this.saveAction(this.createAction(ActionConstants.ACTION_UPDATED, entity));
        }

        return entity;
    }

    public CrmContract getByCotId(String opporId) {
        return  crmOpportunityCotDao.getByCotId(opporId);
    }
}
