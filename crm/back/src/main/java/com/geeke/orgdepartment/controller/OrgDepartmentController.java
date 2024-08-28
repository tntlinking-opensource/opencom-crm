package com.geeke.orgdepartment.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.orgdepartment.entity.OrgDepartment;
import com.geeke.orgdepartment.service.OrgDepartmentService;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门查询Controller
 * @author
 * @version
 */
@RestController
@RequestMapping(value = "/orgdepartment/orgDepartment")
public class OrgDepartmentController extends BaseController {

    @Autowired
    private OrgDepartmentService orgDepartmentService;

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        OrgDepartment entity = orgDepartmentService.get(id);

        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }

    @GetMapping("/getdeparbycompany")
    public ResponseEntity<JSONObject> getBycompany() {
        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println("当前用户"+loginName);
        List<OrgDepartment> result =  orgDepartmentService.getBycompany(loginName);

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = { "list", "" })
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<OrgDepartment> result = orgDepartmentService.listPage(
            searchParams.getParams(),
            searchParams.getOffset(),
            searchParams.getLimit(),
            searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<OrgDepartment> result = orgDepartmentService.listAll(searchParams.getParams(), searchParams.getOrderby());

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestBody OrgDepartment entity) {
        String id = orgDepartmentService.save(entity).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody OrgDepartment entity) {
        int rows = orgDepartmentService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<OrgDepartment> entitys) {
        List<String> ids = orgDepartmentService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<OrgDepartment> entitys) {
        List<String> ids = orgDepartmentService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<OrgDepartment> entitys) {
        int rows = orgDepartmentService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }
}
