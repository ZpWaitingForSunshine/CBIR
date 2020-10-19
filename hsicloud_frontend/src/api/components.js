import request from '@/utils/request'

// export function login(data) {
//   return request({
//     url: '/user/getToken',
//     method: 'post',
//     data
//   })
// }

export function fetchList() {
  return request({
    url: '/components/getAll',
    method: 'get',
  })
}

export function fetchOne(data) {
  return request({
    url: '/components/getOne',
    method: 'post',
    params: data
  })
}

export function fetchState(data) {
  // submissionId required
  return request({
    url: '/components/check',
    method: 'post',
    params: data
  })
}
export function fetchdag(data) {
  // submissionId required
  return request({
    url: '/dag/write',
    method: 'post',
    params: data
  })
}
export function fetchvm(data) {
  return request({
    url: '/run/startvm',
    method: 'post',
    params: data
  })
}

export function fetchdriverparams1(data) {
  return request({
    url: '/run/driverparams',
    method: 'post',
    params: data
  })
}
export function fetchxml(data) {
  return request({
    url: '/xml/analysis',
    method: 'post',
    params: data
  })
}

export function fetchaldetail(data) {
  return request({
    url: '/components/getaldetail',
    method: 'post',
    params: data
  })
}
export function exec(data) {
  return request({
    url: '/components/exec',
    method: 'post',
    params: data
  })
}
export function dagcheck(data) {
  return request({
    url: '/run/check',
    method: 'post',
    params: data
  })
}

export function fetchDispatchurl(data) {
  return request({
    url: '/savedga/geturl',
    method: 'get',
    params: data
  })
}
export function fetchresulturl(data) {
  return request({
    url: '/run/getResulturl',
    method: 'post',
    params: data
  })
}
export function fetchgantt(data) {
  return request({
    url: '/savedga/getGantt',
    method: 'get',
    params: data
  })
}
export function getdaginfo(data) {
  return request({
    url: '/savedga/getInfo',
    method: 'get',
    params: data
  })
}
export function savedaginfo(data) {
  return request({
    url: '/savedga/saveInfo',
    method: 'post',
    params: data
  })
}
export function getConponentName() {
  return request({
    url: '/savedga/getName',
    method: 'get'

  })
}

export function dodispatch(data) {
  return request({
    url: '/savedga/dodispatch',
    method: 'post',
    params: data
  })
}

