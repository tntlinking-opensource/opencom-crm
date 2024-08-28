package com.geeke.orders.service;

import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.crmCluePool.entity.CrmUserls;
import com.geeke.orders.dao.CrmOrdersDao;
import com.geeke.orders.entity.CrmCopCp;
import com.geeke.orders.entity.CrmOrders;
import com.geeke.sys.service.SysFileService;
import com.geeke.utils.IdGen;
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
import org.springframework.web.multipart.MultipartFile;

/**
 * 订单管理Service
 * @author
 * @version
 */

@Service("crmOrdersService")
@Transactional(readOnly = true)
public class CrmOrdersService extends CrudService<CrmOrdersDao, CrmOrders> {

    @Autowired
    private SysFileService sysFileService;

    @Autowired
    private CrmOrdersDao crmOrdersDao;



    public int getseleddcpcount(String bsoyName,Integer pageNum, Integer pageSize){
        return crmOrdersDao.getseleddcpcount(bsoyName, pageNum, pageSize);
    }

    public List<CrmCopCp> getseleddcp(String bsoyName, Integer pageNum, Integer pageSize){
        return crmOrdersDao.getseleddcp( bsoyName, pageNum,  pageSize);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public CrmOrders save(CrmOrders crmOrders, MultipartFile[] wbnumberUploads, String[] deleteIds) throws java.io.IOException {
        if(!ArrayUtils.isEmpty(wbnumberUploads) || !ArrayUtils.isEmpty(deleteIds)){
            if (StringUtils.isEmpty(crmOrders.getWbnumber())) {
                crmOrders.setWbnumber(IdGen.uuid());
            }
            // 保存附件
            sysFileService.changeAndSaveSysFileList(wbnumberUploads, crmOrders.getWbnumber());

            // 根据附件ID删除附件信息
            if (null != deleteIds && deleteIds.length > 0) {
                sysFileService.delete(deleteIds);
            }
        }
        CrmOrders crmOrdersTemp = super.save(crmOrders);

        return crmOrdersTemp;
    }
}
