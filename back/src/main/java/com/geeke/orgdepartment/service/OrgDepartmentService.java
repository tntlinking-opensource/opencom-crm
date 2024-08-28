package com.geeke.orgdepartment.service;

import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.orgdepartment.dao.OrgDepartmentDao;
import com.geeke.orgdepartment.entity.OrgDepartment;
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
 * 部门查询Service
 * @author
 * @version
 */

@Service("orgDepartmentService")
@Transactional(readOnly = true)
public class OrgDepartmentService extends CrudService<OrgDepartmentDao, OrgDepartment> {}
