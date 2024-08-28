package com.geeke.myClue.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.common.data.PageRequest;
import com.geeke.common.data.Parameter;
import com.geeke.myClue.dao.CrmCluesDao;
import com.geeke.myClue.entity.MyCrmCluePool;
import com.geeke.myClue.service.CrmCluesService;
import com.geeke.myClue.service.MyCrmCluePoolService;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import java.util.List;

import com.google.common.collect.Lists;
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
 * 我的线索Controller
 * @author
 * @version
 */
@RestController
@RequestMapping(value = "/myClue/myCrmCluePool")
public class MyCrmCluePoolController extends BaseController {

    @Autowired
    private MyCrmCluePoolService myCrmCluePoolService;
    @Autowired
    private CrmCluesService crmCluesService;
    @Autowired
    private CrmCluesDao crmCluesDao;

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        MyCrmCluePool entity = myCrmCluePoolService.get(id);

        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }

    @PostMapping(value = { "list", "" })
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<MyCrmCluePool> result = myCrmCluePoolService.listPage(
            searchParams.getParams(),
            searchParams.getOffset(),
            searchParams.getLimit(),
            searchParams.getOrderby()
        );
        if (result.getTotal()!=0){
            List<MyCrmCluePool> rows = result.getRows();
            for (MyCrmCluePool row : rows) {
                List<Parameter> params = null;
                /*获取子表列表   线索池管理*/
                params = Lists.newArrayList();
                params.add(new Parameter("clues_pool_id", "=", row.getId()));
                PageRequest pageRequest = new PageRequest(1, 1, params, "");
                int count = crmCluesDao.count(pageRequest);
                row.setRemarks(Integer.toString(count));
            }
        }
        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<MyCrmCluePool> result = myCrmCluePoolService.listAll(searchParams.getParams(), searchParams.getOrderby());

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestBody MyCrmCluePool entity) {
        String id = myCrmCluePoolService.save(entity).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody MyCrmCluePool entity) {
        int rows = myCrmCluePoolService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<MyCrmCluePool> entitys) {
        List<String> ids = myCrmCluePoolService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<MyCrmCluePool> entitys) {
        List<String> ids = myCrmCluePoolService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<MyCrmCluePool> entitys) {
        int rows = myCrmCluePoolService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }
}
