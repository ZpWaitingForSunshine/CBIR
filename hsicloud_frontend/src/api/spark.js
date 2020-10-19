import request from '@/utils/sparkrequest'

// export function login(data) {
//   return request({
//     url: '/user/getToken',
//     method: 'post',
//     data
//   })
// }


export function fetchSparkState(token) {
  return request({
    url: '/json',
    method: 'post',
  })
}
