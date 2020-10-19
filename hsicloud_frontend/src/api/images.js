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
    url: '/image/getAll',
    method: 'get'
  })
}

export function login(data) {
  console.log('login')
  return request({
    url: '/user/getToken',
    method: 'post'
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}

export function imgUpload(query) {
  return request({
    url: '/image/imgupload',
    method: 'post',
    params: query
  })
}

export function exec(query) {
  return request({
    url: '/image/exec',
    method: 'post',
    params: query
  })
}

// 单张图片数据
export function fetchsignal(data) {
  return request({
    url: '/image/getimage?id=' + data,
    method: 'post'
  })
}
// 光谱库
export function getSpecLibByName(data) {
  return request({
    url: '/image/getAllSpecLibName',
    method: 'get',
    params: data
  })
}
// 库内光谱信息
export function getSpecByLib(data) {
  return request({
    url: '/image/getSpecByLib/?specLibName=' + data,
    method: 'get'
  })
}
// 光谱具体信息
export function getSpecDataById(id) {
  return request({
    url: '/image/getSpecDataById?id=' + id,
    method: 'get'
  })
}

// 光谱信息
export function getSpecdataByImageID(data) {
  return request({
    url: '/image/getSpecdata',
    method: 'post',
    data
  })
}
// 旧接口
