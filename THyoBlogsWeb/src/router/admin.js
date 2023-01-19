export const adminRouter = [
    {
        path: "",
        name: "admin-home",
        meta: { name: "首页", keep: true },
        component: () => import('../adminViews/home.vue'),
    },

    {
        path: "article",
        name: "article-index",
        meta: { name: "文章", keep: true },
        component: () => import('../App.vue'),
        children: [
            {
                path: "list",
                name: "article-list",
                meta: { name: "文章列表", keep: true },
                component: () => import('../adminViews/article/list.vue'),
            },
            {
                path: "class",
                name: "article-class",
                meta: { name: "文章分类", keep: true },
                component: () => import('../adminViews/article/class.vue'),
            },
            {
                path: "label",
                name: "article-label",
                meta: { name: "文章标签", keep: true },
                component: () => import('../adminViews/article/label.vue'),
            },
            {
                path: "comment",
                name: "article-comment",
                meta: { name: "文章评论", keep: true },
                component: () => import('../adminViews/article/comment.vue'),
            }
        ]
    },
    {
        path: "user",
        name: "users-index",
        meta: { name: "用户", keep: true },
        component: () => import('../App.vue'),
        children: [
            {
                path: "list",
                name: "user-list",
                meta: { name: "用户列表", keep: true },
                component: () => import('../adminViews/user/list.vue'),
            },
            {
                path: "authority",
                name: "user-authority",
                meta: { name: "角色设置", keep: true },
                component: () => import('../adminViews/user/authority.vue'),
            }
        ]
    },
    {
        path: "system",
        name: "system-index",
        meta: { name: "系统管理", keep: true },
        component: () => import('../App.vue'),
        children: [
            {
                path: "menus",
                name: "menus-list",
                meta: { name: "菜单设置", keep: true },
                component: () => import('../adminViews/system/menus.vue'),
            },
            {
                path: "resource",
                name: "resource",
                meta: { name: "网站资源", keep: true },
                component: () => import('../adminViews/system/resource.vue'),
            }, {
                path: "webInfo",
                name: "webInfo",
                meta: { name: "网站信息", keep: true },
                component: () => import('../adminViews/system/webInfo.vue'),
            }
        ]
    },
    {
        path: "err",
        name: "err",
        meta: { name: "错误", keep: true },
        component: () => import('../adminViews/404.vue'),
    }
]