<template>
  <div class="article-comment">
    <div class="query-tool">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="ID" align="center">
          <el-input
            v-model="formInline.user"
            placeholder=""
            :input-style="{ width: '50px' }"
          />
        </el-form-item>
        <el-form-item label="标题">
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
      <el-table-column type="expand">
        <template #default="props">
          <div class="childrenTable">
            <el-table :data="props.row.replyList" border style="width: 100%">
              <el-table-column prop="id" label="ID" width="50" />
              <el-table-column prop="articleId" label="文章ID" width="180" />
              <el-table-column prop="userName" label="评论用户" width="130" />
              <el-table-column prop="content" label="评论内容" width="200" />
              <el-table-column
                prop="creationTime"
                label="评论时间"
                width="180"
              />
              <el-table-column prop="creation_time" label="操作" align="right">
                <template #default>
                  <el-button size="small" type="danger">删除</el-button>
                  <el-button size="small" type="warning">屏蔽</el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </template>
      </el-table-column>

      <el-table-column prop="id" label="ID" width="50" />
      <el-table-column prop="articleId" label="文章ID" width="180" />
      <el-table-column prop="userName" label="评论用户" width="130" />
      <el-table-column prop="content" label="评论内容" width="200" />
      <el-table-column prop="creationTime" label="评论时间" width="180" />
      <el-table-column prop="creation_time" label="操作" align="right">
        <template #default>
          <el-button size="small" type="danger">删除</el-button>
          <el-button size="small" type="warning">屏蔽</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination background layout="prev, pager, next" :total="1000" />
  </div>
</template>
<script>
import { ref, reactive } from "vue";
export default {
  setup() {
    const tableData = [
      {
        id: 3,
        articleId: 2,
        content: "评论一级-3",
        userId: 1,
        userName: "THyo",
        creationTime: "2022-09-13 22:01:21",
        parentId: null,
        replyList: [],
      },
      {
        id: 2,
        articleId: 2,
        content: "评论一级-2",
        userId: 1,
        userName: "THyo",
        creationTime: "2022-09-13 22:01:18",
        parentId: null,
        replyList: [
          {
            id: 6,
            articleId: 2,
            content: "评论2级-1",
            userId: 1,
            userName: "THyo",
            creationTime: "2022-09-13 22:01:30",
            parentId: 2,
            replyList: null,
          },
        ],
      },
      {
        id: 1,
        articleId: 2,
        content: "评论一级-1",
        userId: 1,
        userName: "THyo",
        creationTime: "2022-09-13 21:59:03",
        parentId: null,
        replyList: [
          {
            id: 4,
            articleId: 2,
            content: "评论2级-1",
            userId: 1,
            userName: "THyo",
            creationTime: "2022-09-13 22:01:23",
            parentId: 1,
            replyList: null,
          },
          {
            id: 5,
            articleId: 2,
            content: "评论2级-2",
            userId: 1,
            userName: "THyo",
            creationTime: "2022-09-13 22:01:27",
            parentId: 1,
            replyList: null,
          },
        ],
      },
    ];
    const formInline = reactive({
      user: "",
      region: "",
    });
    let input = ref("");
    const onSubmit = () => {
      console.log("submit!");
    };
    return {
      tableData,
      formInline,
      onSubmit,
      input,
    };
  },
};
</script>
<style lang="less" scoped>
.article-comment {
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
  .childrenTable {
    // width: 1400px;
    padding: 10px 120px 10px 46px;
    background: #ebeef5;
  }
}
</style>