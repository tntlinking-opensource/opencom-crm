package com.geeke.crmCustomer.service;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.common.data.PageRequest;
import com.geeke.common.data.Parameter;
import com.geeke.common.service.CrudService;
import com.geeke.crmBusiness.entity.CrmOpportunity;
import com.geeke.crmBusiness.service.CrmOpportunityProductService;
import com.geeke.crmBusiness.service.CrmOpportunityService;
import com.geeke.crmCustomer.dao.CrmCustomerOppDao;
import com.geeke.crmCustomer.entity.CrmCustomerOpp;
import com.geeke.crmCustomer.entity.CrmCustomerOppLine;
import com.geeke.crmProInfo.entity.CrmProducts;
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

@Service("crmCustomerOppService")
@Transactional(readOnly = true)

public class CrmCustomerOppService extends CrudService<CrmCustomerOppDao, CrmCustomerOpp> {

    @Autowired
    private DynamicRestTemplate restTemplate;

    @Autowired
    private CrmOpportunityService crmOpportunityService;

    @Autowired
    private CrmCustomerOppDao crmCustomerOppDao;

    @Autowired
    private CrmOpportunityProductService crmOpportunityProductService;

    @Override
    public Page<CrmCustomerOpp> listPage(List<Parameter> parameters, int offset, int limit, String orderby) {
        Page<CrmCustomerOpp> result = super.listPage(parameters, offset, limit, orderby);

        //查看是否多次调用
        int flag = RemoteResultResolver.getRemoteFlag();
        //组装数据
        if (flag <= 1 && Objects.nonNull(result.getRows())) {
            //设置Header
            HttpHeaders headers = new HttpHeaders();
            headers.add(RemoteResultResolver.REMOTE_FLAG, String.valueOf(flag++));

            SearchParams crmCommunicateParam = RemoteResultResolver.param(result.getRows(), "cotId", "id");
            JSONObject crmCommunicateList = restTemplate.post(
                    "crm",
                    "/crmCommunicate/crmCommunicate/listAll",
                    JSONObject.class,
                    crmCommunicateParam,
                    headers
            );
            Integer crmCommunicateCode = Objects.requireNonNull(crmCommunicateList.getInteger("code"));
            if (crmCommunicateCode == 100) {
                List<Map<String, Object>> cot = crmCommunicateList.getObject("data", ArrayList.class);
                RemoteResultResolver.resultResolver(result.getRows(), cot, "cot", "cotId");
            }
        }

        return result;
    }

    @Override
    public List<CrmCustomerOpp> listAll(List<Parameter> parameters, String orderby) {
        PageRequest pageRequest = new PageRequest(parameters, orderby);

        List<CrmCustomerOpp> result = dao.listAll(pageRequest);

        //查看是否多次调用
        int flag = RemoteResultResolver.getRemoteFlag();
        //组装数据
        if (flag <= 1 && Objects.nonNull(result)) {
            //设置Header
            HttpHeaders headers = new HttpHeaders();
            headers.add(RemoteResultResolver.REMOTE_FLAG, String.valueOf(flag++));

            SearchParams crmCommunicateParam = RemoteResultResolver.param(result, "cotId", "id");
            JSONObject crmCommunicateList = restTemplate.post(
                    "crm",
                    "/crmCommunicate/crmCommunicate/listAll",
                    JSONObject.class,
                    crmCommunicateParam,
                    headers
            );
            Integer crmCommunicateCode = Objects.requireNonNull(crmCommunicateList.getInteger("code"));
            if (crmCommunicateCode == 100) {
                List<Map<String, Object>> cot = crmCommunicateList.getObject("data", ArrayList.class);
                RemoteResultResolver.resultResolver(result, cot, "cot", "cotId");
            }
        }

        return result;
    }


    public Page<CrmCustomerOppLine> crmCustomerOppLinelistPage(List<Parameter> parameters, int offset, int limit, String orderby) {
        Page<CrmCustomerOpp> page = super.listPage(parameters, offset, limit, orderby);
        List<CrmCustomerOppLine> crmCustomerOppList = new ArrayList<>();
        if (page.getRows() != null) {
            for (CrmCustomerOpp crmCustomerOpp : page.getRows()) {
                String oppId = crmCustomerOpp.getOppId();
                CrmOpportunity crmOpportunity = crmOpportunityService.get(oppId);
                List<CrmProducts> products = crmOpportunityProductService.queryProductByOpp(oppId);

                CrmCustomerOppLine crmCustomerOppLine = new CrmCustomerOppLine();
                crmCustomerOppLine.setCustomer(crmOpportunity.getCustomer());
                crmCustomerOppLine.setOppContacts(crmOpportunity.getOppContacts());
                crmCustomerOppLine.setOppName(crmOpportunity.getOppName());
                crmCustomerOppLine.setOppPay(crmOpportunity.getOppPay());
                crmCustomerOppLine.setOppDepartment(crmOpportunity.getOppDepartment());
                crmCustomerOppLine.setOppDepartmentId(crmOpportunity.getOppDepartmentId());
                crmCustomerOppLine.setOppResponsible(crmOpportunity.getOppResponsible());
                crmCustomerOppLine.setOppStage(crmOpportunity.getOppStage());
                crmCustomerOppLine.setOppSource(crmOpportunity.getOppSource());
                crmCustomerOppLine.setCusId(crmCustomerOpp.getCusId());
                crmCustomerOppLine.setOppId(oppId);
                crmCustomerOppLine.setId(crmCustomerOpp.getId());
                crmCustomerOppLine.setUpdateDate(crmCustomerOpp.getUpdateDate());
                crmCustomerOppLine.setOppUpdateDate(crmOpportunity.getUpdateDate());
                crmCustomerOppLine.setIsSendPriceSheet(crmOpportunity.getOppQuotation());
                if(products.size()>0){
                    crmCustomerOppLine.setProductCount(String.valueOf(products.size()));
                }else{
                    crmCustomerOppLine.setProductCount("0");
                }

                crmCustomerOppList.add(crmCustomerOppLine);
            }
        }
        Page<CrmCustomerOppLine> crmCustomerOppListPage = new Page<>(page.getTotal(), crmCustomerOppList);

        return crmCustomerOppListPage;
    }

    public CrmCustomerOppLine getCustomerOppLineByCrmCustomerOppId(String crmCustomerOppId) {

        CrmCustomerOpp crmCustomerOpp = this.get(crmCustomerOppId);
        String oppId = crmCustomerOpp.getOppId();
        CrmOpportunity crmOpportunity = crmOpportunityService.get(oppId);
        CrmCustomerOppLine crmCustomerOppLine = new CrmCustomerOppLine();
        crmCustomerOppLine.setCustomer(crmOpportunity.getCustomer());
        crmCustomerOppLine.setOppContacts(crmOpportunity.getOppContacts());
        crmCustomerOppLine.setOppName(crmOpportunity.getOppName());
        crmCustomerOppLine.setOppPay(crmOpportunity.getOppPay());
        crmCustomerOppLine.setOppDepartment(crmOpportunity.getOppDepartment());
        crmCustomerOppLine.setOppDepartmentId(crmOpportunity.getOppDepartmentId());
        crmCustomerOppLine.setOppResponsible(crmOpportunity.getOppResponsible());
        crmCustomerOppLine.setOppStage(crmOpportunity.getOppStage());
        crmCustomerOppLine.setOppSource(crmOpportunity.getOppSource());
        crmCustomerOppLine.setCusId(crmCustomerOpp.getCusId());
        crmCustomerOppLine.setOppId(oppId);
        crmCustomerOppLine.setId(crmCustomerOpp.getId());
        crmCustomerOppLine.setUpdateDate(crmOpportunity.getUpdateDate());
        crmCustomerOppLine.setOppUpdateDate(crmOpportunity.getUpdateDate());
        return crmCustomerOppLine;
    }


    public CrmCustomerOpp getCrmCustomerOppByOppId(String oppId) {
        return crmCustomerOppDao.getCrmCustomerOppByOppId(oppId);
    }

    public List<CrmOpportunity> getCrmOppsByCusId(String cusId) {
        List<CrmOpportunity> crmOpportunityList = new ArrayList<>();
        List<CrmCustomerOpp> crmCustomerOppList=crmCustomerOppDao.getCrmCustomerOppsByCusId(cusId);
        for (CrmCustomerOpp crmCustomerOpp : crmCustomerOppList) {
            CrmOpportunity crmOpportunity = crmOpportunityService.get(crmCustomerOpp.getOppId());
            crmOpportunityList.add(crmOpportunity);
        }
        return crmOpportunityList;
    }
}
