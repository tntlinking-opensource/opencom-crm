package com.geeke.myClue.service;

import com.alibaba.fastjson.JSON;
import com.geeke.common.constants.ActionConstants;
import com.geeke.common.data.Page;
import com.geeke.common.data.PageRequest;
import com.geeke.common.data.Parameter;
import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.myClue.dao.CrmCluesDao;
import com.geeke.myClue.dao.MyCrmCluePoolDao;
import com.geeke.myClue.entity.CrmClues;
import com.geeke.myClue.entity.MyCrmCluePool;
import com.geeke.myClue.service.MyCrmCluePoolService;
import com.geeke.org.entity.Company;
import com.geeke.sys.entity.Action;
import com.geeke.sys.entity.ActionRecycle;
import com.geeke.sys.entity.DictItem;
import com.geeke.sys.service.DictItemService;
import com.geeke.utils.Reflections;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.SessionUtils;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jxl.Sheet;
import jxl.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.read.biff.BiffException;
import org.apache.commons.lang.ObjectUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 我的线索Service
 * @author
 * @version
 */

@Service("crmCluesService")
@Transactional(readOnly = false)
public class CrmCluesService extends CrudService<CrmCluesDao, CrmClues> {

    @Autowired
    private MyCrmCluePoolDao myCrmCluePoolDao;

    @Autowired
    private MyCrmCluePoolService myCrmCluePoolService;
    @Autowired
    private DictItemService dictItemService;
    @Override
    public CrmClues get(String id) {
        CrmClues crmClues = super.get(id);

        List<Parameter> params = null;

        /*获取子表列表   线索池管理*/
        params = Lists.newArrayList();

        params.add(new Parameter("id", "=", crmClues.getId()));
        crmClues.setMyCrmCluePoolList(myCrmCluePoolService.listAll(params, ""));

        return crmClues;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public CrmClues save(CrmClues crmClues) {
        CrmClues crmCluesTemp = super.save(crmClues);

        /* 保存子表数据     线索池管理 */
        saveMyCrmCluePoolList(crmCluesTemp);

        return crmCluesTemp;
    }

    /**
     * 删除
     * @param crmClues
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public int delete(CrmClues crmClues) {
//        List<Parameter> params = null;
//        PageRequest pageRequest;
//
//        /* 处理子表     线索池管理 */
//        params = Lists.newArrayList();
//
//        params.add(new Parameter("id", "=", crmClues.getId()));
//        pageRequest = new PageRequest(params);
//        crmClues.setMyCrmCluePoolList(myCrmCluePoolDao.listAll(pageRequest));
//
//        if (crmClues.getMyCrmCluePoolList() != null && crmClues.getMyCrmCluePoolList().size() > 0) {
//            myCrmCluePoolService.bulkDelete(crmClues.getMyCrmCluePoolList());
//        }

        int rows = super.delete(crmClues);
        return rows;
    }
    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
    public List<String> bulkUpdate(List<CrmClues> entitys) {
        List<String> ids = Lists.newArrayList();
        for(CrmClues entity: entitys) {
//            entity.preUpdate();
            ids.add(entity.getId());
        }

        for (CrmClues entity : entitys) {
            CrmClues save = super.save(entity);
        }


        return ids;
    }
    /**
     * 删除
     * @param crmClues
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public int physicsDelete(List<CrmClues> crmClues) {
//        List<Parameter> params = null;
//        PageRequest pageRequest;
//
//        /* 处理子表     线索池管理 */
//        params = Lists.newArrayList();
//
//        params.add(new Parameter("id", "=", crmClues.getId()));
//        pageRequest = new PageRequest(params);
//        crmClues.setMyCrmCluePoolList(myCrmCluePoolDao.listAll(pageRequest));
//
//        if (crmClues.getMyCrmCluePoolList() != null && crmClues.getMyCrmCluePoolList().size() > 0) {
//            myCrmCluePoolService.bulkDelete(crmClues.getMyCrmCluePoolList());
//        }

        int rows = dao.physicsDelete(crmClues);
        return rows;
    }

    /**
     * 批量删除
     * @param crmCluesList
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public int bulkDelete(List<CrmClues> crmCluesList) {
        for (CrmClues crmClues : crmCluesList) {
            delete(crmClues);
        }

        return crmCluesList.size();
    }

    /**
     * 生成操作日志
     * @param actionTypeId  操作类型Id
     * @param entity        操作的实体对象
     * @return
     */
    @Override
    protected Action createAction(String actionTypeId, CrmClues entity) {
        Action action = super.createAction(actionTypeId, entity);
        if (action == null) {
            return null;
        }
        // 删除时记录把保存的数据保存到回收站
        if (ActionConstants.ACTION_DELETED.equals(actionTypeId)) {
            for (MyCrmCluePool child : entity.getMyCrmCluePoolList()) {
                ActionRecycle recycle = new ActionRecycle();
                recycle.setTableName(child.getBusTableName());
                recycle.setObjectId(child.getId());
                recycle.setObjectName((String) Reflections.invokeGetter(child, "name"));
                action.getActionRecycleList().add(recycle);
            }
        }
        return action;
    }

    /* 保存子表数据     线索池管理 */
    private void saveMyCrmCluePoolList(CrmClues crmClues) {
        List<Parameter> params = Lists.newArrayList();

        params.add(new Parameter("id", "=", crmClues.getId()));
        PageRequest pageRequest = new PageRequest(params);
        List<MyCrmCluePool> list_MyCrmCluePool = myCrmCluePoolDao.listAll(pageRequest);

        List<MyCrmCluePool> deletes = Lists.newArrayList(); // 删除列表
        List<MyCrmCluePool> inserts = Lists.newArrayList(); // 添加列表
        List<MyCrmCluePool> updates = Lists.newArrayList(); // 更新列表
        for (MyCrmCluePool myCrmCluePoolSaved : list_MyCrmCluePool) {
            boolean found = false;
            for (MyCrmCluePool myCrmCluePool : crmClues.getMyCrmCluePoolList()) {
                if (myCrmCluePoolSaved.getId().equals(myCrmCluePool.getId())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                deletes.add(myCrmCluePoolSaved);
            }
        }
        if (deletes.size() > 0) {
            myCrmCluePoolService.bulkDelete(deletes);
        }
        for (MyCrmCluePool myCrmCluePool : crmClues.getMyCrmCluePoolList()) {
            if (StringUtils.isBlank(myCrmCluePool.getId())) {
                myCrmCluePool.setId(crmClues.getId()); // 子表id与父表id一致

                inserts.add(myCrmCluePool);
            } else {
                updates.add(myCrmCluePool);
            }
        }
        if (updates.size() > 0) {
            myCrmCluePoolService.bulkUpdate(updates);
        }
        if (inserts.size() > 0) {
            myCrmCluePoolService.bulkInsert(inserts);
        }
    }

    public int importExport(InputStream is) throws Exception {
        List<Parameter> params = null;
        String companyId = com.geeke.utils.SessionUtils.getUser().getCompanyId().toString();
        Workbook wk = Workbook.getWorkbook(is);
        Sheet st = wk.getSheet(0);  //读取第一个表格
        Company company = JSON.parseObject(com.geeke.sys.utils.SessionUtils.getUserJson().getJSONObject("company").toJSONString(), Company.class);
        int rowlength= st.getRows();  //获取表格数据的行数

        this.checkFile(st); //检查导入数据的字段是否匹配
        List<CrmClues> list = new ArrayList<>();
        //因为第一行是字段名，所以循环从1开始
        //获取字典状态
        params = Lists.newArrayList();

        params.add(new Parameter("dictType.code", "=", "CLUE_SOURCE"));
        List<DictItem> dictItems = dictItemService.listAll(params, "");
        String err="";
        for (int row = 1; row < rowlength; row++) {
            String code = ObjectUtils.toString(st.getCell(0, row).getContents().trim());//无用
            String cluesName = ObjectUtils.toString(st.getCell(1, row).getContents().trim());
            String cluesPoolCode = ObjectUtils.toString(st.getCell(2, row).getContents().trim());
            String cluesPoolName = ObjectUtils.toString(st.getCell(3, row).getContents().trim());
            String cluesSources = ObjectUtils.toString(st.getCell(4, row).getContents().trim());
            String cluesContacts = ObjectUtils.toString(st.getCell(5, row).getContents().trim());
            String phoneNumber = ObjectUtils.toString(st.getCell(6, row).getContents().trim());
            CrmClues crmClues = new CrmClues();

            crmClues.setCluesName(cluesName);

            for (DictItem dictItem : dictItems) {
                if (dictItem.getName().equals(cluesSources)){
                    crmClues.setCluesSources(dictItem.getValue());
                }
            }
            if (StringUtils.isNullOrEmpty(crmClues.getCluesSources())){
                err+="序号"+code+"线索来源不存在";
            }
            crmClues.setCluesResponsible(SessionUtils.getUser().getId());
            crmClues.setCluesContacts(cluesContacts);
            crmClues.setPhoneNumber(phoneNumber);
            crmClues.setCluesState("0");
//            MyCrmCluePool myCrmCluePool = new MyCrmCluePool();


            /*获取子表列表   线索池管理*/
            params = Lists.newArrayList();

            params.add(new Parameter("cpl_name", "=", cluesPoolName));
            List<MyCrmCluePool> myCrmCluePools = myCrmCluePoolService.listAll(params, "");
            if (myCrmCluePools.size()==0){
                err+="序号"+code+"线索池不存在";
            }

            crmClues.setCluesPool(myCrmCluePools.get(0));
            list.add(crmClues);
        }
        return this.bulkInsert(list).size();
    }
    public int importExport1(InputStream is) throws Exception {
        List<Parameter> params = null;
        String companyId = com.geeke.utils.SessionUtils.getUser().getCompanyId().toString();
        Workbook wk = Workbook.getWorkbook(is);
        Sheet st = wk.getSheet(0);  //读取第一个表格
        Company company = JSON.parseObject(com.geeke.sys.utils.SessionUtils.getUserJson().getJSONObject("company").toJSONString(), Company.class);
        int rowlength= st.getRows();  //获取表格数据的行数

        this.checkFile(st); //检查导入数据的字段是否匹配
        List<CrmClues> list = new ArrayList<>();
        //因为第一行是字段名，所以循环从1开始
        //获取字典状态
        params = Lists.newArrayList();

        params.add(new Parameter("dictType.code", "=", "CLUE_SOURCE"));
        List<DictItem> dictItems = dictItemService.listAll(params, "");
        String err="";
        for (int row = 1; row < rowlength; row++) {
            String code = ObjectUtils.toString(st.getCell(0, row).getContents().trim());//无用
            String cluesName = ObjectUtils.toString(st.getCell(1, row).getContents().trim());
            String cluesPoolCode = ObjectUtils.toString(st.getCell(2, row).getContents().trim());
            String cluesPoolName = ObjectUtils.toString(st.getCell(3, row).getContents().trim());
            String cluesSources = ObjectUtils.toString(st.getCell(4, row).getContents().trim());
            String cluesContacts = ObjectUtils.toString(st.getCell(5, row).getContents().trim());
            String phoneNumber = ObjectUtils.toString(st.getCell(6, row).getContents().trim());
            CrmClues crmClues = new CrmClues();

            crmClues.setCluesName(cluesName);

            for (DictItem dictItem : dictItems) {
                if (dictItem.getName().equals(cluesSources)){
                    crmClues.setCluesSources(dictItem.getValue());
                }
            }
            if (StringUtils.isNullOrEmpty(crmClues.getCluesSources())){
                err+="序号"+code+"线索来源不存在";
            }
            crmClues.setCluesResponsible(SessionUtils.getUser().getId());
            crmClues.setCluesContacts(cluesContacts);
            crmClues.setPhoneNumber(phoneNumber);
            crmClues.setCluesState("-1");
//            MyCrmCluePool myCrmCluePool = new MyCrmCluePool();


            /*获取子表列表   线索池管理*/
            params = Lists.newArrayList();

            params.add(new Parameter("cpl_name", "=", cluesPoolName));
            List<MyCrmCluePool> myCrmCluePools = myCrmCluePoolService.listAll(params, "");
            if (myCrmCluePools.size()==0){
                err+="序号"+code+"线索池不存在";
            }

            crmClues.setCluesPool(myCrmCluePools.get(0));
            list.add(crmClues);
        }
        return this.bulkInsert(list).size();
    }
    private void checkFile(Sheet sheet) throws Exception {
        if (        "序号".equals(sheet.getCell(0, 0).getContents())
                && "线索名称".equals(sheet.getCell(1, 0).getContents())
                && "所属线索池编号".equals(sheet.getCell(2, 0).getContents())
                && "所属线索池名称".equals(sheet.getCell(3, 0).getContents())
                && "线索来源".equals(sheet.getCell(4, 0).getContents())
                && "线索联系人".equals(sheet.getCell(5, 0).getContents())
                && "手机号".equals(sheet.getCell(6, 0).getContents())
        )
        {}
        else
        {
            throw new Exception("模版不正确!请重新选择");
        }
    }
}
