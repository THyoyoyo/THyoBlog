import { createStore } from 'vuex'
import createPersistedState from "vuex-persistedstate"

import globalConfig from "./modules/globalConfigStore"

export default createStore({
  state: {
    MainTab: [],  //  顶部页面 tab 数据  
    userInfo: {},
    adminAuthority: {} //可访问地址
  },
  mutations: {
    addMainTab(state, data) {
      state.MainTab.unshift(data)
    },
    upMainTab(state, data) {
      state.MainTab.splice(data.index, 1);
      state.MainTab.unshift(data)
    },
    delMainTab(state, index) {
      state.MainTab.splice(index, 1);
    },

    // 设置权限
    setAuthority(state, data) {
      state.adminAuthority = data
    },
    // 设置用户信息
    setUserInfo(state, data) {
      state.userInfo = data
    },
    // 重置
    setReset(state) {
      state.MainTab = []
      state.userInfo = {}
      state.adminAuthority = {}
    }

  },
  actions: {
  },
  modules: {
    globalConfig
  },
  plugins: [createPersistedState({
    key: "dataBase"
  })]
})
