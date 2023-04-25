
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



export function speedToolSavaInfo(data) {
    return request({
        url: `/speedTool/savaInfo`,
        method: 'post',
        data
    })
}