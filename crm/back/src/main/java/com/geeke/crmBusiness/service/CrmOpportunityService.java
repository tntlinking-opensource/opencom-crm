package com.geeke.crmBusiness.service;

import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geeke.common.data.Page;
import com.geeke.common.data.PageRequest;
import com.geeke.common.data.Parameter;
import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.contacts.entity.CrmContacts;
import com.geeke.contacts.service.CrmContactsService;
import com.geeke.contract.entity.CrmContract;
import com.geeke.contract.enums.ContractStatus;
import com.geeke.contract.service.CrmContractService;
import com.geeke.crmBusiness.dao.CrmOpportunityDao;
import com.geeke.crmBusiness.entity.CrmOpportunity;
import com.geeke.crmBusiness.entity.CrmOpportunityPay;
import com.geeke.crmBusiness.entity.CrmOpportunityProduct;
import com.geeke.dbtype.controller.SysDbTypeController;
import com.geeke.dictitem.entity.SysDictItem;
import com.geeke.dictitem.service.SysDictItemService;
import com.geeke.notice.entity.NoticeSend;
import com.geeke.notice.service.NoticeSendService;
import com.geeke.sys.utils.SessionUtils;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.SpringUtils;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商机管理Service
 * @author
 * @version
 */

@Service("crmOpportunityService")
@Transactional(readOnly = true)
public class CrmOpportunityService extends CrudService<CrmOpportunityDao, CrmOpportunity> {
    @Autowired
    private CrmOpportunityProductService productService; // 商机明细-产品
    @Autowired
    private CrmOpportunityCotService cotService; // 商机明细-合同
    @Autowired
    private CrmOpportunityPayService payService; // 商机明细-回款
    @Autowired
    private CrmOpportunityLinkService linkService; // 商机明细-联系人
    @Autowired
    private NoticeSendService noticeSendService;

    @Autowired
    private SysDictItemService itemService;

    @Transactional
    public int deleteAll(CrmOpportunity entity) {
        // 校验商机关联合同状态
        String opporId = entity.getId();// 商机ID
        // 根据ID查找对于合同状态  一个商机对于一个合同
        CrmContract contract = cotService.getByCotId(opporId);
        if (contract != null) {
            String contractStatus = contract.getContractStatus();
            if (!contractStatus.equals(ContractStatus.SAVE.getValue()) && !contractStatus.equals(ContractStatus.REJECT.getValue())) {
                throw new CommonJsonException(ResultUtil.warningJson(ErrorEnum.E_50001, "当前状态无法删除合同"));
            }
        }
        // 获取联系人
        List<CrmContacts> contacts = linkService.getByTacts(opporId);
        // 删除商机明细-联系人
        if (contacts.size() != 0) {
            contacts.forEach(item->{
                linkService.deleteCrmContacts(item);
            });
        }
        // 根据商机ID获取Pay实体
        List<Parameter> parameterList = new ArrayList<>();
        Parameter parameter = new Parameter("opp_id","=",opporId);
        parameterList.add(parameter);
        List<CrmOpportunityPay> opportunityPays = payService.listAll(parameterList, "opp_id");
        if (opportunityPays.size() !=0 ){
            opportunityPays.forEach(item->{
                // 删除商机明细-回款  删除商机明细合同  删除合同 \合同回款
                payService.deletePay(item);
            });
        }
        // 删除商机明细产品
        List<CrmOpportunityProduct> opportunityProducts = productService.listAll(parameterList, "opp_id");
        if (opportunityProducts.size() != 0) {
            productService.bulkDelete(opportunityProducts);
        }
        // 删除商机
        super.delete(entity);
        return 0;
    }

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
        String opportunityId = jsonObject.getString("opportunityId");
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

        noticeSendService.sendMail(noticeSend,attachments);

        // 修改是否已发送报价单
        CrmOpportunity opportunity = super.get(opportunityId);
        opportunity.setOppQuotation("1");
        CrmOpportunity save = super.save(opportunity);
        return save.getOppStage();
    }

    public Page<CrmOpportunity> listPage(List<Parameter> parameters, int offset, int limit, String orderby) {
        PageRequest pageRequest = new PageRequest(offset, limit, parameters, orderby);
        int total = dao.count(pageRequest);
        List<CrmOpportunity> list;
        if(total > 0) {
            list = dao.listPage(pageRequest);
            List<SysDictItem> crmDictOppsStage = itemService.getbycode("CRM_DICT_OPPS_STAGE");
            crmDictOppsStage.forEach(items-> list.forEach(itemss->{
                if (itemss.getOppStage().equals(items.getValue())){
                    itemss.setOppStage(items.getName());
                }
            }));
        } else {
            list = null;
        }
        return new Page<CrmOpportunity>(total, list);
    }

    @Override
    public CrmOpportunity save(CrmOpportunity entity) {
        entity.setOppQuotation("0");
        if (StringUtils.isBlank(entity.getOppResponsible())){
            entity.setOppResponsible(com.geeke.utils.SessionUtils.getUser().getId());
        }
        if (StringUtils.isBlank(entity.getOppRespondepartId())) {
            entity.setOppRespondepartId( com.geeke.utils.SessionUtils.getUser().getDepartment().getId());
        }
        Parameter contractIdParam = new Parameter("a.opp_id", "=", entity.getId());
        if (!StringUtils.isNullOrEmpty(entity.getId())){
            List<CrmOpportunityProduct> opportunityProducts = productService.listAll(Lists.newArrayList(contractIdParam), "a.create_date");
            if (!opportunityProducts.isEmpty()){
                entity.setOppStage("1");
            }
        }
        return super.save(entity);
    }

}
