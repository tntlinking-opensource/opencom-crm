package com.geeke.crmCustomer.service;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.common.data.PageRequest;
import com.geeke.common.data.Parameter;
import com.geeke.common.service.CrudService;
import com.geeke.crmCustomer.dao.CrmCustomerProductsDao;
import com.geeke.crmCustomer.entity.CrmCustomerProducts;
import com.geeke.crmCustomer.entity.CrmCustomerProductsLine;
import com.geeke.crmProInfo.entity.CrmProducts;
import com.geeke.crmProInfo.service.CrmProductsService;
import com.geeke.utils.RemoteResultResolver;
import com.newtouch.cloud.common.dependency.remote.rest.DynamicRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 客户管理Service
 *
 * @author
 */

@Service("crmCustomerProductsService")
@Transactional(readOnly = true)
public class CrmCustomerProductsService extends CrudService<CrmCustomerProductsDao, CrmCustomerProducts> {

    @Autowired
    private DynamicRestTemplate restTemplate;

    @Autowired
    private CrmProductsService crmProductsService;


    @Override
    public Page<CrmCustomerProducts> listPage(List<Parameter> parameters, int offset, int limit, String orderby) {
        Page<CrmCustomerProducts> result = super.listPage(parameters, offset, limit, orderby);

        //查看是否多次调用
        int flag = RemoteResultResolver.getRemoteFlag();
        //组装数据
        if (flag <= 1 && Objects.nonNull(result.getRows())) {
            //设置Header
            HttpHeaders headers = new HttpHeaders();
            headers.add(RemoteResultResolver.REMOTE_FLAG, String.valueOf(flag++));

            SearchParams crmCustomerProductsParam = RemoteResultResolver.param(result.getRows(), "productsId", "id");
            JSONObject crmCustomerProductsList = restTemplate.post(
                    "crm",
                    "/crmCustomerProducts/crmCustomerProducts/listAll",
                    JSONObject.class,
                    crmCustomerProductsParam,
                    headers
            );
            Integer crmCustomerProductsCode = Objects.requireNonNull(crmCustomerProductsList.getInteger("code"));
            if (crmCustomerProductsCode == 100) {
                List<Map<String, Object>> products = crmCustomerProductsList.getObject("data", ArrayList.class);
                RemoteResultResolver.resultResolver(result.getRows(), products, "products", "productsId");
            }
        }

        return result;
    }

    @Override
    public List<CrmCustomerProducts> listAll(List<Parameter> parameters, String orderby) {
        PageRequest pageRequest = new PageRequest(parameters, orderby);

        List<CrmCustomerProducts> result = dao.listAll(pageRequest);

        //查看是否多次调用
        int flag = RemoteResultResolver.getRemoteFlag();
        //组装数据
        if (flag <= 1 && Objects.nonNull(result)) {
            //设置Header
            HttpHeaders headers = new HttpHeaders();
            headers.add(RemoteResultResolver.REMOTE_FLAG, String.valueOf(flag++));

            SearchParams crmCustomerProductsParam = RemoteResultResolver.param(result, "productsId", "id");
            JSONObject crmCustomerProductsList = restTemplate.post(
                    "crm",
                    "/crmCustomerProducts/crmCustomerProducts/listAll",
                    JSONObject.class,
                    crmCustomerProductsParam,
                    headers
            );
            Integer crmCustomerProductsCode = Objects.requireNonNull(crmCustomerProductsList.getInteger("code"));
            if (crmCustomerProductsCode == 100) {
                List<Map<String, Object>> products = crmCustomerProductsList.getObject("data", ArrayList.class);
                RemoteResultResolver.resultResolver(result, products, "products", "productsId");
            }
        }

        return result;
    }

    public Page<CrmProducts> crmCustomerProductslistPage(List<Parameter> parameters, int offset, int limit, String orderby) {
        Page<CrmCustomerProducts> page = super.listPage(parameters, offset, limit, orderby);
        List<CrmProducts> crmCustomerProductsList = new ArrayList<>();
        if (page.getRows() != null) {
            for (CrmCustomerProducts crmCustomerProducts : page.getRows()) {
                String productsId = crmCustomerProducts.getProductId();
                CrmProducts crmProducts = crmProductsService.get(productsId);
//                CrmCustomerProductsLine crmCustomerProductsLine = new CrmCustomerProductsLine();
//                crmCustomerProductsLine.setProductsId(crmProducts.getId());
//                crmCustomerProductsLine.setPdNo(crmProducts.getPdNo());
//                crmCustomerProductsLine.setRemarks(crmProducts.getRemarks());
//                crmCustomerProductsLine.setPdName(crmProducts.getPdName());
//                crmCustomerProductsLine.setPdClassify(crmProducts.getPdClassify());
//                crmCustomerProductsLine.setPdGuidePrice(crmProducts.getPdGuidePrice());
//                crmCustomerProductsLine.setCusId(crmCustomerProducts.getCusId());
//                crmCustomerProductsLine.setPdTypeId(crmProducts.getPdTypeId());
//                crmCustomerProductsLine.setId(crmCustomerProducts.getId());
//                crmCustomerProductsLine.setOldUpdateDate(crmCustomerProducts.getUpdateDate());
                crmCustomerProductsList.add(crmProducts);

            }
        }
        Page<CrmProducts> crmCustomerProductsListPage = new Page<>(page.getTotal(), crmCustomerProductsList);

        return crmCustomerProductsListPage;
    }


    public CrmCustomerProductsLine getCustomerProductsLineByCustomerProductsId(String customerProductsId) {
        CrmCustomerProducts crmCustomerProducts = this.get(customerProductsId);
        CrmProducts crmProducts = crmProductsService.get(crmCustomerProducts.getProductId());
        CrmCustomerProductsLine crmCustomerProductsLine = new CrmCustomerProductsLine();
        crmCustomerProductsLine.setProductsId(crmProducts.getId());
        crmCustomerProductsLine.setPdNo(crmProducts.getPdNo());
        crmCustomerProductsLine.setRemarks(crmProducts.getRemarks());
        crmCustomerProductsLine.setPdName(crmProducts.getPdName());
        crmCustomerProductsLine.setPdClassifyId(crmProducts.getPdClassify().getId());
        crmCustomerProductsLine.setPdGuidePrice(crmProducts.getPdGuidePrice());
        crmCustomerProductsLine.setCusId(crmCustomerProducts.getCusId());
        crmCustomerProductsLine.setPdTypeId(crmProducts.getPdTypeId());
        crmCustomerProductsLine.setId(crmCustomerProducts.getId());
        crmCustomerProductsLine.setOldUpdateDate(crmCustomerProducts.getUpdateDate());
        return crmCustomerProductsLine;
    }
}
