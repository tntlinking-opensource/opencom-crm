package com.geeke.crmCustomer.service;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.common.data.PageRequest;
import com.geeke.common.data.Parameter;
import com.geeke.common.service.CrudService;
import com.geeke.crmCommunicate.entity.CrmCommunicate;
import com.geeke.crmCommunicate.service.CrmCommunicateService;
import com.geeke.crmCustomer.dao.CrmCustomerCntDao;
import com.geeke.crmCustomer.entity.CrmCustomerCnt;
import com.geeke.crmCustomer.entity.CrmCustomerCntLine;
import com.geeke.utils.RemoteResultResolver;
import com.newtouch.cloud.common.dependency.remote.rest.DynamicRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 客户管理Service
 *
 * @author
 */

@Service("crmCustomerCntService")
@Transactional(readOnly = true)
public class CrmCustomerCntService extends CrudService<CrmCustomerCntDao, CrmCustomerCnt> {

    @Autowired
    private DynamicRestTemplate restTemplate;

    @Autowired
    private CrmCommunicateService crmCommunicateService;

    @Autowired
    private CrmCustomerCntDao crmCustomerCntDao;


    @Override
    public Page<CrmCustomerCnt> listPage(List<Parameter> parameters, int offset, int limit, String orderby) {
        Page<CrmCustomerCnt> result = super.listPage(parameters, offset, limit, orderby);

        //查看是否多次调用
        int flag = RemoteResultResolver.getRemoteFlag();
        //组装数据
        if (flag <= 1 && Objects.nonNull(result.getRows())) {
            //设置Header
            HttpHeaders headers = new HttpHeaders();
            headers.add(RemoteResultResolver.REMOTE_FLAG, String.valueOf(flag++));

            SearchParams crmCommunicateParam = RemoteResultResolver.param(result.getRows(), "cntId", "id");
            JSONObject crmCommunicateList = restTemplate.post(
                    "crm",
                    "/crmCommunicate/crmCommunicate/listAll",
                    JSONObject.class,
                    crmCommunicateParam,
                    headers
            );
            Integer crmCommunicateCode = Objects.requireNonNull(crmCommunicateList.getInteger("code"));
            if (crmCommunicateCode == 100) {
                List<Map<String, Object>> cnt = crmCommunicateList.getObject("data", ArrayList.class);
                RemoteResultResolver.resultResolver(result.getRows(), cnt, "cnt", "cntId");
            }
        }

        return result;
    }


    @Override
    public List<CrmCustomerCnt> listAll(List<Parameter> parameters, String orderby) {
        PageRequest pageRequest = new PageRequest(parameters, orderby);

        List<CrmCustomerCnt> result = dao.listAll(pageRequest);

        //查看是否多次调用
        int flag = RemoteResultResolver.getRemoteFlag();
        //组装数据
        if (flag <= 1 && Objects.nonNull(result)) {
            //设置Header
            HttpHeaders headers = new HttpHeaders();
            headers.add(RemoteResultResolver.REMOTE_FLAG, String.valueOf(flag++));

            SearchParams crmCommunicateParam = RemoteResultResolver.param(result, "cntId", "id");
            JSONObject crmCommunicateList = restTemplate.post(
                    "crm",
                    "/crmCommunicate/crmCommunicate/listAll",
                    JSONObject.class,
                    crmCommunicateParam,
                    headers
            );
            Integer crmCommunicateCode = Objects.requireNonNull(crmCommunicateList.getInteger("code"));
            if (crmCommunicateCode == 100) {
                List<Map<String, Object>> cnt = crmCommunicateList.getObject("data", ArrayList.class);
                RemoteResultResolver.resultResolver(result, cnt, "cnt", "cntId");
            }
        }

        return result;
    }

    public Page<CrmCustomerCntLine> crmCustomerCntLinelistPage(List<Parameter> parameters, int offset, int limit, String orderby) {
        Page<CrmCustomerCnt> page = super.listPage(parameters, offset, limit, orderby);
        List<CrmCustomerCntLine> crmCustomerCntList = new ArrayList<>();
        if (page.getRows() != null) {
            for (CrmCustomerCnt crmCustomerCnt : page.getRows()) {
                String cntId = crmCustomerCnt.getCntId();
                CrmCommunicate crmCommunicate = crmCommunicateService.get(cntId);
                CrmCustomerCntLine crmCustomerCntLine = new CrmCustomerCntLine();
                crmCustomerCntLine.setCntContent(crmCommunicate.getCntContent());
                crmCustomerCntLine.setCntType(crmCommunicate.getCntTypeId());
                crmCustomerCntLine.setCntDate(crmCommunicate.getCntDate());
                crmCustomerCntLine.setCreateBy(crmCommunicate.getCreateBy());
                crmCustomerCntLine.setCntPname(crmCommunicate.getCntPname());
                crmCustomerCntLine.setCutRemind("");
                crmCustomerCntLine.setCutRemindType("");
                crmCustomerCntLine.setCutNextDate("");
                crmCustomerCntLine.setCusId(crmCustomerCnt.getCusId());
                crmCustomerCntLine.setCntId(cntId);
                crmCustomerCntLine.setId(crmCustomerCnt.getId());
                crmCustomerCntLine.setUpdateBy(crmCommunicate.getUpdateBy());
                crmCustomerCntLine.setUpdateDate(crmCustomerCnt.getUpdateDate());
                crmCustomerCntLine.setCntContent(crmCommunicate.getCntContent());
                crmCustomerCntLine.setCntUpdateDate(crmCommunicate.getUpdateDate());
                crmCustomerCntList.add(crmCustomerCntLine);
            }
        }
        Page<CrmCustomerCntLine> crmCustomerCntListPage = new Page<>(page.getTotal(), crmCustomerCntList);

        return crmCustomerCntListPage;
    }

    public CrmCustomerCntLine getCustomerCntLineByCrmCustomerCntId(String crmCustomerCntId) {

        CrmCustomerCnt crmCustomerCnt = this.get(crmCustomerCntId);
        String cntId = crmCustomerCnt.getCntId();
        CrmCommunicate crmCommunicate = crmCommunicateService.get(crmCustomerCnt.getCntId());
        CrmCustomerCntLine crmCustomerCntLine = new CrmCustomerCntLine();
        crmCustomerCntLine.setCntContent(crmCommunicate.getCntContent());
        crmCustomerCntLine.setCntType(crmCommunicate.getCntTypeId());
        crmCustomerCntLine.setCntDate(crmCommunicate.getCntDate());
        crmCustomerCntLine.setCreateBy(crmCommunicate.getCreateBy());
        crmCustomerCntLine.setCntPname(crmCommunicate.getCntPname());
//        crmCustomerCntLine.setCutRemind(crmCommunicate.getCutRemind());
//        crmCustomerCntLine.setCutRemindType(crmCommunicate.getCutRemindType());
//        crmCustomerCntLine.setCutNextDate(crmCommunicate.getCutNextDate());
        crmCustomerCntLine.setCusId(crmCustomerCnt.getCusId());
        crmCustomerCntLine.setCntId(cntId);
        crmCustomerCntLine.setId(crmCustomerCnt.getId());
        crmCustomerCntLine.setUpdateBy(crmCommunicate.getUpdateBy());
        crmCustomerCntLine.setUpdateDate(crmCustomerCnt.getUpdateDate());
        crmCustomerCntLine.setCntContent(crmCommunicate.getCntContent());
        crmCustomerCntLine.setCntUpdateDate(crmCommunicate.getUpdateDate());
        return crmCustomerCntLine;
    }

    public CrmCustomerCnt getCustomerCntByCntId(String cntId) {

        return crmCustomerCntDao.getCustomerCntByCntId(cntId);
    }
}
