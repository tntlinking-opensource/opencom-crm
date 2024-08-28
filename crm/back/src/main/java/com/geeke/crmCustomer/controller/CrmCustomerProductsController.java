package com.geeke.crmCustomer.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.crmCustomer.entity.CrmCustomerProductReq;
import com.geeke.crmCustomer.entity.CrmCustomerProducts;
import com.geeke.crmCustomer.entity.CrmCustomerProductsInsert;
import com.geeke.crmCustomer.entity.CrmCustomerProductsLine;
import com.geeke.crmCustomer.service.CrmCustomerProductsService;
import com.geeke.crmProInfo.entity.CrmProducts;
import com.geeke.crmProInfo.service.CrmProductsService;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.RemoteResultResolver;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.SessionUtils;
import com.geeke.utils.StringUtils;
import com.newtouch.cloud.common.dependency.remote.rest.DynamicRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户管理Controller
 *
 * @author
 */
@RestController
@RequestMapping(value = "/crmCustomer/crmCustomerProducts")
public class CrmCustomerProductsController extends BaseController {

    @Autowired
    private CrmCustomerProductsService crmCustomerProductsService;

    @Autowired
    private CrmProductsService crmProductsService;

    @Autowired
    private DynamicRestTemplate restTemplate;

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {

        return ResponseEntity.ok(ResultUtil.successJson( crmCustomerProductsService.getCustomerProductsLineByCustomerProductsId(id)));
    }

    @PostMapping(value = {"list", ""})
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<CrmProducts> result = crmCustomerProductsService.crmCustomerProductslistPage(
                searchParams.getParams(),
                searchParams.getOffset(),
                searchParams.getLimit(),
                searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<CrmCustomerProducts> result = crmCustomerProductsService.listAll(searchParams.getParams(), searchParams.getOrderby());

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "productsListAll")
    public ResponseEntity<JSONObject> productsListAll(@RequestBody SearchParams searchParams) {
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
    public ResponseEntity<JSONObject> save(@RequestBody CrmCustomerProductReq entity) {
//        CrmProducts crmProducts = new CrmProducts();
//        crmProducts.setPdName(entity.getPdName());
//        crmProducts.setPdNo(entity.getPdNo());
//        crmProducts.setRemarks(entity.getRemarks());
//        crmProducts.setPdCostPrice(entity.getPdCostPrice());
//        crmProducts.setPdGuidePrice(entity.getPdGuidePrice());
//        CrmProductsClassify pdClassify = new CrmProductsClassify();
//        pdClassify.setId(entity.getPdClassifyId());
//        pdClassify.setName(entity.getPdClassifyName());
//        crmProducts.setPdClassify(pdClassify);
//        crmProducts.setPdTypeId(entity.getPdTypeId());
//        crmProducts.setPdTotal(entity.getPdTotal());
        CrmProducts crmProducts = entity.getCrmProducts();
        crmProductsService.save(crmProducts).getId();

        String productsId = crmProducts.getId();
        CrmCustomerProducts crmCustomerProducts = new CrmCustomerProducts();
        crmCustomerProducts.setId(entity.getId());
        crmCustomerProducts.setCusId(entity.getCusId());
        crmCustomerProducts.setProductId(productsId);
        crmCustomerProducts.setUpdateDate(entity.getUpdateDate());
         String id = crmCustomerProductsService.save(crmCustomerProducts).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));

    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody CrmCustomerProducts entity) {
        int rows = crmCustomerProductsService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<CrmCustomerProducts> entitys) {
        List<String> ids = crmCustomerProductsService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<CrmCustomerProducts> entitys) {
        List<String> ids = crmCustomerProductsService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<CrmCustomerProducts> entitys) {
        int rows = crmCustomerProductsService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }
}
