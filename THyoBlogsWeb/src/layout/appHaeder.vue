<template>
  <div class="appHaeder" v-appHaederScroll>
    <div class="appHaeder-layout">
      <div class="appHaeder-layout-left web-font vibrate-1">THYO'S BLOG</div>
      <div class="appHaeder-layout-right">
        <div class="menus web-font">
          <ul>
            <li @click="goRouter('')">首页</li>
            <li @click="goRouter('archive')">归档</li>
            <li @click="goRouter('AboutMe')">关于</li>
            <li>留言板</li>
          </ul>
        </div>
        <el-input v-model="input" placeholder="Please input" />
      </div>
    </div>
  </div>
</template>
<script>
import { ref } from "vue";
import { useRouter } from "vue-router";
export default {
  setup(props) {
    let input = ref("");
    const router = useRouter();

    function goRouter(url) {
      router.push(`/${url}`);
    }
    return {
      input,
      goRouter,
    };
  },
  directives: {
    appHaederScroll: {
      mounted: function (el) {
        window.addEventListener("scroll", () => {
          // 滚动距离
          var scrollTop =
            document.documentElement.scrollTop || document.body.scrollTop;
          if (scrollTop <= 1000) {
            let depth =
              parseInt((scrollTop / 1000) * 100) < 10
                ? "0" + parseInt((scrollTop / 1000) * 100)
                : parseInt((scrollTop / 1000) * 100);
            el.style.background = `#000000${depth}`;
          }
        });
      },
    },
  },
};
</script>
<style lang="less" scoped>
.appHaeder {
  user-select: none;
  height: 80px;
  width: 100%;
  background: transparent;
  position: fixed;
  z-index: 999;
  right: 0;
  top: 0;
  .appHaeder-layout {
    width: 1200px;
    height: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin: 0 auto;
    .appHaeder-layout-left {
      color: #fff;
      font-size: 24px;
    }
    .appHaeder-layout-right {
      color: #fff;
      display: flex;
      align-items: center;
      .menus {
        ul {
          display: flex;
          li {
            font-size: 16px;
            cursor: pointer;
            margin-right: 30px;
            transition: all 0.3s;
            min-width: 70px;
            text-align: center;
            &:hover {
              color: rgb(224, 224, 224);
            }
          }
        }
      }
      :deep(.el-input) {
        --el-input-focus-border: #fff;
        .el-input__inner {
          background: #ffffff30;
          border-color: #ffffff70;
          color: #fff;
        }
      }
    }
  }
}
</style>