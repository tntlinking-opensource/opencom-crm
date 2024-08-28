package com.geeke.crmCluePool.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.common.data.Parameter;
import com.geeke.crmCluePool.dao.CrmCluePoolDao;
import com.geeke.crmCluePool.entity.CrmCluePool;
import com.geeke.crmCluePool.entity.CrmUserls;
import com.geeke.crmCluePool.service.CrmCluePoolService;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.IdGen;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 线索池管理Controller
 * @author
 * @version
 */
@RestController
@RequestMapping(value = "/crmCluePool/crmCluePool")
public class CrmCluePoolController extends BaseController {

    @Autowired
    private CrmCluePoolService crmCluePoolService;

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        CrmCluePool entity = crmCluePoolService.get(id);

        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }

    @PostMapping(value = { "list", "" })
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<CrmCluePool> result = crmCluePoolService.listPage(
            searchParams.getParams(),
            searchParams.getOffset(),
            searchParams.getLimit(),
            searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<CrmCluePool> result = crmCluePoolService.listAll(searchParams.getParams(), searchParams.getOrderby());

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestBody CrmCluePool entity) {
        String id = crmCluePoolService.save(entity).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody CrmCluePool entity) {
        int rows = crmCluePoolService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<CrmCluePool> entitys) {
        List<String> ids = crmCluePoolService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<CrmCluePool> entitys) {
        List<String> ids = crmCluePoolService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<CrmCluePool> entitys) {
        int rows = crmCluePoolService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @GetMapping("/getseleuserls")
    public Map<String, Object> getuserlists(String userofjober, String userofname, Integer pageNum, Integer pageSize) {
        pageNum = (pageNum-1)*pageSize;
        int countuser = crmCluePoolService.gettest(userofjober,userofname, pageNum, pageSize);
        List<CrmUserls> crmUserls = crmCluePoolService.getuserlists(userofjober,userofname, pageNum, pageSize);
        Map<String,Object> res = new HashMap<>();
        res.put("data",crmUserls);
        res.put("total",countuser);
        return res;
    }

    @PostMapping(value = "saveccpl")
    public ResponseEntity<JSONObject> saveccpl(
                                               String id,
                                               String cplName,
                                               String cplResponsible,
                                               String ccpms,
                                               String cplMemberClaimRules,
                                               String cplMemberClaimLimit,
                                               String AnnualAssessmentData,
                                               String cplRecycleDay,
                                               String cplRecycleStatus
    ) {
        try{
            int rescount1 = 0;
            String loginName = SecurityUtils.getSubject().getPrincipal().toString();
            if(id.equals("null")){
                CrmCluePool crmCluePool = new CrmCluePool();
                String qjid = IdGen.uuid();
                crmCluePool.setId(qjid);
                crmCluePool.setCplName(cplName);
                crmCluePool.setCplResponsible(cplResponsible);
                crmCluePool.setCplMemberClaimRules(cplMemberClaimRules);
                crmCluePool.setCplMemberClaimLimit(Integer.valueOf(cplMemberClaimLimit));
                crmCluePool.setCplAutoWhere1(AnnualAssessmentData);
                crmCluePool.setCplRecycleDay(Integer.valueOf(cplRecycleDay));
                crmCluePool.setCplRecycleStatus(cplRecycleStatus);
                crmCluePool.setCreateBy(loginName);
                rescount1 = crmCluePoolService.saveccpl(crmCluePool);
                String[] ccpmss = ccpms.split(",");
                for (String element : ccpmss) {
                    System.out.println(element.split("\\|")[0]);
                    crmCluePoolService.saveccpms(qjid,IdGen.uuid(),element.split("\\|")[0],loginName);
                }
            }else{
                CrmCluePool crmCluePool = new CrmCluePool();

                crmCluePool.setId(id);
                crmCluePool.setCplName(cplName);
                crmCluePool.setCplResponsible(cplResponsible);
                crmCluePool.setCplMemberClaimRules(cplMemberClaimRules);
                crmCluePool.setCplMemberClaimLimit(Integer.valueOf(cplMemberClaimLimit));
                crmCluePool.setCplAutoWhere1(AnnualAssessmentData);
                crmCluePool.setCplRecycleDay(Integer.valueOf(cplRecycleDay));
                crmCluePool.setCplRecycleStatus(cplRecycleStatus);
                crmCluePool.setCreateBy(loginName);
                rescount1 = crmCluePoolService.upccpl(crmCluePool);
                int rescount2 = crmCluePoolService.delbycplid(id);
                String[] ccpmss = ccpms.split(",");
                for (String element : ccpmss) {
                    System.out.println(element.split("\\|")[0]);
                    crmCluePoolService.saveccpms(id,IdGen.uuid(),element.split("\\|")[0],loginName);
                }
            }
            if(rescount1 > 0){
                return ResponseEntity.ok(ResultUtil.successJson(loginName));
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }catch (Exception e){
            System.out.println("error"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @PostMapping(value = "mergecpl")
    public ResponseEntity<JSONObject> mergecpl(String id,
                                               String cluesName,
                                               String cplResponsible,
                                               String member,
                                               String cplMemberClaimLimit
    ) {
        try{
            String loginName = SecurityUtils.getSubject().getPrincipal().toString();
            CrmCluePool crmCluePool = new CrmCluePool();
            String qjid = IdGen.uuid();
            crmCluePool.setId(qjid);
            crmCluePool.setCplName(cluesName);
            crmCluePool.setCplResponsible(cplResponsible);
            crmCluePool.setCplMemberClaimRules(member);
            crmCluePool.setCplMemberClaimLimit(Integer.valueOf(cplMemberClaimLimit));
            crmCluePool.setCplRecycleDay(0);
            crmCluePool.setCplRecycleStatus("");
            crmCluePool.setCreateBy(loginName);
            crmCluePool.setCreateBy(loginName);
            int rescount1 = crmCluePoolService.saveccpl(crmCluePool);
            String[] a=id.split(",");
            List<String> brandIdlLst = Arrays.asList(a);
           crmCluePoolService.delecpls(brandIdlLst);
            crmCluePoolService.updatecplmids(brandIdlLst,qjid);
            System.out.println("此处全局sql"+qjid);
            crmCluePoolService.delecf(qjid);
            if(rescount1 > 0){
                return ResponseEntity.ok(ResultUtil.successJson(loginName));
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }catch (Exception e){
            System.out.println("error"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
