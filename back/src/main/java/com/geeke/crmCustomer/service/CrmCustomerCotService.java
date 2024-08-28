package com.geeke.crmCustomer.service;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.common.data.PageRequest;
import com.geeke.common.data.Parameter;
import com.geeke.common.service.CrudService;
import com.geeke.contract.entity.CrmContract;
import com.geeke.contract.service.CrmContractService;
import com.geeke.crmBusiness.dao.CrmOpportunityDao;
import com.geeke.crmBusiness.entity.CrmOpportunity;
import com.geeke.crmBusiness.entity.CrmOpportunityCot;
import com.geeke.crmCustomer.dao.CrmCustomerCotDao;
import com.geeke.crmCustomer.entity.CrmCustomer;
import com.geeke.crmCustomer.entity.CrmCustomerCot;
import com.geeke.crmCustomer.entity.CrmCustomerCotLine;
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
import java.util.concurrent.atomic.AtomicReference;

/**
 * 客户管理Service
 * @author
 * @version
 */

@Service("crmCustomerCotService")
@Transactional(readOnly = true)
public class CrmCustomerCotService extends CrudService<CrmCustomerCotDao, CrmCustomerCot> {

    @Autowired
    private DynamicRestTemplate restTemplate;


    @Autowired
    private CrmContractService crmContractService;

    @Autowired
    private CrmCustomerCotDao crmCustomerCotDao;

    @Autowired
    private CrmOpportunityDao crmOpportunityDao;

    @Transactional
    public CrmContract saveContract(CrmContract crmContract) {
        // 保存商机管理-合同
//        AtomicReference<CrmOpportunityCot> crmOpportunityCot = new AtomicReference<>(new CrmOpportunityCot());
        AtomicReference<CrmCustomerCot> customerCot = new AtomicReference<>(new CrmCustomerCot());
//        CrmOpportunity opportunity = new CrmOpportunity();
        CrmCustomer customer = new CrmCustomer();
        customer.setId(crmContract.getOpportunityId());

        // 先查出是否具有商机明细-合同
        List<Parameter> list = new ArrayList<>();
        Parameter parameter = new Parameter("cot_id", "=", crmContract.getId());
        list.add(parameter);
        List<CrmCustomerCot> crmOpportunityCots = super.listAll(list, "cot_id");
        if (crmOpportunityCots.size() != 0) {
            crmOpportunityCots.forEach(item -> {
                customerCot.set(item);
                customerCot.get().setId(item.getId());
            });
        } else {
            customerCot.get().setCotId(crmContract.getId());
            customerCot.get().setCusId(crmContract.getCustomerId());
            customerCot.get().setRemarks(crmContract.getRemark());
        }
        this.save(customerCot.get());
        return crmContract;

    }


    @Override
    public Page<CrmCustomerCot> listPage(List<Parameter> parameters, int offset, int limit, String orderby) {
        Page<CrmCustomerCot> result = super.listPage(parameters, offset, limit, orderby);

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
    public List<CrmCustomerCot> listAll(List<Parameter> parameters, String orderby) {
        PageRequest pageRequest = new PageRequest(parameters, orderby);

        List<CrmCustomerCot> result = dao.listAll(pageRequest);

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


    public Page<CrmCustomerCotLine> crmCustomerCotLinelistPage(List<Parameter> parameters, int offset, int limit, String orderby) {
        Page<CrmCustomerCot> page = super.listPage(parameters, offset, limit, orderby);
        List<CrmCustomerCotLine> crmCustomerCotList = new ArrayList<>();
        if (page.getRows() != null) {
            for (CrmCustomerCot crmCustomerCot : page.getRows()) {
                String cotId = crmCustomerCot.getCotId();
                CrmContract crmContract = crmContractService.get(cotId);
                if(null==crmContract){
                    continue;
                }
                CrmOpportunity crmOpportunity = crmOpportunityDao.get(crmContract.getOpportunityId());
                CrmCustomerCotLine crmCustomerCotLine = new CrmCustomerCotLine();
                crmCustomerCotLine.setContractCode(crmContract.getContractCode());
                crmCustomerCotLine.setContractName(crmContract.getContractName());
                crmCustomerCotLine.setOppName(crmOpportunity.getOppName());
                crmCustomerCotLine.setContractMoney(crmContract.getContractAmount());
                crmCustomerCotLine.setSginDate(crmContract.getSignDate());
                crmCustomerCotLine.setContractStatus(crmContract.getContractStatus());
                crmCustomerCotLine.setCreateBy(crmContract.getCreateBy());
                crmCustomerCotLine.setCreateDate(crmContract.getCreateDate());
//                crmCustomerCotLine.setCustomer(crmContract.getCustomer());
//                crmCustomerCotLine.setCotContacts(crmContract.getCotContacts());
//                crmCustomerCotLine.setCotName(crmContract.getCotName());
//                crmCustomerCotLine.setCotPay(crmContract.getCotPay());
//                crmCustomerCotLine.setCotDepartment(crmContract.getCotDepartment());
//                crmCustomerCotLine.setCotDepartmentId(crmContract.getCotDepartmentId());
//                crmCustomerCotLine.setCotResponsible(crmContract.getCotResponsible());
//                crmCustomerCotLine.setCotStage(crmContract.getCotStage());
//                crmCustomerCotLine.setCotSource(crmContract.getCotSource());
                crmCustomerCotLine.setCusId(crmCustomerCot.getCusId());
                crmCustomerCotLine.setCotId(cotId);
                crmCustomerCotLine.setId(crmCustomerCot.getId());
                crmCustomerCotLine.setUpdateDate(crmCustomerCot.getUpdateDate());
                crmCustomerCotLine.setCotUpdateDate(crmContract.getUpdateDate());
                crmCustomerCotList.add(crmCustomerCotLine);
            }
        }
        Page<CrmCustomerCotLine> crmCustomerCotListPage = new Page<>(page.getTotal(), crmCustomerCotList);

        return crmCustomerCotListPage;
    }

    public CrmCustomerCotLine getCustomerCotLineByCrmCustomerCotId(String crmCustomerCotId) {

        CrmCustomerCot crmCustomerCot = this.get(crmCustomerCotId);
        String cotId = crmCustomerCot.getCotId();
        CrmContract crmContract = crmContractService.get(cotId);
        CrmCustomerCotLine crmCustomerCotLine = new CrmCustomerCotLine();
        crmCustomerCotLine.setContractCode(crmContract.getContractCode());
        crmCustomerCotLine.setContractName(crmContract.getContractName());
        crmCustomerCotLine.setOppName(crmContract.getOpportunityName());
//        crmCustomerCotLine.setContractMoney(crmContract.getC);
        crmCustomerCotLine.setSginDate(crmContract.getSignDate());
        crmCustomerCotLine.setContractStatus(crmContract.getContractStatus());
        crmCustomerCotLine.setCreateBy(crmContract.getCreateBy());
        crmCustomerCotLine.setCreateDate(crmContract.getCreateDate());
//        crmCustomerCotLine.setCustomer(crmContract.getCustomer());
//        crmCustomerCotLine.setCotContacts(crmContract.getCotContacts());
//        crmCustomerCotLine.setCotName(crmContract.getCotName());
//        crmCustomerCotLine.setCotPay(crmContract.getCotPay());
//        crmCustomerCotLine.setCotDepartment(crmContract.getCotDepartment());
//        crmCustomerCotLine.setCotDepartmentId(crmContract.getCotDepartmentId());
//        crmCustomerCotLine.setCotResponsible(crmContract.getCotResponsible());
//        crmCustomerCotLine.setCotStage(crmContract.getCotStage());
//        crmCustomerCotLine.setCotSource(crmContract.getCotSource());
        crmCustomerCotLine.setCusId(crmCustomerCot.getCusId());
        crmCustomerCotLine.setCotId(cotId);
        crmCustomerCotLine.setId(crmCustomerCot.getId());
        crmCustomerCotLine.setUpdateDate(crmCustomerCot.getUpdateDate());
        crmCustomerCotLine.setCotUpdateDate(crmContract.getUpdateDate());
        return crmCustomerCotLine;
    }


    public CrmCustomerCot getCrmCustomerCotByCotId(String cotId) {
        return crmCustomerCotDao.getCrmCustomerCotByCotId(cotId);
    }

    public List<CrmContract> getCrmCustomersCotByCusId(String cusId) {
        List<CrmContract> crmContractList = new ArrayList<>();
        List<CrmCustomerCot> crmCustomerCotList=crmCustomerCotDao.getCrmCustomerCotsByCusId(cusId);
        for (CrmCustomerCot crmCustomerCot : crmCustomerCotList) {
            CrmContract crmContract = crmContractService.get(crmCustomerCot.getCotId());
            crmContractList.add(crmContract);
        }
        return crmContractList;
    }
}
