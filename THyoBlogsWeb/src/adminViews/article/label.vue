<template>
  <div class="article-label">
    <div class="query-tool">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="ID" align="center">
          <el-input
            v-model="formInline.user"
            placeholder=""
            :input-style="{ width: '50px' }"
          />
        </el-form-item>
        <el-form-item label="标签名称">
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
      <el-table-column prop="id" label="ID" width="180" />
      <el-table-column prop="name" label="标签名" width="180" />
      <el-table-column prop="userName" label="所属用户" width="180" />
      <el-table-column prop="creationTime" label="创建时间" width="180" />
      <el-table-column prop="creation_time" label="操作" align="right">
        <template #header>
          <el-button size="small" type="success" @click="openLabelBox(1)"
            >添加新的标签</el-button
          >
        </template>
        <template #default="scoped">
          <el-button size="small" @click="openLabelBox(2, scoped.row)"
            >编辑</el-button
          >
          <el-popconfirm
            :title="`是否删除${scoped.row.name}`"
            @confirm="delLabel(scoped.row.id)"
          >
            <template #reference>
              <el-button size="small" type="danger">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      layout="prev, pager, next"
      :pageSize="pageInfo.pageSize"
      :total="pageInfo.total"
      @current-change="(index) => loadList(index)"
    />

    <!-- 添加弹窗 -->
    <el-dialog
      v-model="labelBox.state"
      :title="labelBox.title"
      width="30%"
      center
    >
      <el-form
        label-width="100px"
        :model="labelForm"
        ref="refClassForm"
        style="max-width: 460px"
      >
        <el-form-item label="分类标题：">
          <el-input v-model="labelForm.name" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="labelBox.state = false">取消</el-button>
          <el-button type="primary" @click="onSubmit()">提交保存</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<script>
import { ref, reactive, toRefs } from "vue";
import {
  articleLabelAdd,
  articleLabelUp,
  articleLabelList,
  articleLabelDel,
} from "../../api/article";
import { ElNotification } from "element-plus";
export default {
  setup() {
    let state = reactive({
      tableData: [],
    });

    const formInline = reactive({
      id: "",
      name: "",
    });
    let inform = (message) => {
      ElNotification({
        title: "系统提示",
        message: message,
        type: "success",
      });
    };
    const onSubmit = () => {
      let { id } = labelForm;
      if (id) {
        articleLabelUp(labelForm).then((res) => {
          if (res.code == 200) {
            inform("更新成功！");
            loadList();
          }
        });
      } else {
        articleLabelAdd(labelForm).then((res) => {
          if (res.code == 200) {
            inform("添加成功！");
            loadList(1);
          }
        });
      }
      labelBox.state = false;
    };

    let labelBox = reactive({
      state: false,
      title: "",
      type: null,
    });

    let labelFormData = () => {
      return {
        id: "",
        name: "",
      };
    };

    let labelForm = reactive(labelFormData());

    function openLabelBox(type, item) {
      labelBox.state = true;
      labelBox.type = type;
      if (item) {
        let { id, name } = item;
        labelBox.title = `正在编辑： ${name}`;
        labelForm.id = id;
        labelForm.name = name;
        return;
      }
      labelBox.title = `新增标签`;
      Object.assign(labelForm, labelFormData());
    }

    // 获取列表
    let pageInfo = reactive({
      page: 1,
      pageSize: 10,
      total: 0,
    });
    let loadList = (page) => {
      if (page) {
        pageInfo.page = page;
      }
      articleLabelList(pageInfo).then((res) => {
        if (res.code == 200) {
          state.tableData = res.data.list;
          pageInfo.total = res.data.total;
        }
      });
    };
    loadList();
    // 删除
    let delLabel = (id) => {
      articleLabelDel({ id }).then((res) => {
        if (res.code) {
          inform("删除成功！");
          loadList();
        }
      });
    };
    return {
      ...toRefs(state),
      formInline,
      onSubmit,
      labelBox,
      openLabelBox,
      labelForm,
      pageInfo,
      loadList,
      delLabel,
    };
  },
};
</script>
<style lang="less" scoped>
.article-label {
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
}
</style>