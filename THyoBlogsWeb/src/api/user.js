import request from "../utils/request"



// 获取用户列表
export function userList(data) {
    return request({
        url: `/user/list`,
        method: 'get',
        params: data
    })
}


// 获取用户新增
export function userAdd(data) {
    return request({
        url: `/user/add`,
        method: 'post',
        data
    })
}



// 获取用户编辑
export function userUp(data) {
    return request({
        url: `/user/up`,
        method: 'post',
        data
    })
}


// 获取用户删除
export function userDel(data) {
    return request({
        url: `/user/del`,
        method: 'get',
        data
    })

}



// 检测用户登录信息
export function checkInfo() {
    return request({
        url: `/user/checkInfo`,
        method: 'get',
    })
}