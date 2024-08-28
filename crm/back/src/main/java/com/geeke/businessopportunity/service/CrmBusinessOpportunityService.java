package com.geeke.businessopportunity.service;

import com.geeke.businessopportunity.dao.CrmBusinessOpportunityDao;
import com.geeke.businessopportunity.entity.CrmBusinessOpportunity;
import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
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
 * 商机查询Service
 * @author
 * @version
 */

@Service("crmBusinessOpportunityService")
@Transactional(readOnly = true)
public class CrmBusinessOpportunityService extends CrudService<CrmBusinessOpportunityDao, CrmBusinessOpportunity> {}
