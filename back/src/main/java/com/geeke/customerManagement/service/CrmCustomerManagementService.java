package com.geeke.customerManagement.service;

import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.customerManagement.dao.CrmCustomerManagementDao;
import com.geeke.customerManagement.entity.CrmCustomerManagement;
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
 * 客户名称查询Service
 * @author
 * @version
 */

@Service("crmCustomerManagementService")
@Transactional(readOnly = true)
public class CrmCustomerManagementService extends CrudService<CrmCustomerManagementDao, CrmCustomerManagement> {}
