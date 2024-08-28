import request from '@/utils/request'

export const getCrmCustomerPayById = (id) =>
  request({
    url: '/crmCustomer/crmCustomerPay/' + id,
    method: 'get'
  })

  export const saveCrmCustomerPayBacks = (data) =>
  request({
    url: '/crmCustomer/crmCustomerPay/savePayBacks',
    method: 'post',
    data
  })

export const listCrmCustomerPayPage = (search) =>
  request({
    url: '/crmCustomer/crmCustomerPay/list',
    method: 'post',
    data: search
  })

export const listCrmCustomerPayAll = (search) =>
  request({
    url: '/crmCustomer/crmCustomerPay/listAll',
    method: 'post',
    data: search
  })

export const listPayAll = (search) =>
  request({
    url: '/crmCustomer/crmCustomerPay/payListAll',
    method: 'post',
    data: search
  })

export const saveCrmCustomerPay = (crmCustomerPay) =>
  request({
    url: '/crmCustomer/crmCustomerPay/save',
    method: 'post',
    data: crmCustomerPay
  })

export const deleteCrmCustomerPay = (crmCustomerPay) =>
  request({
    url: '/crmCustomer/crmCustomerPay/delete',
    method: 'post',
    data: crmCustomerPay
  })

export const bulkInsertCrmCustomerPay = (crmCustomerPays) =>
  request({
    url: '/crmCustomer/crmCustomerPay/bulkInsert',
    method: 'post',
    data: crmCustomerPays
  })

export const bulkUpdateCrmCustomerPay = (crmCustomerPays) =>
  request({
    url: '/crmCustomer/crmCustomerPay/bulkUpdate',
    method: 'post',
    data: crmCustomerPays
  })

export const bulkDeleteCrmCustomerPay = (crmCustomerPays) =>
  request({
    url: '/crmCustomer/crmCustomerPay/bulkDelete',
    method: 'post',
    data: crmCustomerPays
  })
