package com.geeke.crmCustomer.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.admin.entity.User;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.crmCustomer.entity.CrmCustomer;
import com.geeke.crmCustomer.entity.CrmCustomerResp;
import com.geeke.crmCustomer.service.CrmCustomerService;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.SessionUtils;
import com.geeke.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 客户管理Controller
 * @author
 * @version
 */
@RestController
@RequestMapping(value = "/crmCustomer/crmCustomer")
public class CrmCustomerController extends BaseController {

    @Autowired
    private CrmCustomerService crmCustomerService;

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        CrmCustomer entity = crmCustomerService.get(id);
        CrmCustomerResp crmCustomerResp = crmCustomerService.queryCus(entity);
        return ResponseEntity.ok(ResultUtil.successJson(crmCustomerResp));
    }

    @PostMapping(value = { "list", "" })
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<CrmCustomer> result = crmCustomerService.listPage(
            searchParams.getParams(),
            searchParams.getOffset(),
            searchParams.getLimit(),
            searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<CrmCustomer> result = crmCustomerService.listAll(searchParams.getParams(), searchParams.getOrderby());

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "tree")
    public ResponseEntity<JSONObject> tree(@RequestBody SearchParams searchParams) {
        Page<CrmCustomer> crmCustomerPage = crmCustomerService.treePage(searchParams.getParams(), searchParams.getOffset(), searchParams.getLimit(), searchParams.getOrderby());
        return ResponseEntity.ok(ResultUtil.successJson(crmCustomerPage));
    }

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestBody CrmCustomer entity) {
        //如果新增客户时，负责人ID(当前登录人ID)为空，则获取后放入对象
        if(StringUtils.isNullOrEmpty(entity.getCusResponsible())){
            String cusResponsible = SessionUtils.getUser().getId();
            entity.setCusResponsible(cusResponsible);
        }
        String id = crmCustomerService.save(entity).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody CrmCustomer entity) {
        int rows = crmCustomerService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<CrmCustomer> entitys) {
        List<String> ids = crmCustomerService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<CrmCustomer> entitys) {
        List<String> ids = crmCustomerService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<CrmCustomer> entitys) {
        int rows = crmCustomerService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping("sendPayQuotation")
    public ResponseEntity<JSONObject> sendPayQuotation(@RequestParam("payInfo") String payInfo,
                                                       @RequestParam("files") MultipartFile[] attachments,
                                                       @RequestParam("deleteIds") String strDeleteIds) {
        String payQuotation = crmCustomerService.sendPayQuotation(payInfo, attachments, strDeleteIds);
        return ResponseEntity.ok(ResultUtil.successJson(payQuotation));
    }
}
