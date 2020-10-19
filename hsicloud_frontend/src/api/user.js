import request from '@/utils/request'

// export function login(data) {
//   return request({
//     url: '/user/getToken',
//     method: 'post',
//     data
//   })
// }


export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
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
