import request from '@/utils/request'

export const getCrmContactsById = (id) =>
  request({
    url: '/contacts/crmContacts/' + id,
    method: 'get'
  })

export const listCrmContactsPage = (search) =>
  request({
    url: '/contacts/crmContacts/list',
    method: 'post',
    data: search
  })

export const listCrmContactsAll = (search) =>
  request({
    url: '/contacts/crmContacts/listAll',
    method: 'post',
    data: search
  })

export const saveCrmContacts = (crmContacts) =>
  request({
    url: '/contacts/crmContacts/save',
    method: 'post',
    data: crmContacts
  })

export const deleteCrmContacts = (crmContacts) =>
  request({
    url: '/contacts/crmContacts/delete',
    method: 'post',
    data: crmContacts
  })

export const bulkInsertCrmContacts = (crmContactss) =>
  request({
    url: '/contacts/crmContacts/bulkInsert',
    method: 'post',
    data: crmContactss
  })

export const bulkUpdateCrmContacts = (crmContactss) =>
  request({
    url: '/contacts/crmContacts/bulkUpdate',
    method: 'post',
    data: crmContactss
  })

export const bulkDeleteCrmContacts = (crmContactss) =>
  request({
    url: '/contacts/crmContacts/bulkDelete',
    method: 'post',
    data: crmContactss
  })
