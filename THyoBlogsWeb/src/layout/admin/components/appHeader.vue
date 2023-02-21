<template>
  <div class="app-header">
    <p class="title"></p>

    <div class="colseBtn">
 
      <div class="colseBtn" @click="out()">退出系统</div>
    </div>
  </div>
</template>
<script>
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { login, userOut } from "../../../api/login";

import { TElNotification } from "../../../utils/inform";
export default {
  setup(props) {
    const store = useStore();
    const router = useRouter();
    function out() {
      userOut();
      localStorage.removeItem("dataBase");
      store.commit("setReset");
      router.push("/login");
    }
    function userLogin() {
      let data = {
        account: "522307026",
        password: "qq1212",
      };
      login(data).then((res) => {
        if (res.code == 200) {
          TElNotification("登录成功！").then(() => {
            store.commit("setUserInfo", res.data);
          });
          // 权限配置
          let authority = {};
          res.data.menus.forEach((v) => {
            authority[v.routerName] = true;
            v.childs?.forEach((v2) => {
              authority[v2.routerName] = true;
            });
          });
          store.commit("setAuthority", authority);
        }
      });
    }
    return {
      out,
      userLogin,
    };
  },
};
</script>
<style lang="less" scoped>
.app-header {
  height: 80px;
  width: 100%;
  background: rgb(245, 245, 245);
  padding: 0 30px;
  box-sizing: border-box;
  display: flex;
  justify-content: space-between;
  align-items: center;
  .title {
    font-weight: 700;
    font-size: 35px;
    color: rgb(112, 112, 112);
  }
  .colseBtn {
    cursor: pointer;
    font-weight: 700;
    font-size: 20px;
    letter-spacing: 5px;
    color: rgb(112, 112, 112);
    &:hover {
      color: rgb(241, 63, 63);
    }
  }
}
</style>