package com.geeke.myClue.service;

import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.myClue.dao.CrmMyclueProductsDao;
import com.geeke.myClue.entity.CrmMyclueProducts;
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
 * 我的线索Service
 * @author
 * @version
 */

@Service("crmMyclueProductsService")
@Transactional(readOnly = true)
public class CrmMyclueProductsService extends CrudService<CrmMyclueProductsDao, CrmMyclueProducts> {}
