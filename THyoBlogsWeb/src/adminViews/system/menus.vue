<template>
  <div class="system-menus">
    <el-table :data="tableData" stripe style="width: 100%" border>
      <el-table-column type="expand">
        <template #default="props">
          <div class="childrenTable">
            <el-table :data="props.row.childs" border style="width: 100%">
              <el-table-column prop="id" label="ID" width="60" align="center" />
              <el-table-column prop="icon" label="菜单图标" width="100" />
              <el-table-column prop="name" label="菜单名称" width="120" />
              <el-table-column prop="type" label="类型" width="120">
                <template #default="scoped">
                  <p>{{ scoped.row.type == 1 ? "菜单" : "按钮" }}</p>
                </template>
              </el-table-column>
              <el-table-column prop="routerName" label="路由名称" width="120" />
              <el-table-column prop="sort" label="排序" width="80" />
              <el-table-column prop="router" label="前端路由(URL)" />
              <el-table-column prop="fileAddress" label="前端页面文件地址" />
              <el-table-column
                prop="creationTime"
                label="创建时间"
                width="180"
              />
              <el-table-column prop="creation_time" label="操作" align="right">
                <template #header>
                  <el-button
                    size="small"
                    type="success"
                    @click="openMenusFrom(null, props.row)"
                    >新增 <span class="hi"> {{ props.row.name }} </span> 菜单
                  </el-button>
                </template>
                <template #default="scoped">
                  <el-button
                    size="small"
                    @click="openMenusFrom(scoped.row, props.row)"
                    >编辑</el-button
                  >
                  <el-popconfirm :title="`是否删除${scoped.row.name}`">
                    <template #reference>
                      <el-button size="small" type="danger">删除</el-button>
                    </template>
                  </el-popconfirm>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="id" label="ID" width="60" align="center" />
      <el-table-column prop="icon" label="菜单图标" width="100" />
      <el-table-column prop="name" label="菜单名称" width="120" />
      <el-table-column prop="type" label="类型" width="120">
        <template #default="scoped">
          <p>{{ scoped.row.type == 1 ? "菜单" : "按钮" }}</p>
        </template>
      </el-table-column>
      <el-table-column prop="routerName" label="路由名称" width="120" />
      <el-table-column prop="sort" label="排序" width="80" />
      <el-table-column prop="router" label="前端路由(URL)" />
      <!-- <el-table-column prop="fileAddress" label="前端页面文件地址" /> -->
      <el-table-column prop="creationTime" label="创建时间" width="180" />
      <el-table-column prop="id" align="right">
        <template #header>
          <el-button size="small" type="success" @click="openMenusFrom()"
            >新增菜单</el-button
          >
        </template>
        <template #default="scoped">
          <el-button size="small" @click="openMenusFrom(scoped.row)"
            >编辑</el-button
          >
          <el-popconfirm
            :title="`是否删除${scoped.row.name}`"
            @confirm="delSysMenu(scoped.row.id)"
          >
            <template #reference>
              <el-button size="small" type="danger">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!-- 添加弹窗 -->
    <el-dialog
      v-model="menusBox.state"
      :title="menusBox.title"
      width="30%"
      center
    >
      <el-form
        label-width="120px"
        :model="menusFrom"
        ref="refMenusFrom"
        style="max-width: 520px"
      >
        <el-form-item label="菜单名称：" required>
          <el-input v-model="menusFrom.name" />
        </el-form-item>
        <el-form-item label="类型：">
          <el-radio-group v-model="menusFrom.type">
            <el-radio :label="1">菜单</el-radio>
            <el-radio :label="2">按钮</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="菜单图标：">
          <el-input v-model="menusFrom.icon" />
        </el-form-item>
        <el-form-item label="路由名称：">
          <el-input v-model="menusFrom.routerName" />
        </el-form-item>
        <el-form-item label="前端路由：">
          <el-input v-model="menusFrom.router" />
        </el-form-item>
        <!-- <el-form-item label="前端页面地址">
          <el-input v-model="menusFrom.fileAddress" />
        </el-form-item> -->
        <el-form-item label="排序：">
          <el-input v-model="menusFrom.sort" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="menusBox.state = false">取消</el-button>
          <el-button type="primary" @click="onSubmit()">提交保存</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<script>
import { ref, reactive, toRefs } from "vue";
import {
  getSystemMenusList,
  SystemMenusListSava,
  SystemMenusListDel,
} from "../../api/system";
import { merge } from "../../utils/common";
import { TElNotification } from "../../utils/inform";
export default {
  setup() {
    let state = reactive({
      tableData: [],
    });
    const onSubmit = () => {
      console.log(menusFrom);
      SystemMenusListSava(menusFrom).then((res) => {
        if (res.code == 200) {
          menusBox.state = false;
          if (menusFrom.id) {
            TElNotification("成功修改");
          } else {
            TElNotification("成功添加");
          }
          getList();
        }
      });
    };

    //获取列表
    let getList = () => {
      getSystemMenusList().then((res) => {
        if (res.code == 200) {
          state.tableData = res.data;
        }
      });
    };
    getList();
    // Form
    let menusBox = reactive({
      state: false,
      title: "新增菜单",
    });

    let menusFromData = () => {
      return {
        id: "",
        name: "",
        icon: "",
        router: "",
        routerName: "",
        fileAddress: "",
        parentId: null,
        sort: "",
        type: "",
      };
    };
    let menusFrom = reactive(menusFromData());

    //打开弹窗
    let openMenusFrom = (item, parentItem) => {
      Object.assign(menusFrom, menusFromData());
      menusBox.state = true;
      if (parentItem) {
        if (item) {
          menusBox.title = `正在编辑${item.name}子菜单`;
          merge(menusFrom, item);
          return;
        }
        menusFrom.parentId = parentItem.id;
        menusBox.title = `正在为${parentItem.name}添加子菜单`;
      } else {
        if (item) {
          menusBox.title = `正在编辑${item.name}菜单`;
          merge(menusFrom, item);
          return;
        }
        menusBox.title = `新增菜单`;
      }
    };

    //删除菜单
    let delSysMenu = (id) => {
      SystemMenusListDel({ id }).then((res) => {
        if (res.code == 200) {
          getList();
        }
      });
    };
    return {
      onSubmit,
      ...toRefs(state),
      menusBox,
      menusFrom,
      openMenusFrom,
      getList,
      delSysMenu,
    };
  },
};
</script>
<style lang="less" scoped>
.system-menus {
  margin-top: 20px;
  :deep(.el-pagination) {
    padding: 0;
    margin-top: 20px;
  }
  .hi {
    display: inline-block;
    font-weight: 700;
    color: rgb(211, 15, 15);
    padding: 0 3px;
  }
  .el-table__expanded-cell {
    padding: 0;
    padding: 0;
  }
  .childrenTable {
    background: #e5e5e5;
    padding: 10px 10px 10px 46px;
  }
}
</style>