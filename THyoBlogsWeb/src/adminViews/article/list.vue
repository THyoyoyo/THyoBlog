<template>
  <div class="article-list">
    <div class="query-tool">
      <el-form :inline="true" :model="queryForm" class="demo-form-inline">
        <el-row>
          <el-col :span="6">
            <el-form-item label="ID">
              <el-input v-model="queryForm.id" placeholder="" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="标题">
              <el-input v-model="queryForm.title" placeholder="" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item>
              <el-button type="primary" aria-setsize="mini" @click="loadList(1)"
                >查询</el-button
              >
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <el-table
      :data="tableData"
      stripe
      style="width: 100%"
      max-height="620px"
      border
    >
      <el-table-column prop="id" label="ID" width="50" align="center" />

      <el-table-column
        prop="title"
        show-overflow-tooltip
        label="标题"
        width="250"
      />
      <el-table-column prop="oneName" label="一级分类" width="120" />
      <el-table-column prop="twoName" label="二级分类" width="120" />
      <el-table-column prop="labelName" label="标签">
        <template #default="scope">
          <el-popover
            effect="light"
            trigger="hover"
            placement="top"
            width="auto"
            v-for="(item, key) in scope.row.labelName"
            :key="key"
          >
            <template #default>
              <div>标签ID: {{ item.id }}</div>
              <div>名称: {{ item.name }}</div>
              <div>创建时间: {{ item.creationTime }}</div>
              <div>用户id: {{ item.userId }}</div>
            </template>
            <template #reference>
              <el-tag>{{ item.name }}</el-tag>
            </template>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
        prop="brief"
        show-overflow-tooltip
        label="简介"
        width="300"
      />
      <el-table-column
        prop="homeCommend"
        label="首页展示"
        width="90"
        align="center"
      >
        <template #default="scoped">
          <el-switch
            @change="(id) => changeHomeCommend(id, scoped.row)"
            v-model="scoped.row.homeCommend"
            style="
              --el-switch-on-color: #13ce66;
              --el-switch-off-color: #ff4949;
            "
            :active-value="1"
            :inactive-value="0"
          />
        </template>
      </el-table-column>
      <el-table-column prop="userName" label="创建用户" width="100" />
      <el-table-column prop="creationTime" label="创建时间" width="200" />
      <el-table-column prop="" label="操作" align="right">
        <template #header>
          <el-button
            size="small"
            type="success"
            @click="showarticleBox(1)"
            v-auth="'newArticleBtn'"
            >创建文章</el-button
          >
        </template>
        <template #default="scoped">
          <el-button size="small" @click="openDrawer(scoped.row)"
            >评论</el-button
          >
          <el-button size="small" @click="showarticleBox(1, scoped.row)"
            >编辑</el-button
          >

          <el-popconfirm
            :title="`是否删除${scoped.row.title}`"
            @confirm="delArticle(scoped.row.id)"
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
      :total="pageInfo.total"
      :pageSize="pageInfo.pageSize"
      @current-change="(index) => loadList(index)"
    />
    <!-- 添加弹窗 -->
    <el-dialog
      v-model="articleBox.state"
      :title="articleBox.title"
      width="800px"
      top="20px"
      destroy-on-close
      center
    >
      <el-form
        label-width="100px"
        :model="articleForm"
        ref="refarticleForm"
        style="max-width: 460px"
      >
        <el-form-item label="标题：">
          <el-input v-model="articleForm.title" />
        </el-form-item>

        <el-row>
          <el-col :span="12">
            <el-form-item label="一级分类：">
              <el-select
                v-model="articleForm.classOneId"
                placeholder="请选择一级分类"
                @change="changeClassOne"
              >
                <el-option
                  :label="item.name"
                  :value="item.id"
                  v-for="(item, key) in classList"
                  :key="key"
                />
              </el-select> </el-form-item
          ></el-col>
          <el-col :span="12">
            <el-form-item label="二级分类：">
              <el-select
                v-model="articleForm.classTwoId"
                placeholder="请选择二级分类"
              >
                <el-option
                  :label="item.name"
                  :value="item.id"
                  v-for="(item, key) in atOneClass.list"
                  :key="key"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="所属标签：">
          <el-checkbox-group v-model="articleForm.labels">
            <el-checkbox
              :label="item.id"
              v-for="(item, key) in labelList"
              :key="key"
              >{{ item.name }}</el-checkbox
            >
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="简介：">
          <el-input v-model="articleForm.brief" type="textarea" />
        </el-form-item>
        <el-form-item label="预览首图：">
          <template #default>
            <div class="previewBox">
              <el-upload
                class="avatar-uploader"
                :http-request="uploadFile"
                :show-file-list="false"
                action=""
              >
                <img
                  v-if="articleForm.preview"
                  :src="articleForm.preview"
                  class="avatar"
                />
                <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
              </el-upload>
            </div>
          </template>
          <template #label>
            <div>
              <p>预览首图：</p>
              <el-button
                type="info"
                class="back-btn"
                @click="openImgSelectBox('图片选择弹窗')"
                >资源库</el-button
              >
            </div>
          </template>
        </el-form-item>
        <el-form-item label="文章正文：" v-model="articleForm.content">
          <div id="wangEditor"></div>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="articleBox.state = false">取消</el-button>
          <el-button type="primary" @click="onSubmit()">提交保存</el-button>
        </span>
      </template>
    </el-dialog>
    <el-drawer v-model="drawer.state" :title="drawer.title" direction="rtl">
      <el-timeline>
        <el-timeline-item
          :timestamp="item.creationTime"
          placement="top"
          v-for="(item, key) in commentList"
          :key="key"
        >
          <el-card>
            <h4>{{ item.userName }}</h4>
            <p>{{ item.content }}</p>
            <div class="son-drawer">
              <el-timeline>
                <el-timeline-item
                  v-for="(item2, key2) in item.replyList"
                  :key="key2 + 'son'"
                  :timestamp="item2.creationTime"
                >
                  <h4>{{ item2.userName }}</h4>
                  <p>{{ item2.content }}</p>
                </el-timeline-item>
              </el-timeline>
            </div>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </el-drawer>
    <el-drawer
      v-model="drawerBox.state"
      title="图片资源选择"
      direction="rtl"
      size="700px"
    >
      <div class="drawerBoxMain">
        <div class="imgItem" v-for="(item, key) in imgList" :key="key">
          <img :src="item.url" alt="" />
          <el-button type="info" plain @click="getResourceUrl(item)"
            >选择</el-button
          >
        </div>
      </div>
    </el-drawer>
  </div>
</template>
<script >
import { ref, reactive, nextTick, toRefs } from "vue";
import {
  articList,
  articleClassList,
  articleLabelList,
  articleUp,
  articleAdd,
  articInfoDel,
  setHomeRecommend,
  getCommentById,
} from "../../api/article";
import { ElNotification, ElLoading } from "element-plus";
import { Plus } from "@element-plus/icons";
import { getBase64, imageToBase64 } from "../../utils/getBase64";
import { TElNotification, TElMessage } from "../../utils/inform";
import uploadFiles from "../../utils/uploadFiles";
import { getWebFile } from "../../api/system";
export default {
  components: {
    Plus,
  },
  setup() {
    let state = reactive({
      tableData: [],
      classList: [],
      labelList: [],
      commentList: [],
      atOneClass: {},
      imgList: [],
    });
    const queryForm = reactive({
      id: "",
      title: "",
    });
    let inform = (message, page) => {
      ElNotification({
        title: "系统提示",
        message: message,
        type: "success",
      });
      articleBox.state = false;
      loadList(page);
    };
    const onSubmit = () => {
      let { id } = articleForm;
      if (id) {
        articleUp(articleForm).then((res) => {
          if (res.code == 200) {
            inform("修改成功！");
          }
        });
      } else {
        articleAdd(articleForm).then((res) => {
          if (res.code == 200) {
            inform("添加成功！");
          }
        });
      }
    };

    let articleFormData = () => {
      return {
        id: "",
        title: "",
        classOneId: "",
        classTwoId: "",
        labels: [],
        brief: "",
        content: "",
        preview:
          "http://image.thyo.xyz/img/380a267e-35a6-09bf-306a-88fd08da9b8a",
      };
    };
    let articleForm = reactive(articleFormData());
    let articleBox = reactive({
      state: false,
      title: "",
      type: null,
    });
    let showarticleBox = (type, item) => {
      articleBox.state = true;
      articleBox.type = type;
      // 是否编辑
      if (item) {
        let { title, classOneId, labelName } = item;
        articleBox.title = `正在编辑： ${title}`;
        Object.assign(articleForm, item);
        // 加载二级
        changeClassOne(classOneId, 1);
      } else {
        articleBox.title = `新增文章`;
        Object.assign(articleForm, articleFormData());
      }

      nextTick(() => {
        let E = window.wangEditor;
        let editor = new E("#wangEditor");
        editor.customConfig.uploadImgShowBase64 = true;
        editor.customConfig.onchange = (html) => {
          articleForm.content = html;
        };

        editor.create();

        if (item) {
          editor.txt.html(item.content);
        }
      });
    };

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
      let data = { ...pageInfo, ...queryForm };
      articList(data).then((res) => {
        if (res.code == 200) {
          state.tableData = res.data.list;
          pageInfo.total = res.data.total;
        }
      });
    };
    loadList();

    // 获取分类
    let getClass = () => {
      articleClassList().then((res) => {
        state.classList = res.data;
      });
    };
    getClass();
    // 获取标签
    let getLabel = () => {
      articleLabelList({ page: 1, pageSize: 99 }).then((res) => {
        if (res.code == 200) {
          state.labelList = res.data.list;
        }
      });
    };
    getLabel();

    let changeClassOne = (id, type) => {
      state.atOneClass =
        state.classList.find((v) => {
          return v.id == id;
        }) || [];
      if (!type) {
        articleForm.classTwoId = "";
      }
    };

    let uploadFile = (params) => {
      const loadingInstance1 = ElLoading.service({
        text: "资源上传中...",
        target: document.querySelector(".userBox"),
      });
      uploadFiles(params.file).then((res) => {
        articleForm.preview = process.env.VUE_APP_QIN_NIU_CDN + res.key;
        loadingInstance1.close();
      });
    };
    // 监听变化
    let changeHomeCommend = (id, item) => {
      setHomeRecommend({ id: item.id }).then((res) => {
        if (res.code == 200) {
          TElNotification(id == 1 ? "已推送至首页" : "已从首页删除");
        }
      });
    };

    // 打开评论
    let drawer = reactive({
      state: false,
      title: "",
    });
    let openDrawer = (item) => {
      let { title, id } = item;

      getCommentById({ id }).then((res) => {
        if (res.code == 200) {
          if (res.data.data.length > 0) {
            state.commentList = res.data.data;
            drawer.state = true;
            drawer.title = title;
          } else {
            TElMessage("没有查询到评论！");
          }
        }
      });
    };

    // 删除文章
    let delArticle = (id) => {
      articInfoDel({ id }).then((res) => {
        if (res.code == 200) {
          TElNotification("删除成功！").then(() => {
            loadList();
          });
        }
      });
    };

    // 打开网站资源
    let drawerBox = reactive({
      title: "图片选择弹窗",
      state: false,
    });

    // 打开图片选择弹窗
    let openImgSelectBox = (title, type) => {
      console.log(title, type);
      drawerBox.title = title;
      drawerBox.state = true;
      getImgList();
    };

    // 加载列表
    let getImgList = (queyr = {}) => {
      getWebFile(queyr).then((res) => {
        if (res.code == 200) {
          state.imgList = res.data;
        }
      });
    };

    // 资源库中获取图片
    let getResourceUrl = (item) => {
      articleForm.preview = item.url;
    };

    return {
      queryForm,
      onSubmit,
      articleForm,
      articleBox,
      showarticleBox,
      ...toRefs(state),
      pageInfo,
      loadList,
      changeClassOne,
      uploadFile,
      changeHomeCommend,
      drawer,
      openDrawer,
      delArticle,
      getResourceUrl,
      drawerBox,
      openImgSelectBox,
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

  :deep(.el-dialog) {
    .el-form {
      max-width: max-content !important;
    }
    .el-textarea__inner {
      width: 100%;
    }
  }
  :deep(.el-pagination) {
    padding: 0;
    margin-top: 20px;
  }
  :deep(.el-tag) {
    margin-right: 5px;
  }
  .previewBox {
    :deep(.avatar-uploader) {
      .el-icon.avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 80px;
        height: 80px;
        text-align: center;
        border: 1px dashed #a2a3a3;
      }
      .avatar {
        width: 80px;
        height: 80px;
        text-align: center;
        border: 1px dashed #a2a3a3;
      }
    }
  }
  .son-drawer {
    margin-top: 15px;
  }
  .drawerBoxMain {
    display: flex;
    flex-wrap: wrap;
    .imgItem {
      display: flex;
      flex-direction: column;
      margin-top: 10px;
      margin-right: 15px;
      img {
        height: 170px;
        border: 1px solid #c8c9cc;
        border-bottom: none;
      }

      :deep(.el-button) {
        border-radius: 0;
      }
    }
  }
}
</style>