import request from '@/utils/request'

export const getCrmInvoiceContract = (data) =>
  request({
    url: '/crmInvoice/crmInvoice/getContractList',
    method: 'post',
    data
  })
export const getCrmInvoiceById = (id) =>
  request({
    url: '/crmInvoice/crmInvoice/' + id,
    method: 'get'
  })

export const listCrmInvoicePage = (search) =>
  request({
    url: '/crmInvoice/crmInvoice/list',
    method: 'post',
    data: search
  })

export const lisCrmInvoiceAll = (search) =>
  request({
    url: '/crmInvoice/crmInvoice/listAll',
    method: 'post',
    data: search
  })

export const deleteCrmInvoice = (CrmInvoice) =>
  request({
    url: '/crmInvoice/crmInvoice/delete',
    method: 'post',
    data: CrmInvoice
  })

export const getCrmInvoiceByTaskId = (TaskId) =>
  request({
    url: '/crmInvoice/crmInvoice/task/' + TaskId,
    method: 'get'
  })
export const saveDraftCrmInvoice = (formData) =>
  request({
    url: '/crmInvoice/crmInvoice/saveDraft',
    method: 'post',
    data: formData
  })
export const createCrmInvoice = (processId, formData) =>
  request({
    url: '/crmInvoice/crmInvoice/' + processId + '/create',
    method: 'post',
    data: formData
  })

export const reapplyCrmInvoice = (taskId, formData) =>
  request({
    url: '/crmInvoice/crmInvoice/' + taskId + '/reapply',
    method: 'post',
    data: formData
  })

export const agreeCrmInvoice = (taskId, formData) =>
  request({
    url: '/crmInvoice/crmInvoice/' + taskId + '/agree',
    method: 'post',
    data: formData
  })

export const forwardCrmInvoice = (taskId, formData) =>
  request({
    url: '/crmInvoice/crmInvoice/' + taskId + '/forward',
    method: 'post',
    data: formData
  })

export const commissionCrmInvoice = (taskId, formData) =>
  request({
    url: '/crmInvoice/crmInvoice/' + taskId + '/commission',
    method: 'post',
    data: formData
  })

export const proposeCrmInvoice = (taskId, formData) =>
  request({
    url: '/crmInvoice/crmInvoice/' + taskId + '/propose',
    method: 'post',
    data: formData
  })

export const backCrmInvoice = (taskId, formData) =>
  request({
    url: '/crmInvoice/crmInvoice/' + taskId + '/back',
    method: 'post',
    data: formData
  })
export const rejectCrmInvoice = (taskId, formData) =>
  request({
    url: '/crmInvoice/crmInvoice/' + taskId + '/reject',
    method: 'post',
    data: formData
  })

export const terminateCrmInvoice = (taskId, formData) =>
  request({
    url: '/crmInvoice/crmInvoice/' + taskId + '/terminate',
    method: 'post',
    data: formData
  })

export const reverseCrmInvoice = (taskId) =>
  request({
    url: '/crmInvoice/crmInvoice/' + taskId + '/reverse',
    method: 'post'
  })
export const listBackActivity = (taskId) =>
  request({
    url: '/crmInvoice/crmInvoice/' + taskId + '/listBackActivity',
    method: 'get'
  })
