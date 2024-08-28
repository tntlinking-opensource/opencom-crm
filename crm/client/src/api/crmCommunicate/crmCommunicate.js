import request from '@/utils/request'

export const getCrmCommunicateById = (id) =>
  request({
    url: '/crmCommunicate/crmCommunicate/' + id,
    method: 'get'
  })

export const listCrmCommunicatePage = (search) =>
  request({
    url: '/crmCommunicate/crmCommunicate/list',
    method: 'post',
    data: search
  })

export const listCrmCommunicateAll = (search) =>
  request({
    url: '/crmCommunicate/crmCommunicate/listAll',
    method: 'post',
    data: search
  })

export const saveCrmCommunicate = (crmCommunicate) =>
  request({
    url: '/crmCommunicate/crmCommunicate/save',
    method: 'post',
    data: crmCommunicate
  })

export const deleteCrmCommunicate = (crmCommunicate) =>
  request({
    url: '/crmCommunicate/crmCommunicate/delete',
    method: 'post',
    data: crmCommunicate
  })

export const bulkInsertCrmCommunicate = (crmCommunicates) =>
  request({
    url: '/crmCommunicate/crmCommunicate/bulkInsert',
    method: 'post',
    data: crmCommunicates
  })

export const bulkUpdateCrmCommunicate = (crmCommunicates) =>
  request({
    url: '/crmCommunicate/crmCommunicate/bulkUpdate',
    method: 'post',
    data: crmCommunicates
  })

export const bulkDeleteCrmCommunicate = (crmCommunicates) =>
  request({
    url: '/crmCommunicate/crmCommunicate/bulkDelete',
    method: 'post',
    data: crmCommunicates
  })
