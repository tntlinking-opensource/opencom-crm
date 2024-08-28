import request from '@/utils/request'

export const getCrmProductsClassifyById = (id) =>
  request({
    url: '/crmProClassify/crmProductsClassify/' + id,
    method: 'get'
  })

export const listCrmProductsClassifyPage = (search) =>
  request({
    url: '/crmProClassify/crmProductsClassify/list',
    method: 'post',
    data: search
  })

export const listCrmProductsClassifyAll = (search) =>
  request({
    url: '/crmProClassify/crmProductsClassify/listAll',
    method: 'post',
    data: search
  })

export const treeCrmProductsClassify = (search) =>
  request({
    url: '/crmProClassify/crmProductsClassify/tree',
    method: 'post',
    data: search
  })

export const saveCrmProductsClassify = (crmProductsClassify) =>
  request({
    url: '/crmProClassify/crmProductsClassify/save',
    method: 'post',
    data: crmProductsClassify
  })

export const deleteCrmProductsClassify = (crmProductsClassify) =>
  request({
    url: '/crmProClassify/crmProductsClassify/delete',
    method: 'post',
    data: crmProductsClassify
  })

export const bulkInsertCrmProductsClassify = (crmProductsClassifys) =>
  request({
    url: '/crmProClassify/crmProductsClassify/bulkInsert',
    method: 'post',
    data: crmProductsClassifys
  })

export const bulkUpdateCrmProductsClassify = (crmProductsClassifys) =>
  request({
    url: '/crmProClassify/crmProductsClassify/bulkUpdate',
    method: 'post',
    data: crmProductsClassifys
  })

export const bulkDeleteCrmProductsClassify = (crmProductsClassifys) =>
  request({
    url: '/crmProClassify/crmProductsClassify/bulkDelete',
    method: 'post',
    data: crmProductsClassifys
  })
