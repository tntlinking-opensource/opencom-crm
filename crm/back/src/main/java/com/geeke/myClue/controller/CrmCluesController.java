package com.geeke.myClue.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.admin.entity.Role;
import com.geeke.admin.entity.User;
import com.geeke.admin.entity.UserRole;
import com.geeke.admin.service.UserService;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.common.data.Parameter;
import com.geeke.myClue.entity.CrmClues;
import com.geeke.myClue.entity.MyClueCrmCommunicate;
import com.geeke.myClue.service.CrmCluesService;
import com.geeke.myClue.service.MyClueCrmCommunicateService;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.SessionUtils;
import com.geeke.utils.StringUtils;
import java.util.List;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * 我的线索Controller
 * @author
 * @version
 */
@RestController
@RequestMapping(value = "/myClue/crmClues")
public class CrmCluesController extends BaseController {

    @Autowired
    private CrmCluesService crmCluesService;
    @Autowired
    private MyClueCrmCommunicateService myClueCrmCommunicateService;
    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        CrmClues entity = crmCluesService.get(id);
        List<Parameter> params = null;

        /*获取子表列表   线索池管理*/
        params = Lists.newArrayList();
        params.add(new Parameter("cnt_myclue_id", "=", entity.getId()));
        Page<MyClueCrmCommunicate> update_date = myClueCrmCommunicateService.listPage(params, 0, 1, "");
        if (update_date.getTotal()!=0){
            entity.setName(update_date.getRows().get(0).getCntDate());
        }else {
            entity.setName("");
        }
        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }

    @PostMapping(value = { "list", "" })
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
//        List<Parameter> params1 = searchParams.getParams();
//        params1.add(new Parameter("clues_responsible", "=", SessionUtils.getUser().getId()));
        List<Parameter> params1 = searchParams.getParams();
        Boolean authority=true;
        for (Parameter parameter : params1) {
            if (parameter.getColumnName().equals("clues_responsible")){
                authority=false;
            }
        }
        if (authority){
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
                    params1.add(new Parameter("su1.department_id", "=", SessionUtils.getUser().getDepartment().getId()));
                }else {
                    params1.add(new Parameter("", "(", "","AND"));
                    params1.add(new Parameter("", "(", "",""));
                    params1.add(new Parameter("cluesPool.cpl_member_claim_rules", "=","1",""));
                    params1.add(new Parameter("ccpm.member_id", "=",SessionUtils.getUser().getId()));
                    params1.add(new Parameter("", ")", "",""));
                    params1.add(new Parameter("", "(", "","OR"));
                    params1.add(new Parameter("cluesPool.cpl_member_claim_rules", "=","2",""));
                    params1.add(new Parameter("cluesPool.cpl_responsible", "=",SessionUtils.getUser().getId()));
                    params1.add(new Parameter("", ")", "",""));
                    params1.add(new Parameter("", ")", "",""));
                }
            }
        }
        Page<CrmClues> result = crmCluesService.listPage(
            searchParams.getParams(),
            searchParams.getOffset(),
            searchParams.getLimit(),
            searchParams.getOrderby()
        );
        List<CrmClues> rows = result.getRows();
        if (result.getTotal()==0){
            return ResponseEntity.ok(ResultUtil.successJson(result));
        }
        for (CrmClues row : rows) {
            List<Parameter> params = null;

            /*获取子表列表   线索池管理*/
            params = Lists.newArrayList();
            params.add(new Parameter("cnt_myclue_id", "=", row.getId()));
            Page<MyClueCrmCommunicate> update_date = myClueCrmCommunicateService.listPage(params, 0, 1, "");
            if (update_date.getTotal()!=0){
                row.setRemarks(update_date.getRows().get(0).getCutNextDate());
            }else {
                row.setRemarks("");
            }
        }
        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<CrmClues> result = crmCluesService.listAll(searchParams.getParams(), searchParams.getOrderby());

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestBody CrmClues entity) {
        String id = crmCluesService.save(entity).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody CrmClues entity) {
        int rows = crmCluesService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }
    @PostMapping(value = "physicsDelete")
    public ResponseEntity<JSONObject> physicsDelete(@RequestBody List<CrmClues> entitys) {
        int rows = crmCluesService.physicsDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<CrmClues> entitys) {
        List<String> ids = crmCluesService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<CrmClues> entitys) {
        List<String> ids = crmCluesService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<CrmClues> entitys) {
        int rows = crmCluesService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }
    @PostMapping(value = "importExport")
    public ResponseEntity<JSONObject> importExport(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        int rows = crmCluesService.importExport(file.getInputStream());
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }
    @PostMapping(value = "importExport1")
    public ResponseEntity<JSONObject> importExport1(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        int rows = crmCluesService.importExport1(file.getInputStream());
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }
}
