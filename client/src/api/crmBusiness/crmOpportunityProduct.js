import request from '@/utils/request'

export const getCrmOpportunityProductById = (id) =>
  request({
    url: '/crmBusiness/crmOpportunityProduct/' + id,
    method: 'get'
  })

export const listCrmOpportunityProductPage = (search) =>
  request({
    url: '/crmBusiness/crmOpportunityProduct/list',
    method: 'post',
    data: search
  })

export const listCrmOpportunityProductAll = (search) =>
  request({
    url: '/crmBusiness/crmOpportunityProduct/listAll',
    method: 'post',
    data: search
  })

export const saveCrmOpportunityProduct = (crmOpportunityProduct) =>
  request({
    url: '/crmBusiness/crmOpportunityProduct/save',
    method: 'post',
    data: crmOpportunityProduct
  })

export const deleteCrmOpportunityProduct = (crmOpportunityProduct) =>
  request({
    url: '/crmBusiness/crmOpportunityProduct/delete',
    method: 'post',
    data: crmOpportunityProduct
  })

export const bulkInsertCrmOpportunityProduct = (crmOpportunityProducts) =>
  request({
    url: '/crmBusiness/crmOpportunityProduct/bulkInsert',
    method: 'post',
    data: crmOpportunityProducts
  })

export const bulkUpdateCrmOpportunityProduct = (crmOpportunityProducts) =>
  request({
    url: '/crmBusiness/crmOpportunityProduct/bulkUpdate',
    method: 'post',
    data: crmOpportunityProducts
  })

export const bulkDeleteCrmOpportunityProduct = (crmOpportunityProducts) =>
  request({
    url: '/crmBusiness/crmOpportunityProduct/bulkDelete',
    method: 'post',
    data: crmOpportunityProducts
  })
