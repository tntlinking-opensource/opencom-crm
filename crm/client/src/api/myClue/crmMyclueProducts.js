import request from '@/utils/request'

export const getCrmMyclueProductsById = (id) =>
  request({
    url: '/myClue/crmMyclueProducts/' + id,
    method: 'get'
  })

export const listCrmMyclueProductsPage = (search) =>
  request({
    url: '/myClue/crmMyclueProducts/list',
    method: 'post',
    data: search
  })

export const listCrmMyclueProductsAll = (search) =>
  request({
    url: '/myClue/crmMyclueProducts/listAll',
    method: 'post',
    data: search
  })

export const saveCrmMyclueProducts = (crmMyclueProducts) =>
  request({
    url: '/myClue/crmMyclueProducts/save',
    method: 'post',
    data: crmMyclueProducts
  })

export const deleteCrmMyclueProducts = (crmMyclueProducts) =>
  request({
    url: '/myClue/crmMyclueProducts/delete',
    method: 'post',
    data: crmMyclueProducts
  })

export const bulkInsertCrmMyclueProducts = (crmMyclueProductss) =>
  request({
    url: '/myClue/crmMyclueProducts/bulkInsert',
    method: 'post',
    data: crmMyclueProductss
  })

export const bulkUpdateCrmMyclueProducts = (crmMyclueProductss) =>
  request({
    url: '/myClue/crmMyclueProducts/bulkUpdate',
    method: 'post',
    data: crmMyclueProductss
  })

export const bulkDeleteCrmMyclueProducts = (crmMyclueProductss) =>
  request({
    url: '/myClue/crmMyclueProducts/bulkDelete',
    method: 'post',
    data: crmMyclueProductss
  })
