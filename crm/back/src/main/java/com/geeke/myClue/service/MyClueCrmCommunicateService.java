package com.geeke.myClue.service;

import com.alibaba.fastjson.JSONObject;
import com.geeke.admin.entity.Router;
import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.crmCustomer.entity.CrmCustomer;
import com.geeke.crmCustomer.entity.CrmCustomerCnt;
import com.geeke.crmCustomer.service.CrmCustomerCntService;
import com.geeke.myClue.dao.MyClueCrmCommunicateDao;
import com.geeke.myClue.entity.CrmClues;
import com.geeke.myClue.entity.MyClueCrmCommunicate;
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
 * 我的线索Service
 * @author
 * @version
 */

@Service("myClueCrmCommunicateService")
@Transactional(readOnly = true)
public class MyClueCrmCommunicateService extends CrudService<MyClueCrmCommunicateDao, MyClueCrmCommunicate> {

    @Autowired
    private SysFileService sysFileService;
    @Autowired
    private CrmCluesService crmCluesService;
    @Autowired
    private CrmCustomerCntService crmCustomerCntService;

    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
    public MyClueCrmCommunicate save(MyClueCrmCommunicate entity, MultipartFile[] iconIdUploads, String[] deleteIds) throws java.io.IOException {
        MyClueCrmCommunicate entityTemp = super.save(entity);
        // 保存附件
        sysFileService.changeAndSaveSysFileList(iconIdUploads, entity.getId());
        // 根据附件ID删除附件信息
        if (null != deleteIds && deleteIds.length > 0) {
            sysFileService.delete(deleteIds);
        }
        CrmClues crmClues = crmCluesService.get(entity.getCntMyclueId());
        crmClues.setCluesState("1");
        crmCluesService.save(crmClues);
        return entityTemp;
    }


    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
    public MyClueCrmCommunicate saveCusWithCom(MyClueCrmCommunicate entity, JSONObject json, MultipartFile[] iconIdUploads, String[] deleteIds) throws java.io.IOException {
        MyClueCrmCommunicate entityTemp = super.save(entity);
        // 保存附件
        sysFileService.changeAndSaveSysFileList(iconIdUploads, entity.getId());
        // 根据附件ID删除附件信息
        if (null != deleteIds && deleteIds.length > 0) {
            sysFileService.delete(deleteIds);
        }
        if("".equals(json.getString("cntId")) || null==json.getString("cntId")){
            CrmCustomerCnt crmCustomerCnt = new CrmCustomerCnt();
            crmCustomerCnt.setCusId(json.getString("cusId"));
            crmCustomerCnt.setCntId(entityTemp.getId());
            crmCustomerCntService.save(crmCustomerCnt);
        }

        return entityTemp;
    }

}
