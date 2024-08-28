package com.geeke.crmCustomer.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.crmCustomer.entity.CrmCustomerPay;
import com.geeke.crmCustomer.entity.CrmCustomerPayLine;
import com.geeke.crmCustomer.service.CrmCustomerPayService;
import com.geeke.paymentBack.entity.PaymentBack;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.RemoteResultResolver;
import com.geeke.utils.ResultUtil;
import com.newtouch.cloud.common.dependency.remote.rest.DynamicRestTemplate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户管理Controller
 * @author
 * @version
 */
@RestController
@RequestMapping(value = "/crmCustomer/crmCustomerPay")
public class CrmCustomerPayController extends BaseController {

    @Autowired
    private CrmCustomerPayService crmCustomerPayService;

    @Autowired
    private DynamicRestTemplate restTemplate;

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        PaymentBack entity = crmCustomerPayService.getPayInfo(id);

        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }



    @PostMapping(value = { "list", "" })
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<CrmCustomerPayLine> result = crmCustomerPayService.crmCustomerPayLinelistPage(
            searchParams.getParams(),
            searchParams.getOffset(),
            searchParams.getLimit(),
            searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<CrmCustomerPay> result = crmCustomerPayService.listAll(searchParams.getParams(), searchParams.getOrderby());

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "payListAll")
    public ResponseEntity<JSONObject> payListAll(@RequestBody SearchParams searchParams) {
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
    public ResponseEntity<JSONObject> save(@RequestBody CrmCustomerPay entity) {
//        PaymentBack paymentBack = new PaymentBack();
//        paymentBack.setId(entity.getPayId());
//        paymentBack.setCustomer(entity.getCustomer());
//        paymentBack.setPayContacts(entity.getPayContacts());
//        paymentBack.setPayName(entity.getPayName());
//        paymentBack.setPayPay(entity.getPayPay());
//        paymentBack.setPayDepartment(entity.getPayDepartment());
//        paymentBack.setPayDepartmentId(entity.getPayDepartmentId());
//        paymentBack.setPayResponsible(entity.getPayResponsible());
//        paymentBack.setPayStage(entity.getPayStage());
//        paymentBack.setPaySource(entity.getPaySource());
//        paymentBack.setUpdateDate(entity.getPayUpdateDate());
//        String payId = paymentBackService.save(paymentBack).getId();
        CrmCustomerPay crmCustomerPay = new CrmCustomerPay();
        crmCustomerPay.setCusId(entity.getCusId());
//        crmCustomerPay.setPayId(payId);
        if (entity.getId() == null) {
            String id = crmCustomerPayService.save(crmCustomerPay).getId();
            return ResponseEntity.ok(ResultUtil.successJson(id));
        } else {
            return ResponseEntity.ok(ResultUtil.successJson(entity.getId()));
        }
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody CrmCustomerPay entity) {
        int rows = crmCustomerPayService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<CrmCustomerPay> entitys) {
        List<String> ids = crmCustomerPayService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<CrmCustomerPay> entitys) {
        List<String> ids = crmCustomerPayService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<CrmCustomerPay> entitys) {
        int rows = crmCustomerPayService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }
//
    @Around("execution(* com.geeke.paymentBack.service.PaymentBackService.save(..))")
    public Object AroundSave(ProceedingJoinPoint pjp) {
        String cusId = null;
        Object proceed = null;
        for (Object arg : pjp.getArgs()) {
            PaymentBack paymentBack = (PaymentBack) arg;
            if (paymentBack != null) {
//                cusId = paymentBack.getId();
                break;
            }
        }
        try {
            proceed = pjp.proceed();
            String jsonString = JSONObject.toJSONString(proceed);
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            JSONObject jsonBody = (JSONObject) jsonObject.get("body");
            String payId = (String) jsonBody.get("data");
            CrmCustomerPay crmCustomerPay = crmCustomerPayService.getCrmCustomerPayByPayId(payId);
            if (crmCustomerPay == null || crmCustomerPay.getId() == null) {
                crmCustomerPay = new CrmCustomerPay();
                crmCustomerPay.setPayId(payId);
                crmCustomerPay.setCusId(cusId);
                crmCustomerPayService.save(crmCustomerPay);
            } else {
                crmCustomerPay.setCusId(cusId);
                crmCustomerPayService.save(crmCustomerPay);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            return proceed;
        }
    }

    @PostMapping("savePayBacks")
    public ResponseEntity<JSONObject> savePayBacks(@RequestBody List<PaymentBack> entitys) {
        return ResponseEntity.ok(ResultUtil.successJson(crmCustomerPayService.savePayBacks(entitys)));
    }
}
