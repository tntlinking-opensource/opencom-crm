import request from '@/utils/request'

export const getCrmCustomerById = (id) =>
  request({
    url: '/crmCustomer/crmCustomer/' + id,
    method: 'get'
  })

  export const crmCustomerSave = (data) =>
  request({
    url: 'crmCustomer/crmCustomer/save',
    method: 'post',
    data
  })

  export const crmCustomerSendPayQuotation = (data) =>
  request({
    url: '/crmCustomer/crmCustomer/sendPayQuotation',
    method: 'post',
    data
  })
  

 
export const listCrmCustomerPage = (search) =>
  request({
    url: '/crmCustomer/crmCustomer/list',
    method: 'post',
    data: search
  })

export const listCrmCustomerAll = (search) =>
  request({
    url: '/crmCustomer/crmCustomer/listAll',
    method: 'post',
    data: search
  })

export const treeCrmCustomer = (search) =>
  request({
    url: '/crmCustomer/crmCustomer/tree',
    method: 'post',
    data: search
  })

export const saveCrmCustomer = (crmCustomer) =>
  request({
    url: '/crmCustomer/crmCustomer/save',
    method: 'post',
    data: crmCustomer
  })

export const deleteCrmCustomer = (crmCustomer) =>
  request({
    url: '/crmCustomer/crmCustomer/delete',
    method: 'post',
    data: crmCustomer
  })

export const bulkInsertCrmCustomer = (crmCustomers) =>
  request({
    url: '/crmCustomer/crmCustomer/bulkInsert',
    method: 'post',
    data: crmCustomers
  })

export const bulkUpdateCrmCustomer = (crmCustomers) =>
  request({
    url: '/crmCustomer/crmCustomer/bulkUpdate',
    method: 'post',
    data: crmCustomers
  })

export const bulkDeleteCrmCustomer = (crmCustomers) =>
  request({
    url: '/crmCustomer/crmCustomer/bulkDelete',
    method: 'post',
    data: crmCustomers
  })
