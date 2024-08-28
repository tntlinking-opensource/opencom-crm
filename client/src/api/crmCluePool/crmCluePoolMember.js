import request from '@/utils/request'

export const getCrmCluePoolMemberById = (id) =>
  request({
    url: '/crmCluePool/crmCluePoolMember/' + id,
    method: 'get'
  })

export const listCrmCluePoolMemberPage = (search) =>
  request({
    url: '/crmCluePool/crmCluePoolMember/list',
    method: 'post',
    data: search
  })

export const listCrmCluePoolMemberAll = (search) =>
  request({
    url: '/crmCluePool/crmCluePoolMember/listAll',
    method: 'post',
    data: search
  })

export const saveCrmCluePoolMember = (crmCluePoolMember) =>
  request({
    url: '/crmCluePool/crmCluePoolMember/save',
    method: 'post',
    data: crmCluePoolMember
  })

export const deleteCrmCluePoolMember = (crmCluePoolMember) =>
  request({
    url: '/crmCluePool/crmCluePoolMember/delete',
    method: 'post',
    data: crmCluePoolMember
  })

export const bulkInsertCrmCluePoolMember = (crmCluePoolMembers) =>
  request({
    url: '/crmCluePool/crmCluePoolMember/bulkInsert',
    method: 'post',
    data: crmCluePoolMembers
  })

export const bulkUpdateCrmCluePoolMember = (crmCluePoolMembers) =>
  request({
    url: '/crmCluePool/crmCluePoolMember/bulkUpdate',
    method: 'post',
    data: crmCluePoolMembers
  })

export const bulkDeleteCrmCluePoolMember = (crmCluePoolMembers) =>
  request({
    url: '/crmCluePool/crmCluePoolMember/bulkDelete',
    method: 'post',
    data: crmCluePoolMembers
  })
