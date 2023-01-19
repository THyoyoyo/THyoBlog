<template>
  <div class="Home">
    <div class="Home-header top-padding" ref="homeHeader">
      <!-- 个人信息 -->
      <div class="userInfo" @click="cutBack">
        <img :src="configInfo.userImg" alt="" />
        <p class="name web-font">{{ configInfo.titlte }}</p>
        <span class="signature web-font"></span>
      </div>
    </div>
    <div class="Home-content particles">
      <!-- 文章 -->
      <div class="article">
        <div
          class="article-item"
          v-for="(item, key) in homeRecommendList"
          :key="key"
          @click="goRouter(item.id)"
        >
          <!-- 鼠标未进入 -->
          <div class="article-item-leave">
            <img :src="item.preview" alt="" />
          </div>
          <!-- 标题 -->
          <div class="article-item-title">{{ item.title }}</div>
          <!-- 鼠标进入显示 -->
          <div class="article-item-enter">
            <div class="article-brief">{{ item.brief }}</div>
            <div class="article-info">
              <span><i class="iconfont icon-account" /> {{ item.name }}</span>
              <span
                ><i class="iconfont icon-clock" />
                {{ transitionTime(item.creation_time) }}</span
              >
              <span
                ><i class="iconfont icon-atm" />
                {{ getAllCotnent(item.newComments?.content) || "无" }}</span
              >
              <span
                ><i class="iconfont icon-browse" />
                {{ item.view_num > 999 ? "999+" : item.view_num }}</span
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { computed, onMounted, reactive, ref, toRefs } from "vue";
import { useStore } from "vuex";
import { getHomeRecommend } from "../api/article";
import { transitionTime, getAllCotnent } from "../utils/common";
import { useRouter } from "vue-router";
export default {
  setup(props) {
    let state = reactive({
      homeRecommendList: [],
    });

    const store = useStore();
    // 获取完整全局配置
    let configInfo = computed(() => store.state.globalConfig.info);
    // 背景图
    let homeHeaderBack = ref(
      `linear-gradient(#000000e5 5%, rgba(27, 27, 27, 0.048) 55%),url(${configInfo.value.indexBack})`
    );

    let homeHeader = ref(null);
    let backIndex = 1; //背景图Index
    // 切换背景图
    function cutBack() {
      let img = require(`../static/images/img-${backIndex}.jpg`);
      homeHeader.value.style.backgroundImage = `linear-gradient(#000000e5 5%, rgba(27, 27, 27, 0.048) 55%),url(${img})`;
      if (backIndex >= 5) {
        backIndex = 1;
      } else {
        backIndex++;
      }
    }
    // 打字机效果
    let typed = null;
    onMounted(() => {
      typed = new Typed(".signature", {
        strings: configInfo.value.signature.split(","),
        startDelay: 100,
        loop: false,
        loopCount: 1,
        backDelay: 3000,
        typeSpeed: 100,
        showCursor: true,
        onComplete: (res) => {
          setTimeout(() => {
            if (document.querySelector(".typed-cursor") != null) {
              document.querySelector(".typed-cursor").style.opacity = "0";
            }
          }, 700);
        },
      });
    });
    //  获取首页推荐
    getHomeRecommend().then((res) => {
      if (res.code == 200) {
        state.homeRecommendList = res.data.list;
      }
    });
    // 跳转详情
    const router = useRouter();
    let goRouter = (id) => {
      router.push(`/archive/details?id=${id}`);
    };
    return {
      goRouter,
      homeHeader,
      cutBack,
      homeHeaderBack,
      configInfo,
      transitionTime,
      ...toRefs(state),
      getAllCotnent,
    };
  },
};
</script>
<style lang="less" scoped>
.Home {
  .Home-header {
    position: relative;
    z-index: 1;
    background-image: v-bind(homeHeaderBack);
    background-repeat: no-repeat;
    background-position-x: center;
    background-blend-mode: darken;
    background-size: 1920px;
    height: 1080px;
    .userInfo {
      margin-top: 250px;
      text-align: center;
      color: #fff;
      img {
        width: 120px;
        border-radius: 50%;
      }
      .name {
        margin-top: 10px;
        font-weight: 700;
        font-size: 40px;
        letter-spacing: 1px;
      }
      .signature {
        margin-top: 50px;
        font-size: 20px;
        letter-spacing: 1px;
      }
    }
  }
  .Home-content {
    .article {
      z-index: 1;
      position: relative;
      width: 1000px;
      margin: 0px auto 0 auto;
      transform: translate(0, -100px);
      .article-item {
        position: relative;
        width: 100%;
        height: 350px;
        background: rgb(255, 255, 255);
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
        border-radius: 10px;
        overflow: hidden;
        cursor: pointer;
        box-sizing: border-box;
        padding: 20px;
        margin-bottom: 40px;

        .article-item-leave {
          position: absolute;
          left: 0;
          top: 0;
          width: 100%;
          height: 350px;
          filter: brightness(70%);
          transition: all 0.25s;
          img {
            transition: all 0.25s;
            width: 100%;
            height: 100%;
            object-fit: cover;
            object-position: top;
          }
        }
        .article-item-title {
          position: absolute;
          left: 50%;
          top: 50%;
          transform: translate(-50%, -50%);
          font-weight: 700;
          font-size: 30px;
          color: #fff;
          transition: all 0.25s;
        }
        .article-item-enter {
          width: -webkit-fill-available;
          position: absolute;
          left: 0;
          bottom: 0;
          color: #fff;
          padding: 0 20px;
          opacity: 0;
          transition: all 0.25s;
          transform: translateY(50px);
          .article-brief {
            border-bottom: 1px solid #fff;
            padding-bottom: 10px;
            line-height: 23px;
          }
          .article-info {
            height: 50px;
            display: flex;
            align-items: center;
            span {
              display: flex;
              align-items: center;
              margin-right: 35px;
              i {
                font-size: 20px;
                margin-right: 3px;
              }
            }
          }
        }
        &:hover {
          .article-item-leave {
            filter: brightness(70%) blur(5px);
            img {
              width: 110%;
            }
          }
          .article-item-title {
            top: 35%;
            letter-spacing: 1px;
          }
          .article-item-enter {
            opacity: 1;
            transform: translateY(0px);
          }
        }
      }
    }
  }

  .typed-cursor {
    opacity: 1;
  }
  .typed-cursor.typed-cursor--blink {
    animation: typedjsBlink 0.7s infinite;
    -webkit-animation: typedjsBlink 0.7s infinite;
    animation: typedjsBlink 0.7s infinite;
  }
  @keyframes typedjsBlink {
    50% {
      opacity: 0;
    }
  }
  @-webkit-keyframes typedjsBlink {
    0% {
      opacity: 1;
    }
    50% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
}
:deep(.typed-cursor) {
  display: inline-block;
  font-size: 22px;
  font-weight: 700;
  opacity: 1;
  animation: typedjsBlink 0.7s infinite;
  -webkit-animation: typedjsBlink 0.7s infinite;
  animation: typedjsBlink 0.7s infinite;
  @keyframes typedjsBlink {
    50% {
      opacity: 0;
    }
  }
}
</style>
 