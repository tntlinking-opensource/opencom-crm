import request from '@/utils/request'

export const getCrmCluesById = (id) =>
  request({
    url: '/myClue/crmClues/' + id,
    method: 'get'
  })

export const listCrmCluesPage = (search) =>
  request({
    url: '/myClue/crmClues/list',
    method: 'post',
    data: search
  })

export const listCrmCluesAll = (search) =>
  request({
    url: '/myClue/crmClues/listAll',
    method: 'post',
    data: search
  })

export const saveCrmClues = (crmClues) =>
  request({
    url: '/myClue/crmClues/save',
    method: 'post',
    data: crmClues
  })

export const deleteCrmClues = (crmClues) =>
  request({
    url: '/myClue/crmClues/delete',
    method: 'post',
    data: crmClues
  })

export const bulkInsertCrmClues = (crmCluess) =>
  request({
    url: '/myClue/crmClues/bulkInsert',
    method: 'post',
    data: crmCluess
  })

export const bulkUpdateCrmClues = (crmCluess) =>
  request({
    url: '/myClue/crmClues/bulkUpdate',
    method: 'post',
    data: crmCluess
  })

export const bulkDeleteCrmClues = (crmCluess) =>
  request({
    url: '/myClue/crmClues/bulkDelete',
    method: 'post',
    data: crmCluess
  })
export const physicsDelete = (crmCluess) =>
  request({
    url: '/myClue/crmClues/physicsDelete',
    method: 'post',
    data: crmCluess
  })
export const importExport = (formData) =>
  request({
    url: '/myClue/crmClues/importExport',
    method: 'post',
    data: formData
  })
export const importExport1 = (formData) =>
  request({
    url: '/myClue/crmClues/importExport1',
    method: 'post',
    data: formData
  })
