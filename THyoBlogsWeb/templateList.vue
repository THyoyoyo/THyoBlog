<template>
  <div class="article-list">
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
      <el-table-column prop="date" label="Date" width="180" />
      <el-table-column prop="name" label="Name" width="180" />
      <el-table-column prop="address" label="Address" />
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
        date: "2016-05-03",
        name: "Tom",
        address: "No. 189, Grove St, Los Angeles",
      },
      {
        date: "2016-05-02",
        name: "Tom",
        address: "No. 189, Grove St, Los Angeles",
      },
      {
        date: "2016-05-04",
        name: "Tom",
        address: "No. 189, Grove St, Los Angeles",
      },
      {
        date: "2016-05-01",
        name: "Tom",
        address: "No. 189, Grove St, Los Angeles",
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
.article-list {
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