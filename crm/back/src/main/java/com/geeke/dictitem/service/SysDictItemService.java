package com.geeke.dictitem.service;

import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.dictitem.dao.SysDictItemDao;
import com.geeke.dictitem.entity.SysDictItem;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 码表查询Service
 * @author
 * @version
 */

@Service("sysDictItemService")
@Transactional(readOnly = true)
public class SysDictItemService extends CrudService<SysDictItemDao, SysDictItem> {}
