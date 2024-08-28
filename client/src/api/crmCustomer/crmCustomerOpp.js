import request from '@/utils/request'

export const getCrmCustomerOppById = (id) =>
  request({
    url: '/crmCustomer/crmCustomerOpp/' + id,
    method: 'get'
  })

export const getCrmOppsByCusId = (cusId) =>
  request({
    url: '/crmCustomer/crmCustomerOpp/getCrmOppsByCusId?cusId='+cusId,
    method: 'get'
  })

export const listCrmCustomerOppPage = (search) =>
  request({
    url: '/crmCustomer/crmCustomerOpp/list',
    method: 'post',
    data: search
  })

export const listCrmCustomerOppAll = (search) =>
  request({
    url: '/crmCustomer/crmCustomerOpp/listAll',
    method: 'post',
    data: search
  })

export const listOppAll = (search) =>
  request({
    url: '/crmCustomer/crmCustomerOpp/oppListAll',
    method: 'post',
    data: search
  })

export const saveCrmCustomerOpp = (crmCustomerOpp) =>
  request({
    url: '/crmCustomer/crmCustomerOpp/save',
    method: 'post',
    data: crmCustomerOpp
  })

export const deleteCrmCustomerOpp = (crmCustomerOpp) =>
  request({
    url: '/crmCustomer/crmCustomerOpp/delete',
    method: 'post',
    data: crmCustomerOpp
  })

export const bulkInsertCrmCustomerOpp = (crmCustomerOpps) =>
  request({
    url: '/crmCustomer/crmCustomerOpp/bulkInsert',
    method: 'post',
    data: crmCustomerOpps
  })

export const bulkUpdateCrmCustomerOpp = (crmCustomerOpps) =>
  request({
    url: '/crmCustomer/crmCustomerOpp/bulkUpdate',
    method: 'post',
    data: crmCustomerOpps
  })

export const bulkDeleteCrmCustomerOpp = (crmCustomerOpps) =>
  request({
    url: '/crmCustomer/crmCustomerOpp/bulkDelete',
    method: 'post',
    data: crmCustomerOpps
  })
