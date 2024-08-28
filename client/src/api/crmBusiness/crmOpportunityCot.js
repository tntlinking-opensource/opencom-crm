import request from '@/utils/request'

export const getCrmOpportunityCotById = (id) =>
  request({
    url: '/crmBusiness/crmOpportunityCot/' + id,
    method: 'get'
  })

export const listCrmOpportunityCotPage = (search) =>
  request({
    url: '/crmBusiness/crmOpportunityCot/list',
    method: 'post',
    data: search
  })

export const listCrmOpportunityCotAll = (search) =>
  request({
    url: '/crmBusiness/crmOpportunityCot/listAll',
    method: 'post',
    data: search
  })

export const saveCrmOpportunityCot = (crmOpportunityCot) =>
  request({
    url: '/crmBusiness/crmOpportunityCot/save',
    method: 'post',
    data: crmOpportunityCot
  })

export const saveCrmContract = (formData) =>
  request({
    url: '/crmBusiness/crmOpportunityCot/saveContract',
    method: 'post',
    data: formData
  })

export const deleteCrmOpportunityCot = (crmOpportunityCot) =>
  request({
    url: '/crmBusiness/crmOpportunityCot/delete',
    method: 'post',
    data: crmOpportunityCot
  })

export const bulkInsertCrmOpportunityCot = (crmOpportunityCots) =>
  request({
    url: '/crmBusiness/crmOpportunityCot/bulkInsert',
    method: 'post',
    data: crmOpportunityCots
  })

export const bulkUpdateCrmOpportunityCot = (crmOpportunityCots) =>
  request({
    url: '/crmBusiness/crmOpportunityCot/bulkUpdate',
    method: 'post',
    data: crmOpportunityCots
  })

export const bulkDeleteCrmOpportunityCot = (crmOpportunityCots) =>
  request({
    url: '/crmBusiness/crmOpportunityCot/bulkDelete',
    method: 'post',
    data: crmOpportunityCots
  })
