<template>
  <div class="layout-main-tab">
    <ul>
      <li v-for="(item, key) in mainTab" :key="key" @click="cutTab(item.path)">
        <span v-show="key == 0">当前页面：</span> {{ item.meta.name }}
        <div class="closeBtn" @click.stop="delTab(item, key)">
          <el-icon><close /></el-icon>
        </div>
      </li>
    </ul>
  </div>
</template>
<script>
import { useStore } from "vuex";
import { useRoute, useRouter } from "vue-router";
import { Close } from "@element-plus/icons";
import { computed, reactive } from "@vue/reactivity";
import { onMounted } from "@vue/runtime-core";

export default {
  components: {
    Close,
  },
  setup(props) {
    const store = useStore(); // 使用useStore方法
    const router = useRouter();
    const route = useRoute();

    let mainTab = computed(() => {
      return store.state.MainTab;
    });
    const pathUrl = computed(() => {
      return route.path.value;
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
      let { name } = data;
      let index = mainTab.value.findIndex((v) => {
        return name == v.name;
      });
      store.commit("delMainTab", index);
      if (mainTab.value.length == 0) {
        router.push("/admin");
        return;
      }
      // 如果删除的是第0的一个
      if (key == 0) {
        router.push(mainTab.value[0].path);
      }
    }
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
.layout-main-tab {
  border-bottom: 1px solid rgb(243, 243, 243);
  padding-bottom: 10px;
  height: 27px;
  ul {
    display: flex;
    li {
      position: relative;
      cursor: pointer;
      min-width: 70px;
      text-align: center;
      background: #333333;
      color: white;
      font-weight: 700;
      padding: 3px 15px;
      border-radius: 3px;
      &:nth-child(n + 1) {
        margin-right: 10px;
      }
      &:hover {
        color: rgb(250, 250, 250);
        background: rgb(199, 199, 199);
        .closeBtn {
          visibility: visible;
        }
      }
      .closeBtn {
        position: absolute;
        right: 0;
        top: 0;
        width: 15px;
        height: 15px;
        background: rgb(247, 107, 107);
        border-radius: 0 3px 0 3px;
        display: flex;
        align-items: center;
        visibility: hidden;
      }
      &:first-child {
        background: red;
      }
    }
  }
}
</style>