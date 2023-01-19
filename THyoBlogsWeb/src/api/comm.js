import request from '@/utils/request'



// 获取七牛云CDN
export function getQlyToken(data) {
    return request({
        url: `/comm/getQlyToken`,
        method: 'get',
    })
}