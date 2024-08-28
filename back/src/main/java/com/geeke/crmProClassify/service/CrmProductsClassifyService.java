package com.geeke.crmProClassify.service;

import com.geeke.common.sequence.service.SequenceService;
import com.geeke.common.service.TreeService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.crmProClassify.dao.CrmProductsClassifyDao;
import com.geeke.crmProClassify.entity.CrmProductsClassify;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.SessionUtils;
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
 * 产品分类Service
 * @author
 * @version
 */

@Service("crmProductsClassifyService")
@Transactional(readOnly = true)
public class CrmProductsClassifyService extends TreeService<CrmProductsClassifyDao, CrmProductsClassify> {

    @Autowired
    SequenceService sequenceService;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public CrmProductsClassify save(CrmProductsClassify crmProductsClassify) {
        // 新增时, 处理自动编号字段
        if (StringUtils.isBlank(crmProductsClassify.getId())) {
            String sn = sequenceService.generate(SessionUtils.getUser().getCompanyId(), "PDCCODE", crmProductsClassify);
            crmProductsClassify.setPdcCode(sn);
        }

        CrmProductsClassify crmProductsClassifyTemp = super.save(crmProductsClassify);

        return crmProductsClassifyTemp;
    }
}
