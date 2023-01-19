<template>
  <div class="layout">
    <AppHeader />
    <div class="layout-main">
      <AppLeft class="layout-main-r" />
      <div class="layout-main-l">
        <!-- 页面Tab -->
        <MianTab />
        <!-- 组件缓存 -->
        <router-view v-slot="{ Component }" class="layout-main-content">
          <keep-alive :include="keepLiveRoute">
            <component :is="Component" />
          </keep-alive>
        </router-view>
      </div>
    </div>
  </div>
</template>
<script>
import AppHeader from "./components/appHeader.vue";
import AppLeft from "./components/appLeft.vue";
import MianTab from "../../components/MianTab.vue";
import { useStore } from "vuex";
import { useRoute, useRouter } from "vue-router";
import { Close } from "@element-plus/icons";
import { computed, ref } from "@vue/reactivity";
import { nextTick, onMounted } from "@vue/runtime-core";
import { checkInfo } from "../../api/user";
export default {
  components: {
    AppLeft,
    AppHeader,
    Close,
    MianTab,
  },
  setup(props) {
    const store = useStore(); // 使用useStore方法
    const router = useRouter();
    const route = useRoute();
    const pathUrl = computed(() => {
      return route.path.value;
    });
    // 获取 store
    let mainTab = computed(() => {
      return store.state.MainTab;
    });
    let keepLiveRoute = computed(() => {
      return store.state.keepLiveRoute;
    });
    // tab 页面跳转
    function cutTab(url) {
      router.push(url);
    }
    // tab 删除
    function delTab(data, key) {
      store.commit("delMainTab", { name: data.name });
      if (mainTab.value.length > 0 && key == 0) {
        router.push(mainTab.value[0].url);
      } else {
        router.push("/");
      }
    }

    checkInfo();
    return {
      mainTab,
      keepLiveRoute,
      pathUrl,
      cutTab,
      delTab,
    };
  },
};
</script>
<style lang="less" scoped>
.layout {
  height: 100%;
}
.layout-main {
  display: flex;
  height: calc(~"100% - 80px");
  width: 100%;
  box-sizing: border-box;
  .layout-main-r {
    flex: 1;
    height: 100%;
    min-width: 200px;
  }
  .layout-main-l {
    width: calc(~"100% - 200px");
    box-sizing: border-box;
    padding: 10px;
    overflow-y: auto;
    .layout-main-content {
    }
  }
}
</style>