package com.geeke.myClue.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.admin.entity.Router;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.myClue.entity.MyClueCrmCommunicate;
import com.geeke.myClue.service.MyClueCrmCommunicateService;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 我的线索Controller
 * @author
 * @version
 */
@RestController
@RequestMapping(value = "/myClue/myClueCrmCommunicate")
public class MyClueCrmCommunicateController extends BaseController {

    @Autowired
    private MyClueCrmCommunicateService myClueCrmCommunicateService;

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        MyClueCrmCommunicate entity = myClueCrmCommunicateService.get(id);

        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }

    @PostMapping(value = { "list", "" })
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<MyClueCrmCommunicate> result = myClueCrmCommunicateService.listPage(
                searchParams.getParams(),
                searchParams.getOffset(),
                searchParams.getLimit(),
                searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<MyClueCrmCommunicate> result = myClueCrmCommunicateService.listAll(
                searchParams.getParams(),
                searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestParam(value = "entity", required = false) String strEntity,
                                           @RequestParam(value = "Uploads", required = false) MultipartFile[] Uploads, // 文件: 图片地址
                                           @RequestParam(value = "deleteIds", required = false) String strDeleteIds) throws IOException {
        MyClueCrmCommunicate entity = JSONObject.parseObject(strEntity, MyClueCrmCommunicate.class);
        String[] deleteIds = JSONObject.parseObject(strDeleteIds, String[].class);
        String id = myClueCrmCommunicateService.save(entity, Uploads, deleteIds).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody MyClueCrmCommunicate entity) {
        int rows = myClueCrmCommunicateService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<MyClueCrmCommunicate> entitys) {
        List<String> ids = myClueCrmCommunicateService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<MyClueCrmCommunicate> entitys) {
        List<String> ids = myClueCrmCommunicateService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<MyClueCrmCommunicate> entitys) {
        int rows = myClueCrmCommunicateService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }
}
