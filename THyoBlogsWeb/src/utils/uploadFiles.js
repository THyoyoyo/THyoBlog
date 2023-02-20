
import { getQlyToken } from "../api/comm"

const qiniu = require('qiniu-js')




// 随机数
function S4() {
    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
}
function guid() {
    return (
        S4() +
        S4() +
        "-" +
        S4() +
        "-" +
        S4() +
        "-" +
        S4() +
        "-" +
        S4() +
        S4() +
        S4()
    );
}


function uploadFilled(file) {
    return new Promise((resolve, reject) => {
        getQlyToken().then(res => {
            const observable = qiniu.upload(file, "img/" + guid(), res.data)
            const subscription = observable.subscribe({
                next(res) {
                },
                error(err) {
                    resolve(err)
                },
                complete(res) {
                    resolve(res)
                }
            })
        })
    })
}



// 新版封装文件上传
export function uploadFilledNew(qiniuData, file) {
    return Promise.all(file.map(v => {
        return new Promise((resolve, reject) => {
            const observable = qiniu.upload(v.file, "img/" + guid(), qiniuData.data)
            const subscription = observable.subscribe({
                next(res) {
                },
                error(err) {
                    resolve(err)
                },
                complete(res) {
                    resolve({
                        name: v.file.name,
                        type: v.file.name.slice(v.file.name.indexOf(".") + 1),
                        url: process.env.VUE_APP_QIN_NIU_CDN + res.key,
                    });
                }
            })
        })
    }))
}

export default uploadFilled