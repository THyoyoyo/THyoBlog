import request from '@/utils/request'


// 菜单列表列表
export function getSystemMenusList(data) {
    return request({
        url: `/system/menus/list`,
        method: 'get',
        params: data
    })
}

// 菜单新增编辑
export function SystemMenusListSava(data) {
    return request({
        url: `/system/menus/sava`,
        method: 'post',
        data
    })
}

// 菜单删除
export function SystemMenusListDel(data) {
    return request({
        url: `/system/menus/del`,
        method: 'get',
        params: data
    })
}


// 角色列表
export function getSystemRoleList(data) {
    return request({
        url: `/system/role/list`,
        method: 'get',
        params: data
    })
}

// 角色编辑、新增
export function getSystemRoleSava(data) {
    return request({
        url: `/system/role/sava`,
        method: "post",
        data
    })
}

// 角色删除
export function getSystemRoleDel(data) {
    return request({
        url: `/system/role/del`,
        method: 'get',
        params: data
    })
}



// 保存上传文件资源路径
export function savaUpLoadWebFile(data) {
    return request({
        url: `/system/SavaUpLoadWebFile`,
        method: 'post',
        data
    })
}


// 获取上传文件
export function getWebFile(data) {
    return request({
        url: `/system/getWebFile`,
        method: 'post',
        data
    })
}


// 修改资源文件
export function editByIdFile(data) {
    return request({
        url: `/system/editByIdFile`,
        method: 'post',
        data
    })
}


// 删除资源文件
export function delByIdFile(data) {
    return request({
        url: `/system/delFile`,
        method: 'get',
        params: data
    })
}


//获取网站信息
export function getWebInfo(data) {
    return request({
        url: `/system/getWebInfo`,
        method: 'get',
        params: data
    })
}

//保存网站信息
export function savaWebInfo(data) {
    return request({
        url: `/system/savaWebInfo`,
        method: 'post',
        data
    })
}