<template>
  <div
    class="Archive-list"
    v-infinite-scroll="load"
    :infinite-scroll-disabled="noLoad"
    :infinite-scroll-immediate="false"
  >
    <ArchiveItem v-for="(item, key) in articleList" :key="key" :item="item" />
  </div>
</template>
<script>
import ArchiveItem from "./components/archiveItem";
import { useRoute, useRouter } from "vue-router";
import { articList } from "../../api/article";
import { onMounted, reactive, toRefs, watch } from "vue";

export default {
  components: {
    ArchiveItem,
  },
  setup(props) {
    const router = useRouter();
    const route = useRoute();

    function goRouter(url) {
      router.push(`/${url}`);
    }

    //  获取文章
    let state = reactive({
      articleList: [],
      noLoad: false,
      queryData: {},
    });
    // 获取文章
    let listForm = {
      page: 1,
      pageSize: 4,
      type: "noAll",
    };

    let getArticList = () => {
      articList({ ...listForm, ...state.queryData }).then((res) => {
        if (res.code == 200) {
          if (
            state.articleList.length >= res.data.total ||
            res.data.list.length == 0
          ) {
            state.noLoad = true;
          } else {
            state.articleList = [...state.articleList, ...res.data.list];
          }
        }
      });
    };

    // 滚动加载
    let load = () => {
      listForm.page++;
      getArticList();
    };

    // 路由监听

    watch(
      () => route.query,
      (newValue, oldValue) => {
        // URL参数
        if (route.query.type == "class") {
          state.queryData.oneClassId = route.query.oneClassId;
          state.queryData.twoClassId = route.query.id;
          state.queryData.labelId = undefined;
        }

        if (route.query.type == "label") {
          state.queryData.oneClassId = undefined;
          state.queryData.twoClassId = undefined;
          state.queryData.labelId = route.query.id;
        }
        state.articleList = [];
        listForm.page = 1;
        state.noLoad = false;
        getArticList();
      },
      { immediate: true }
    );

    return {
      load,
      goRouter,
      ...toRefs(state),
    };
  },
};
</script>
<style lang="less" scoped>
</style>