<template>
  <div class="user-list">
    <div class="query-tool">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="ID" align="center">
          <el-input
            v-model="formInline.user"
            placeholder=""
            :input-style="{ width: '50px' }"
          />
        </el-form-item>
        <el-form-item label="昵称">
          <el-input
            v-model="formInline.region"
            placeholder=""
            :input-style="{ width: '100px' }"
          />
        </el-form-item>
        <el-form-item label="帐号">
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
      <el-table-column prop="id" label="ID" width="60" />
      <el-table-column prop="head" label="头像" width="70" align="center">
        <template #default="scoped">
          <div class="tableHeadBox">
            <img :src="scoped.row.head + '?imageView2/2/w/200/h/200/q/75'" />
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="昵称" width="140" />
      <el-table-column prop="age" label="年龄" width="100" />
      <el-table-column prop="account" label="帐号" width="180" />
      <el-table-column prop="roleName" label="角色" width="180" />

      <el-table-column prop="lastTime" label="最近一次登录时间" width="200" />
      <el-table-column prop="creationTime" label="创建时间" width="200" />
      <el-table-column prop="id" label="操作" align="right">
        <template #header>
          <el-button size="small" type="success" @click="showUserBox(1)"
            >新增用户</el-button
          >
        </template>
        <template #default="scoped">
          <el-button size="small" @click="showUserBox(2, scoped.row)"
            >编辑</el-button
          >
          <el-button size="small" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      layout="prev, pager, next"
      :total="pageInfo.total"
      @current-change="(index) => getList(index)"
    />

    <!-- 添加弹窗 -->
    <el-dialog
      v-model="userBox.state"
      :title="userBox.title"
      custom-class="userBox"
      width="500px"
      center
      ref="dialog"
    >
      <el-form
        label-width="100px"
        :model="userForm"
        ref="refuserForm"
        label-position="top"
      >
        <el-form-item label="">
          <template #default>
            <div class="headBox">
              <el-upload
                class="avatar-uploader"
                :http-request="uploadFile"
                :show-file-list="false"
                action=""
              >
                <img
                  v-if="userForm.head"
                  :src="userForm.head + '?imageView2/2/w/200/h/200/q/75'"
                  class="avatar"
                />
                <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
              </el-upload>
            </div>
          </template>
        </el-form-item>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="用户帐号：">
              <el-input v-model="userForm.account" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="用户密码：">
              <el-input v-model="userForm.password" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="昵称：">
              <el-input v-model="userForm.name" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="年龄：">
              <el-input v-model="userForm.age" /> </el-form-item
          ></el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="角色：">
              <el-select
                v-model="userForm.roleId"
                placeholder="请选择用户所属角色"
              >
                <el-option
                  v-for="(item, key) in roleList"
                  :key="key"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态：">
              <el-select v-model="userForm.state">
                <el-option label="启用" :value="1"> </el-option>
                <el-option label="停用" :value="0"> </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="简介：">
          <el-input v-model="userForm.signature" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="userBox.state = false">取消</el-button>
          <el-button type="primary" @click="onSubmit()">提交保存</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<script>
import { ref, reactive, toRefs } from "vue";
import { Plus } from "@element-plus/icons";
import { getBase64 } from "../../utils/getBase64";
import { userList, userAdd, userUp, userDel } from "../../api/user";
import { getSystemRoleList } from "../../api/system";
import { TElNotification } from "../../utils/inform";
import uploadFiles from "../../utils/uploadFiles";
import { ElLoading } from "element-plus";
export default {
  components: {
    Plus,
  },
  setup() {
    let state = reactive({
      tableData: [],
      roleList: [],
    });
    const formInline = reactive({
      user: "",
      region: "",
    });

    let userFormData = () => {
      return {
        id: "",
        name: "",
        age: "",
        signature: "",
        head: "",
        roleId: "",
        account: "",
        password: "",
        state: 1,
      };
    };
    let userForm = reactive(userFormData());

    let userBox = reactive({
      state: false,
      title: "",
      type: null,
    });

    const onSubmit = () => {
      let { id } = userForm;
      if (id) {
        userUp(userForm).then((res) => {
          if (res.code == 200) {
            TElNotification("保存成功").then((res) => {
              userBox.state = false;
              getList();
            });
          }
        });
      } else {
        userAdd(userForm).then((res) => {
          if (res.code == 200) {
            TElNotification("保存成功").then((res) => {
              userBox.state = false;
              getList();
            });
          }
        });
      }
    };

    let showUserBox = (type, item) => {
      userBox.state = true;
      userBox.type = type;
      // 是否编辑
      if (item) {
        userBox.title = `正在编辑 ： ${item.name}`;
        Object.assign(userForm, item);
        return;
      }
      userBox.title = `新增用户`;
      Object.assign(userForm, userFormData());
    };
    // 上传文件
    let uploadFile = (params) => {
      const loadingInstance1 = ElLoading.service({
        text: "资源上传中...",
        target: document.querySelector(".userBox"),
      });
      uploadFiles(params.file).then((res) => {
        userForm.head = process.env.VUE_APP_QIN_NIU_CDN + res.key;
        loadingInstance1.close();
      });
    };

    // 获取列表
    let pageInfo = reactive({
      page: 1,
      pageSize: 10,
      total: 0,
    });
    let getList = (page) => {
      if (page) {
        pageInfo.page = page;
      }
      userList(pageInfo).then((res) => {
        if (res.code == 200) {
          state.tableData = res.data.list;
          pageInfo.total = res.data.total;
        }
      });
    };
    getList();

    // 获取角色列表
    getSystemRoleList().then((res) => {
      state.roleList = res.data;
    });
    getSystemRoleList();
    return {
      ...toRefs(state),
      formInline,
      onSubmit,
      userForm,
      userBox,
      showUserBox,
      uploadFile,
      pageInfo,
      getList,
    };
  },
};
</script>
<style lang="less" scoped>
.user-list {
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

  .tableHeadBox {
    img {
      width: 30px;
    }
  }

  .headBox {
    width: 100%;
    display: flex;
    justify-content: center;
    :deep(.avatar-uploader) {
      .el-icon.avatar-uploader-icon {
        font-size: 28px;
        border-radius: 50%;
        color: #8c939d;
        width: 100px;
        height: 100px;
        text-align: center;
        border: 1px dashed #a2a3a3;
      }
      .avatar {
        width: 100px;
        height: 100px;
        text-align: center;
        border-radius: 50%;
        border: 1px dashed #a2a3a3;
      }
    }
  }
}
</style>