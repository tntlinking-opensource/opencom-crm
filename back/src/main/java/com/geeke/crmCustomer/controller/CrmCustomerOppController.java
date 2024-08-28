package com.geeke.crmCustomer.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.crmBusiness.entity.CrmOpportunity;
import com.geeke.crmBusiness.service.CrmOpportunityService;
import com.geeke.crmCustomer.entity.CrmCustomer;
import com.geeke.crmCustomer.entity.CrmCustomerOpp;
import com.geeke.crmCustomer.entity.CrmCustomerOppInsert;
import com.geeke.crmCustomer.entity.CrmCustomerOppLine;
import com.geeke.crmCustomer.service.CrmCustomerOppService;
import com.geeke.crmCustomer.service.CrmCustomerService;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.RemoteResultResolver;
import com.geeke.utils.ResultUtil;
import com.newtouch.cloud.common.dependency.remote.rest.DynamicRestTemplate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户管理Controller
 *
 * @author
 */
@RestController
@RequestMapping(value = "/crmCustomer/crmCustomerOpp")
@Aspect
public class CrmCustomerOppController extends BaseController {

    @Autowired
    private CrmCustomerOppService crmCustomerOppService;

    @Autowired
    private CrmOpportunityService crmOpportunityService;

    @Autowired
    private CrmCustomerService customerService;

    @Autowired
    private DynamicRestTemplate restTemplate;

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(ResultUtil.successJson(crmCustomerOppService.getCustomerOppLineByCrmCustomerOppId(id)));
    }

    @GetMapping("/getCrmOppsByCusId")
    public ResponseEntity<JSONObject> getCrmOppsByCusId(String cusId) {
        return ResponseEntity.ok(ResultUtil.successJson(crmCustomerOppService.getCrmOppsByCusId(cusId)));
    }

    @PostMapping(value = {"list", ""})
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<CrmCustomerOppLine> result = crmCustomerOppService.crmCustomerOppLinelistPage(
                searchParams.getParams(),
                searchParams.getOffset(),
                searchParams.getLimit(),
                searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<CrmCustomerOpp> result = crmCustomerOppService.listAll(searchParams.getParams(), searchParams.getOrderby());

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "cotListAll")
    public ResponseEntity<JSONObject> cotListAll(@RequestBody SearchParams searchParams) {
        //查看是否多次调用
        int flag = RemoteResultResolver.getRemoteFlag();
        //设置Header
        HttpHeaders headers = new HttpHeaders();
        headers.add(RemoteResultResolver.REMOTE_FLAG, String.valueOf(flag++));
        JSONObject listAll = restTemplate.post(
                "crm",
                "/crmOpportunity/crmOpportunity/listAll",
                JSONObject.class,
                searchParams,
                headers
        );
        return ResponseEntity.ok(ResultUtil.successJson(listAll.get("data")));
    }

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestBody CrmCustomerOppInsert entity) {
        CrmOpportunity crmOpportunity = new CrmOpportunity();
        crmOpportunity.setId(entity.getOppId());
        crmOpportunity.setCustomer(entity.getCustomer());
        crmOpportunity.setOppContacts(entity.getOppContacts());
        crmOpportunity.setOppName(entity.getOppName());
        crmOpportunity.setOppPay(entity.getOppPay());
        crmOpportunity.setOppDepartment(entity.getOppDepartment());
        crmOpportunity.setOppDepartmentId(entity.getOppDepartmentId());
        crmOpportunity.setOppResponsible(entity.getOppResponsible());
        crmOpportunity.setOppStage(entity.getOppStage());
        crmOpportunity.setOppSource(entity.getOppSource());
        crmOpportunity.setUpdateDate(entity.getOppUpdateDate());
        String oppId = crmOpportunityService.save(crmOpportunity).getId();
        CrmCustomerOpp crmCustomerOpp = new CrmCustomerOpp();
        crmCustomerOpp.setCusId(entity.getCusId());
        crmCustomerOpp.setOppId(oppId);
        if (entity.getId() == null) {
            String id = crmCustomerOppService.save(crmCustomerOpp).getId();
            return ResponseEntity.ok(ResultUtil.successJson(id));
        } else {
            return ResponseEntity.ok(ResultUtil.successJson(entity.getId()));
        }
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody CrmCustomerOpp entity) {
//        entity.setUpdateDate(entity.getUpdateDate());
        int rows = crmCustomerOppService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<CrmCustomerOpp> entitys) {
        List<String> ids = crmCustomerOppService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<CrmCustomerOpp> entitys) {
        List<String> ids = crmCustomerOppService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<CrmCustomerOpp> entitys) {
        int rows = crmCustomerOppService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }


    @Around("execution(* com.geeke.crmBusiness.controller.CrmOpportunityController.save(..))")
    public Object AroundSave(ProceedingJoinPoint pjp) {
        String cusId = null;
        Object proceed = null;
        for (Object arg : pjp.getArgs()) {
            CrmOpportunity crmOpportunity = (CrmOpportunity) arg;
            if (crmOpportunity != null) {
                cusId = crmOpportunity.getCustomer().getId();
                break;
            }
        }
        try {
            proceed = pjp.proceed();
            String jsonString = JSONObject.toJSONString(proceed);
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            JSONObject jsonBody = (JSONObject) jsonObject.get("body");
            String oppId = (String) jsonBody.get("data");
            CrmCustomerOpp crmCustomerOpp = crmCustomerOppService.getCrmCustomerOppByOppId(oppId);
            CrmCustomer crmCustomer = customerService.get(cusId);
            crmCustomer.setCusStage("02");
            List<CrmCustomer> list = new ArrayList<>();
            list.add(crmCustomer);
            customerService.bulkUpdate(list);
            if (crmCustomerOpp == null || crmCustomerOpp.getId() == null) {
                crmCustomerOpp = new CrmCustomerOpp();
                crmCustomerOpp.setOppId(oppId);
                crmCustomerOpp.setCusId(cusId);
                crmCustomerOppService.save(crmCustomerOpp);
            } else {
                crmCustomerOpp.setCusId(cusId);
                crmCustomerOppService.save(crmCustomerOpp);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            return proceed;
        }
    }
}
