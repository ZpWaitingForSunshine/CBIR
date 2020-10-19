import request from '@/utils/request'

export function imgUpdate(query) {
  return request({
    url: '/image/imgupdate',
    method: 'post',
    params: query
  })
}

export function imgUpload(query) {
  return request({
    url: '/image/imgupload',
    method: 'post'
  })
}

export function exec(query) {
  return request({
    url: '/image/exec',
    method: 'post',
    params: query
  })
}
// 图库
export function fetchList(data) {
  return request({
    url: '/image/getImageByPage',
    method: 'post',
    params: data
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

// 图片光谱信息
export function getSpecdataByImageID(data) {
  return request({
    url: '/image/getSpecdata',
    method: 'post',
    data
  })
}

// 丰度条件
export function searchImage(data) {
  return request({
    url: '/image/searchImg',
    method: 'post',
    data
  })
}

// 提交解混
export function unmixing(data) {
  return request({
    url: '/image/unmixing',
    method: 'post',
    data
  })
}