import request from '@/utils/request'

export const getCrmContractById = (id) =>
  request({
    url: '/crmBusiness/crmContract/' + id,
    method: 'get'
  })

export const listCrmContractPage = (search) =>
  request({
    url: '/crmBusiness/crmContract/list',
    method: 'post',
    data: search
  })

export const listCrmContractAll = (search) =>
  request({
    url: '/crmBusiness/crmContract/listAll',
    method: 'post',
    data: search
  })

export const deleteCrmContract = (crmContract) =>
  request({
    url: '/crmBusiness/crmContract/delete',
    method: 'post',
    data: crmContract
  })

export const getCrmContractByTaskId = (TaskId) =>
  request({
    url: '/crmBusiness/crmContract/task/' + TaskId,
    method: 'get'
  })
export const saveDraftCrmContract = (formData) =>
  request({
    url: '/crmBusiness/crmContract/saveDraft',
    method: 'post',
    data: formData
  })
export const createCrmContract = (processId, formData) =>
  request({
    url: '/crmBusiness/crmContract/' + processId + '/create',
    method: 'post',
    data: formData
  })

export const reapplyCrmContract = (taskId, formData) =>
  request({
    url: '/crmBusiness/crmContract/' + taskId + '/reapply',
    method: 'post',
    data: formData
  })

export const agreeCrmContract = (taskId, formData) =>
  request({
    url: '/crmBusiness/crmContract/' + taskId + '/agree',
    method: 'post',
    data: formData
  })

export const forwardCrmContract = (taskId, formData) =>
  request({
    url: '/crmBusiness/crmContract/' + taskId + '/forward',
    method: 'post',
    data: formData
  })

export const commissionCrmContract = (taskId, formData) =>
  request({
    url: '/crmBusiness/crmContract/' + taskId + '/commission',
    method: 'post',
    data: formData
  })

export const proposeCrmContract = (taskId, formData) =>
  request({
    url: '/crmBusiness/crmContract/' + taskId + '/propose',
    method: 'post',
    data: formData
  })

export const backCrmContract = (taskId, formData) =>
  request({
    url: '/crmBusiness/crmContract/' + taskId + '/back',
    method: 'post',
    data: formData
  })
export const rejectCrmContract = (taskId, formData) =>
  request({
    url: '/crmBusiness/crmContract/' + taskId + '/reject',
    method: 'post',
    data: formData
  })

export const terminateCrmContract = (taskId, formData) =>
  request({
    url: '/crmBusiness/crmContract/' + taskId + '/terminate',
    method: 'post',
    data: formData
  })

export const reverseCrmContract = (taskId) =>
  request({
    url: '/crmBusiness/crmContract/' + taskId + '/reverse',
    method: 'post'
  })

export const listBackActivity = (taskId) =>
  request({
    url: '/crmBusiness/crmContract/' + taskId + '/listBackActivity',
    method: 'get'
  })
