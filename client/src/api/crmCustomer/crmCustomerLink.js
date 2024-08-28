import request from '@/utils/request'

export const saveCrmtacts = (crmtacts) =>
  request({
    url: 'crmCustomer/crmCustomerLink/saveCrmContacts',
    method: 'post',
    data: crmtacts
  })


export const getCrmCustomerLinkById = (id) =>
  request({
    url: '/crmCustomer/crmCustomerLink/' + id,
    method: 'get'
  })

export const listCrmCustomerLinkPage = (search) =>
  request({
    url: '/crmCustomer/crmCustomerLink/list',
    method: 'post',
    data: search
  })

export const listCrmCustomerLinkAll = (search) =>
  request({
    url: '/crmCustomer/crmCustomerLink/listAll',
    method: 'post',
    data: search
  })

export const listLinkAll = (search) =>
  request({
    url: '/crmCustomer/crmCustomerLink/linkListAll',
    method: 'post',
    data: search
  })

export const saveCrmCustomerLink = (crmCustomerLink) =>
  request({
    url: '/crmCustomer/crmCustomerLink/save',
    method: 'post',
    data: crmCustomerLink
  })

export const deleteCrmCustomerLink = (crmCustomerLink) =>
  request({
    url: '/crmCustomer/crmCustomerLink/delete',
    method: 'post',
    data: crmCustomerLink
  })

export const bulkInsertCrmCustomerLink = (crmCustomerLinks) =>
  request({
    url: '/crmCustomer/crmCustomerLink/bulkInsert',
    method: 'post',
    data: crmCustomerLinks
  })

export const bulkUpdateCrmCustomerLink = (crmCustomerLinks) =>
  request({
    url: '/crmCustomer/crmCustomerLink/bulkUpdate',
    method: 'post',
    data: crmCustomerLinks
  })

export const bulkDeleteCrmCustomerLink = (crmCustomerLinks) =>
  request({
    url: '/crmCustomer/crmCustomerLink/bulkDelete',
    method: 'post',
    data: crmCustomerLinks
  })
