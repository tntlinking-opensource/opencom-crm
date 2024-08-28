package com.geeke.crmCommunicate.service;

import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.crmCommunicate.dao.CrmCommunicateDao;
import com.geeke.crmCommunicate.entity.CrmCommunicate;
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
 * 沟通记录Service
 * @author
 * @version
 */

@Service("crmCommunicateService")
@Transactional(readOnly = true)
public class CrmCommunicateService extends CrudService<CrmCommunicateDao, CrmCommunicate> {}
