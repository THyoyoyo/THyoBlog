import request from '@/utils/request'


export function getBattleList() {
    return request({
        url: `/LolTool/getBattleList`,
        method: 'get',
    })
}



export function getOnlineStatus() {
    return request({
        url: `/LolTool/getOnlineStatus`,
        method: 'get',
    })
}


export function userProfileInfo() {
    return request({
        url: `/LolTool/userProfileInfo`,
        method: 'get',
    })
}
