let authority = {
    state: () => ({
        // 权限
        routers_auth: [],
        // 用户可用权限（菜单列表）
        routers_user: []
    }),
    mutations: {
        // 更新可用列表
        upRouter(state, data) {
            state.routers_user = data
        },
        colseRouter(state, data) {
            state.routers_user = []
        }
    },
    actions: {
        // 获取可用权限列表
        getRouter(store, data) {
            // 传递过来的可用权限（与后端协商好）
            let userRouter = []
            store.state.routers_auth.forEach(v => {
                if (data.indexOf(v.name) != -1) {
                    userRouter.push(v)
                }
            });
            store.commit('upRouter', userRouter)
            return userRouter
        }
    },
    getters: {}
}


export default authority