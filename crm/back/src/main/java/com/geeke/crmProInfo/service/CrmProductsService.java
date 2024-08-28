package com.geeke.crmProInfo.service;

import com.geeke.common.sequence.service.SequenceService;
import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.crmProInfo.dao.CrmProductsDao;
import com.geeke.crmProInfo.entity.CrmProducts;
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
 * 产品管理Service
 * @author
 * @version
 */

@Service("crmProductsService")
@Transactional(readOnly = true)
public class CrmProductsService extends CrudService<CrmProductsDao, CrmProducts> {

    @Autowired
    SequenceService sequenceService;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public CrmProducts save(CrmProducts crmProducts) {
        // 新增时, 处理自动编号字段
        if (StringUtils.isBlank(crmProducts.getId())) {
            String sn = sequenceService.generate(SessionUtils.getUser().getCompanyId(), "PDCODE", crmProducts);
            crmProducts.setPdNo(sn);
        }

        CrmProducts crmProductsTemp = super.save(crmProducts);

        return crmProductsTemp;
    }
}
