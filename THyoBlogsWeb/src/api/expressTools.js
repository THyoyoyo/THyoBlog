import request from '@/utils/request'


// 获取QQ昵称与头像
export function getQqInfo() {
    return request({
        method: 'get',
        url: "/expressTools/getQinfo",
        params: data
    })
}