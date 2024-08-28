import request from '@/utils/request'

export const getCrmCluePoolById = (id) =>
  request({
    url: '/crmCluePool/crmCluePool/' + id,
    method: 'get'
  })

export const listCrmCluePoolPage = (search) =>
  request({
    url: '/crmCluePool/crmCluePool/list',
    method: 'post',
    data: search
  })

export const listCrmCluePoolAll = (search) =>
  request({
    url: '/crmCluePool/crmCluePool/listAll',
    method: 'post',
    data: search
  })

export const saveCrmCluePool = (crmCluePool) =>
  request({
    url: '/crmCluePool/crmCluePool/save',
    method: 'post',
    data: crmCluePool
  })

export const deleteCrmCluePool = (crmCluePool) =>
  request({
    url: '/crmCluePool/crmCluePool/delete',
    method: 'post',
    data: crmCluePool
  })

export const bulkInsertCrmCluePool = (crmCluePools) =>
  request({
    url: '/crmCluePool/crmCluePool/bulkInsert',
    method: 'post',
    data: crmCluePools
  })

export const bulkUpdateCrmCluePool = (crmCluePools) =>
  request({
    url: '/crmCluePool/crmCluePool/bulkUpdate',
    method: 'post',
    data: crmCluePools
  })

export const bulkDeleteCrmCluePool = (crmCluePools) =>
  request({
    url: '/crmCluePool/crmCluePool/bulkDelete',
    method: 'post',
    data: crmCluePools
  })

export function seleStaff(data) {
  return request({
    url: '/crmCluePool/crmCluePool/getseleuserls',
    method: 'get',
    params:data
  })
}


export const saveccpl = (params) =>
  request({
    url: '/crmCluePool/crmCluePool/saveccpl',
    method: 'post',
    params:params
  })


export const mergecpl = (params) =>
  request({
    url: '/crmCluePool/crmCluePool/mergecpl',
    method: 'post',
    params:params
  })
