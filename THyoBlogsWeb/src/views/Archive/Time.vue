<template>
  <div
    class="ArchiveTime"
    v-infinite-scroll="load"
    :infinite-scroll-disabled="noLoad"
    :infinite-scroll-immediate="false"
  >
    <el-timeline>
      <el-timeline-item
        :timestamp="item.creationTime"
        placement="top"
        color="#bbcaf0"
        v-for="(item, key) in articleList"
        :key="key"
      >
        <el-card
          :style="`--preview: url(${item.preview})`"
          @click="goRouter(item.id)"
        >
          <h4 class="title">{{ item.title }}</h4>
          <div class="labels">
            <el-tag
              type="info"
              v-for="(item2, key2) in item.labelName"
              :key="key2"
              >{{ item2.name }}</el-tag
            >
          </div>
          <p class="brief">{{ item.brief }}</p>
          <div class="article-info">
            <span><i class="iconfont icon-account" /> {{ item.userName }}</span>
            <span
              ><i class="iconfont icon-clock" /> {{ item.creationTime }}</span
            >
            <span
              ><i class="iconfont icon-atm" />

              {{ getAllCotnent(item.latestComments?.content) || "无" }}</span
            >
            <span
              ><i class="iconfont icon-browse" />
              {{ item.viewNum > 999 ? "999+" : item.viewNum }}</span
            >
          </div>
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>
<script>
import { reactive, ref, toRefs } from "vue";
import { articList } from "../../api/article";
import { getAllCotnent } from "../../utils/common";
import { useRouter } from "vue-router";
export default {
  setup() {
    let state = reactive({
      articleList: [],
      noLoad: false,
    });
    // 获取文章
    let listForm = {
      page: 1,
      pageSize: 4,
      type: "noAll",
    };

    let getArticList = () => {
      articList(listForm).then((res) => {
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
    getArticList();
    // 滚动加载
    let load = () => {
      listForm.page++;
      getArticList();
    };
    // 跳转详情
    const router = useRouter();
    let goRouter = (id) => {
      router.push(`/archive/details?id=${id}`);
    };
    return {
      goRouter,
      load,
      ...toRefs(state),
      getAllCotnent,
    };
  },
};
</script>
<style lang="less" scoped>
.ArchiveTime {
  height: auto;
}
:deep(.el-card) {
  border-radius: 6px;
}
:deep(.el-card__body) {
  padding: 15px 15px 0 15px;
  // background-image: var(--preview);
  // @back:linear-gradient (#000000e5 5%, rgba(27, 27, 27, 0.2) 100%),;
  background-image: linear-gradient(#000000e5 5%, rgba(27, 27, 27, 0.2) 100%),
    var(--preview);
  background-repeat: no-repeat;
  background-size: 100%;
  color: #fff;
  .title {
    font-size: 20px;
  }

  .brief {
    padding: 10px 0;
    line-height: 25px;
    border-bottom: 1px solid #ddd;
  }
  .article-info {
    height: 40px;
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
  .labels {
    margin-top: 10px;
    .el-tag {
      background: transparent;
      color: #fff;
      margin-right: 5px;
    }
  }
}
</style>