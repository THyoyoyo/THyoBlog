import { createRouter, createWebHistory } from 'vue-router'
import store from '../store/index';
import { serverRouter } from './server'
import { adminRouter } from './admin'
import layout from '../layout/index.vue'
import adminLayout from "../layout/admin/layout.vue"



const routes = [
  // 用户端PC
  {
    path: "/",
    component: layout,
    children: [...serverRouter]
  },
  // 管理端
  {
    path: "/admin",
    name: "admin",
    component: adminLayout,
    children: [...adminRouter]
  },
  // 后台登录页
  {
    path: "/login",
    name: "login",
    component: () => import("../adminViews/login.vue"),
  }
]
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})



router.beforeEach((to, from, next) => {
  let { name, meta, path } = to;
  let { state } = store;

  //   --Admin--------判断角色使用拥有当前路由权限--start---
  const { menus } = JSON.parse(localStorage.getItem("user")) || {};
  // 判断后台路径
  if (to.path.indexOf("admin") != -1 && to.name != "err") {

    if (!state.adminAuthority[to.name]) {
      console.log(2);
      next("/admin/err")
      return
    }
  }
  //   --Admin--------判断角色使用拥有当前路由权限--end---

  // 判断是否加入Tab组件
  if (meta.keep) {
    let index = state.MainTab.findIndex(v => {
      return name == v.name
    })
    if (index == -1) {
      store.commit("addMainTab", { name, meta, path })
    } else {
      store.commit("upMainTab", { name, meta, path, index })
    }
  }
  next()
})


export default router
