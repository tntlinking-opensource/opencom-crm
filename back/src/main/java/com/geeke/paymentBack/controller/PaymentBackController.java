package com.geeke.paymentBack.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.paymentBack.entity.PaymentBack;
import com.geeke.paymentBack.entity.QueryCondition;
import com.geeke.paymentBack.service.PaymentBackService;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.ResultUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 回款信息Controller
 * @author
 * @version
 */
@RestController
@RequestMapping(value = "/paymentBack")
public class PaymentBackController extends BaseController {

    @Autowired
    private PaymentBackService paymentBackService;

    @GetMapping("/{contractId}")
    public ResponseEntity<JSONObject> getByContractId(@PathVariable("contractId") String contractId) {
        PaymentBack entity = paymentBackService.getByContractId(contractId);
        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }

    @PostMapping(value = { "list", "" })
    public ResponseEntity<JSONObject> listPage(@RequestBody QueryCondition queryCondition) {
        Page<PaymentBack> result = paymentBackService.listPage(queryCondition);
        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<PaymentBack> result = paymentBackService.listAll(searchParams.getParams(), searchParams.getOrderby());

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestBody PaymentBack entity) {
        String id = paymentBackService.save(entity).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody PaymentBack entity) {
        int rows = paymentBackService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<PaymentBack> entitys) {
        List<String> ids = paymentBackService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<PaymentBack> entitys) {
        List<String> ids = paymentBackService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<PaymentBack> entitys) {
        int rows = paymentBackService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }
}
