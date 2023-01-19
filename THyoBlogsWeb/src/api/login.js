import request from '@/utils/request'

// 登录
export function login(data) {
    return request({
        url: `/login/register`,
        method: 'post',
        data
    })
}


// 退出
export function userOut(data) {
    return request({
        url: `/login/out`,
        method: 'get',
    })
}