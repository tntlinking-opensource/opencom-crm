package com.geeke.crmInvoice.dao;


import com.geeke.crmInvoice.entity.CrmInvoice;
import com.geeke.crmInvoice.vo.CrmContract;
import com.geeke.wf.persistence.WfCrudDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 发票管理DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmInvoiceDao extends WfCrudDao<CrmInvoice> {

    /**
     * 获取已审批合同
     * @param contractStatus
     * @return
     */
    List<CrmContract> getContractList(@Param("contractStatus") String contractStatus, @Param("contractCode")String contractCode);

    void updateBackInvoice(@Param("status")String status,@Param("procInst")String procInst);

}
