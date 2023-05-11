<template>
  <div class="speed_login">
    <el-dialog
      v-model="loginBox"
      title="用户登录"
      width="400px"
      custom-class="custom-dialog"
      :close-on-click-modal="false"
      :before-close="handleClose"
    >
      <!--       :before-close="handleClose" -->
      <el-form :model="loginFrom" ref="loginFromEl" label-width="70px">
        <el-form-item
          label="账户："
          prop="account"
          :rules="[{ required: true, message: '请填写登录账户' }]"
        >
          <el-input v-model="loginFrom.account" />
        </el-form-item>

        <el-form-item
          label="密码："
          prop="account"
          :rules="[{ required: true, message: '请填写登录密码' }]"
        >
          <el-input v-model="loginFrom.password" />
        </el-form-item>
      </el-form>

      <div class="history-account" v-if="historyAccount.length > 0">
        <div class="history-account-titem">历史登录账号</div>
        <div class="history-account-ctx">
          <div
            class="ctx-item"
            v-for="(item, key) in historyAccount"
            :key="key"
          >
            <div class="ctx-item-account">{{ item.account }}</div>
            <div class="ctx-item-btn">
              <el-button type="info" size="small" @click="rapidLogin(item)"
                >登录</el-button
              >
            </div>
          </div>
        </div>
      </div>

      <template #footer>
        <span class="dialog-footer">
          <el-button @click="cutLoginType(0)">前往注册</el-button>
          <el-button type="primary" @click="userLogin()"> 登录 </el-button>
        </span>
      </template>
    </el-dialog>

    <el-dialog
      v-model="registerBox"
      title="新用户注册"
      width="400px"
      custom-class="custom-dialog"
      :close-on-click-modal="false"
      :before-close="handleCloseRegister"
    >
      <el-form :model="registerFrom" ref="registerFromEl" label-width="100px">
        <el-form-item
          label="账户："
          prop="account"
          :rules="[{ required: true, message: '请填写登录账户' }]"
        >
          <el-input v-model="registerFrom.account" />
        </el-form-item>

        <el-form-item
          label="密码："
          prop="account"
          :rules="[{ required: true, message: '请填写登录密码' }]"
        >
          <el-input v-model="registerFrom.password" />
        </el-form-item>
        <el-form-item
          label="QQ："
          prop="qq"
          :rules="[{ required: true, message: '请填写QQ' }]"
        >
          <el-input v-model="registerFrom.qq" />
        </el-form-item>
        <el-form-item
          label="权限链接："
          prop="referer"
          :rules="[{ required: true, message: '请填写权限链接' }]"
        >
          <el-input v-model="registerFrom.referer" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="cutLoginType(1)">前往登录</el-button>
          <el-button type="primary" @click="register()">注册 </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<script>
import { reactive, toRefs, ref, onMounted } from "vue";
import { login } from "@/api/login";
import { TElNotification } from "@/utils/inform";
import { useStore } from "vuex";
import { ElMessageBox } from "element-plus";
import { speedToolSavaInfo } from "../../api/speedTool";
export default {
  props: {
    loginBox: {
      type: Boolean,
      default: () => {
        return false;
      },
    },
    registerBox: {
      type: Boolean,
      default: () => {
        return false;
      },
    },
  },
  emits: ["openLoginBox", "getList", "openRegisterBox", "getReferer"],
  setup(props, { emit }) {
    const loginFromEl = ref();
    const registerFromEl = ref();
    const store = useStore();
    let state = reactive({
      loginFrom: {
        account: "",
        password: "",
      },
      registerFrom: {
        account: "",
        password: "",
        qq: "",
        referer: " ",
      },
      historyAccount: [],
    });

    let userLogin = () => {
      loginFromEl.value.validate((valid) => {
        if (valid) {
          login(state.loginFrom).then((res) => {
            if (res.code == 200) {
              store.commit("setReset");
              TElNotification("登录成功！");
              store.commit("setUserInfo", res.data);
              emit("openLoginBox", false, () => {
                emit("getList");
                emit("getReferer");
              });
              let accountData = localStorage.getItem("accountData")
                ? JSON.parse(localStorage.getItem("accountData"))
                : [];

              let index = accountData.findIndex((v) => {
                return v.account == state.loginFrom.account;
              });

              if (index == -1) {
                accountData.push(state.loginFrom);
                localStorage.setItem(
                  "accountData",
                  JSON.stringify(accountData)
                );
              }
            } else {
              ElMessageBox.confirm(`Sorry,${res.message}`, "网站提示", {
                confirmButtonText: "知道了",
                showCancelButton: false,
                type: "warning",
              });
            }
          });
        } else {
        }
      });
    };

    const handleClose = (done) => {
      emit("openLoginBox", false);
    };
    const handleCloseRegister = (done) => {
      emit("openRegisterBox", false);
    };

    const register = () => {
      registerFromEl.value.validate((valid) => {
        if (valid) {
          //权限链接校验
          let obj = {};
          state.registerFrom.referer
            .split("?")[1]
            .split("&")
            .forEach((v) => {
              let arr = v.split("=");
              obj[arr[0]] = arr[1];
            });

          let data = { ...state.registerFrom, ...obj };
          speedToolSavaInfo(data).then((res) => {
            if (res.code == 200) {
              TElNotification("注册成功！");
              emit("openRegisterBox", false);
              emit("openLoginBox", true);
            } else {
              ElMessageBox.confirm(`Sorry,${res.message}`, "网站提示", {
                confirmButtonText: "知道了",
                showCancelButton: false,
                type: "warning",
              });
            }
          });
        }
      });
    };

    //登录与注册切换 0登录 1注册
    const cutLoginType = (type) => {
      if (type) {
        emit("openRegisterBox", false);
        emit("openLoginBox", true);
      } else {
        emit("openLoginBox", false);
        emit("openRegisterBox", true);
      }
    };

    onMounted(() => {
      state.historyAccount = localStorage.getItem("accountData")
        ? JSON.parse(localStorage.getItem("accountData"))
        : [];
    });

    // 快速登录
    let rapidLogin = (item) => {
      state.loginFrom.account = item.account;
      state.loginFrom.password = item.password;
      userLogin();
    };
    return {
      ...toRefs(state),
      userLogin,
      rapidLogin,
      loginFromEl,
      registerFromEl,
      handleClose,
      handleCloseRegister,
      register,
      cutLoginType,
    };
  },
};
</script>
<style lang="less" scoped>
.history-account {
  .history-account-titem {
    margin-top: 30px;
    padding-left: 18px;
  }

  .history-account-ctx {
    width: 280px;
    box-sizing: border-box;
    padding-left: 70px;
    .ctx-item {
      display: flex;
      align-items: center;
      justify-content: space-between;
      margin-top: 5px;
      border-bottom: 1px solid #e7e7e7;
      padding-bottom: 5px;
      .ctx-item-account {
        font-size: 16px;
        font-weight: 700;
      }

      /deep/ .ctx-item-btn {
        .el-button {
        }
      }
    }
  }
}
</style>