import request from '@/utils/request'

export const getCrmCustomerManagementById = () =>
  request({
    url: '/customerManagement/crmCustomerManagement/',
    method: 'get'
  })

export const getCrmCustomerManagementByIds = () =>
  request({
    url: '/customerManagement/crmCustomerManagement/getbyids',
    method: 'get'
  })

export const getCrmddfzrs = () =>
  request({
    url: '/customerManagement/crmCustomerManagement/getCrmddfzrs',
    method: 'get'
  })

export const listCrmCustomerManagementPage = (search) =>
  request({
    url: '/customerManagement/crmCustomerManagement/list',
    method: 'post',
    data: search
  })

export const listCrmCustomerManagementAll = (search) =>
  request({
    url: '/customerManagement/crmCustomerManagement/listAll',
    method: 'post',
    data: search
  })

export const saveCrmCustomerManagement = (crmCustomerManagement) =>
  request({
    url: '/customerManagement/crmCustomerManagement/save',
    method: 'post',
    data: crmCustomerManagement
  })

export const deleteCrmCustomerManagement = (crmCustomerManagement) =>
  request({
    url: '/customerManagement/crmCustomerManagement/delete',
    method: 'post',
    data: crmCustomerManagement
  })

export const bulkInsertCrmCustomerManagement = (crmCustomerManagements) =>
  request({
    url: '/customerManagement/crmCustomerManagement/bulkInsert',
    method: 'post',
    data: crmCustomerManagements
  })

export const bulkUpdateCrmCustomerManagement = (crmCustomerManagements) =>
  request({
    url: '/customerManagement/crmCustomerManagement/bulkUpdate',
    method: 'post',
    data: crmCustomerManagements
  })

export const bulkDeleteCrmCustomerManagement = (crmCustomerManagements) =>
  request({
    url: '/customerManagement/crmCustomerManagement/bulkDelete',
    method: 'post',
    data: crmCustomerManagements
  })
