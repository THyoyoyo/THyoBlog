<template>
  <div class="speed">
    <div class="speed-header top-padding" ref="homeHeader"></div>
    <div class="speed-ctx">
      <div class="tool">
        <el-button type="primary" size="large" color="#000000"
          >就 现 在 , 立 刻 加 入 ！</el-button
        >
        <el-button type="primary" size="large" color="#000000"
          >这个有什么用？</el-button
        >
      </div>
      <div class="list">
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

    <el-dialog v-model="checkOpenBox" title="开启宝箱" width="500px" center>
      <div class="checkOpenBox">
        <div
          class="key-item"
          v-for="(item, key) in atBoxInfo.keyInfo.list"
          :key="key"
        >
          <img
            v-if="item.keyList.length > 0"
            :src="`https://iips.speed.qq.com/images/${item.keyList[0].keyid}.png`"
            alt=""
          />
          <p v-if="item.keyList.length > 0">
            {{ item.keyList[0].name }}*{{ item.keyList[0].hasNum }}
          </p>
        </div>
      </div>

      <template #footer>
        <span class="dialog-footer">
          <el-button @click="checkOpenBox = false">关闭</el-button>
          <el-button type="primary" @click="checkOpenBox = false">
            立即开启
          </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<script>
import { reactive, toRefs } from "vue";
import { getUserBagInfo, getUserBoxItemInfoV2 } from "../api/speedTool";
import { TElMessage } from "../utils/inform";
export default {
  setup() {
    let state = reactive({
      avatar: [],
      car: [],
      pet: [],
      box: [],
      checkOpenBox: false,
      atBoxInfo: {
        num: "36",
        name: "魔幻圣典",
        type: "0",
        keyInfo: {
          openNum: "3",
          list: [
            {
              keytype: "1",
              keyList: [
                {
                  num: "1",
                  name: "幻灵附魔石",
                  hasNum: "0",
                  keyid: "16818",
                },
              ],
            },
            {
              keytype: "1",
              keyList: [
                {
                  num: "1",
                  name: "圣洁附魔石",
                  hasNum: "0",
                  keyid: "16820",
                },
              ],
            },
            {
              keytype: "1",
              keyList: [
                {
                  num: "1",
                  name: "闪雷附魔石",
                  hasNum: "0",
                  keyid: "16819",
                },
              ],
            },
          ],
        },
        boxid: "16817",
      },
    });
    getUserBagInfo().then((res) => {
      if (res.code == 200 && !res.data.res) {
        state.avatar = res.data.data.avatar_list;
        state.car = res.data.data.car_list;
        state.pet = res.data.data.pet_list;
      }
    });
    getUserBoxItemInfoV2().then((res) => {
      if (res.code == 200 && !res.data.res) {
        state.box = res.data.data.itemList;
      }
    });

    let userOpenBox = (item, type) => {
      if (type == "1") {
        state.checkOpenBox = true;
      } else {
        TElMessage("已开启" + item.name);
      }
      state.atBoxInfo = item;
    };
    return {
      ...toRefs(state),
      userOpenBox,
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
    user-select: none;
    width: 140px;
    border: 1px solid #cacaca;
    background: #f1f1f1af;
    margin: 0 auto;
    img {
      width: 140px;
      height: 140px;
    }

    p {
      text-align: center;
      padding: 5px 0;
    }
  }
}
</style>
