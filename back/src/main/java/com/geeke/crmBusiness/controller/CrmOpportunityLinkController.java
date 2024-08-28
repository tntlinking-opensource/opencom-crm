package com.geeke.crmBusiness.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.contacts.entity.CrmContacts;
import com.geeke.crmBusiness.entity.CrmOpportunityLink;
import com.geeke.crmBusiness.service.CrmOpportunityLinkService;
import com.geeke.noticesend.utils.SendMail;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商机明细-联系人Controller
 * @author
 * @version
 */
@RestController
@RequestMapping(value = "/crmBusiness/crmOpportunityLink")
@PropertySource({"classpath:mail-config.properties"})
public class CrmOpportunityLinkController extends BaseController {

    @Autowired
    private CrmOpportunityLinkService crmOpportunityLinkService;

    @Value("${mail.user}")
    private String sendEmail;

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        CrmOpportunityLink entity = crmOpportunityLinkService.get(id);

        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }

    @PostMapping(value = { "list", "" })
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<CrmOpportunityLink> result = crmOpportunityLinkService.listPage(
            searchParams.getParams(),
            searchParams.getOffset(),
            searchParams.getLimit(),
            searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<CrmOpportunityLink> result = crmOpportunityLinkService.listAll(
            searchParams.getParams(),
            searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestBody CrmOpportunityLink entity) {
        String id = crmOpportunityLinkService.save(entity).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }

    @PostMapping(value = "saveCrmContacts")
    public ResponseEntity<JSONObject> saveCrmContacts(@RequestBody CrmContacts entity) {
        String id = crmOpportunityLinkService.saveCrmContacts(entity).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody CrmContacts entity) {
        int rows = crmOpportunityLinkService.deleteCrmContacts(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<CrmOpportunityLink> entitys) {
        List<String> ids = crmOpportunityLinkService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<CrmOpportunityLink> entitys) {
        List<String> ids = crmOpportunityLinkService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<CrmOpportunityLink> entitys) {
        int rows = crmOpportunityLinkService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping("/getSendEmail")
    public ResponseEntity<JSONObject> getSendEmail(){

        return ResponseEntity.ok(ResultUtil.successJson(sendEmail));

    }
}
