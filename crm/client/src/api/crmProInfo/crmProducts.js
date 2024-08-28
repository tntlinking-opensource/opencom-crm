import request from '@/utils/request'

export const getCrmProductsById = (id) =>
  request({
    url: '/crmProInfo/crmProducts/' + id,
    method: 'get'
  })

export const listCrmProductsPage = (search) =>
  request({
    url: '/crmProInfo/crmProducts/list',
    method: 'post',
    data: search
  })

export const listCrmProductsAll = (search) =>
  request({
    url: '/crmProInfo/crmProducts/listAll',
    method: 'post',
    data: search
  })

export const saveCrmProducts = (crmProducts) =>
  request({
    url: '/crmProInfo/crmProducts/save',
    method: 'post',
    data: crmProducts
  })

export const deleteCrmProducts = (crmProducts) =>
  request({
    url: '/crmProInfo/crmProducts/delete',
    method: 'post',
    data: crmProducts
  })

export const bulkInsertCrmProducts = (crmProductss) =>
  request({
    url: '/crmProInfo/crmProducts/bulkInsert',
    method: 'post',
    data: crmProductss
  })

export const bulkUpdateCrmProducts = (crmProductss) =>
  request({
    url: '/crmProInfo/crmProducts/bulkUpdate',
    method: 'post',
    data: crmProductss
  })

export const bulkDeleteCrmProducts = (crmProductss) =>
  request({
    url: '/crmProInfo/crmProducts/bulkDelete',
    method: 'post',
    data: crmProductss
  })
