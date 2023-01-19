
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

export default uploadFilled