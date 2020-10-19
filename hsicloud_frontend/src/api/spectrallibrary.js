import request from '@/utils/request'

export function fetchSpectralNames() {
  return request({
    url: '/spectrallibrary/getAllSpecLibName',
    method: 'get'
  })
}

export function fetchSpectralById(id) {
  return request({
    url: '/spectrallibrary/getSpecByID',
    method: 'get',
    params: { id }
  })
}

export function fetchSpectralInformationByID(id){
    return request({
        url: '/spectrallibrary/getSpectralInformationByID',
        method: 'get',
        params: { id }
      })
}