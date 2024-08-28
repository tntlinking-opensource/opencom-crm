import request from '@/utils/request'

export const getCrmOpportunityLinkById = (id) =>
  request({
    url: '/crmBusiness/crmOpportunityLink/' + id,
    method: 'get'
  })

export const listCrmOpportunityLinkPage = (search) =>
  request({
    url: '/crmBusiness/crmOpportunityLink/list',
    method: 'post',
    data: search
  })

export const listCrmOpportunityLinkAll = (search) =>
  request({
    url: '/crmBusiness/crmOpportunityLink/listAll',
    method: 'post',
    data: search
  })

export const saveCrmOpportunityLink = (crmOpportunityLink) =>
  request({
    url: '/crmBusiness/crmOpportunityLink/save',
    method: 'post',
    data: crmOpportunityLink
  })

export const saveCrmtacts = (crmtacts) =>
  request({
    url: '/crmBusiness/crmOpportunityLink/saveCrmContacts',
    method: 'post',
    data: crmtacts
  })

export const deleteCrmOpportunityLink = (crmOpportunityLink) =>
  request({
    url: '/crmBusiness/crmOpportunityLink/delete',
    method: 'post',
    data: crmOpportunityLink
  })

export const bulkInsertCrmOpportunityLink = (crmOpportunityLinks) =>
  request({
    url: '/crmBusiness/crmOpportunityLink/bulkInsert',
    method: 'post',
    data: crmOpportunityLinks
  })

export const bulkUpdateCrmOpportunityLink = (crmOpportunityLinks) =>
  request({
    url: '/crmBusiness/crmOpportunityLink/bulkUpdate',
    method: 'post',
    data: crmOpportunityLinks
  })

export const bulkDeleteCrmOpportunityLink = (crmOpportunityLinks) =>
  request({
    url: '/crmBusiness/crmOpportunityLink/bulkDelete',
    method: 'post',
    data: crmOpportunityLinks
  })
export const getSendEmail = () =>
  request({
    url: '/crmBusiness/crmOpportunityLink/getSendEmail',
    method: 'post',
  })
