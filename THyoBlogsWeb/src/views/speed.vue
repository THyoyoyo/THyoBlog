<template>
  <div class="speed">
    <div class="speed-header top-padding" ref="homeHeader"></div>
    <div class="speed-ctx">
      <div class="tool">
        <template v-if="!userInfo.id">
          <el-button
            type="primary"
            size="large"
            color="#000000"
            @click="openLoginBox(true)"
            >登录</el-button
          >
          <el-button
            type="primary"
            size="large"
            color="#000000"
            @click="openRegisterBox(true)"
            >就 现 在 , 立 刻 加 入 ！</el-button
          >
        </template>
        <el-input
          v-else
          v-model="referer"
          placeholder="请前往掌上APP抓取referer"
        >
          <template #prepend>我的referer链接</template>
          <template #append>
            <el-button @click="setReferer()">保存</el-button>
          </template>
        </el-input>
      </div>
      <div class="list">
        <div class="info-item">
          <div class="info-item-title">
            <span>点券：{{ UserBagInfo.super_money }}</span>
            <span>消费券：{{ UserBagInfo.coupons }}</span>
          </div>
        </div>
        <div class="info-item">
          <div class="info-item-title">赛车</div>
          <div class="info-item-ctx">
            <div class="ctx-item" v-for="(item, key) in car" :key="key">
              <img
                :src="`https://iips.speed.qq.com/images/${item.avtarid}.png`"
                alt=""
              />
              <p>{{ item.avtarname }}</p>
            </div>
          </div>
        </div>
        <div class="info-item">
          <div class="info-item-title">宠物</div>
          <div class="info-item-ctx">
            <div class="ctx-item" v-for="(item, key) in pet" :key="key">
              <img
                :src="`https://iips.speed.qq.com/images/${item.avtarid}.png`"
                alt=""
              />
              <p>{{ item.avtarname }}</p>
            </div>
          </div>
        </div>
        <div class="info-item">
          <div class="info-item-title">服饰套装</div>
          <div class="info-item-ctx">
            <div class="ctx-item" v-for="(item, key) in avatar" :key="key">
              <img
                :src="`https://iips.speed.qq.com/images/${item.avtarid}.png`"
                alt=""
              />
              <p>{{ item.avtarname }}</p>
            </div>
          </div>
        </div>
        <div class="info-item">
          <div class="info-item-title">宝箱</div>
          <div class="info-item-ctx">
            <el-popover
              placement="top-start"
              trigger="hover"
              v-for="(item, key) in box"
              :key="key"
            >
              <template #reference>
                <div
                  class="ctx-item open-box"
                  @click="userOpenBox(item, item.keyInfo.list[0].keytype)"
                >
                  <img
                    :src="`https://iips.speed.qq.com/images/${item.boxid}.png`"
                    alt=""
                  />
                  <p>{{ item.name }} *{{ item.num }}</p>
                </div>
              </template>
              <div v-if="item.keyInfo.list[0].keytype == '1'">
                <div v-for="(_item, _key) in item.keyInfo.list" :key="_key">
                  <div
                    v-for="(__item, __key) in _item.keyList"
                    :key="__key"
                    class="key"
                  >
                    <img
                      :src="`https://iips.speed.qq.com/images/${__item.keyid}.png`"
                      alt=""
                    />
                    {{ __item.name }}*{{ __item.hasNum }}
                  </div>
                </div>
              </div>
              <div v-else>当前宝箱可直接打开</div>
            </el-popover>
          </div>
        </div>
      </div>
    </div>
    <!-- 开启宝箱 -->
    <el-dialog
      v-model="checkOpenBox"
      title="开启宝箱"
      width="500px"
      center
      :close-on-click-modal="false"
    >
      <div class="checkOpenBox">
        <div
          class="key-item"
          :class="
            item.keyList.length > 0 &&
            atBoInfoKey.keyid == item.keyList[0].keyid
              ? 'at-key-item'
              : ''
          "
          v-for="(item, key) in atBoxInfo.keyInfo.list"
          :key="key"
          @click="hanldBoxKey(item.keyList[0])"
        >
          <img
            v-if="item.keyList && item.keyList.length > 0"
            :src="`https://iips.speed.qq.com/images/${item.keyList[0].keyid}.png`"
            alt=""
          />
          <p v-if="item.keyList && item.keyList.length > 0">
            {{ item.keyList[0].name }}*{{ item.keyList[0].hasNum }}
          </p>
        </div>
      </div>

      <template #footer>
        <span class="dialog-footer">
          <el-button @click="checkOpenBox = false">关闭</el-button>
          <el-button type="primary" @click="openBoxKeyApi()">
            立即开启
          </el-button>
        </span>
      </template>
    </el-dialog>
    <!-- 开启宝箱成功返回 -->
    <el-dialog
      v-model="succeedBox"
      title="恭喜获得"
      width="600px"
      center
      :close-on-click-modal="false"
    >
      <div class="checkOpenBox">
        <div
          class="key-item"
          v-for="(item, key) in succeedBoxInfo.itemList"
          :key="key"
        >
          <img
            :src="`https://iips.speed.qq.com/images/${item.avtarid}.png`"
            alt=""
          />
          <p>
            {{ item.avtarname }} * {{ item.num }}
            <span v-if="item.expTime > 0">（{{ item.expTime / 24 }}天)</span>
          </p>
        </div>
      </div>

      <template #footer>
        <span class="dialog-footer">
          <el-button @click="succeedBox = false">关闭</el-button>
          <el-button
            type="primary"
            @click="atBoInfoKey.keytype == 1 ? openBoxKeyApi() : openBoxApi()"
          >
            继续开启
          </el-button>
        </span>
      </template>
    </el-dialog>
    <!-- 登录注册 -->
    <login
      :loginBox="loginBox"
      :registerBox="registerBox"
      @openLoginBox="openLoginBox"
      @openRegisterBox="openRegisterBox"
      @getList="getList"
      @getReferer="getReferer"
    />
  </div>
</template>
<script>
import { computed, reactive, toRefs } from "vue";
import {
  getUserBagInfo,
  getUserBoxItemInfoV2,
  openBoxByKey,
  getRefererInfo,
  upReferer,
  openBox,
} from "../api/speedTool";
import { TElMessage } from "../utils/inform";
import login from "../components/speed/login.vue";
import { useStore } from "vuex";
export default {
  components: {
    login,
  },
  setup() {
    const store = useStore();

    let userInfo = computed(() => {
      return store.state.userInfo;
    });

    let state = reactive({
      avatar: [],
      car: [],
      pet: [],
      box: [],
      checkOpenBox: false, // 打开宝箱
      atBoxInfo: {},
      atBoInfoKey: {},
      succeedBoxInfo: {},
      succeedBox: false,
      loginBox: false,
      registerBox: false,
      referer: "",
      UserBagInfo: {},
    });
    // ---------------------初始数据区---------------------

    let getList = () => {
      getUserBagInfo().then((res) => {
        if (res.code == 200 && !res.data.res) {
          state.avatar = res.data.data.avatar_list;
          state.car = res.data.data.car_list;
          state.pet = res.data.data.pet_list;

          state.UserBagInfo = res.data.data;
        }
      });
      getUserBoxItemInfoV2().then((res) => {
        if (res.code == 200 && !res.data.res) {
          state.box = res.data.data.itemList;
        }
      });
    };
    let getReferer = () => {
      getRefererInfo().then((res) => {
        if (res.code == 200) {
          state.referer = res.data.referer;
        }
      });
    };
    // 如果登录获取数据
    if (userInfo.value.id) {
      getList();
      getReferer();
    }

    let userOpenBox = (item, type) => {
      state.atBoxInfo = item;
      // type:是要用钥匙开启
      if (type == "1") {
        state.checkOpenBox = true;

        // // //  默认选择一个可用的钥匙
        try {
          item.keyInfo.list.forEach((v) => {
            if (v.keyList[0].hasNum > 0) {
              state.atBoInfoKey = v.keyList[0];
              state.atBoInfoKey.keytype = type;
              throw new Error("结束循环");
            }
          });
        } catch (error) {}
      } else {
        state.atBoInfoKey = item;
        state.atBoInfoKey.keytype = type;
        openBoxApi();
      }
    };

    let hanldBoxKey = (item) => {
      state.atBoInfoKey = item;
    };

    let openBoxKeyApi = () => {
      let data = {
        boxId: state.atBoxInfo.boxid,
        keyId1: state.atBoInfoKey.keyid,
        keyNum1: state.atBoInfoKey.num,
      };
      openBoxByKey(data).then((res) => {
        if (res.code == 200 && !res.data.data.error_no) {
          state.checkOpenBox = false;
          state.succeedBox = true;
          state.succeedBoxInfo = res.data.data;
        } else {
          TElMessage(res.data.data.msg, "warning");
        }
      });
    };

    let openBoxApi = (boxId) => {
      let data = {
        boxId: state.atBoxInfo.boxid,
      };

      openBox(data).then((res) => {
        if (res.code == 200 && !res.data.data.error_no) {
          state.checkOpenBox = false;
          state.succeedBox = true;
          state.succeedBoxInfo = res.data.data;
        } else {
          TElMessage(res.data.data.msg, "warning");
        }
      });
    };

    //------------------登录注册功能区-----------------
    //打开关闭登录
    let openLoginBox = (type, fun) => {
      if (type) {
        state.loginBox = true;
      } else {
        state.loginBox = false;
        fun && fun();
      }
    };

    // 打开关闭注册
    let openRegisterBox = (type, fun) => {
      if (type) {
        state.registerBox = true;
      } else {
        state.registerBox = false;
        fun && fun();
      }
    };
    // -----------------操作功能区-------------------
    // 更新Referer
    let setReferer = () => {
      //权限链接校验
      try {
        let obj = {};
        state.referer
          .split("?")[1]
          .split("&")
          .forEach((v) => {
            let arr = v.split("=");
            obj[arr[0]] = arr[1];
          });
        obj.referer = state.referer;
        upReferer(obj).then((res) => {
          if (res.code == 200) {
            TElMessage("修改成功！");
            getList();
          }
        });
      } catch (error) {
        TElMessage("referer格式存在错误，请检查", "warning");
        return;
      }
    };

    return {
      ...toRefs(state),
      userOpenBox,
      hanldBoxKey,
      openBoxKeyApi,
      openLoginBox,
      getList,
      openRegisterBox,
      userInfo,
      setReferer,
      openBoxApi,
    };
  },
};
</script>
<style lang="less" scoped>
.speed {
  position: relative;
  .speed-header {
    position: fixed;
    z-index: 1;
    width: 100%;
    right: 0;
    top: 0;
    background-image: linear-gradient(
      to left,
      black,
      rgba(0, 0, 0, 0.459),
      black
    );
    background-size: 300%;
    animation: backMove 10s linear infinite;
  }

  .speed-ctx {
    position: relative;
    padding-top: 80px;
    z-index: 0;
  }
  .tool {
    margin-top: 20px;
    position: relative;
    z-index: 2;
    padding: 0 30px;
    display: flex;
    align-items: center;
    opacity: 0.9;
    :deep(.el-input-group--append) {
      .el-input-group__prepend {
        background: #333333;
        color: #fff;
        box-shadow: none;
      }
      .el-input-group__append {
        .el-button {
          background: #333333;
          color: #fff;
        }
      }
    }
  }
  .list {
    margin-top: 40px;
    .info-item {
      margin-top: 20px;
      box-sizing: border-box;
      padding: 0px 30px;
      .info-item-title {
        font-size: 26px;
        font-weight: 700;
        letter-spacing: 4px;
        margin-bottom: 20px;
        color: #fff;
        padding: 8px 20px;
        background-image: linear-gradient(
          to left,
          black,
          rgba(0, 0, 0, 0.459),
          black
        );
        background-size: 300%;
        animation: backMove 8s linear infinite;
        border-radius: 3px;
        span{
          display: inline-block;
          margin-right: 100px;
        }
      }

      .info-item-ctx {
        display: flex;
        flex-wrap: wrap;
        gap: 20px 20px;
        .ctx-item {
          flex: none;
          width: 140px;
          border: 1px solid #cacaca;
          background: #f1f1f1af;
          &:hover {
            img {
              transform: scale(1.3);
            }
          }
          img {
            transition: all 0.2s;
            width: 140px;
            height: 140px;
          }
          img {
          }

          p {
            border-top: 1px solid #cacaca;
            text-align: center;
            padding: 5px 0;
          }
        }
      }
    }
    .open-box {
      cursor: pointer;
    }
  }
  @keyframes backMove {
    to {
      background-position-x: 0;
    }
    from {
      background-position-x: 300%;
    }
  }
}
.key {
  display: flex;
  align-items: center;
  img {
    height: 30px;
  }
}

.checkOpenBox {
  display: flex;
  gap: 20px;
  .key-item {
    cursor: pointer;
    user-select: none;
    width: 140px;
    box-sizing: border-box;
    border: 1px solid #cacaca;
    background: #f1f1f1af;
    margin: 0 auto;
    border-radius: 5px;
    img {
      width: 140px;
      height: 140px;
    }

    p {
      text-align: center;
      padding: 5px 0;
    }
  }
  .at-key-item {
    border: 3px solid #ff2424;
  }
}
</style>
