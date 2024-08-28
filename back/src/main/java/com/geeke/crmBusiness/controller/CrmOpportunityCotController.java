package com.geeke.crmBusiness.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.contract.entity.CrmContract;
import com.geeke.contract.service.CrmContractService;
import com.geeke.crmBusiness.entity.CrmOpportunityCot;
import com.geeke.crmBusiness.service.CrmOpportunityCotService;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.ResultUtil;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商机管理-合同Controller
 * @author
 * @version
 */
@RestController
@RequestMapping(value = "/crmBusiness/crmOpportunityCot")
public class CrmOpportunityCotController extends BaseController {

    @Autowired
    private CrmOpportunityCotService crmOpportunityCotService;
    @Autowired
    private CrmContractService crmContractService;


    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        CrmOpportunityCot entity = crmOpportunityCotService.get(id);

        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }

    @PostMapping(value = { "list", "" })
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<CrmOpportunityCot> result = crmOpportunityCotService.listPage(
            searchParams.getParams(),
            searchParams.getOffset(),
            searchParams.getLimit(),
            searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<CrmOpportunityCot> result = crmOpportunityCotService.listAll(searchParams.getParams(), searchParams.getOrderby());

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestBody CrmOpportunityCot entity) {
        String id = crmOpportunityCotService.save(entity).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }

    @PostMapping(value = "saveContract")
    public ResponseEntity<JSONObject> saveContract(CrmContract crmContract, @RequestParam("files")MultipartFile[] attachments,
                                                   @RequestParam(value = "deleteIds", required = false) String strDeleteIds) {
        String[] deleteIds = JSONObject.parseObject(strDeleteIds, String[].class);
        crmOpportunityCotService.saveContractWithAttachment(crmContract, attachments,deleteIds);
        return ResponseEntity.ok(ResultUtil.successJson());
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody CrmOpportunityCot entity) {
        int rows = crmOpportunityCotService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<CrmOpportunityCot> entitys) {
        List<String> ids = crmOpportunityCotService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<CrmOpportunityCot> entitys) {
        List<String> ids = crmOpportunityCotService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<CrmOpportunityCot> entitys) {
        int rows = crmOpportunityCotService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }
}
