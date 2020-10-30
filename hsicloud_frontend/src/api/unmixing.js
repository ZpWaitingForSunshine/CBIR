import request from '@/utils/request'

export function unmixing(data) {
	return request({
		url: '/unmixing/submitTask',
		method: 'post',
		data
	})
}
