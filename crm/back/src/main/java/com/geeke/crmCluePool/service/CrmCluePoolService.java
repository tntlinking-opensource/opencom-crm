package com.geeke.crmCluePool.service;

import com.geeke.common.constants.ActionConstants;
import com.geeke.common.data.Page;
import com.geeke.common.data.PageRequest;
import com.geeke.common.data.Parameter;
import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.crmCluePool.dao.CrmCluePoolDao;
import com.geeke.crmCluePool.dao.CrmCluePoolMemberDao;
import com.geeke.crmCluePool.entity.CrmCluePool;
import com.geeke.crmCluePool.entity.CrmCluePoolMember;
import com.geeke.crmCluePool.entity.CrmUserls;
import com.geeke.crmCluePool.service.CrmCluePoolMemberService;
import com.geeke.sys.entity.Action;
import com.geeke.sys.entity.ActionRecycle;
import com.geeke.utils.Reflections;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 线索池管理Service
 * @author
 * @version
 */

@Service("crmCluePoolService")
@Transactional(readOnly = true)
public class CrmCluePoolService extends CrudService<CrmCluePoolDao, CrmCluePool> {

    @Autowired
    private CrmCluePoolMemberDao crmCluePoolMemberDao;

    @Autowired
    private CrmCluePoolDao crmCluePoolDao1;

    @Autowired
    private CrmCluePoolMemberService crmCluePoolMemberService;


    public int gettest(String userofjober,String userofname,Integer pageNum, Integer pageSize){
        return crmCluePoolDao1.getest( userofjober, userofname, pageNum,  pageSize);
    }

    public List<CrmUserls> getuserlists(String userofjober,String userofname,Integer pageNum, Integer pageSize){
        return crmCluePoolDao1.getuserlists( userofjober, userofname, pageNum,  pageSize);
    }

    @Transactional(readOnly=false)
    public int saveccpl(CrmCluePool crmCluePool){
        return crmCluePoolDao1.saveccpl(crmCluePool);
    }


    @Transactional(readOnly=false)
    public int upccpl(CrmCluePool crmCluePool){
        return crmCluePoolDao1.upccpl(crmCluePool);
    }


    @Transactional(readOnly=false)
    public int delbycplid(String id){
        return crmCluePoolDao1.delbycplid(id);
    }


    @Transactional(readOnly=false)
    public int delecpls(List<String> idlist){
        return crmCluePoolDao1.delecpls(idlist);
    }


    @Transactional(readOnly=false)
    public int updatecplmids(List<String> brandIdlLst,String qjid){
        return crmCluePoolDao1.updatecplmids(brandIdlLst,qjid);
    }

    @Transactional(readOnly=false)
    public int delecf(String qjid){
        return crmCluePoolDao1.delecf(qjid);
    }


    @Transactional(readOnly=false)
    public int saveccpms(String qjid,String id,String element,String loginName){
        return crmCluePoolDao1.saveccpms(qjid, id, element, loginName);
    }



    @Override
    public CrmCluePool get(String id) {
        CrmCluePool crmCluePool = super.get(id);

        List<Parameter> params = null;

        /*获取子表列表   线索池管理-成员*/
        params = Lists.newArrayList();

        params.add(new Parameter("cpl_id", "=", crmCluePool.getId()));
        crmCluePool.setCrmCluePoolMemberList(crmCluePoolMemberService.listAll(params, ""));

        return crmCluePool;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public CrmCluePool save(CrmCluePool crmCluePool) {
        CrmCluePool crmCluePoolTemp = super.save(crmCluePool);

        /* 保存子表数据     线索池管理-成员 */
        saveCrmCluePoolMemberList(crmCluePoolTemp);

        return crmCluePoolTemp;
    }

    /**
     * 删除
     * @param crmCluePool
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public int delete(CrmCluePool crmCluePool) {
        List<Parameter> params = null;
        PageRequest pageRequest;

        /* 处理子表     线索池管理-成员 */
        params = Lists.newArrayList();

        params.add(new Parameter("cpl_id", "=", crmCluePool.getId()));
        pageRequest = new PageRequest(params);
        crmCluePool.setCrmCluePoolMemberList(crmCluePoolMemberDao.listAll(pageRequest));

        if (crmCluePool.getCrmCluePoolMemberList() != null && crmCluePool.getCrmCluePoolMemberList().size() > 0) {
            crmCluePoolMemberService.bulkDelete(crmCluePool.getCrmCluePoolMemberList());
        }

        int rows = super.delete(crmCluePool);
        return rows;
    }

    /**
     * 批量删除
     * @param crmCluePoolList
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public int bulkDelete(List<CrmCluePool> crmCluePoolList) {
        for (CrmCluePool crmCluePool : crmCluePoolList) {
            delete(crmCluePool);
        }

        return crmCluePoolList.size();
    }

    /**
     * 生成操作日志
     * @param actionTypeId  操作类型Id
     * @param entity        操作的实体对象
     * @return
     */
    @Override
    protected Action createAction(String actionTypeId, CrmCluePool entity) {
        Action action = super.createAction(actionTypeId, entity);
        if (action == null) {
            return null;
        }
        // 删除时记录把保存的数据保存到回收站
        if (ActionConstants.ACTION_DELETED.equals(actionTypeId)) {
            for (CrmCluePoolMember child : entity.getCrmCluePoolMemberList()) {
                ActionRecycle recycle = new ActionRecycle();
                recycle.setTableName(child.getBusTableName());
                recycle.setObjectId(child.getId());
                recycle.setObjectName((String) Reflections.invokeGetter(child, "name"));
                action.getActionRecycleList().add(recycle);
            }
        }
        return action;
    }


    /* 保存子表数据     线索池管理-成员 */
    private void saveCrmCluePoolMemberList(CrmCluePool crmCluePool) {
        List<Parameter> params = Lists.newArrayList();

        params.add(new Parameter("cpl_id", "=", crmCluePool.getId()));
        PageRequest pageRequest = new PageRequest(params);
        List<CrmCluePoolMember> list_CrmCluePoolMember = crmCluePoolMemberDao.listAll(pageRequest);

        List<CrmCluePoolMember> deletes = Lists.newArrayList(); // 删除列表
        List<CrmCluePoolMember> inserts = Lists.newArrayList(); // 添加列表
        List<CrmCluePoolMember> updates = Lists.newArrayList(); // 更新列表
        for (CrmCluePoolMember crmCluePoolMemberSaved : list_CrmCluePoolMember) {
            boolean found = false;
            for (CrmCluePoolMember crmCluePoolMember : crmCluePool.getCrmCluePoolMemberList()) {
                if (crmCluePoolMemberSaved.getId().equals(crmCluePoolMember.getId())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                deletes.add(crmCluePoolMemberSaved);
            }
        }
        if (deletes.size() > 0) {
            crmCluePoolMemberService.bulkDelete(deletes);
        }
        for (CrmCluePoolMember crmCluePoolMember : crmCluePool.getCrmCluePoolMemberList()) {
            if (StringUtils.isBlank(crmCluePoolMember.getId())) {
                crmCluePoolMember.setCpl(crmCluePool);

                inserts.add(crmCluePoolMember);
            } else {
                updates.add(crmCluePoolMember);
            }
        }
        if (updates.size() > 0) {
            crmCluePoolMemberService.bulkUpdate(updates);
        }
        if (inserts.size() > 0) {
            crmCluePoolMemberService.bulkInsert(inserts);
        }
    }
}
