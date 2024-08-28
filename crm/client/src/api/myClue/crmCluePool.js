import request from '@/utils/request'

export const getMyCrmCluePoolById = (id) =>
  request({
    url: '/myClue/myCrmCluePool/' + id,
    method: 'get'
  })

export const listMyCrmCluePoolPage = (search) =>
  request({
    url: '/myClue/myCrmCluePool/list',
    method: 'post',
    data: search
  })

export const listMyCrmCluePoolAll = (search) =>
  request({
    url: '/myClue/myCrmCluePool/listAll',
    method: 'post',
    data: search
  })

export const saveMyCrmCluePool = (myCrmCluePool) =>
  request({
    url: '/myClue/myCrmCluePool/save',
    method: 'post',
    data: myCrmCluePool
  })

export const deleteMyCrmCluePool = (myCrmCluePool) =>
  request({
    url: '/myClue/myCrmCluePool/delete',
    method: 'post',
    data: myCrmCluePool
  })

export const bulkInsertMyCrmCluePool = (myCrmCluePools) =>
  request({
    url: '/myClue/myCrmCluePool/bulkInsert',
    method: 'post',
    data: myCrmCluePools
  })

export const bulkUpdateMyCrmCluePool = (myCrmCluePools) =>
  request({
    url: '/myClue/myCrmCluePool/bulkUpdate',
    method: 'post',
    data: myCrmCluePools
  })

export const bulkDeleteMyCrmCluePool = (myCrmCluePools) =>
  request({
    url: '/myClue/myCrmCluePool/bulkDelete',
    method: 'post',
    data: myCrmCluePools
  })
