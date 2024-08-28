package com.geeke.orders.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.crmCluePool.entity.CrmUserls;
import com.geeke.orders.entity.CrmCopCp;
import com.geeke.orders.entity.CrmOrders;
import com.geeke.orders.service.CrmOrdersService;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 订单管理Controller
 * @author
 * @version
 */
@RestController
@RequestMapping(value = "/orders/crmOrders")
public class CrmOrdersController extends BaseController {

    @Autowired
    private CrmOrdersService crmOrdersService;

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        CrmOrders entity = crmOrdersService.get(id);

        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }

    @PostMapping(value = { "list", "" })
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<CrmOrders> result = crmOrdersService.listPage(
            searchParams.getParams(),
            searchParams.getOffset(),
            searchParams.getLimit(),
            searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<CrmOrders> result = crmOrdersService.listAll(searchParams.getParams(), searchParams.getOrderby());

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(
        @RequestParam(value = "entity", required = false) String strEntity,
        @RequestParam(value = "wbnumberUploads", required = false) MultipartFile[] wbnumberUploads, // 文件: 附件
        @RequestParam(value = "deleteIds", required = false) String strDeleteIds
    ) throws java.io.IOException {
        CrmOrders entity = JSONObject.parseObject(strEntity, CrmOrders.class);
        String[] deleteIds = JSONObject.parseObject(strDeleteIds, String[].class);
        String id = crmOrdersService.save(entity, wbnumberUploads, deleteIds).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody CrmOrders entity) {
        int rows = crmOrdersService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<CrmOrders> entitys) {
        List<String> ids = crmOrdersService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<CrmOrders> entitys) {
        List<String> ids = crmOrdersService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<CrmOrders> entitys) {
        int rows = crmOrdersService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @GetMapping("/getseleddcp")
    public Map<String, Object> getseleddcp(String bsoyName, Integer pageNum, Integer pageSize) {
        pageNum = (pageNum-1)*pageSize;
        int countuser = crmOrdersService.getseleddcpcount(bsoyName, pageNum, pageSize);
        List<CrmCopCp> crmUserls = crmOrdersService.getseleddcp(bsoyName, pageNum, pageSize);
        Map<String,Object> res = new HashMap<>();
        res.put("data",crmUserls);
        res.put("total",countuser);
        return res;
    }
}
