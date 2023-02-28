<template>
  <div class="tyGame">
    <div class="tyGame-header top-padding" ref="homeHeader"></div>
    <div class="tyGame-ctx">
      <div class="tool"></div>
      <div class="account">
        <div
          class="account-item account-item-my"
          v-for="(item, key) in 1"
          :key="key"
        >
          <img
            src="http://image.thyo.xyz/img/c568965f-8a5d-40d4-3a37-917b6d6cf2c2?imageView2/2/w/200/h/200/q/75"
            alt=""
          />
          <p>THyo</p>
          <p>ID:6307548385280</p>
          <p>自动监测中</p>
          <p>2023-2-28 15:42:19</p>
          <div class="item-mark">
            <el-button type="success">启动监测</el-button>
            <el-button type="danger">暂停时间</el-button>
            <el-button type="danger">停止监测</el-button>
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
          <el-button>取消</el-button>
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
export default {
  setup() {
    let state = reactive({
      tableData: [],
    });

    let tyGameFromData = () => {
      return {
        id: null,
        autoCloseTime: new Date(2016, 9, 10, 23, 59, 59, 59),
        userId: null,
        password: null,
        qq: null,
      };
    };
    let tyGameFrom = reactive(tyGameFromData());

    // Form
    let tyGameBox = reactive({
      state: false,
      title: "新增-腾游用户",
    });
    let formRef = ref(null);
    const onSubmit = (e) => {
      e.validate((valid) => {
        if (valid) {
          console.log("submit!");
        } else {
          console.log("error submit!");
          return false;
        }
      });
    };
    return {
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