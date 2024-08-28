import request from '@/utils/request'

export const getCrmContractById = (id) =>
  request({
    url: '/crmCustomer/crmContract/' + id,
    method: 'get'
  })

export const listCrmContractPage = (search) =>
  request({
    url: '/crmCustomer/crmContract/list',
    method: 'post',
    data: search
  })

export const listCrmContractAll = (search) =>
  request({
    url: '/crmCustomer/crmContract/listAll',
    method: 'post',
    data: search
  })

export const deleteCrmContract = (crmContract) =>
  request({
    url: '/crmCustomer/crmContract/delete',
    method: 'post',
    data: crmContract
  })

export const getCrmContractByTaskId = (TaskId) =>
  request({
    url: '/crmCustomer/crmContract/task/' + TaskId,
    method: 'get'
  })
export const saveDraftCrmContract = (formData) =>
  request({
    url: '/crmCustomer/crmContract/saveDraft',
    method: 'post',
    data: formData
  })
export const createCrmContract = (processId, formData) =>
  request({
    url: '/crmCustomer/crmContract/' + processId + '/create',
    method: 'post',
    data: formData
  })

export const reapplyCrmContract = (taskId, formData) =>
  request({
    url: '/crmCustomer/crmContract/' + taskId + '/reapply',
    method: 'post',
    data: formData
  })

export const agreeCrmContract = (taskId, formData) =>
  request({
    url: '/crmCustomer/crmContract/' + taskId + '/agree',
    method: 'post',
    data: formData
  })

export const forwardCrmContract = (taskId, formData) =>
  request({
    url: '/crmCustomer/crmContract/' + taskId + '/forward',
    method: 'post',
    data: formData
  })

export const commissionCrmContract = (taskId, formData) =>
  request({
    url: '/crmCustomer/crmContract/' + taskId + '/commission',
    method: 'post',
    data: formData
  })

export const proposeCrmContract = (taskId, formData) =>
  request({
    url: '/crmCustomer/crmContract/' + taskId + '/propose',
    method: 'post',
    data: formData
  })

export const backCrmContract = (taskId, formData) =>
  request({
    url: '/crmCustomer/crmContract/' + taskId + '/back',
    method: 'post',
    data: formData
  })
export const rejectCrmContract = (taskId, formData) =>
  request({
    url: '/crmCustomer/crmContract/' + taskId + '/reject',
    method: 'post',
    data: formData
  })

export const terminateCrmContract = (taskId, formData) =>
  request({
    url: '/crmCustomer/crmContract/' + taskId + '/terminate',
    method: 'post',
    data: formData
  })

export const reverseCrmContract = (taskId) =>
  request({
    url: '/crmCustomer/crmContract/' + taskId + '/reverse',
    method: 'post'
  })

export const listBackActivity = (taskId) =>
  request({
    url: '/crmCustomer/crmContract/' + taskId + '/listBackActivity',
    method: 'get'
  })
