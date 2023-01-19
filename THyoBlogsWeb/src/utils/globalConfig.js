import { getWebInfo } from "../api/system"
import store from '../store'
export let globalConfig = {

}

// 获取全局配置信息
getWebInfo().then(res => {
    if (res.code == 200) {
        store.commit("setGlobalConfig", res.data)
    }
})