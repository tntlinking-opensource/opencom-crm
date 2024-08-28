import request from '@/utils/request'

export const getCrmCustomerCotById = (id) =>
  request({
    url: '/crmCustomer/crmCustomerCot/' + id,
    method: 'get'
  })
export const getCrmCustomersCotByCusId = (cusId) =>
  request({
    url: '/crmCustomer/crmCustomerCot/getCrmCustomersCotByCusId?cusId='+cusId,
    method: 'get'
  })
export const listCrmCustomerCotPage = (search) =>
  request({
    url: '/crmCustomer/crmCustomerCot/list',
    method: 'post',
    data: search
  })

export const listCrmCustomerCotAll = (search) =>
  request({
    url: '/crmCustomer/crmCustomerCot/listAll',
    method: 'post',
    data: search
  })

export const listCotAll = (search) =>
  request({
    url: '/crmCustomer/crmCustomerCot/cotListAll',
    method: 'post',
    data: search
  })

export const saveCrmCustomerCot = (crmCustomerCot) =>
  request({
    url: '/crmCustomer/crmCustomerCot/save',
    method: 'post',
    data: crmCustomerCot
  })

export const deleteCrmCustomerCot = (crmCustomerCot) =>
  request({
    url: '/crmCustomer/crmCustomerCot/delete',
    method: 'post',
    data: crmCustomerCot
  })

export const bulkInsertCrmCustomerCot = (crmCustomerCots) =>
  request({
    url: '/crmCustomer/crmCustomerCot/bulkInsert',
    method: 'post',
    data: crmCustomerCots
  })

export const bulkUpdateCrmCustomerCot = (crmCustomerCots) =>
  request({
    url: '/crmCustomer/crmCustomerCot/bulkUpdate',
    method: 'post',
    data: crmCustomerCots
  })

export const bulkDeleteCrmCustomerCot = (crmCustomerCots) =>
  request({
    url: '/crmCustomer/crmCustomerCot/bulkDelete',
    method: 'post',
    data: crmCustomerCots
  })
