package com.geeke.crmInvoice.service;

import com.alibaba.fastjson.JSONObject;
import com.geeke.camundaex.entity.ActResult;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.contract.enums.ContractStatus;
import com.geeke.crmInvoice.dao.CrmInvoiceDao;
import com.geeke.crmInvoice.entity.CrmInvoice;

import com.geeke.crmInvoice.enums.InvoiceStatus;
import com.geeke.crmInvoice.vo.CrmContract;
import com.geeke.wf.service.BizInProcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 发票管理Service
 * @author
 * @version
 */

@Service("crmInvoiceService")
@Transactional(readOnly = true)
public class CrmInvoiceService extends BizInProcService<CrmInvoiceDao, CrmInvoice> {

    @Autowired
    CrmInvoiceDao crmInvoiceDao;

    /**
     * 获取表单对象的class
     * @return
     */
    @Override
    protected Class<CrmInvoice> getBizClass() {
        return CrmInvoice.class;
    }

    /**
     * 创建流程后
     * @param crmInvoice
     * @return
     */
    @Override
    public ActResult afterCreate(CrmInvoice crmInvoice) {
        crmInvoice.setStatus(InvoiceStatus.DSH.getValue());
        crmInvoiceDao.update(crmInvoice);
        return super.afterCreate(crmInvoice);
    }

    @Override
    public ActResult afterReapply(CrmInvoice entity) {
        return super.afterReapply(entity);
    }

    /**
     * 流程同意后
     * @param crmInvoice
     * @return
     */
    @Override
    public ActResult afterAgree(CrmInvoice crmInvoice) {
        //修改同意的发票数据状态为 已开具
        crmInvoiceDao.updateBackInvoice(InvoiceStatus.YKJ.getValue(),crmInvoice.getProcInst());
        return super.afterAgree(crmInvoice);
    }

    /**
     * 流程退回后
     * @param crmInvoice
     * @param toStart
     * @return
     */
    @Override
    public ActResult afterBack(CrmInvoice crmInvoice,boolean toStart) {
//        crmInvoice.setStatus(InvoiceStatus.SHBTG.getValue());
//        crmInvoiceDao.update(crmInvoice);
        //修改退回后新建的发票数据状态为 审核不通过
        crmInvoiceDao.updateBackInvoice(InvoiceStatus.SHBTG.getValue(),crmInvoice.getProcInst());
        return new ActResult(ActResult.EnumCodes.Done, "回调成功");
    }

    public List<CrmContract> getContractList(String contractCode) {
        List<CrmContract> list = crmInvoiceDao.getContractList(ContractStatus.APPROVE.getValue(), contractCode);
        return  list;
    }
}
