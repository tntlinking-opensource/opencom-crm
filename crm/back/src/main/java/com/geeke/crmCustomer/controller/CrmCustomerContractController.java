package com.geeke.crmCustomer.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.camundaex.entity.ActResult;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.contract.entity.CrmContract;
import com.geeke.contract.entity.CrmContractPay;
import com.geeke.crmBusiness.entity.CrmOpportunity;
import com.geeke.crmBusiness.service.CrmBusunessContractService;
import com.geeke.crmBusiness.service.CrmOpportunityService;
import com.geeke.crmCustomer.service.CrmCustomerContractService;
import com.geeke.crmCustomer.service.CrmCustomerService;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.SessionUtils;
import com.geeke.wf.entity.WfDraft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 合同管理Controller
 * @author
 * @version
 */
@RestController
@RequestMapping(value = "/crmCustomer/crmContract")
public class CrmCustomerContractController extends BaseController {

    @Autowired
    private CrmCustomerContractService crmContractService;
    @Autowired
    private CrmCustomerService crmCustomerService;

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        CrmContract entity = crmContractService.get(id);
        crmContractService.entityToDto(entity, true);
        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }

    @GetMapping("/save")
    public ResponseEntity<JSONObject> save(CrmContract crmContract, MultipartFile[] attachments) {
        crmContractService.saveContractWithAttachment(crmContract, attachments);
        return ResponseEntity.ok(ResultUtil.successJson());
    }

    @PostMapping(value = { "list", "" })
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<CrmContract> result = crmContractService.listPage(
            searchParams.getParams(),
            searchParams.getOffset(),
            searchParams.getLimit(),
            searchParams.getOrderby()
        );
        crmContractService.entityListToDtoList(result.getRows(), false);
        Page<CrmContract> resultData = new Page<>(result.getTotal(), result.getRows());
        return ResponseEntity.ok(ResultUtil.successJson(resultData));
    }


    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<CrmContract> result = crmContractService.listAll(searchParams.getParams(), searchParams.getOrderby());

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody CrmContract entity) {
        int rows = crmContractService.deleteContract(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    /**
     * 通过流程任务Id获取业务对象
     * @param taskId
     * @return
     */
    @GetMapping("/task/{taskId}")
    public ResponseEntity<JSONObject> getByTaskId(@PathVariable("taskId") String taskId) {
        CrmContract entity = crmContractService.getByTaskId(taskId);
        List<CrmContractPay> payList = crmContractService.getPayList(entity.getId());
        entity.setPayList(payList);
        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }

    /**
     * 保存草稿
     * @param strDraft 草稿对象
     * @param strEntity    业务对象
     * @param attachments 审批附件
     * @return
     */
    @PostMapping(value = "/saveDraft")
    public ResponseEntity<JSONObject> saveDraft(
        @RequestParam(value = "strDraft", required = false) String strDraft,
        @RequestParam(value = "strEntity", required = false) String strEntity,
        @RequestParam(value = "attachments", required = false) MultipartFile[] attachments
    ) throws java.io.IOException {
        CrmContract entity = JSONObject.parseObject(strEntity, CrmContract.class);
        WfDraft wfDraft = JSONObject.parseObject(strDraft, WfDraft.class);
        int ret = crmContractService.saveDraft(wfDraft, entity, attachments);
        return ResponseEntity.ok(ResultUtil.successSaveDraft(ret));
    }

    /**
     * 发起申请
     * @param procDefId 流程定义Id
     * @param strEntity    业务对象
     * @param attachments 审批附件
     * @return
     */
    @PostMapping(value = "/{procDefId}/create")
    public ResponseEntity<JSONObject> create(
        @PathVariable("procDefId") String procDefId,
        @RequestParam(value = "strEntity", required = false) String strEntity,
        @RequestParam(value = "attachments", required = false) MultipartFile[] attachments
    ) throws java.io.IOException {
        CrmContract entity = JSONObject.parseObject(strEntity, CrmContract.class);
        /*if (StringUtils.isEmpty(entity.getId())) {
            crmContractService.saveContractWithAttachment(entity, attachments);
        }*/
        entity.setUserId(SessionUtils.getUser().getId());
        entity.setDeptId(SessionUtils.getUser().getDepartment().getId());
        ActResult actResult = crmContractService.create(procDefId, entity, attachments);
        return ResponseEntity.ok(ResultUtil.successJson(actResult));
    }

    /**
     * 重新发起申请
     * @param taskId   任务Id
     * @param strEntity 业务对象
     * @param attachments 审批附件
     * @return
     */
    @PostMapping(value = "/{taskId}/reapply")
    public ResponseEntity<JSONObject> reapply(
        @PathVariable("taskId") String taskId,
        @RequestParam(value = "strEntity", required = false) String strEntity,
        @RequestParam(value = "attachments", required = false) MultipartFile[] attachments
    ) throws java.io.IOException {
        CrmContract entity = JSONObject.parseObject(strEntity, CrmContract.class);
        ActResult actResult = crmContractService.reapply(taskId, entity, attachments);
        return ResponseEntity.ok(ResultUtil.successJson(actResult));
    }

    /**
     * 同意申请
     * @param taskId   任务Id
     * @param strEntity 业务对象
     * @param attachments 附件列表
     * @return
     */
    @PostMapping(value = "/{taskId}/agree")
    public ResponseEntity<JSONObject> agree(
        @PathVariable("taskId") String taskId,
        @RequestParam(value = "strEntity", required = false) String strEntity,
        @RequestParam(value = "attachments", required = false) MultipartFile[] attachments
    ) throws java.io.IOException {
        CrmContract entity = JSONObject.parseObject(strEntity, CrmContract.class);
        ActResult actResult = crmContractService.agree(taskId, entity, attachments);
        return ResponseEntity.ok(ResultUtil.successJson(actResult));
    }

    /**
     * 转派任务
     * @param taskId    任务Id
     * @param strEntity    业务对象
     * @param attachments
     * @return
     */
    @PostMapping(value = "/{taskId}/forward")
    public ResponseEntity<JSONObject> forward(
        @PathVariable("taskId") String taskId,
        @RequestParam(value = "strEntity", required = false) String strEntity,
        @RequestParam(value = "attachments", required = false) MultipartFile[] attachments
    ) throws java.io.IOException {
        CrmContract entity = JSONObject.parseObject(strEntity, CrmContract.class);
        ActResult actResult = crmContractService.forward(taskId, entity, attachments);
        return ResponseEntity.ok(ResultUtil.successJson(actResult));
    }

    /**
     * 委派任务
     * @param taskId    任务Id
     * @param strEntity    业务对象
     * @param attachments
     * @return
     */
    @PostMapping(value = "/{taskId}/commission")
    public ResponseEntity<JSONObject> commission(
        @PathVariable("taskId") String taskId,
        @RequestParam(value = "strEntity", required = false) String strEntity,
        @RequestParam(value = "attachments", required = false) MultipartFile[] attachments
    ) throws java.io.IOException {
        CrmContract entity = JSONObject.parseObject(strEntity, CrmContract.class);
        ActResult actResult = crmContractService.commission(taskId, entity, attachments);
        return ResponseEntity.ok(ResultUtil.successJson(actResult));
    }

    /**
     * 拟办任务 ，  办理委派过来的任务，把任务转给任务持有者
     * @param taskId    任务Id
     * @param strEntity    业务对象
     * @param attachments
     * @return
     */
    @PostMapping(value = "/{taskId}/propose")
    public ResponseEntity<JSONObject> propose(
        @PathVariable("taskId") String taskId,
        @RequestParam(value = "strEntity", required = false) String strEntity,
        @RequestParam(value = "attachments", required = false) MultipartFile[] attachments
    ) throws java.io.IOException {
        CrmContract entity = JSONObject.parseObject(strEntity, CrmContract.class);
        ActResult actResult = crmContractService.propose(taskId, entity, attachments);
        return ResponseEntity.ok(ResultUtil.successJson(actResult));
    }

    /**
     * 退回任务到指定的节点和用户
     * @param taskId    任务Id
     * @param strEntity    业务对象
     * @param attachments
     * @return
     */
    @PostMapping(value = "/{taskId}/back")
    public ResponseEntity<JSONObject> back(
        @PathVariable("taskId") String taskId,
        @RequestParam(value = "strEntity", required = false) String strEntity,
        @RequestParam(value = "attachments", required = false) MultipartFile[] attachments
    ) throws java.io.IOException {
        CrmContract entity = JSONObject.parseObject(strEntity, CrmContract.class);
        ActResult actResult = crmContractService.back(taskId, entity, attachments);
        return ResponseEntity.ok(ResultUtil.successJson(actResult));
    }

    /**
     * 退回任务到指定的节点和用户
     * @param taskId    任务Id
     * @param strEntity    业务对象
     * @param attachments
     * @return
     */
    @PostMapping(value = "/{taskId}/reject")
    public ResponseEntity<JSONObject> reject(
        @PathVariable("taskId") String taskId,
        @RequestParam(value = "strEntity", required = false) String strEntity,
        @RequestParam(value = "attachments", required = false) MultipartFile[] attachments
    ) throws java.io.IOException {
        CrmContract entity = JSONObject.parseObject(strEntity, CrmContract.class);
        ActResult actResult = crmContractService.reject(taskId, entity, attachments);
        return ResponseEntity.ok(ResultUtil.successJson(actResult));
    }

    /**
     * 终止任务的申请流程
     * @param taskId    任务Id
     * @param strEntity    业务对象
     * @param attachments
     * @return
     */
    @PostMapping(value = "/{taskId}/terminate")
    public ResponseEntity<JSONObject> terminate(
        @PathVariable("taskId") String taskId,
        @RequestParam(value = "strEntity", required = false) String strEntity,
        @RequestParam(value = "attachments", required = false) MultipartFile[] attachments
    ) throws java.io.IOException {
        CrmContract entity = JSONObject.parseObject(strEntity, CrmContract.class);
        int rows = crmContractService.terminate(taskId, entity, attachments);
//        CrmOpportunity opportunity = new CrmOpportunity();
//        opportunity.setId(entity.getOpportunityId());
//        opportunityService.deleteAll(opportunity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    /**
     * 撤回已处理的任务，仅在后续节点任务未处理时可以撤回
     * @param taskId
     * @return
     */
    @PostMapping(value = "/{taskId}/reverse")
    public ResponseEntity<JSONObject> reverse(@PathVariable("taskId") String taskId) {
        CrmContract entity = crmContractService.getByTaskId(taskId);
        ActResult actResult = crmContractService.reverse(taskId, entity);
        return ResponseEntity.ok(ResultUtil.successJson(actResult));
    }

    /**
     * 列举可回退的节点和用户
     * @param taskId    任务Id
     * @return
     */
    @GetMapping(value = "/{taskId}/listBackActivity")
    public ResponseEntity<JSONObject> listBackActivity(@PathVariable("taskId") String taskId) {
        ActResult actResult = crmContractService.listBackActivity(taskId);
        return ResponseEntity.ok(ResultUtil.successJson(actResult));
    }
}
