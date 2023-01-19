const cdnMap = {
    css: [
        'https://lf6-cdn-tos.bytecdntp.com/cdn/expire-1-M/element-plus/2.0.4/index.min.css',
        "https://lf6-cdn-tos.bytecdntp.com/cdn/expire-1-M/wangEditor/10.0.13/wangEditor.min.css",
        process.env.VUE_APP_A_LI_CION_CND
    ],
    js: [
        'https://unpkg.zhimg.com/vue@next',
        'https://unpkg.zhimg.com/vuex@next',
        'https://unpkg.zhimg.com/vue-router@next',
        'https://cdn.bootcdn.net/ajax/libs/axios/0.21.1/axios.min.js',
        'https://lf26-cdn-tos.bytecdntp.com/cdn/expire-1-M/element-plus/2.0.4/index.full.min.js',
        'https://cdn.bootcss.com/typed.js/2.0.5/typed.js',
        'https://lf26-cdn-tos.bytecdntp.com/cdn/expire-1-M/wangEditor/10.0.13/wangEditor.min.js',

    ]
}
// 需要扩展的资源
// https://cdn.bootcdn.net/ajax/libs/qrcodejs/1.0.0/qrcode.min.js
const externals = {
    "vue": "Vue",
    "vuex": "Vuex",
    "vue-router": "VueRouter",
    "element-plus": "ElementPlus",
    "axios": "axios",
    'wangEditor': 'wangEditor'
}
const regDev = RegExp(/development/);



module.exports = {
    // publicPath: "./",
    productionSourceMap: false,
    chainWebpack: config => {
        config.plugins.delete('preload')
        config.plugins.delete('prefetch')
        // 外部扩展
        config.externals(externals)
        config.plugin('html').tap(args => {
            args[0].title = "THyo";
            args[0].cdn = cdnMap
            return args
        })
    },

    devServer: {
        port: 8082,
        disableHostCheck: true,
        // 此处开启 https\
        proxy: {
            '/api': {
                // 正式：http://www.thyo.xyz:8081
                target: "http://localhost:8081", //API服务器的地址    
                changeOrigin: true, // 虚拟的站点需要更管origin
                pathRewrite: { //重写路径 比如'/api/aaa/ccc'重写为'/aaa/ccc'
                    '^/': '/'
                },
                ws: true
            },
            "/getIp": {
                target: "https://ip.900cha.com/", //API服务器的地址    
                changeOrigin: true, // 虚拟的站点需要更管origin
                pathRewrite: { //重写路径 比如'/api/aaa/ccc'重写为'/aaa/ccc'
                    '^/getIp': '/'
                },
            }
        }
    }
}