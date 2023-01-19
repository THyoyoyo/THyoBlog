<template>
  <el-drawer
    v-model="drawerBox.state"
    title="字体图标选择器"
    direction="rtl"
    size="700px"
  >
    <div class="drawerBoxMain">
      <div
        class="icon-item"
        v-for="(item, key) in iconsData.glyphs"
        :key="key"
        @click="setIcon(item)"
      >
        <i :class="['iconfont', 'icon-' + item.font_class]"></i>
        <p>{{ item.name }}</p>
      </div>
    </div>
  </el-drawer>
</template>

<script>
import { reactive, defineEmit, toRefs } from "vue";

import axios from "axios";
export default {
  name: "drawerBoxIcon",
  emits: ["setDraweIcon"],
  setup(props, ctx) {
    let state = reactive({
      iconsData: {
        glyphs: [],
      },
    });
    let drawerBox = reactive({
      state: false,
    });
    let openDrawerIconBox = () => {
      drawerBox.state = true;
    };
    let setIcon = (val) => {
      ctx.emit("setDraweIcon", val.font_class);
      drawerBox.state = false;
    };
    // 获取阿里icon字体库JSON
    axios({
      url: process.env.VUE_APP_A_LI_CION_JSON,
    }).then((res) => {
      state.iconsData = res.data;
    });
    return {
      drawerBox,
      openDrawerIconBox,
      setIcon,
      ...toRefs(state),
    };
  },
};
</script>

<style lang="less" scoped>
div.drawerBoxMain {
  display: flex;
  flex-wrap: wrap;
  div.icon-item {
    margin-top: 10px;
    margin-right: 23px;
    flex: none;
    width: 70px;
    display: flex;
    flex-direction: column;
    align-items: center;
    i {
      flex: none;
      width: 38px;
      font-size: 38px;
    }
    p {
      width: 80px;
      text-align: center;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
    &:hover {
      background: #eee;
      cursor: pointer;
    }
  }
}
</style> 