package com.geeke.crmCluePool.service;

import com.geeke.common.constants.ActionConstants;
import com.geeke.common.data.Page;
import com.geeke.common.data.PageRequest;
import com.geeke.common.data.Parameter;
import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.crmCluePool.dao.CrmCluePoolMemberDao;
import com.geeke.crmCluePool.entity.CrmCluePoolMember;
import com.geeke.utils.Reflections;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 线索池管理Service
 * @author
 * @version
 */

@Service("crmCluePoolMemberService")
@Transactional(readOnly = true)
public class CrmCluePoolMemberService extends CrudService<CrmCluePoolMemberDao, CrmCluePoolMember> {}
