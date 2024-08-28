import request from '@/utils/request'

export const getMyClueCrmCommunicateById = (id) =>
  request({
    url: '/myClue/myClueCrmCommunicate/' + id,
    method: 'get'
  })

export const listMyClueCrmCommunicatePage = (search) =>
  request({
    url: '/myClue/myClueCrmCommunicate/list',
    method: 'post',
    data: search
  })

export const listMyClueCrmCommunicateAll = (search) =>
  request({
    url: '/myClue/myClueCrmCommunicate/listAll',
    method: 'post',
    data: search
  })

export const saveMyClueCrmCommunicate = (myClueCrmCommunicate) =>
  request({
    url: '/myClue/myClueCrmCommunicate/save',
    method: 'post',
    data: myClueCrmCommunicate
  })

export const deleteMyClueCrmCommunicate = (myClueCrmCommunicate) =>
  request({
    url: '/myClue/myClueCrmCommunicate/delete',
    method: 'post',
    data: myClueCrmCommunicate
  })

export const bulkInsertMyClueCrmCommunicate = (myClueCrmCommunicates) =>
  request({
    url: '/myClue/myClueCrmCommunicate/bulkInsert',
    method: 'post',
    data: myClueCrmCommunicates
  })

export const bulkUpdateMyClueCrmCommunicate = (myClueCrmCommunicates) =>
  request({
    url: '/myClue/myClueCrmCommunicate/bulkUpdate',
    method: 'post',
    data: myClueCrmCommunicates
  })

export const bulkDeleteMyClueCrmCommunicate = (myClueCrmCommunicates) =>
  request({
    url: '/myClue/myClueCrmCommunicate/bulkDelete',
    method: 'post',
    data: myClueCrmCommunicates
  })
