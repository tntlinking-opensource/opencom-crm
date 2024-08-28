import request from '@/utils/request'

export const getCrmCustomerProductsById = (id) =>
  request({
    url: '/crmCustomer/crmCustomerProducts/' + id,
    method: 'get'
  })

export const listCrmCustomerProductsPage = (search) =>
  request({
    url: '/crmCustomer/crmCustomerProducts/list',
    method: 'post',
    data: search
  })

export const listCrmCustomerProductsAll = (search) =>
  request({
    url: '/crmCustomer/crmCustomerProducts/listAll',
    method: 'post',
    data: search
  })

export const listProductsAll = (search) =>
  request({
    url: '/crmCustomer/crmCustomerProducts/productsListAll',
    method: 'post',
    data: search
  })

export const saveCrmCustomerProducts = (crmCustomerProducts) =>
  request({
    url: '/crmCustomer/crmCustomerProducts/save',
    method: 'post',
    data: crmCustomerProducts
  })

export const deleteCrmCustomerProducts = (crmCustomerProducts) =>
  request({
    url: '/crmCustomer/crmCustomerProducts/delete',
    method: 'post',
    data: crmCustomerProducts
  })

export const bulkInsertCrmCustomerProducts = (crmCustomerProductss) =>
  request({
    url: '/crmCustomer/crmCustomerProducts/bulkInsert',
    method: 'post',
    data: crmCustomerProductss
  })

export const bulkUpdateCrmCustomerProducts = (crmCustomerProductss) =>
  request({
    url: '/crmCustomer/crmCustomerProducts/bulkUpdate',
    method: 'post',
    data: crmCustomerProductss
  })

export const bulkDeleteCrmCustomerProducts = (crmCustomerProductss) =>
  request({
    url: '/crmCustomer/crmCustomerProducts/bulkDelete',
    method: 'post',
    data: crmCustomerProductss
  })
