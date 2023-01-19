import axios from 'axios'
import { ElLoading } from 'element-plus'
import { TElMessage } from "./inform"
import router from "../router/index"
// import store from '@/store'
// import VueRouter from 'vue-router'
// import Vue from 'vue'
// create an axios instance
const service = axios.create({
    baseURL: '/api', // url = base url + request url
    // withCredentials: true, // send cookies when cross-domain requests
    timeout: 20000, // request timeout
    retryDelay: 1000, // 请求间隙
    retry: 2, // 重试次数
    // withCredentials: true, // 是否携带cookie信息
    // headers: {
    //     'Content-Type': 'application/json; charset=*'
    // },
});
// 页面等待加载
let loadingInstance = null;

// 请求拦截器
service.interceptors.request.use(

    config => {
        if (!loadingInstance) {
            loadingInstance = ElLoading.service({ text: "加载中", fullscreen: true })
        }

        // do something before request is sent

        // if (store.state.token != '') {
        //     //  如果登录 则携带
        //     config.headers['Authorization'] = store.state.token
        // }
        //  config.headers['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8'

        return config
    },
    error => {
        // do something with request error
        ////console.log(error) // for debug
        return Promise.reject(error)
    }
)

// 响应拦截器
service.interceptors.response.use(
    response => {
        const res = response.data
        if (loadingInstance) {
            loadingInstance.close()
            loadingInstance = null
        }


        if (res.code == 401) {
            router.push("/login")
            TElMessage(res.message, "warning")
        }


        return res
    },
    error => {
        if (loadingInstance) {
            loadingInstance.close()
            loadingInstance = null
        }
        return Promise.reject(error)
    }
)

export default service