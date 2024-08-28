package com.geeke.customerManagement.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.admin.entity.User;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.customerManagement.entity.CrmCustomerManagement;
import com.geeke.customerManagement.service.CrmCustomerManagementService;
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
 * 客户名称查询Controller
 * @author
 * @version
 */
@RestController
@RequestMapping(value = "/customerManagement/crmCustomerManagement")
public class CrmCustomerManagementController extends BaseController {

    @Autowired
    private CrmCustomerManagementService crmCustomerManagementService;

    @GetMapping("/")
    public ResponseEntity<JSONObject> getById( AuthenticationToken authcToken) {

        String loginName = (String) authcToken.getPrincipal();
        System.out.println("当前用户"+loginName);
        CrmCustomerManagement entity = crmCustomerManagementService.get(loginName);

        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }

    @GetMapping("/getbyids")
    public ResponseEntity<JSONObject> getByIds( AuthenticationToken authcToken) {

        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println("当前用户"+loginName);
        List<CrmCustomerManagement> result = crmCustomerManagementService.gets(loginName);

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }


    @GetMapping("/getCrmddfzrs")
    public ResponseEntity<JSONObject> getCrmddfzrs( AuthenticationToken authcToken) {

        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println("当前用户"+loginName);
        List<CrmCustomerManagement> result = crmCustomerManagementService.getCrmddfzrs(loginName);
        System.out.println("id："+result.get(0).getId());

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = { "list", "" })
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<CrmCustomerManagement> result = crmCustomerManagementService.listPage(
            searchParams.getParams(),
            searchParams.getOffset(),
            searchParams.getLimit(),
            searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<CrmCustomerManagement> result = crmCustomerManagementService.listAll(
            searchParams.getParams(),
            searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestBody CrmCustomerManagement entity) {
        String id = crmCustomerManagementService.save(entity).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody CrmCustomerManagement entity) {
        int rows = crmCustomerManagementService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<CrmCustomerManagement> entitys) {
        List<String> ids = crmCustomerManagementService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<CrmCustomerManagement> entitys) {
        List<String> ids = crmCustomerManagementService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<CrmCustomerManagement> entitys) {
        int rows = crmCustomerManagementService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }
}
