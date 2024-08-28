package com.geeke.crmCustomer.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.contract.entity.CrmContract;
import com.geeke.contract.service.CrmContractService;
import com.geeke.crmCustomer.entity.CrmCustomerCot;
import com.geeke.crmCustomer.entity.CrmCustomerCotLine;
import com.geeke.crmCustomer.service.CrmCustomerCotService;
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

import java.util.List;

/**
 * 客户管理Controller
 *
 * @author
 */
@RestController
@Aspect
@RequestMapping(value = "/crmCustomer/crmCustomerCot")
public class CrmCustomerCotController extends BaseController {

    @Autowired
    private CrmCustomerCotService crmCustomerCotService;

    @Autowired
    private DynamicRestTemplate restTemplate;

    @Autowired
    private CrmContractService crmContractService;

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(ResultUtil.successJson(crmCustomerCotService.getCustomerCotLineByCrmCustomerCotId(id)));
    }
    @GetMapping("/getCrmCustomersCotByCusId")
    public ResponseEntity<JSONObject> getCrmCustomerCotByCusId(String cusId) {
        return ResponseEntity.ok(ResultUtil.successJson(crmCustomerCotService.getCrmCustomersCotByCusId(cusId)));
    }
    @PostMapping(value = {"list", ""})
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<CrmCustomerCotLine> result = crmCustomerCotService.crmCustomerCotLinelistPage(
                searchParams.getParams(),
                searchParams.getOffset(),
                searchParams.getLimit(),
                searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<CrmCustomerCot> result = crmCustomerCotService.listAll(searchParams.getParams(), searchParams.getOrderby());

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
                "/crmCommunicate/crmCommunicate/listAll",
                JSONObject.class,
                searchParams,
                headers
        );
        return ResponseEntity.ok(ResultUtil.successJson(listAll.get("data")));
    }

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestBody CrmCustomerCot entity) {
        CrmContract crmContract = new CrmContract();
        crmContract.setId(entity.getCotId());
//        crmContract.setCustomer(entity.getCustomer());
//        crmContract.setCotContacts(entity.getCotContacts());
//        crmContract.setCotName(entity.getCotName());
//        crmContract.setCotPay(entity.getCotPay());
//        crmContract.setCotDepartment(entity.getCotDepartment());
//        crmContract.setCotDepartmentId(entity.getCotDepartmentId());
//        crmContract.setCotResponsible(entity.getCotResponsible());
//        crmContract.setCotStage(entity.getCotStage());
//        crmContract.setCotSource(entity.getCotSource());
//        crmContract.setUpdateDate(entity.getCotUpdateDate());
        String cotId = crmContractService.save(crmContract).getId();
        CrmCustomerCot crmCustomerCot = new CrmCustomerCot();
        crmCustomerCot.setCusId(entity.getCusId());
        crmCustomerCot.setCotId(cotId);
        if (entity.getId() == null) {
            String id = crmCustomerCotService.save(crmCustomerCot).getId();
            return ResponseEntity.ok(ResultUtil.successJson(id));
        } else {
            return ResponseEntity.ok(ResultUtil.successJson(entity.getId()));
        }
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody CrmCustomerCot entity) {
        int rows = crmCustomerCotService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<CrmCustomerCot> entitys) {
        List<String> ids = crmCustomerCotService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<CrmCustomerCot> entitys) {
        List<String> ids = crmCustomerCotService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<CrmCustomerCot> entitys) {
        int rows = crmCustomerCotService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }


    @Around("execution(* com.geeke.contract.service.CrmContractService.saveContractWithAttachment(..))")
    public Object AroundSave(ProceedingJoinPoint pjp) {
        String cusId = null;
        Object proceed = null;

        try {
            for (Object arg : pjp.getArgs()) {
                CrmContract crmContract = (CrmContract) arg;
                if (crmContract != null) {
                    cusId = crmContract.getCustomerId();
                    break;
                }
            }
            proceed = pjp.proceed();
            String jsonString = JSONObject.toJSONString(proceed);
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            String cotId = (String) jsonObject.get("id");
            CrmCustomerCot crmCustomerCot = crmCustomerCotService.getCrmCustomerCotByCotId(cotId);
            if (crmCustomerCot == null || crmCustomerCot.getId() == null) {
                CrmCustomerCot newCrmCustomerCot = new CrmCustomerCot();
                newCrmCustomerCot.setCotId(cotId);
                newCrmCustomerCot.setCusId(cusId);
                crmCustomerCotService.save(newCrmCustomerCot);
            } else {
                crmCustomerCot.setCusId(cusId);
                crmCustomerCotService.save(crmCustomerCot);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            return proceed;
        }
    }
}
