package com.geeke.businessopportunity.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.businessopportunity.entity.CrmBusinessOpportunity;
import com.geeke.businessopportunity.service.CrmBusinessOpportunityService;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.customerManagement.entity.CrmCustomerManagement;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商机查询Controller
 * @author
 * @version
 */
@RestController
@RequestMapping(value = "/businessopportunity/crmBusinessOpportunity")
public class CrmBusinessOpportunityController extends BaseController {

    @Autowired
    private CrmBusinessOpportunityService crmBusinessOpportunityService;

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        CrmBusinessOpportunity entity = crmBusinessOpportunityService.get(id);

        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }

    @GetMapping("/getcusnames/{id}")
    public ResponseEntity<JSONObject> getBycusnames(@PathVariable("id") String id) {
        System.out.println("请求参数"+id);
        List<CrmBusinessOpportunity> result = crmBusinessOpportunityService.getBycusnames(id);
        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    /*性别*/
   /* @GetMapping("/getdicts/{type}")
    public ResponseEntity<JSONObject> getdicts(@PathVariable("type") String type) {
        List<CrmCustomerManagement> result = crmBusinessOpportunityService.getdicts(type);
        return ResponseEntity.ok(ResultUtil.successJson(result));
    }*/





    @PostMapping(value = { "list", "" })
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<CrmBusinessOpportunity> result = crmBusinessOpportunityService.listPage(
            searchParams.getParams(),
            searchParams.getOffset(),
            searchParams.getLimit(),
            searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<CrmBusinessOpportunity> result = crmBusinessOpportunityService.listAll(
            searchParams.getParams(),
            searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestBody CrmBusinessOpportunity entity) {
        String id = crmBusinessOpportunityService.save(entity).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody CrmBusinessOpportunity entity) {
        int rows = crmBusinessOpportunityService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<CrmBusinessOpportunity> entitys) {
        List<String> ids = crmBusinessOpportunityService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<CrmBusinessOpportunity> entitys) {
        List<String> ids = crmBusinessOpportunityService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<CrmBusinessOpportunity> entitys) {
        int rows = crmBusinessOpportunityService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }
}
