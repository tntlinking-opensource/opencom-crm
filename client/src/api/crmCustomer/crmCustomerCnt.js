import request from '@/utils/request'

export const getCrmCustomerCntById = (id) =>
  request({
    url: '/crmCustomer/crmCustomerCnt/' + id,
    method: 'get'
  })

  export const crmCustomerSaveCusWithCom = (data) =>
  request({
    url: '/crmCustomer/crmCustomerCnt/saveCusWithCom',
    method: 'post',
    data
  })

export const listCrmCustomerCntPage = (search) =>
  request({
    url: '/crmCustomer/crmCustomerCnt/list',
    method: 'post',
    data: search
  })

export const listCrmCustomerCntAll = (search) =>
  request({
    url: '/crmCustomer/crmCustomerCnt/listAll',
    method: 'post',
    data: search
  })

export const listCntAll = (search) =>
  request({
    url: '/crmCustomer/crmCustomerCnt/cntListAll',
    method: 'post',
    data: search
  })

export const saveCrmCustomerCnt = (crmCustomerCnt) =>
  request({
    url: '/crmCustomer/crmCustomerCnt/save',
    method: 'post',
    data: crmCustomerCnt
  })

export const deleteCrmCustomerCnt = (crmCustomerCnt) =>
  request({
    url: '/crmCustomer/crmCustomerCnt/delete',
    method: 'post',
    data: crmCustomerCnt
  })

export const bulkInsertCrmCustomerCnt = (crmCustomerCnts) =>
  request({
    url: '/crmCustomer/crmCustomerCnt/bulkInsert',
    method: 'post',
    data: crmCustomerCnts
  })

export const bulkUpdateCrmCustomerCnt = (crmCustomerCnts) =>
  request({
    url: '/crmCustomer/crmCustomerCnt/bulkUpdate',
    method: 'post',
    data: crmCustomerCnts
  })

export const bulkDeleteCrmCustomerCnt = (crmCustomerCnts) =>
  request({
    url: '/crmCustomer/crmCustomerCnt/bulkDelete',
    method: 'post',
    data: crmCustomerCnts
  })
