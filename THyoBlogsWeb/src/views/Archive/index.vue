<template>
  <div class="archive">
    <div class="archive-header top-padding">
      <div class="archive-haeder-title web-font">
        <p>文章归档</p>
        <p>The Article Archive</p>
      </div>
    </div>
    <div class="archive-content particles">
      <div
        class="archive-content-layout"
        :class="
          $route.name == 'ArchiveDetails'
            ? 'archive-content-details-layout'
            : ''
        "
      >
        <div class="archive-content-left">
          <router-view v-slot="{ Component }">
            <transition name="fade">
              <component :is="Component" />
            </transition>
          </router-view>
        </div>
        <div class="archive-content-right" v-archiveRightScroll>
          <!-- 标签 -->
          <div class="archive-label">
            <div class="archive-right-title web-font">标签</div>
            <div class="archive-right-content">
              <ul class="archive-labe-ul">
                <li
                  v-for="(item, key) in labelList"
                  :key="key"
                  @click="goRouter('archive/list', 'label', item)"
                >
                  {{ item.name }}
                </li>
              </ul>
            </div>
          </div>
          <!-- 分类 -->
          <div class="archive-class">
            <div class="archive-right-title web-font">分类</div>
            <div class="archive-right-content">
              <el-menu
                class="archive-right-menu"
                active-text-color="#525f7f"
                :default-openeds="['0', '1']"
              >
                <el-sub-menu
                  :index="key + ''"
                  v-for="(item, key) in classList"
                  :key="key"
                >
                  <template #title>
                    <i :class="['iconfont', 'icon-' + item.icon]"></i>
                    <span>{{ item.name }}</span>
                  </template>
                  <el-menu-item
                    v-for="(item2, key2) in item.list"
                    :key="key2"
                    :index="`${key}-${key2}`"
                    @click="goRouter('archive/list', 'class', item2)"
                  >
                    <template #title>
                      <i :class="['iconfont', 'icon-' + item2.icon]"></i>
                      <span>{{ item2.name }}</span>
                    </template></el-menu-item
                  >
                </el-sub-menu>
              </el-menu>
            </div>
          </div>
        </div>
        <span class="msg web-font" />
      </div>
    </div>
  </div>
</template>
<script>
import { nextTick, onMounted, reactive, ref, toRefs, computed } from "vue";
import { useRouter, onBeforeRouteUpdate } from "vue-router";
import { useStore } from "vuex";
import {
  articleClassList,
  articleLabelList,
  articList,
  statistics,
} from "../../api/article";
export default {
  setup(props) {
    const store = useStore();
    // 获取完整全局配置
    let configInfo = computed(() => store.state.globalConfig.info);
    // 背景图
    let homeHeaderBack = ref(
      `linear-gradient(#000000e5 5%, rgba(27, 27, 27, 0.048) 55%),url(${configInfo.value.archiveBack})`
    );

    let state = reactive({
      classList: [],
      labelList: [],
    });
    // 打字机效果
    let typed = null;
    statistics().then((res) => {
      if (res.code == 200) {
        console.log("目前共计");

        typed = new Typed(".msg", {
          strings: [`很好! 目前共计 ${res.data.total} 篇日志。 继续努力哦。`],
          startDelay: 100,
          loop: false,
          loopCount: 1,
          backDelay: 3000,
          typeSpeed: 100,
        });
      }
    });

    const router = useRouter();
    function goRouter(url, type, val) {
      if (type == "class") {
        let { id, oneClassId } = val;
        router.replace({
          path: "/archive/list",
          query: { id, oneClassId, type },
        });
      }

      if (type == "label") {
        let { id } = val;
        router.replace({ path: "/archive/list", query: { id, type } });
      }
    }

    // 背景重置

    // 路由监听

    // 获取分类列表
    articleClassList().then((res) => {
      if (res.code == 200) {
        state.classList = res.data;
      }
    });
    // 获取标签
    articleLabelList({ page: 1, pageSize: 15 }).then((res) => {
      if (res.code == 200) {
        state.labelList = res.data.list;
      }
    });

    return {
      goRouter,
      ...toRefs(state),
      homeHeaderBack,
    };
  },
  directives: {
    archiveRightScroll: {
      mounted: function (el) {
        window.addEventListener("scroll", () => {
          // 滚动距离
          var scrollTop =
            document.documentElement.scrollTop || document.body.scrollTop;
          if (scrollTop > 300) {
            el.style.transform = `translateY(${scrollTop - 400 + 100}px)`;
          } else {
            el.style.transform = `translateY(0px)`;
          }
        });
      },
    },
  },
};
</script>
<style lang="less" scoped>
.archive {
  background: rgb(252, 252, 252);
  .archive-header {
    position: relative;
    z-index: 1;
    height: 500px;
    background-image: v-bind(homeHeaderBack);
    background-repeat: no-repeat;
    background-position-x: center;
    background-position-y: center;
    background-blend-mode: darken;
    background-size: 1920px;
    .archive-haeder-title {
      margin-top: 100px;
      p {
        font-weight: 700;
        font-size: 35px;
        text-align: center;
        color: #fff;

        &:nth-child(1) {
          letter-spacing: 3px;
        }
        &:nth-child(2) {
        }
      }
    }
  }
  .archive-content {
    position: relative;
    .archive-content-layout {
      position: relative;
      z-index: 1;
      width: 1210px;
      margin: 0 auto;
      transform: translateY(-100px);
      box-sizing: border-box;
      display: flex;
      justify-content: space-between;
      transition: all 0.2s;
      .archive-content-left {
        width: 870px;
        min-height: 500px;
        background: rgba(255, 255, 255, 0.8);
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
        border-radius: 5px;
        padding: 30px;
        box-sizing: border-box;
        transition: all 0.2s;
      }
      .archive-content-right {
        width: 300px;
        height: 100%;
        right: 0;
        transition: all 0.2s;
        .archive-label {
          // height: 200px;
          background: rgba(255, 255, 255, 0.8);
          box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
          border-radius: 5px;
          box-sizing: border-box;
          padding: 10px 10px 20px 10px;
          .archive-labe-ul {
            display: flex;
            flex-wrap: wrap;
            li {
              color: #fff;
              flex: none;
              padding: 5px 8px;
              background: #bbcaf0;
              margin-top: 10px;
              margin-right: 6px;
              border-radius: 5px;
              cursor: pointer;
            }
          }
        }
        .archive-class {
          margin-top: 20px;
          background: rgba(255, 255, 255, 0.8);
          box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
          border-radius: 5px;
          box-sizing: border-box;
          padding: 10px;
        }
        .archive-right-title {
          text-align: center;
          font-size: 20px;
          color: #525f7f;
          border-bottom: 2px solid #bbcaf0;
          padding-bottom: 10px;
        }
        .archive-right-content {
          :deep(.archive-right-menu) {
            border-right: none;
            background-color: transparent;
            .el-menu {
              background-color: transparent;
            }
            .el-sub-menu__title,
            .el-menu-item {
              color: #525f7f;
            }
            .iconfont {
              margin-right: 3px;
              font-size: 18px;
            }
          }
        }
      }
      .msg {
        position: absolute;
        top: -23px;
        left: 0;
        color: whitesmoke;
        letter-spacing: 1px;
        display: inline-block;
      }
    }
  }
}
.fade-enter-active,
.fade-leave-active {
  transition: all 0.2s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>