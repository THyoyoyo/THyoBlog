import { ElMessage, ElNotification } from 'element-plus'



export const TElNotification = (message, title = "系统通知", type = "success") => {
    return new Promise((resolve, reject) => {
        ElNotification({
            title: title,
            message: message,
            type: type,
        })
        resolve()
    })
}



export const TElMessage = (message, type = "success") => {
    return new Promise((resolve, reject) => {
        ElMessage({
            message: message,
            type: type,
            center: true,
        })
        resolve()
    })
}