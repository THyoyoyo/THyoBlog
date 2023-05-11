<template>
  <div class="speed">
    <div class="speed-header top-padding" ref="homeHeader"></div>
    <div class="speed-ctx">
      <div class="tool">
        <div class="tool-item">
          <el-button type="info" color="red" @click="drawerBoxCourse = true">
            <span>使用教程</span>
          </el-button>
        </div>
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
        <template v-else>
          <div class="tool-item w600">
            <el-input v-model="referer" placeholder="请前往掌上APP抓取referer">
              <template #prepend>权限链接</template>
              <template #append>
                <el-button @click="setReferer()">保存</el-button>
              </template>
            </el-input>
          </div>
          <div class="tool-item">
            <el-button type="primary" color="#333333" @click="setState()">
              <span v-if="info.state" class="flex-center"
                >关闭定时任务
                <el-tooltip placement="top">
                  <template #content>
                    开启自动完成如下功能：<br />
                    1、自动00.00.00开启宝箱（需点击下方宝箱设置开启宝箱）<br />
                    2、每日自动6点领取APP礼包（每日点券、签到奖励、周末福利）
                  </template>
                  <el-icon><InfoFilled /></el-icon></el-tooltip
              ></span>
              <span v-else class="flex-center"
                >启动定时任务
                <el-tooltip placement="top">
                  <template #content>
                    开启自动完成如下功能：<br />
                    1、自动00.00.00开启宝箱<br />
                    2、每日自动6点领取APP礼包
                  </template>
                  <el-icon><InfoFilled /></el-icon></el-tooltip
              ></span>
            </el-button>
          </div>
          <div class="tool-item">
            <el-button
              type="primary"
              color="#333333"
              @click="getAwardReceiving()"
            >
              <span>一键领取APP礼包</span>
            </el-button>
          </div>
          <div class="tool-item">
            <el-button type="primary" color="#333333" @click="openBoxLogBox()">
              <span>自动开箱记录</span>
            </el-button>
          </div>
          <div class="tool-item">
            <el-button
              type="primary"
              color="#333333"
              @click="openLoginBox(true)"
            >
              <span>切换账号</span>
            </el-button>
          </div>
        </template>
      </div>
      <div class="list">
        <div class="info-item">
          <div class="info-item-title">
            <span>点券：{{ UserBagInfo.super_money }}</span>
            <span>消费券：{{ UserBagInfo.coupons }}</span>

            <span style="color: red" v-if="autoBoxInfo"
              >定时任务宝箱&数量：{{ autoBoxInfo.name }}（{{
                autoBoxInfo.num
              }}）</span
            >
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
          <div class="dialog-footer-btn-flex">
            <div class="flex-box">
              <el-input-number v-model="openNum" :min="1" :max="99" />
              <el-button type="primary" @click="setAutoBoxById()">
                设置定时开启宝箱
              </el-button>
            </div>
          </div>
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
    <!--  抽屉  -->
    <el-drawer
      v-model="drawerBoxLog"
      title="三天内开箱记录"
      direction="ltr"
      size="600px"
    >
      <div class="box-log-ctx">
        <el-table :data="openBoxLogList" :row-class-name="rowClassName">
          <el-table-column
            prop="created"
            label="开启时间"
            width="180"
            align="center"
          />

          <el-table-column prop="score" label="获取道具" align="center">
            <template #default="scope">
              <div class="box-log-dataList">
                <div
                  v-for="(item, key) in scope.row.dataList"
                  :key="key"
                  class="dataList-info"
                >
                  <img
                    :src="`https://iips.speed.qq.com/images/${item.avtarid}.png`"
                  />
                  <p>{{ item.avtarname }}</p>
                  <p v-if="item.expTime > 0">({{ item.expTime / 24 }}天)</p>
                  <p v-else>(永久)</p>
                </div>
              </div>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-drawer>
    <!-- 教程course -->
    <el-drawer
      v-model="drawerBoxCourse"
      title="使用教程"
      direction="ltr"
      size="600px"
    >
      <div class="course-box">
        <h2>注意事项：</h2>
        <p>1、教程手机系统：IOS</p>
        <p>2、需要下载App：掌上飞车、Stream</p>
        <p>3、Android系统需要在自己寻找抓包工具，流程与本教程一致</p>
        <p>
          4、抓包完成后请永久关闭掌上飞车App,不能再次打开，否则抓包数据会失效
        </p>

        <h2>链接示例（以下只是演示、请复制完整URL）：</h2>
        <p>
          https://bang.qq.com/app/speed/card/userbag?serverName=&areaName=************
        </p>
        <h2>视频教程：</h2>
        <iframe
          src="//player.bilibili.com/player.html?aid=271018840&bvid=BV18c411K7YU&cid=1125885447&page=1"
          scrolling="no"
          border="0"
          frameborder="no"
          framespacing="0"
          allowfullscreen="true"
        >
        </iframe>
      </div>
    </el-drawer>
  </div>
</template>
<script>
import { computed, reactive, toRefs } from "vue";
import { InfoFilled } from "@element-plus/icons";
import {
  getUserBagInfo,
  getUserBoxItemInfoV2,
  openBoxByKey,
  getRefererInfo,
  upReferer,
  openBox,
  speedToolUpstate,
  speedToolSetAutoBoxInfo,
  speedToolAwardReceiving,
  speedToolGetOpenBoxLog,
} from "../api/speedTool";
import { TElMessage } from "../utils/inform";
import login from "../components/speed/login.vue";
import { useStore } from "vuex";
export default {
  components: {
    login,
    InfoFilled,
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
      openNum: 1,
      info: {},
      openBoxLogList: [],
      drawerBoxLog: false,
      drawerBoxCourse: false,
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
          state.info = res.data;

          // 回显定时任务宝箱
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

    let getOpenBoxLog = () => {
      speedToolGetOpenBoxLog().then((res) => {
        state.openBoxLogList = res.data;
      });
    };

    let rowClassName = ({ row }) => {
      const date = new Date(row.created);
      const today = new Date();
      if (date.toDateString() === today.toDateString()) {
        return "today-row";
      }
      return "";
    };

    let autoBoxInfo = computed(() => {
      let info = state.box.find((v) => v.boxid == state.info.boxId);
      if (info) {
        return { name: info.name, num: state.info.openNum };
      }
      return;
    });
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

    // 启动、关闭
    let setState = () => {
      speedToolUpstate().then((res) => {
        if (res.code == 200) {
          state.info.state = state.info.state == 1 ? 0 : 1;
          TElMessage("已更新");
        }
      });
    };

    // 设置自动开启宝箱
    let setAutoBoxById = () => {
      let data = {
        boxId: state.atBoxInfo.boxid,
        keyId1: state.atBoInfoKey.keyid,
        keyNum1: state.atBoInfoKey.num,
        openNum: state.openNum,
      };
      speedToolSetAutoBoxInfo(data).then((res) => {
        if (res.code == 200) {
          TElMessage("设置成功！");
        }
      });
    };

    //一键领取APP每日签到奖励
    let getAwardReceiving = () => {
      speedToolAwardReceiving().then((res) => {
        if (res.code == 200) {
          TElMessage("领取成功！");
        }
      });
    };

    //打开记录
    let openBoxLogBox = () => {
      state.drawerBoxLog = true;
      getOpenBoxLog();
    };

    return {
      ...toRefs(state),
      openBoxLogBox,
      rowClassName,
      getAwardReceiving,
      setAutoBoxById,
      setState,
      userOpenBox,
      hanldBoxKey,
      openBoxKeyApi,
      openLoginBox,
      getList,
      openRegisterBox,
      userInfo,
      setReferer,
      getReferer,
      openBoxApi,
      autoBoxInfo,
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
    .w600 {
      width: 400px;
    }
    .tool-item {
      margin-right: 20px;
    }
    .select-box {
      :deep(.el-select) {
        width: 180px;
      }
      display: flex;
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
        span {
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
.dialog-footer-btn-flex {
  margin-left: 10px;
  display: inline-block;
  transform: translateY(2px);
  .flex-box {
    display: flex;
  }
  :deep(.el-input-number) {
    width: 110px;
    .el-input {
      line-height: normal;
    }
  }
}
.box-log-ctx {
  border: 1px solid #e0e0e0;
  border-bottom: none;
}
.box-log-dataList {
  display: flex;
  justify-content: center;
  gap: 10px;
  .dataList-info {
    flex: none;
    width: 80px;
    img {
      width: 50px;
    }
  }
}
:deep(.today-row) {
  td {
    background-color: rgb(236, 236, 236);
  }
}
.flex-center {
  display: flex;
  align-items: center;
  gap: 5px;
}
.course-box {
  h2 {
    margin: 5px 0;
  }
  p {
    text-indent: 2em;
  }
  iframe {
    border: 1px solid #cacaca;
    width: 100%;
    height: 600px;
  }
}
</style>

<style lang="less" scoped>
.select-box-option-item {
  display: flex;
  align-items: center;
  img {
    height: 30px;
  }
}
</style>
