package com.geeke.crmBusiness.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.crmBusiness.entity.CrmOpportunityPay;
import com.geeke.crmBusiness.service.CrmOpportunityPayService;
import com.geeke.paymentBack.entity.PaymentBack;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商机明细-回款Controller
 * @author
 * @version
 */
@RestController
@RequestMapping(value = "/contract/crmOpportunityPay")
public class CrmOpportunityPayController extends BaseController {

    @Autowired
    private CrmOpportunityPayService crmOpportunityPayService;

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        PaymentBack entity = crmOpportunityPayService.getPayInfo(id);

        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }

    @PostMapping(value = { "list", "" })
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<CrmOpportunityPay> result = crmOpportunityPayService.listPage(
            searchParams.getParams(),
            searchParams.getOffset(),
            searchParams.getLimit(),
            searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<CrmOpportunityPay> result = crmOpportunityPayService.listAll(searchParams.getParams(), searchParams.getOrderby());

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestBody CrmOpportunityPay entity) {
        String id = crmOpportunityPayService.save(entity).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody CrmOpportunityPay entity) {
        int rows = crmOpportunityPayService.deletePay(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<CrmOpportunityPay> entitys) {
        List<String> ids = crmOpportunityPayService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<CrmOpportunityPay> entitys) {
        List<String> ids = crmOpportunityPayService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<CrmOpportunityPay> entitys) {
        int rows = crmOpportunityPayService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping("savePayBacks")
    public ResponseEntity<JSONObject> savePayBacks(@RequestBody List<PaymentBack> entitys) {
        return ResponseEntity.ok(ResultUtil.successJson(crmOpportunityPayService.savePayBacks(entitys)));
    }
}
