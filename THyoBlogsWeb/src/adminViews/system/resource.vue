<!--  -->
<template>
  <div class="resource">
    <div class="query-tool">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="ID" align="center">
          <el-input
            v-model="formInline.id"
            placeholder=""
            :input-style="{ width: '50px' }"
          />
        </el-form-item>
        <el-form-item label="资源名称">
          <el-input
            v-model="formInline.name"
            placeholder=""
            :input-style="{ width: '100px' }"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" aria-setsize="mini" @click="querySubmit"
            >查询</el-button
          >
        </el-form-item>
      </el-form>
    </div>
    <el-table
      :data="tableData"
      stripe
      style="width: 100%"
      max-height="720px"
      border
    >
      <el-table-column prop="id" label="ID" width="80" align="center" />
      <el-table-column prop="name" label="预览图" width="180" align="center">
        <template #default="scoped">
          <el-image
            :src="scoped.row.url"
            :preview-src-list="[
              scoped.row.url + '?imageView2/2/w/200/h/200/q/75',
            ]"
            :initial-index="4"
            fit="cover"
            preview-teleported
          />
        </template>
      </el-table-column>
      <el-table-column prop="name" label="文件名称" width="250">
        <template #default="scoped">
          <el-input
            v-model="scoped.row.name"
            placeholder="Please input"
            class="input-with-select"
          >
            <template #append>
              <el-button @click="upFileName(scoped.row)"
                >修改</el-button
              ></template
            >
          </el-input>
        </template>
      </el-table-column>
      <el-table-column prop="type" label="文件类型" width="180" />
      <el-table-column prop="url" label="地址" width="600" />
      <el-table-column prop="id" label="操作" align="right">
        <template #header>
          <el-button
            size="small"
            type="success"
            @click="resourceBox.state = true"
            >添加文件</el-button
          >
        </template>
        <template #default="scoped">
          <el-popconfirm
            :title="`是否删除${scoped.row.name}`"
            @confirm="delFile(scoped.row.id)"
          >
            <template #reference>
              <el-button size="small" type="danger">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!-- 弹窗 -->
    <el-dialog
      v-model="resourceBox.state"
      custom-class="resourceBox"
      title="添加资源"
      width="1000px"
      destroy-on-close
      center
    >
      <div class="fileBox">
        <el-upload
          v-model:file-list="fileList"
          action=""
          list-type="picture-card"
          :http-request="uploadFile"
          :on-remove="handleRemove"
          multiple
        >
          <el-icon><Plus /></el-icon>
        </el-upload>
      </div>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="resourceBox.state = false">取消 </el-button>
          <el-button type="primary" @click="onSubmit()">确认</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import { Plus, Search } from "@element-plus/icons";
import { reactive, ref, toRefs } from "vue";
import uploadFiles from "../../utils/uploadFiles";
import { ElLoading } from "element-plus";
import {
  savaUpLoadWebFile,
  getWebFile,
  editByIdFile,
  delByIdFile,
} from "../../api/system";
import { TElNotification } from "../../utils/inform";
export default {
  components: {
    Plus,
    Search,
  },

  setup() {
    let state = reactive({
      tableData: [],
    });
    let formInline = reactive({
      id: "",
      name: "",
      type: "",
    });

    let querySubmit = () => {
      loadList(formInline);
    };
    // 弹窗
    let resourceBox = reactive({
      state: false,
    });
    // 加载列表
    let loadList = (queyr = {}) => {
      getWebFile(queyr).then((res) => {
        if (res.code == 200) {
          state.tableData = res.data;
        }
      });
    };
    loadList();
    // ---------图片上传-start
    let fileList = ref([]); //默认展示图片
    let upFileList = []; //文件列表
    let time = null;
    let formFileList = ref([]);
    let uploadFile = (params) => {
      upFileList.push(params);
      if (time != null) {
        clearTimeout(time);
      }
      time = setTimeout(() => {
        const loadingInstance1 = ElLoading.service({
          text: "资源上传中...",
          target: document.querySelector(".resourceBox"),
        });
        Promise.all(
          upFileList.map((v) => {
            return new Promise((resolve, reject) => {
              uploadFiles(v.file).then((res) => {
                resolve({
                  name: v.file.name,
                  type: v.file.name.slice(v.file.name.indexOf(".") + 1),
                  url: process.env.VUE_APP_QIN_NIU_CDN + res.key,
                });
              });
            });
          })
        ).then((arr) => {
          upFileList = [];
          formFileList.value = [...formFileList.value, ...arr];
          loadingInstance1.close();
        });
      }, 200);
    };
    let handleRemove = (uploadFile, uploadFiles) => {
      formFileList.value = formFileList.value.filter((v) => {
        return uploadFile.name != v.name;
      });
    };
    // ---------图片上传-end
    //资源保存提交
    let onSubmit = () => {
      savaUpLoadWebFile({ urlList: formFileList.value }).then((res) => {
        if (res.code == 200) {
          TElNotification("上传成功！");
          resourceBox.state = false;
          formFileList.value = [];
        }
      });
    };
    // 修改文件名
    let upFileName = ({ name, id }) => {
      let data = {
        name: name || `图${id}`,
        id,
      };
      editByIdFile(data).then((res) => {
        if (res.code == 200) {
          TElNotification("修改成功！");
        }
      });
    };
    // 删除文件
    let delFile = (id) => {
      delByIdFile({ id }).then((res) => {
        if (res.code == 200) {
          TElNotification("删除成功！");
          state.tableData = state.tableData.filter((v) => {
            return v.id != id;
          });
        }
      });
    };

    return {
      fileList,
      formFileList,
      uploadFile,
      handleRemove,
      formInline,
      ...toRefs(state),
      querySubmit,
      resourceBox,
      onSubmit,
      upFileName,
      delFile,
    };
  },
};
</script>
<style lang='less' scoped>
.resource {
  padding: 10px 0;
  :deep(.el-upload-list) {
    .el-upload-list__item-thumbnail {
      object-fit: initial;
    }
  }
  .previewImg {
    height: 100px;
    max-width: 120px;
  }
}
</style>