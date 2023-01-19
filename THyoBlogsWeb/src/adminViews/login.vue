<template>
  <div class="login">
    <div class="login-wrapper">
      <div class="header">后台管理登录</div>
      <div class="form-wrapper">
        <input
          type="text"
          name="username"
          placeholder="username"
          class="input-item"
          v-model="userForm.account"
        />
        <input
          type="password"
          name="password"
          placeholder="password"
          class="input-item"
          v-model="userForm.password"
        />
        <div class="btn web-font" @click="userLogin()">Login</div>
      </div>
      <div class="msg">
        还没有帐号?
        <a href="javascript:void(0);" @click="register()">立即注册</a>
      </div>
    </div>
  </div>
</template>
<script>
import { ElMessage, ElMessageBox } from "element-plus";
import { reactive } from "@vue/reactivity";
import { login } from "../api/login";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { TElNotification } from "../utils/inform";
export default {
  setup() {
    const router = useRouter();
    const store = useStore();
    // 注册
    let register = () => {
      ElMessageBox.confirm("Sorry,网站注册功能暂未开放", "网站提示", {
        confirmButtonText: "知道了",
        showCancelButton: false,
        type: "warning",
      });
    };
    // 登录
    let userForm = reactive({
      account: "522307026",
      password: "qq1212",
    });

    let userLogin = () => {
      login(userForm).then((res) => {
        if (res.code == 200) {
          store.commit("setReset");
          TElNotification("登录成功！");
          store.commit("setUserInfo", res.data);
          // 权限配置
          let authority = {};
          res.data.menus.forEach((v) => {
            authority[v.routerName] = true;
            v.childs?.forEach((v2) => {
              authority[v2.routerName] = true;
            });
          });
          store.commit("setAuthority", authority);
          router.push("/admin");
        } else {
          ElMessageBox.confirm(`Sorry,${res.message}`, "网站提示", {
            confirmButtonText: "知道了",
            showCancelButton: false,
            type: "warning",
          });
        }
      });
    };

    return {
      register,
      userLogin,
      userForm,
    };
  },
};
</script>
<style lang="less" scoped>
.login {
  user-select: none;
  height: 100%;
  background-image: url("../static/images/img-3.jpg");
  background-repeat: no-repeat;
  background-position-x: center;
  background-blend-mode: darken;
  background-size: 1920px;
}
.login-wrapper {
  background-color: #fff;
  width: 358px;
  height: 488px;
  border-radius: 15px;
  padding: 0 50px;
  position: relative;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}
.header {
  letter-spacing: 2px;
  font-size: 38px;
  font-weight: bold;
  text-align: center;
  line-height: 200px;
}
.input-item {
  display: block;
  width: 100%;
  margin-bottom: 20px;
  border: 0;
  padding: 10px;
  border-bottom: 1px solid rgb(128, 125, 125);
  font-size: 15px;
  outline: none;
}
.input-item:placeholder {
  text-transform: uppercase;
}
.btn {
  cursor: pointer;
  text-align: center;
  padding: 10px;
  width: 100%;
  margin-top: 40px;
  background-image: linear-gradient(to right, #a6c1ee, #fbc2eb, #a6c1ee);
  background-size: 300%;
  color: #fff;
  font-size: 16px;
  animation: btnBack 2s linear infinite;
}
@keyframes btnBack {
  from {
    background-position-x: 0%;
  }
  to {
    background-position-x: 300%;
  }
}

.msg {
  text-align: center;
  line-height: 88px;
}
a {
  text-decoration-line: none;
  color: #abc1ee;
}
</style>