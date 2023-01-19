<template>
  <div class="app-left">
    <el-menu :default-active="$route.path" class="el-menu-vertical-demo" router>
      <template v-for="(item, key) in menus" :key="key">
        <el-menu-item
          :index="item.router"
          v-if="item.childs == null || item.childs.length < 1"
        >
          <el-icon>
            <component :is="item.icon" />
          </el-icon>
          <span>{{ item.name }}</span>
        </el-menu-item>
        <el-sub-menu :index="`/admin${item.router}`" v-else>
          <template #title>
            <el-icon>
              <component :is="item.icon" />
            </el-icon>
            <span>{{ item.name }}</span>
          </template>
          <el-menu-item
            v-for="(item2, key2) in item.childs"
            :key="key2"
            :index="`/admin${item.router}/${item2.router}`"
            >{{ item2.name }}</el-menu-item
          >
        </el-sub-menu>
      </template>
    </el-menu>
  </div>
</template>
<script>
import {
  Setting,
  Menu,
  House,
  Avatar,
  Notification,
  User,
  Key,
  Notebook,
} from "@element-plus/icons";
import { useRoute, useRouter } from "vue-router";
import { reactive, toRefs } from "@vue/reactivity";
import home from "../../../adminViews/home.vue";
import { useStore } from "vuex";
export default {
  components: {
    Setting,
    Menu,
    House,
    Avatar,
    House,
    User,
    Key,
    Notification,
    Notebook,
  },
  setup(props) {
    const store = useStore();

    const { menus } = store.state.userInfo || {};

    let state = reactive({
      menus: [],
    });

    const router = useRouter();
    //  渲染菜单 同时创建路由
    if (menus && menus.length > 0) {
      state.menus = menus;
    }

    return {
      ...toRefs(state),
    };
  },
};
</script>
<style lang="less" scoped>
.app-left {
  height: 100%;
  background: rgb(248, 243, 238);
  .el-menu-vertical-demo {
    height: 100%;
    overflow-x: hidden;
    overflow-y: auto;
  }
  :deep(.el-menu) {
    .el-menu-item.is-active {
      background: #535353;
      color: #fff;
    }
    .el-menu-item:hover {
      background: #535353;
      color: #fff;
    }
  }
}
</style>