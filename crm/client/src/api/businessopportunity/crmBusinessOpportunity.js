import request from '@/utils/request'

export const getCrmBusinessOpportunityById = (id) =>
  request({
    url: '/businessopportunity/crmBusinessOpportunity/' + id,
    method: 'get'
  })

export const getCrmBusinessOpportunityBycusname = (id) =>
  request({
    url: '/businessopportunity/crmBusinessOpportunity/getcusnames/' + id,
    method: 'get'
  })

export const listCrmBusinessOpportunityPage = (search) =>
  request({
    url: '/businessopportunity/crmBusinessOpportunity/list',
    method: 'post',
    data: search
  })

export const listCrmBusinessOpportunityAll = (search) =>
  request({
    url: '/businessopportunity/crmBusinessOpportunity/listAll',
    method: 'post',
    data: search
  })

export const saveCrmBusinessOpportunity = (crmBusinessOpportunity) =>
  request({
    url: '/businessopportunity/crmBusinessOpportunity/save',
    method: 'post',
    data: crmBusinessOpportunity
  })

export const deleteCrmBusinessOpportunity = (crmBusinessOpportunity) =>
  request({
    url: '/businessopportunity/crmBusinessOpportunity/delete',
    method: 'post',
    data: crmBusinessOpportunity
  })

export const bulkInsertCrmBusinessOpportunity = (crmBusinessOpportunitys) =>
  request({
    url: '/businessopportunity/crmBusinessOpportunity/bulkInsert',
    method: 'post',
    data: crmBusinessOpportunitys
  })

export const bulkUpdateCrmBusinessOpportunity = (crmBusinessOpportunitys) =>
  request({
    url: '/businessopportunity/crmBusinessOpportunity/bulkUpdate',
    method: 'post',
    data: crmBusinessOpportunitys
  })

export const bulkDeleteCrmBusinessOpportunity = (crmBusinessOpportunitys) =>
  request({
    url: '/businessopportunity/crmBusinessOpportunity/bulkDelete',
    method: 'post',
    data: crmBusinessOpportunitys
  })
