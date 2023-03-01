import request from '@/utils/request'


export function tyStopTyTimePassWord(data) {
    return request({
        url: `/tyGame/stopTyTimePassWord`,
        method: 'post',
        data
    })
}


export function tyUpCheck(data) {
    return request({
        url: `/tyGame/upCheck`,
        method: 'get',
        params: data
    })
}

export function tyUserList(data) {
    return request({
        url: `/tyGame/userList`,
        method: 'get',
        params: data
    })
}


export function tyUserSava(data) {
    return request({
        url: `/tyGame/userSava`,
        method: 'post',
        data
    })
}



export function checkUserInfo(data) {
    return request({
        url: `/tyGame/checkUserInfo`,
        method: 'get',
        params: data
    })
}