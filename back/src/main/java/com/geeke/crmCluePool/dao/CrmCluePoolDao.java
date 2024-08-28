package com.geeke.crmCluePool.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.crmCluePool.entity.CrmCluePool;
import com.geeke.crmCluePool.entity.CrmUserls;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 线索池管理DAO接口
 * @author
 * @version
 */
@Mapper
public interface CrmCluePoolDao extends CrudDao<CrmCluePool> {

    public int getest(String userofjober,String userofname,Integer pageNum, Integer pageSize);

    public int saveccpl(@Param("crmCluePool") CrmCluePool crmCluePool);

    public int upccpl(@Param("crmCluePool") CrmCluePool crmCluePool);

    public int delecpls(@Param("idlist") List<String> idlist);

    public int delbycplid(@Param("id") String id);

    public int delecf(@Param("qjid") String qjid);

    public int updatecplmids(@Param("brandIdlLst") List<String> brandIdlLst,@Param("qjid") String qjid);

    public int saveccpms(String qjid,String id,String element,String loginName);

    public List<CrmUserls> getuserlists(String userofjober,String userofname,Integer pageNum, Integer pageSize);

}
