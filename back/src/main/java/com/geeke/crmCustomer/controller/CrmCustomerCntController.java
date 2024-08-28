package com.geeke.crmCustomer.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.crmCommunicate.entity.CrmCommunicate;
import com.geeke.crmCommunicate.service.CrmCommunicateService;
import com.geeke.crmCustomer.entity.CrmCustomerCnt;
import com.geeke.crmCustomer.entity.CrmCustomerCntInsert;
import com.geeke.crmCustomer.entity.CrmCustomerCntLine;
import com.geeke.crmCustomer.service.CrmCustomerCntService;
import com.geeke.myClue.entity.MyClueCrmCommunicate;
import com.geeke.myClue.service.MyClueCrmCommunicateService;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.RemoteResultResolver;
import com.geeke.utils.ResultUtil;
import com.newtouch.cloud.common.dependency.remote.rest.DynamicRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 客户管理Controller
 *
 * @author
 */
@RestController
@RequestMapping(value = "/crmCustomer/crmCustomerCnt")
public class CrmCustomerCntController extends BaseController {

    @Autowired
    private CrmCustomerCntService crmCustomerCntService;

    @Autowired
    private CrmCommunicateService crmCommunicateService;

    @Autowired
    private DynamicRestTemplate restTemplate;

    @Autowired
    private MyClueCrmCommunicateService myClueCrmCommunicateService;


    @PostMapping(value = {"list", ""})
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<CrmCustomerCntLine> result = crmCustomerCntService.crmCustomerCntLinelistPage(
                searchParams.getParams(),
                searchParams.getOffset(),
                searchParams.getLimit(),
                searchParams.getOrderby()
        );
        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<CrmCustomerCnt> result = crmCustomerCntService.listAll(searchParams.getParams(), searchParams.getOrderby());

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "cntListAll")
    public ResponseEntity<JSONObject> cntListAll(@RequestBody SearchParams searchParams) {
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
    public ResponseEntity<JSONObject> save(@RequestBody CrmCustomerCntInsert entity) {
        CrmCommunicate crmCommunicate = new CrmCommunicate();
        crmCommunicate.setId(entity.getCntId());
        crmCommunicate.setCntContent(entity.getCntContent());
        crmCommunicate.setCntDate(entity.getCntDate());
        crmCommunicate.setCntPname(entity.getCntPname());
        crmCommunicate.setCntTypeId(entity.getCntType());
        crmCommunicate.setUpdateDate(entity.getCntUpdateDate());
        String cntId = crmCommunicateService.save(crmCommunicate).getId();
        CrmCustomerCnt crmCustomerCnt = new CrmCustomerCnt();
        crmCustomerCnt.setCusId(entity.getCusId());
        crmCustomerCnt.setCntId(cntId);
        if (entity.getId() == null) {
            String id = crmCustomerCntService.save(crmCustomerCnt).getId();
            return ResponseEntity.ok(ResultUtil.successJson(id));
        }else {
            return ResponseEntity.ok(ResultUtil.successJson(entity.getId()));
        }
    }

    /**
     * 保存客户的沟通记录
     * @param strEntity
     * @param Uploads
     * @param strDeleteIds
     * @return
     * @throws IOException
     */
    @PostMapping(value = "saveCusWithCom")
    public ResponseEntity<JSONObject> saveCusWithCom(@RequestParam(value = "entity", required = false) String strEntity,
                                           @RequestParam(value = "Uploads", required = false) MultipartFile[] Uploads, // 文件: 图片地址
                                           @RequestParam(value = "deleteIds", required = false) String strDeleteIds) throws IOException {
        MyClueCrmCommunicate entity = JSONObject.parseObject(strEntity, MyClueCrmCommunicate.class);
        JSONObject json = JSONObject.parseObject(strEntity);
        String[] deleteIds = JSONObject.parseObject(strDeleteIds, String[].class);
        String id = myClueCrmCommunicateService.saveCusWithCom(entity, json,Uploads, deleteIds).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody CrmCustomerCnt entity) {
//        entity.setUpdateDate(entity.getUpdateDate());
        int rows = crmCustomerCntService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

//    @PostMapping(value = "deleteById")
//    public ResponseEntity<JSONObject> delete(String id) {
//        CrmCustomerCnt crmCustomerCnt = new CrmCustomerCnt();
//        crmCustomerCnt.setId(id);
//        int rows = crmCustomerCntService.delete(crmCustomerCnt);
//        return ResponseEntity.ok(ResultUtil.successJson(rows));
//    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<CrmCustomerCnt> entitys) {
        List<String> ids = crmCustomerCntService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<CrmCustomerCnt> entitys) {
        List<String> ids = crmCustomerCntService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<CrmCustomerCnt> entitys) {
        int rows = crmCustomerCntService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(ResultUtil.successJson(crmCustomerCntService.getCustomerCntLineByCrmCustomerCntId(id)));
    }

}
