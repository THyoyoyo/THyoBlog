<template>
  <div class="user-role">
    <div class="query-tool">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="ID" align="center">
          <el-input
            v-model="formInline.user"
            placeholder=""
            :input-style="{ width: '50px' }"
          />
        </el-form-item>
        <el-form-item label="角色名称">
          <el-input
            v-model="formInline.region"
            placeholder=""
            :input-style="{ width: '100px' }"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" aria-setsize="mini" @click="onSubmit"
            >查询</el-button
          >
        </el-form-item>
      </el-form>
    </div>
    <el-table
      :data="tableData"
      stripe
      style="width: 100%"
      max-height="620px"
      border
    >
      <el-table-column prop="id" label="id" width="180" />
      <el-table-column prop="name" label="Name" width="180" />
      <el-table-column prop="creationTime" label="creationTime" width="180" />
      <el-table-column prop="" label="操作" align="right">
        <template #header>
          <el-button size="small" type="success" @click="openRoleFrom()"
            >创建角色</el-button
          >
        </template>
        <template #default="scoped">
          <el-button size="small" @click="openRoleFrom(scoped.row)"
            >编辑</el-button
          >

          <el-popconfirm :title="`是否删除${scoped.row.title}`">
            <template #reference>
              <el-button size="small" type="danger">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加弹窗 -->
    <el-dialog
      v-model="roleBox.state"
      :title="roleBox.title"
      destroy-on-close
      width="30%"
      center
    >
      <el-form
        label-width="120px"
        :model="roleFrom"
        ref="refRoleFrom"
        style="max-width: 520px"
      >
        <el-form-item label="角色名称：">
          <el-input v-model="roleFrom.name" />
        </el-form-item>
        <el-form-item label="菜单：">
          <el-tree
            node-key="id"
            ref="treeRef"
            :data="menusLis"
            :props="defaultProps"
            show-checkbox
            default-expand-all
            check-strictly
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="roleBox.state = false">取消</el-button>
          <el-button type="primary" @click="onSubmit()">提交保存</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<script>
import { ref, reactive, toRefs, nextTick } from "vue";
import { merge } from "../../utils/common";
import { TElNotification } from "../../utils/inform";
import {
  getSystemRoleList,
  getSystemRoleSava,
  getSystemRoleDel,
  getSystemMenusList,
} from "../../api/system";
export default {
  setup() {
    const formInline = reactive({
      user: "",
      region: "",
    });

    let state = reactive({
      tableData: [],
      menusLis: [],
    });

    // 加载列表
    let loadList = () => {
      getSystemRoleList().then((res) => {
        if (res.code == 200) {
          state.tableData = res.data;
        }
      });
    };
    loadList();
    //加载菜单
    getSystemMenusList({ type: "all" }).then((res) => {
      state.menusLis = res.data;
    });
    let roleFromData = () => {
      return {
        id: "",
        menuIds: [],
        name: "",
      };
    };
    let roleFrom = reactive(roleFromData());

    let roleBox = reactive({
      state: false,
      title: "新增菜单",
    });
    const treeRef = ref(null);
    //打开弹窗
    let openRoleFrom = (item) => {
      roleBox.state = true;
      Object.assign(roleFrom, roleFromData());
      if (item) {
        roleBox.title = `正在编辑${item.name}菜单`;
        merge(roleFrom, item);
        nextTick(() => {
          treeRef.value.setCheckedKeys(item.menuIds, false);
        });
        return;
      }
      roleBox.title = `新增菜单`;
      nextTick(() => {
        treeRef.value.setCheckedKeys([], false);
      });
    };
    //Tree配置
    const defaultProps = {
      children: "childs",
      label: "name",
      disabled: "disabled",
    };
    //提交
    const onSubmit = () => {




 
      let menuIds = treeRef.value.getCheckedNodes(false,true).map((v) => {
        return v.id;
      });
      
      let data = { ...roleFrom, menuIds };
      getSystemRoleSava(data).then((res) => {
        if (res.code == 200) {
          TElNotification("保存成功").then(() => {
            roleBox.state = false;
            loadList();
          });
        }
      });
    };
    return {
      openRoleFrom,
      roleBox,
      roleFrom,
      formInline,
      onSubmit,
      ...toRefs(state),
      treeRef,
      defaultProps,
    };
  },
};
</script>
<style lang="less" scoped>
.user-role {
  .query-tool {
    margin: 20px 0;
    border: 1px solid #ebeef5;
    :deep(.el-form) {
      display: flex;
      align-items: center;
      .el-form-item {
        margin-bottom: 0;
        padding: 10px 10px;
      }
    }
  }
  :deep(.el-pagination) {
    padding: 0;
    margin-top: 20px;
  }
  :deep(.el-tree) {
    background: transparent;
  }
}
</style>