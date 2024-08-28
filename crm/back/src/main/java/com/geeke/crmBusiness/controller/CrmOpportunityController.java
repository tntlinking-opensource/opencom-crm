package com.geeke.crmBusiness.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.admin.entity.Role;
import com.geeke.admin.entity.User;
import com.geeke.admin.entity.UserRole;
import com.geeke.admin.service.UserService;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.common.data.Parameter;
import com.geeke.crmBusiness.entity.CrmOpportunity;
import com.geeke.crmBusiness.service.CrmOpportunityService;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.SessionUtils;
import com.geeke.utils.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商机管理Controller
 * @author
 * @version
 */
@RestController
@RequestMapping(value = "/crmBusiness/crmOpportunity")
public class CrmOpportunityController extends BaseController {

    @Autowired
    private CrmOpportunityService crmOpportunityService;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        CrmOpportunity entity = crmOpportunityService.get(id);

        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }

    @PostMapping(value = { "list", "" })
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        List<Parameter> params1 = searchParams.getParams();
        params1.add(new Parameter("opp_responsible","=",SessionUtils.getUser().getId()));
        User user = userService.get(SessionUtils.getUser().getId());
        List<UserRole> userRoleList = user.getUserRoleList();
        Boolean manage = false;
        Boolean CRM = false;
        //这里判断登录人是否有职位
        for (UserRole userRole : userRoleList) {
            Role role = userRole.getRole();
            if (role.getCode().equals("manage")){
                manage=true;
            };
            if (role.getCode().equals("CRM")){
                CRM=true;
            };
        }
        if (!CRM){
            if (manage){
                params1.add(new Parameter("a.opp_respondepartId", "=", SessionUtils.getUser().getDepartment().getId()));
            }else {
                params1.remove(new Parameter("opp_responsible","=",SessionUtils.getUser().getId()));
                params1.remove(new Parameter("a.opp_respondepartId", "=", SessionUtils.getUser().getDepartment().getId()));
            }
        }
        Page<CrmOpportunity> result = crmOpportunityService.listPage(
            searchParams.getParams(),
            searchParams.getOffset(),
            searchParams.getLimit(),
            searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<CrmOpportunity> result = crmOpportunityService.listAll(searchParams.getParams(), searchParams.getOrderby());

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestBody CrmOpportunity entity) {
        String id = crmOpportunityService.save(entity).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody CrmOpportunity entity) {
        int rows = crmOpportunityService.deleteAll(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<CrmOpportunity> entitys) {
        List<String> ids = crmOpportunityService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<CrmOpportunity> entitys) {
        List<String> ids = crmOpportunityService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<CrmOpportunity> entitys) {
        int rows = crmOpportunityService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }


    @PostMapping("sendPayQuotation")
    public ResponseEntity<JSONObject> sendPayQuotation(@RequestParam("payInfo") String payInfo,
                                                       @RequestParam("files") MultipartFile[] attachments,
                                                       @RequestParam("deleteIds") String strDeleteIds) {
        String payQuotation = crmOpportunityService.sendPayQuotation(payInfo, attachments, strDeleteIds);
        return ResponseEntity.ok(ResultUtil.successJson(payQuotation));
    }
}
