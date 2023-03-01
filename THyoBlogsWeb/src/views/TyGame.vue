<template>
  <div class="tyGame">
    <div class="tyGame-header top-padding" ref="homeHeader"></div>
    <div class="tyGame-ctx">
      <div class="tool">
        <el-button
          type="primary"
          size="large"
          color="#000000"
          @click="tyGameBox.state = true"
          >就 现 在 , 立 刻 加 入 ！</el-button
        >
      </div>
      <div class="account">
        <div class="account-item" v-for="(item, key) in tableData" :key="key">
          <img :src="item.qqImg" alt="" />
          <p>{{ item.qqName }}</p>
          <p>ID:{{ item.userId }}</p>
          <p v-if="item.state == 1">自动监测中</p>
          <p v-else style="color: green">未启动</p>
          <p>{{ item.created }}</p>
          <div class="item-mark">
            <el-button type="success" @click="openItemTyGameBox(item)"
              >编辑信息</el-button
            >
            <el-button
              type="success"
              v-if="item.state == 0"
              @click="upCheckStaua(item, 1)"
              >启动监测</el-button
            >
            <el-button
              type="danger"
              v-if="item.state == 1"
              @click="upCheckStaua(item, 0)"
              >停止监测</el-button
            >
            <el-button type="danger" @click="stopTyTime(item)"
              >暂停时间</el-button
            >
          </div>
        </div>
      </div>
    </div>

    <!-- 添加弹窗 -->
    <el-dialog
      v-model="tyGameBox.state"
      :title="tyGameBox.title"
      width="580px"
      center
      @close="handleClose"
      destroy-on-close
    >
      <el-form
        label-width="120px"
        :model="tyGameFrom"
        ref="formRef"
        style="width: 520px"
      >
        <el-form-item
          label="QQ："
          prop="qq"
          :rules="[{ required: true, message: '请填写正确的QQ号' }]"
        >
          <el-input v-model="tyGameFrom.qq" />
        </el-form-item>
        <el-form-item
          label="腾游用户ID："
          prop="userId"
          :rules="[{ required: true, message: '请填写正确的腾游用户ID：' }]"
        >
          <el-input v-model="tyGameFrom.userId" />
        </el-form-item>
        <p style="width: 100%; text-align: right; margin-bottom: 15px">
          <a
            style="color: red"
            href="https://www.tengyoujiasu.com/u/"
            target="_blank"
            >请点击我打开腾游加速器用户中心并登陆</a
          >
          获取用户ID
        </p>
        <el-form-item
          prop="password"
          label="操作密码："
          :rules="[{ required: true, message: '请填写操作密码' }]"
        >
          <el-input v-model="tyGameFrom.password" />
        </el-form-item>
        <p style="width: 100%; text-align: right; margin-bottom: 15px">
          该密码仅用于本模块，操作权限依据
        </p>
        <el-form-item label="定时检查时间：" required>
          <el-time-picker
            v-model="tyGameFrom.autoCloseTime"
            format="HH:mm:ss"
            value-format="x"
          />
        </el-form-item>
        <p style="width: 100%; text-align: right">
          设置一个时间，每天在这个时间检查是否已暂停加速器
        </p>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="tyGameBox.state = false">取消</el-button>
          <el-button type="primary" @click="onSubmit(formRef)"
            >提交保存</el-button
          >
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<script>
import { ref, reactive, toRefs } from "vue";
import {
  tyStopTyTimePassWord,
  tyUpCheck,
  tyUserList,
  tyUserSava,
  checkUserInfo,
} from "../api/tyGame";
import { TElMessage } from "../utils/inform";
import { merge } from "../utils/common";
import { ElMessage, ElMessageBox } from "element-plus";
export default {
  setup() {
    let state = reactive({
      tableData: [],
    });

    let tyGameFromData = () => {
      return {
        id: null,
        autoCloseTime: new Date(2000, 1, 1, 59, 59, 59, 59).getTime(),
        userId: null,
        password: null,
        qq: null,
        checkPassword: null,
      };
    };
    let tyGameFrom = reactive(tyGameFromData());

    // Form
    let tyGameBox = reactive({
      state: false,
      title: "新增-腾游监控用户",
    });

    // 弹窗关闭
    const handleClose = () => {
      merge(tyGameFrom, tyGameFromData());
    };
    let formRef = ref(null);

    // 就现在立刻加入
    const onSubmit = (e) => {
      e.validate((valid) => {
        if (valid) {
          tyUserSava(tyGameFrom).then((res) => {
            if (res.code == 200) {
              if (tyGameFrom.id) {
                TElMessage("信息更新成功！");
              } else {
                TElMessage("你已成功加入，快去开启吧！");
              }
              tyGameBox.state = false;
              loadList();
            } else {
              TElMessage(res.message, "warning");
            }
          });
        } else {
          TElMessage("请填写完整信息", "warning");
          return false;
        }
      });
    };

    //列表加载
    let loadList = () => {
      tyUserList().then((res) => {
        if (res.code == 200) {
          state.tableData = res.data;
        }
      });
    };
    loadList();

    //  编辑信息
    let openItemTyGameBox = (item) => {
      checkUserPass((val) => {
        checkUserInfo({ password: val, qq: item.qq }).then((res) => {
          if (res.code == 200) {
            tyGameBox.state = true;
            res.data.autoCloseTime = res.data.autoCloseTime * 1;
            merge(tyGameFrom, res.data);
            tyGameFrom.checkPassword = res.data.password;
          } else {
            TElMessage(res.message, "warning");
          }
        });
      });
    };

    //  操作密码确认
    let checkUserPass = (callback) => {
      ElMessageBox.prompt("请输入操作密码", "本人确认！", {
        confirmButtonText: "提交",
        cancelButtonText: "关闭",
      })
        .then(({ value }) => {
          callback(value);
        })
        .catch(() => {});
    };

    // 编辑自动监测状态
    let upCheckStaua = (item, state) => {
      checkUserPass((val) => {
        checkUserInfo({ password: val, qq: item.qq }).then((res) => {
          if (res.code == 200) {
            tyUpCheck({ password: val, qq: item.qq, state }).then((res) => {
              if (res.code == 200) {
                if (state == 1) {
                  TElMessage("已开启，自动监测");
                }
                if (state == 0) {
                  TElMessage("已关闭，自动监测");
                }
                loadList();
              } else {
                TElMessage(res.message, "warning");
              }
            });
          } else {
            TElMessage(res.message, "warning");
          }
        });
      });
    };

    // 腾游账号时间停止停止
    let stopTyTime = (item) => {
      checkUserPass((val) => {
        checkUserInfo({ password: val, qq: item.qq }).then((res) => {
          if (res.code == 200) {
            tyStopTyTimePassWord({ password: val, qq: item.qq }).then((res) => {
              if (res.code == 200) {
                if (res.data.code == 200) {
                  TElMessage("加速时间已成功暂停！");
                }
                TElMessage(res.data.msg, "warning");
              } else {
                TElMessage(res.message, "warning");
              }
            });
          } else {
            TElMessage(res.message, "warning");
          }
        });
      });
    };

    return {
      stopTyTime,
      upCheckStaua,
      handleClose,
      openItemTyGameBox,
      formRef,
      onSubmit,
      tyGameBox,
      tyGameFrom,
      ...toRefs(state),
    };
  },
};
</script>
<style lang="less" scoped>
.tyGame {
  .tyGame-header {
    background-image: linear-gradient(
      to left,
      black,
      rgba(0, 0, 0, 0.459),
      black
    );
    background-size: 300%;
    animation: backMove 10s linear infinite;
  }
  @keyframes backMove {
    to {
      background-position-x: 0;
    }
    from {
      background-position-x: 300%;
    }
  }
  .tool {
    margin-top: 20px;
    position: relative;
    z-index: 2;
    padding: 0 10px;
    display: flex;
    align-items: center;
    opacity: 0.9;
  }
  .account {
    position: relative;
    z-index: 1;
    display: flex;
    flex-wrap: wrap;
    box-sizing: border-box;
    padding: 10.5px;
    .account-item {
      overflow: hidden;
      position: relative;
      width: 161px;
      height: 220px;
      text-align: center;
      background: #bebebec7;
      padding: 20px;
      margin-right: 10px;
      margin-top: 10px;
      border-radius: 3px;
      img {
        width: 100px;
        height: 100px;
        border-radius: 50%;
      }
      p {
        font-size: 18px;
        margin-top: 6px;
        font-weight: 700;
        color: rgb(61, 61, 61);
      }
      p:nth-child(2) {
      }
      p:nth-child(3) {
      }
      p:nth-child(4) {
        color: red;
      }
      p:nth-child(5) {
        font-size: 16px;
        margin-top: 10px;
      }
      &:hover {
        .item-mark {
          top: 0;
        }
      }
      .item-mark {
        width: 100%;
        height: 100%;
        position: absolute;
        background: rgba(90, 90, 90, 0.534);
        left: 0;
        top: -100%;
        transition: all 0.25s;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        > button {
          margin: 10px 0 0 0;
          padding: 10px;
        }
      }
    }
    .account-item-my {
      &::after {
        position: absolute;
        content: "";
        font-size: 20px;
        width: 80px;
        height: 80px;
        background: #ffe600c7;
        right: -40px;
        top: -40px;
        display: inline-block;
        transform: rotate(45deg);
      }
      &::before {
        z-index: 2;
        content: "我";
        position: absolute;
        font-size: 20px;
        right: 8px;
        top: 0px;
        display: inline-block;
        color: white;
      }
    }
  }
}
</style>