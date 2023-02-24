import { ElMessage, ElNotification } from 'element-plus'



export const TElNotification = (message, title = "系统通知", type = "success", dangerouslyUseHTMLString = false) => {
    return new Promise((resolve, reject) => {
        ElNotification({
            title: title,
            message: message,
            type: type,
            dangerouslyUseHTMLString,
        })
        resolve()
    })
}



export const TElMessage = (message, type = "success", offset = 20) => {
    return new Promise((resolve, reject) => {
        ElMessage({
            message: message,
            type: type,
            center: true,
            offset
        })
        resolve()
    })
}