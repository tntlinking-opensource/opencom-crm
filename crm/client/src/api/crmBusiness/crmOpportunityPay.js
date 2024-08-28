import request from '@/utils/request'

export const getCrmOpportunityPayById = (id) =>
  request({
    url: '/contract/crmOpportunityPay/' + id,
    method: 'get'
  })

export const listCrmOpportunityPayPage = (search) =>
  request({
    url: '/contract/crmOpportunityPay/list',
    method: 'post',
    data: search
  })

export const listCrmOpportunityPayAll = (search) =>
  request({
    url: '/contract/crmOpportunityPay/listAll',
    method: 'post',
    data: search
  })

export const saveCrmOpportunityPay = (crmOpportunityPay) =>
  request({
    url: '/contract/crmOpportunityPay/save',
    method: 'post',
    data: crmOpportunityPay
  })

export const deleteCrmOpportunityPay = (crmOpportunityPay) =>
  request({
    url: '/contract/crmOpportunityPay/delete',
    method: 'post',
    data: crmOpportunityPay
  })

export const bulkInsertCrmOpportunityPay = (crmOpportunityPays) =>
  request({
    url: '/contract/crmOpportunityPay/bulkInsert',
    method: 'post',
    data: crmOpportunityPays
  })

export const bulkUpdateCrmOpportunityPay = (crmOpportunityPays) =>
  request({
    url: '/contract/crmOpportunityPay/bulkUpdate',
    method: 'post',
    data: crmOpportunityPays
  })

export const bulkDeleteCrmOpportunityPay = (crmOpportunityPays) =>
  request({
    url: '/contract/crmOpportunityPay/bulkDelete',
    method: 'post',
    data: crmOpportunityPays
  })
export const getContractPayDetails = (search) =>
  request({
    url: '/contract/crmContractPay/list',
    method:'post',
    data:search
  })

export const savePayBackInfo = (payBackLists) =>
  request({
    url: '/contract/crmOpportunityPay/savePayBacks',
    method: 'post',
    data: payBackLists
  })

