package com.geeke.crmCustomer.service;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.common.data.PageRequest;
import com.geeke.common.data.Parameter;
import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.contacts.dao.CrmContactsDao;
import com.geeke.contacts.entity.CrmContacts;
import com.geeke.crmBusiness.entity.CrmOpportunity;
import com.geeke.crmBusiness.entity.CrmOpportunityLink;
import com.geeke.crmCustomer.dao.CrmCustomerLinkDao;
import com.geeke.crmCustomer.entity.CrmCustomer;
import com.geeke.crmCustomer.entity.CrmCustomerLink;
import com.geeke.utils.RemoteResultResolver;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.google.common.collect.Lists;
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
 * @author
 * @version
 */

@Service("crmCustomerLinkService")
@Transactional(readOnly = true)
public class CrmCustomerLinkService extends CrudService<CrmCustomerLinkDao, CrmCustomerLink> {

    @Autowired
    private DynamicRestTemplate restTemplate;

    @Autowired
    private CrmContactsDao crmContactsDao;

//    @Override
//    public Page<CrmCustomerLink> listPage(List<Parameter> parameters, int offset, int limit, String orderby) {
//        Page<CrmCustomerLink> result = super.listPage(parameters, offset, limit, orderby);
//
//        //查看是否多次调用
//        int flag = RemoteResultResolver.getRemoteFlag();
//        //组装数据
//        if (flag <= 1 && Objects.nonNull(result.getRows())) {
//            //设置Header
//            HttpHeaders headers = new HttpHeaders();
//            headers.add(RemoteResultResolver.REMOTE_FLAG, String.valueOf(flag++));
//
//            SearchParams crmCommunicateParam = RemoteResultResolver.param(result.getRows(), "cotId", "id");
//            JSONObject crmCommunicateList = restTemplate.post(
//                "crm",
//                "/crmCommunicate/crmCommunicate/listAll",
//                JSONObject.class,
//                crmCommunicateParam,
//                headers
//            );
//            Integer crmCommunicateCode = Objects.requireNonNull(crmCommunicateList.getInteger("code"));
//            if (crmCommunicateCode == 100) {
//                List<Map<String, Object>> cot = crmCommunicateList.getObject("data", ArrayList.class);
//                RemoteResultResolver.resultResolver(result.getRows(), cot, "cot", "cotId");
//            }
//        }
//
//        return result;
//    }

    @Override
    public List<CrmCustomerLink> listAll(List<Parameter> parameters, String orderby) {
        PageRequest pageRequest = new PageRequest(parameters, orderby);

        List<CrmCustomerLink> result = dao.listAll(pageRequest);

        //查看是否多次调用
        int flag = RemoteResultResolver.getRemoteFlag();
        //组装数据
        if (flag <= 1 && Objects.nonNull(result)) {
            //设置Header
            HttpHeaders headers = new HttpHeaders();
            headers.add(RemoteResultResolver.REMOTE_FLAG, String.valueOf(flag++));

            SearchParams crmCommunicateParam = RemoteResultResolver.param(result, "cotId", "id");
            JSONObject crmCommunicateList = restTemplate.post(
                "crm",
                "/crmCommunicate/crmCommunicate/listAll",
                JSONObject.class,
                crmCommunicateParam,
                headers
            );
            Integer crmCommunicateCode = Objects.requireNonNull(crmCommunicateList.getInteger("code"));
            if (crmCommunicateCode == 100) {
                List<Map<String, Object>> cot = crmCommunicateList.getObject("data", ArrayList.class);
                RemoteResultResolver.resultResolver(result, cot, "cot", "cotId");
            }
        }

        return result;
    }

    @Transactional
    public CrmCustomerLink saveCrmContacts(CrmContacts entity) {CrmCustomerLink link = new CrmCustomerLink();

        // 为空则创建
        if (StringUtils.isBlank(entity.getId())){
            // 判断手机号是否重复
            String phoneNumber = entity.getPhoneNumber();
            Parameter contractIdParam = new Parameter("phone_number", "=", phoneNumber);
            PageRequest pageRequest = new PageRequest(0, 1, Lists.newArrayList(contractIdParam), "phone_number");

            List<CrmContacts> crmContacts = crmContactsDao.listAll(pageRequest);
            if (!crmContacts.isEmpty()) {
                throw new CommonJsonException(ResultUtil.warningJson(ErrorEnum.E_50001, "填写手机号已存在，无需重复添加"));
            }
            entity.preInsert();
            crmContactsDao.insert(entity);
            // 再保存到商机明细-联系人
            CrmCustomer crmCustomer = new CrmCustomer();
            crmCustomer.setId(entity.getCustomName());
            link.setCus(crmCustomer);
            link.setLink(entity);
            super.save(link);
            return link;
        }else{
            entity.preUpdate();
            crmContactsDao.update(entity);
        }
        return  link;
    }
}
