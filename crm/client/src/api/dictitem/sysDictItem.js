import request from '@/utils/request'

export const getSysDictItemById = (id) =>
  request({
    url: '/dictitem/sysDictItem/' + id,
    method: 'get'
  })


export const getSysDictItemBytype = (type) =>
  request({
    url: '/dictitem/sysDictItem/getbycode/' + type,
    method: 'get'
  })

export const listSysDictItemPage = (search) =>
  request({
    url: '/dictitem/sysDictItem/list',
    method: 'post',
    data: search
  })

export const listSysDictItemAll = (search) =>
  request({
    url: '/dictitem/sysDictItem/listAll',
    method: 'post',
    data: search
  })

export const saveSysDictItem = (sysDictItem) =>
  request({
    url: '/dictitem/sysDictItem/save',
    method: 'post',
    data: sysDictItem
  })

export const deleteSysDictItem = (sysDictItem) =>
  request({
    url: '/dictitem/sysDictItem/delete',
    method: 'post',
    data: sysDictItem
  })

export const bulkInsertSysDictItem = (sysDictItems) =>
  request({
    url: '/dictitem/sysDictItem/bulkInsert',
    method: 'post',
    data: sysDictItems
  })

export const bulkUpdateSysDictItem = (sysDictItems) =>
  request({
    url: '/dictitem/sysDictItem/bulkUpdate',
    method: 'post',
    data: sysDictItems
  })

export const bulkDeleteSysDictItem = (sysDictItems) =>
  request({
    url: '/dictitem/sysDictItem/bulkDelete',
    method: 'post',
    data: sysDictItems
  })
