import request from '@/utils/request'

export const getCrmOrdersById = (id) =>
  request({
    url: '/orders/crmOrders/' + id,
    method: 'get'
  })

export const listCrmOrdersPage = (search) =>
  request({
    url: '/orders/crmOrders/list',
    method: 'post',
    data: search
  })

export const listCrmOrdersAll = (search) =>
  request({
    url: '/orders/crmOrders/listAll',
    method: 'post',
    data: search
  })

export const saveCrmOrders = (formData) =>
  request({
    url: '/orders/crmOrders/save',
    method: 'post',
    data: formData
  })

export const deleteCrmOrders = (crmOrders) =>
  request({
    url: '/orders/crmOrders/delete',
    method: 'post',
    data: crmOrders
  })

export const bulkInsertCrmOrders = (crmOrderss) =>
  request({
    url: '/orders/crmOrders/bulkInsert',
    method: 'post',
    data: crmOrderss
  })

export const bulkUpdateCrmOrders = (crmOrderss) =>
  request({
    url: '/orders/crmOrders/bulkUpdate',
    method: 'post',
    data: crmOrderss
  })

export const bulkDeleteCrmOrders = (crmOrderss) =>
  request({
    url: '/orders/crmOrders/bulkDelete',
    method: 'post',
    data: crmOrderss
  })

export function getseleddcp(data) {
  return request({
    url: '/orders/crmOrders/getseleddcp',
    method: 'get',
    params:data
  })
}
