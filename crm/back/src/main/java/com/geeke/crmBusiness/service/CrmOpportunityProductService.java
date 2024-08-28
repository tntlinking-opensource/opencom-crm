package com.geeke.crmBusiness.service;

import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.crmBusiness.dao.CrmOpportunityProductDao;
import com.geeke.crmBusiness.entity.CrmOpportunityProduct;
import com.geeke.crmProInfo.entity.CrmProducts;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商机明细Service
 * @author
 * @version
 */

@Service("crmOpportunityProductService")
@Transactional(readOnly = true)
public class CrmOpportunityProductService extends CrudService<CrmOpportunityProductDao, CrmOpportunityProduct> {

    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
    public List<String> bulkInsert(List<CrmOpportunityProduct> entitys) {
        List<String> ids = Lists.newArrayList();
        List<CrmOpportunityProduct> updateEntitys = new ArrayList<>();
        List<CrmOpportunityProduct> addEntitys = new ArrayList<>();
        for(CrmOpportunityProduct entity: entitys) {
            if (StringUtils.isNullOrEmpty(entity.getId())){
                entity.preInsert();
                ids.add(entity.getId());
                addEntitys.add(entity);
            }else{
                entity.preUpdate();
                ids.add(entity.getId());
                updateEntitys.add(entity);
            }

        }
        int update = updateEntitys.size() == 0 ? 0 : dao.bulkUpdate(updateEntitys);
        int add = addEntitys.size() == 0 ? 0 : dao.bulkInsert(addEntitys);
        return ids;
    }
    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
    public int bulkDelete(List<CrmOpportunityProduct> entitys) {
        for(CrmOpportunityProduct entity: entitys) {
            if (StringUtils.isNullOrEmpty(entity.getId())){
                continue;
            }
            entity.preUpdate();
        }
        int delete = dao.bulkDelete(entitys);
        if (delete != entitys.size()) {
            int size = entitys.size() - delete;
            throw new CommonJsonException(ResultUtil.warningJson(ErrorEnum.E_50002, "存在非最新版本数据，操作失败！失败条数：" + size));
        }
        return delete;
    }

    public List<CrmProducts> queryProductByOpp(String id){
        return dao.queryProductByOpp(id);
    }
}
