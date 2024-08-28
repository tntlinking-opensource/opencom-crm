import request from '@/utils/request'

export const getByContractId = (id) =>
  request({
    url: '/paymentBack/' + id,
    method: 'get'
  })

export const listPaymentBackPage = (search) =>
  request({
    url: '/paymentBack/list',
    method: 'post',
    data: search
  })

export const listCrmContractAll = (search) =>
  request({
    url: '/contract/crmContract/listAll',
    method: 'post',
    data: search
  })

export const deleteCrmContract = (crmContract) =>
  request({
    url: '/contract/crmContract/delete',
    method: 'post',
    data: crmContract
  })

export const getCrmContractByTaskId = (TaskId) =>
  request({
    url: '/contract/crmContract/task/' + TaskId,
    method: 'get'
  })
export const savePaymentBack = (formData) =>
  request({
    url: '/paymentBack/bulkUpdate',
    method: 'post',
    data: formData
  })
export const createCrmContract = (processId, formData) =>
  request({
    url: '/contract/crmContract/' + processId + '/create',
    method: 'post',
    data: formData
  })

export const reapplyCrmContract = (taskId, formData) =>
  request({
    url: '/contract/crmContract/' + taskId + '/reapply',
    method: 'post',
    data: formData
  })

export const agreeCrmContract = (taskId, formData) =>
  request({
    url: '/contract/crmContract/' + taskId + '/agree',
    method: 'post',
    data: formData
  })

export const forwardCrmContract = (taskId, formData) =>
  request({
    url: '/contract/crmContract/' + taskId + '/forward',
    method: 'post',
    data: formData
  })

export const commissionCrmContract = (taskId, formData) =>
  request({
    url: '/contract/crmContract/' + taskId + '/commission',
    method: 'post',
    data: formData
  })

export const proposeCrmContract = (taskId, formData) =>
  request({
    url: '/contract/crmContract/' + taskId + '/propose',
    method: 'post',
    data: formData
  })

export const backCrmContract = (taskId, formData) =>
  request({
    url: '/contract/crmContract/' + taskId + '/back',
    method: 'post',
    data: formData
  })
export const rejectCrmContract = (taskId, formData) =>
  request({
    url: '/contract/crmContract/' + taskId + '/reject',
    method: 'post',
    data: formData
  })

export const terminateCrmContract = (taskId, formData) =>
  request({
    url: '/contract/crmContract/' + taskId + '/terminate',
    method: 'post',
    data: formData
  })

export const reverseCrmContract = (taskId) =>
  request({
    url: '/contract/crmContract/' + taskId + '/reverse',
    method: 'post'
  })

export const listBackActivity = (taskId) =>
  request({
    url: '/contract/crmContract/' + taskId + '/listBackActivity',
    method: 'get'
  })
