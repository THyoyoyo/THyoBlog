<template>
  <div class="speed">
    <div class="speed-header top-padding" ref="homeHeader"></div>
    <div class="speed-ctx">
      <div class="list">
        <div class="info-item">
          <div class="info-item-title">
            <span v-if="userInfo.gameInfoList">
              {{ userInfo.gameInfoList[0].roleName }}</span
            >
            <span> {{ onlineInfo.online_text }}</span>
          </div>
        </div>
      </div>
      <el-table :data="battleList" style="width: 100%">
        <el-table-column prop="game_id" label="ID" width="180" align="center" />
        <el-table-column
          prop="champion_id"
          label="使用英雄"
          width="180"
          align="center"
        >
          <template #default="scope">
            <div>
              <img
                style="width: 50px"
                :src="`http://218.201.39.185:49155/down.qq.com/lolapp/lol/hero/head/${scope.row.champion_id}.png`"
                alt=""
              />
            </div>
          </template>
        </el-table-column>
        <el-table-column
          prop="battle_time"
          label="对局开始时间"
          width="180"
          align="center"
        />
        <el-table-column
          prop="game_mode_name"
          label="模式"
          width="180"
          align="center"
        />
        <el-table-column
          prop="champions_killed"
          label="击杀"
          width="180"
          align="center"
        />
        <el-table-column
          prop="num_deaths"
          label="死亡"
          width="180"
          align="center"
        />
        <el-table-column
          prop="assists"
          label="助攻"
          width="180"
          align="center"
        />
        <el-table-column prop="score" label="评分" align="center" />
      </el-table>
    </div>
  </div>
</template>
<script>
import { reactive, toRefs } from "vue";
import { getBattleList, getOnlineStatus, userProfileInfo } from "../api/lol";
export default {
  setup(props) {
    let state = reactive({
      battleList: [],
      onlineInfo: {},
      userInfo: {},
    });
    getBattleList().then((res) => {
      state.battleList = res.data.data.player_battle_brief_list;
    });

    getOnlineStatus().then((res) => {
      state.onlineInfo = res.data.data;
    });

    userProfileInfo().then((res) => {
      state.userInfo = res.data.data[0];
    });
    return {
      ...toRefs(state),
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
    padding: 80px 30px 0 30px;
    .list {
      margin-top: 40px;
      .info-item {
        margin-top: 20px;
        box-sizing: border-box;

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
  }
}
</style>
 