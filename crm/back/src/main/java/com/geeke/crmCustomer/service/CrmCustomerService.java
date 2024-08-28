package com.geeke.crmCustomer.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geeke.common.service.TreeService;
import com.geeke.crmBusiness.entity.CrmOpportunity;
import com.geeke.crmBusiness.service.CrmOpportunityService;
import com.geeke.crmCommunicate.entity.CrmCommunicate;
import com.geeke.crmCustomer.dao.CrmCustomerDao;
import com.geeke.crmCustomer.entity.CrmCustomer;
import com.geeke.crmCustomer.entity.CrmCustomerResp;
import com.geeke.notice.entity.NoticeSend;
import com.geeke.notice.service.NoticeSendService;
import com.geeke.sys.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 客户管理Service
 * @author
 * @version
 */

@Service("crmCustomerService")
@Transactional(readOnly = true)
public class CrmCustomerService extends TreeService<CrmCustomerDao, CrmCustomer> {
    @Autowired
    private NoticeSendService noticeSendService;
    @Autowired
    private CrmCustomerDao crmCustomerDao;
    @Autowired
    private CrmOpportunityService crmOpportunityService;
    /**
     *   发送右键并修改状态
     * @param payInfo  正文标题，接收人列表
     * @param attachments 附件
     * @param strDeleteIds 附件删除
     */
    @Transactional
    public String sendPayQuotation(String payInfo, MultipartFile[] attachments, String strDeleteIds) {
        JSONObject jsonObject = JSONObject.parseObject(payInfo);
        String title = jsonObject.getString("title"); // 标题
        String textInfo = jsonObject.getString("textInfo"); // 正文
        JSONArray jsonArray = jsonObject.getJSONArray("recipient");
        String emailString = jsonArray.stream().map(Object::toString)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(","));

        NoticeSend noticeSend = new NoticeSend();
        JSONObject userObj = SessionUtils.getUserJson();
        noticeSend.setSenderId(userObj.getString("id"));
        noticeSend.setEmailContent(textInfo);
        noticeSend.setTitle(title);
        noticeSend.setIsEmail("1");
        noticeSend.setReceivedBy(emailString);
        CrmOpportunity crmOpportunity = crmOpportunityService.get(jsonObject.getString("opportunityId"));
        ArrayList<CrmOpportunity> list = new ArrayList<>();
        crmOpportunity.setOppQuotation("1");
        list.add(crmOpportunity);
        crmOpportunityService.bulkUpdate(list);
        noticeSendService.sendMail(noticeSend,attachments);
        return "";
    }

    @Transactional
    public CrmCustomerResp  queryCus(CrmCustomer customer){
        CrmCustomerResp crmCustomerResp = new CrmCustomerResp();

        List<CrmCommunicate> crmCommunicates = crmCustomerDao.queryCon(customer.getId());
        if(crmCommunicates.size()>0){
            String cntPname = crmCommunicates.get(0).getCntPname();
            String cntDate = crmCommunicates.get(0).getCntDate();
            crmCustomerResp.setCntPname(cntPname);
            crmCustomerResp.setCntDate(cntDate);
        }
        crmCustomerResp.setCrmCustomer(customer);
        return  crmCustomerResp;
    };

}
