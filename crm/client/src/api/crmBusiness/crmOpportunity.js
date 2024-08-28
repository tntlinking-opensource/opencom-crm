import request from '@/utils/request'

export const getCrmOpportunityById = (id) =>
  request({
    url: '/crmBusiness/crmOpportunity/' + id,
    method: 'get'
  })

export const listCrmOpportunityPage = (search) =>
  request({
    url: '/crmBusiness/crmOpportunity/list',
    method: 'post',
    data: search
  })

export const listCrmOpportunityAll = (search) =>
  request({
    url: '/crmBusiness/crmOpportunity/listAll',
    method: 'post',
    data: search
  })

export const saveCrmOpportunity = (crmOpportunity) =>
  request({
    url: '/crmBusiness/crmOpportunity/save',
    method: 'post',
    data: crmOpportunity
  })

export const deleteCrmOpportunity = (crmOpportunity) =>
  request({
    url: '/crmBusiness/crmOpportunity/delete',
    method: 'post',
    data: crmOpportunity
  })

export const bulkInsertCrmOpportunity = (crmOpportunitys) =>
  request({
    url: '/crmBusiness/crmOpportunity/bulkInsert',
    method: 'post',
    data: crmOpportunitys
  })

export const bulkUpdateCrmOpportunity = (crmOpportunitys) =>
  request({
    url: '/crmBusiness/crmOpportunity/bulkUpdate',
    method: 'post',
    data: crmOpportunitys
  })

export const bulkDeleteCrmOpportunity = (crmOpportunitys) =>
  request({
    url: '/crmBusiness/crmOpportunity/bulkDelete',
    method: 'post',
    data: crmOpportunitys
  })
export const sendPayQuotation = (forData) =>
  request({
    url: '/crmBusiness/crmOpportunity/sendPayQuotation',
    method: 'post',
    data: forData
  })
