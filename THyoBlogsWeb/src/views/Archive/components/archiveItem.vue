<template>
  <div class="article-item" @click="goRouter(item.id)">
    <!-- 鼠标未进入 -->
    <div class="article-item-leave">
      <img :src="item.preview" alt="" />
    </div>
    <!-- 标题 -->
    <div class="article-item-title">{{ item.title }}</div>
    <!-- 鼠标进入显示 -->
    <div class="article-item-enter">
      <div class="article-brief">
        {{ item.brief }}
      </div>
      <div class="article-info">
        <span><i class="iconfont icon-account" /> {{ item.userName }}</span>
        <span><i class="iconfont icon-clock" /> {{ item.creationTime }}</span>
        <span
          ><i class="iconfont icon-atm" />
          {{ getAllCotnent(item.latestComments?.content) || "无" }}</span
        >

        <span
          ><i class="iconfont icon-browse" />
          {{ item.viewNum > 999 ? "999+" : item.viewNum }}</span
        >
      </div>
    </div>
  </div>
</template>
<script>
import { ref, toRefs } from "vue";
import { getAllCotnent } from "../../../utils/common";
import { useRouter } from "vue-router";
export default {
  props: ["item"],
  setup(props) {
    // 跳转详情
    const router = useRouter();
    let goRouter = (id) => {
      window.open(`/archive/details?id=${id}`, "_blank");
    };
    return {
      goRouter,
      getAllCotnent,
      ...toRefs(props),
    };
  },
};
</script>
<style lang="less" scoped>
.article-item {
  position: relative;
  width: 100%;
  height: 150px;
  background: rgb(255, 255, 255);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  border-radius: 10px;
  overflow: hidden;
  cursor: pointer;
  box-sizing: border-box;
  padding: 20px;
  margin-bottom: 20px;
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
    font-size: 22px;
    color: #fff;
    transition: all 0.25s;
  }
  .article-item-enter {
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
      height: 40px;
      line-height: 23px;
      text-overflow: ellipsis; /*设置隐藏部分为省略号*/
      overflow: hidden; /*设置隐藏*/
      display: -webkit-box;
      -webkit-line-clamp: 2; /*设置显示行数，此处为2行，可设置其他数字*/
      -webkit-box-orient: vertical;
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
  }
  &:hover {
    .article-item-leave {
      filter: brightness(70%) blur(5px);
      img {
        width: 110%;
      }
    }
    .article-item-title {
      top: 15%;
      letter-spacing: 1px;
    }
    .article-item-enter {
      opacity: 1;
      transform: translateY(0px);
    }
  }
}
</style>