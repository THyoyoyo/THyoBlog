
import request from "../utils/request"

export function getUserBagInfo(data) {
    return request({
        url: `/speedTool/getUserBagInfo`,
        method: 'get',
    })
}

export function getUserBoxItemInfoV2(data) {
    return request({
        url: `/speedTool/getUserBoxItemInfoV2`,
        method: 'get',
    })
}

export function openBox(data) {
    return request({
        url: `/speedTool/openBox`,
        method: 'get',
        params: data
    })
}

export function openBoxByKey(data) {
    return request({
        url: `/speedTool/openBoxByKey`,
        method: 'get',
        params: data
    })
}
export function getRefererInfo(data) {
    return request({
        url: `/speedTool/getRefererInfo`,
        method: 'get',
        params: data
    })
}


export function upReferer(data) {
    return request({
        url: `/speedTool/upReferer`,
        method: 'post',
        data
    })
}



export function speedToolSavaInfo(data) {
    return request({
        url: `/speedTool/savaInfo`,
        method: 'post',
        data
    })
}


export function speedToolUpstate() {
    return request({
        url: `/speedTool/upstate`,
        method: 'get',
    })
}


export function speedToolSetAutoBoxInfo(data) {
    return request({
        url: `/speedTool/setAutoBoxInfo`,
        method: 'post',
        data
    })
}



export function speedToolAwardReceiving(data) {
    return request({
        url: `/speedTool/awardReceiving`,
        method: 'get'
    })
}


export function speedToolGetOpenBoxLog(data) {
    return request({
        url: `/speedTool/getOpenBoxLog`,
        method: 'get'
    })
}