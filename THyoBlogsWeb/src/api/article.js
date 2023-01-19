import request from '@/utils/request'

// 获取分类列表
export function articleClassList(data) {
    return request({
        url: `/article/class/list`,
        method: 'get',
        params: data
    })
}

// 新增分类二
export function articleClassAddTwo(data) {
    return request({
        url: `/article/class/addTwo`,
        method: 'post',
        data
    })
}
// 新增分类一
export function articleClassAddOne(data) {
    return request({
        url: `/article/class/addOne`,
        method: 'post',
        data
    })
}

// 删除分类二
export function articleClassDelTwo(data) {
    return request({
        url: `/article/class/delTwo`,
        method: 'get',
        params: data
    })
}
// 删除分类一
export function articleClassDelOne(data) {
    return request({
        url: `/article/class/delOne`,
        method: 'get',
        params: data
    })
}

// 编辑分类一
export function articleClassUpOne(data) {
    return request({
        url: `/article/class/upOne`,
        method: 'post',
        data
    })
}

// 编辑分类二
export function articleClassUpTwo(data) {
    return request({
        url: `/article/class/upTwo`,
        method: 'post',
        data
    })
}


// 添加标签
export function articleLabelAdd(data) {
    return request({
        url: `/article/label/add`,
        method: 'post',
        data
    })
}
// 编辑标签
export function articleLabelUp(data) {
    return request({
        url: `/article/label/up`,
        method: 'post',
        data
    })
}
// 获取标签
export function articleLabelList(data) {
    return request({
        url: `/article/label/list`,
        method: 'get',
        params: data
    })
}
// 删除
export function articleLabelDel(data) {
    return request({
        url: `/article/label/del`,
        method: 'get',
        params: data
    })
}

// 获取文章LIST
export function articList(data) {
    return request({
        url: `/article/list`,
        method: 'get',
        params: data
    })
}
// 获取文章
export function articInfo(data) {
    return request({
        url: `/article/info`,
        method: 'get',
        params: data
    })
}

// 添加文章
export function articleAdd(data) {
    return request({
        url: `/article/add`,
        method: 'post',
        data
    })
}
// 更新文章
export function articleUp(data) {
    return request({
        url: `/article/up`,
        method: 'post',
        data
    })
}


// 删除文章
export function articInfoDel(data) {
    return request({
        url: `/article/del`,
        method: 'get',
        params: data
    })
}


// 设置首页推荐文章
export function setHomeRecommend(data) {
    return request({
        url: `/article/homeRecommend`,
        method: 'get',
        params: data
    })
}



// 获取评论
export function getCommentById(data) {
    return request({
        url: `/article/getComment`,
        method: 'get',
        params: data
    })
}

// 发布评论
export function pushComment(data) {
    return request({
        url: `/article/pushComment`,
        method: 'post',
        data
    })
}

// 获取首页推荐
export function getHomeRecommend(data) {
    return request({
        url: `/article/getHomeRecommend`,
        method: 'get',
        params: data
    })
}


// 文章统计
export function statistics(data) {
    return request({
        url: `/article/statistics`,
        method: 'get',
        params: data
    })
}























