import request from '@/utils/request'

export const getOrgDepartmentById = (id) =>
  request({
    url: '/orgdepartment/orgDepartment/' + id,
    method: 'get'
  })


export const getdeparbycompany = () =>
  request({
    url: '/orgdepartment/orgDepartment/getdeparbycompany',
    method: 'get'
  })

export const listOrgDepartmentPage = (search) =>
  request({
    url: '/orgdepartment/orgDepartment/list',
    method: 'post',
    data: search
  })

export const listOrgDepartmentAll = (search) =>
  request({
    url: '/orgdepartment/orgDepartment/listAll',
    method: 'post',
    data: search
  })

export const saveOrgDepartment = (orgDepartment) =>
  request({
    url: '/orgdepartment/orgDepartment/save',
    method: 'post',
    data: orgDepartment
  })

export const deleteOrgDepartment = (orgDepartment) =>
  request({
    url: '/orgdepartment/orgDepartment/delete',
    method: 'post',
    data: orgDepartment
  })

export const bulkInsertOrgDepartment = (orgDepartments) =>
  request({
    url: '/orgdepartment/orgDepartment/bulkInsert',
    method: 'post',
    data: orgDepartments
  })

export const bulkUpdateOrgDepartment = (orgDepartments) =>
  request({
    url: '/orgdepartment/orgDepartment/bulkUpdate',
    method: 'post',
    data: orgDepartments
  })

export const bulkDeleteOrgDepartment = (orgDepartments) =>
  request({
    url: '/orgdepartment/orgDepartment/bulkDelete',
    method: 'post',
    data: orgDepartments
  })
