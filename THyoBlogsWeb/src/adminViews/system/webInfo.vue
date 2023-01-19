<template>
  <div class="web-info">
    <el-row :gutter="20">
      <el-col :span="12">
        <div class="web-info-form">
          <el-form :model="form" label-width="100px" size="large">
            <el-form-item label="网站标题">
              <el-input v-model="form.titlte" />
            </el-form-item>
            <el-form-item label="个人昵称">
              <el-input v-model="form.userName" />
            </el-form-item>
            <el-form-item label="特效签名">
              <el-input
                v-model="form.signature"
                type="textarea"
                rows="3"
                placeholder="一行一段、第二行请换行输入"
              />
            </el-form-item>
            <el-form-item label="个人头像">
              <template #default>
                <div class="previewBox">
                  <el-upload
                    class="avatar-uploader"
                    :http-request="
                      (params) => uploadFileUser(params, 'userImg')
                    "
                    :show-file-list="false"
                    action=""
                  >
                    <img
                      v-if="form.userImg"
                      :src="form.userImg"
                      class="avatar"
                    />
                    <el-icon v-else class="avatar-uploader-icon"
                      ><Plus
                    /></el-icon>
                  </el-upload>
                </div>
                <el-button
                  type="info"
                  @click="openImgSelectBox('图片选择弹窗', 'userImg')"
                  >从网站资源中获取</el-button
                >
              </template>
            </el-form-item>
            <el-form-item label="微信">
              <el-input v-model="form.wechat" />
            </el-form-item>
            <el-form-item label="GitHub">
              <el-input v-model="form.github" />
            </el-form-item>
            <el-form-item label="微博">
              <el-input v-model="form.weibo" />
            </el-form-item>
            <el-form-item label="QQ">
              <el-input v-model="form.qq" />
            </el-form-item>
            <div class="th-from-item">
              <el-form-item>
                <template #default>
                  <div class="preview-background">
                    <el-upload
                      class="avatar-uploader"
                      :http-request="
                        (params) => uploadFileUser(params, 'indexBack')
                      "
                      :show-file-list="false"
                      action=""
                    >
                      <img
                        v-if="form.indexBack"
                        :src="form.indexBack"
                        class="avatar"
                      />
                      <img
                        v-else
                        draggable="false"
                        :src="
                          form.indexBack ||
                          'https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png'
                        "
                        alt=""
                      />
                    </el-upload>
                  </div>
                  <div class="preview-background-other">
                    <p class="back-title">首页</p>
                    <button
                      type="button"
                      class="back-btn"
                      @click="openImgSelectBox('图片选择弹窗', 'indexBack')"
                    >
                      资源库
                    </button>
                  </div>
                </template>
              </el-form-item>
              <el-form-item>
                <template #default>
                  <div class="preview-background">
                    <el-upload
                      class="avatar-uploader"
                      :http-request="
                        (params) => uploadFileUser(params, 'archiveBack')
                      "
                      :show-file-list="false"
                      action=""
                    >
                      <img
                        v-if="form.archiveBack"
                        :src="form.archiveBack"
                        class="avatar"
                      />
                      <img
                        v-else
                        draggable="false"
                        :src="
                          form.archiveBack ||
                          'https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png'
                        "
                        alt=""
                      />
                    </el-upload>
                  </div>

                  <div class="preview-background-other">
                    <p class="back-title">归档</p>
                    <button
                      type="button"
                      class="back-btn"
                      @click="openImgSelectBox('图片选择弹窗', 'archiveBack')"
                    >
                      资源库
                    </button>
                  </div>
                </template>
              </el-form-item>
              <el-form-item>
                <template #default>
                  <div class="preview-background">
                    <el-upload
                      class="avatar-uploader"
                      :http-request="
                        (params) => uploadFileUser(params, 'aboutmeBack')
                      "
                      :show-file-list="false"
                      action=""
                    >
                      <img
                        v-if="form.aboutmeBack"
                        :src="form.aboutmeBack"
                        class="avatar"
                      />
                      <img
                        v-else
                        draggable="false"
                        :src="
                          form.aboutmeBack ||
                          'https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png'
                        "
                        alt=""
                      />
                    </el-upload>
                  </div>
                  <div class="preview-background-other">
                    <p class="back-title">关于</p>
                    <button
                      type="button"
                      class="back-btn"
                      @click="openImgSelectBox('图片选择弹窗', 'aboutmeBack')"
                    >
                      资源库
                    </button>
                  </div>
                </template>
              </el-form-item>
              <el-form-item>
                <template #default>
                  <div class="preview-background">
                    <el-upload
                      class="avatar-uploader"
                      :http-request="
                        (params) => uploadFileUser(params, 'messageboardBack')
                      "
                      :show-file-list="false"
                      action=""
                    >
                      <img
                        v-if="form.messageboardBack"
                        :src="form.messageboardBack"
                        class="avatar"
                      />
                      <img
                        v-else
                        draggable="false"
                        :src="
                          form.messageboardBack ||
                          'https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png'
                        "
                        alt=""
                      />
                    </el-upload>
                  </div>
                  <div class="preview-background-other">
                    <p class="back-title">留言</p>
                    <button
                      type="button"
                      class="back-btn"
                      @click="
                        openImgSelectBox('图片选择弹窗', 'messageboardBack')
                      "
                    >
                      资源库
                    </button>
                  </div>
                </template>
              </el-form-item>
            </div>
          </el-form>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="user-brief">
          <div class="user-brief-title">关于内容编辑</div>
          <div class="user-brief-content">
            <div id="wangEditor"></div>
          </div>
        </div>
        <div class="web-info-btn">
          <el-button type="info" @click="submit()">保存修改</el-button>
        </div>
      </el-col>
    </el-row>

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

<script>
import { onMounted, reactive, toRefs } from "vue";
import { Plus } from "@element-plus/icons";
import { TElNotification, TElMessage } from "../../utils/inform";
import { ElLoading } from "element-plus";
import uploadFiles from "../../utils/uploadFiles";
import { getWebFile, getWebInfo, savaWebInfo } from "../../api/system";
export default {
  components: {
    Plus,
  },
  setup() {
    let state = reactive({
      imgList: [],
    });
    const form = reactive({
      titlte: "",
      userImg: "",
      brief: "",
      userName: "",
      signature: "",
      wechat: "",
      github: "",
      qq: "",
      weibo: "",
      indexBack: "",
      archiveBack: "",
      aboutmeBack: "",
      messageboardBack: "",
    });

    // 加载列表
    let getImgList = (queyr = {}) => {
      getWebFile(queyr).then((res) => {
        if (res.code == 200) {
          state.imgList = res.data;
        }
      });
    };

    let drawerBox = reactive({
      title: "",
      state: false,
      type: "",
    });
    onMounted(() => {
      let E = window.wangEditor;
      let editor = new E("#wangEditor");
      editor.customConfig.uploadImgShowBase64 = true;
      editor.customConfig.onchange = (html) => {
        form.brief = html;
      };
      editor.create();

      // 获取配置信息
      getWebInfo({ type: "all" }).then((res) => {
        Object.assign(form, res.data);
        editor.txt.html(res.data.brief);
      });
    });

    let uploadFileUser = (params, type) => {
      const loadingInstance1 = ElLoading.service({
        text: "资源上传中...",
        target: document.querySelector(".web-info"),
      });
      uploadFiles(params.file).then((res) => {
        form[type] = process.env.VUE_APP_QIN_NIU_CDN + res.key;
        loadingInstance1.close();
      });
    };
    // 打开图片选择弹窗
    let openImgSelectBox = (title, type) => {
      console.log(title, type);
      drawerBox.title = title;
      drawerBox.state = true;
      drawerBox.type = type;
      getImgList();
    };
    // 资源库中获取图片
    let getResourceUrl = (item) => {
      form[drawerBox.type] = item.url;
      console.log(form);
    };
    // 提交保存
    let submit = () => {
      savaWebInfo(form).then((res) => {
        console.log(res);
      });
    };
    return {
      form,
      uploadFileUser,
      drawerBox,
      ...toRefs(state),
      openImgSelectBox,
      getResourceUrl,
      submit,
    };
  },
};
</script>
<style lang='less' scoped>
.web-info {
  // margin-top: 10px;
  .web-info-form {
  }
  .previewBox {
    margin-right: 10px;
    :deep(.avatar-uploader) {
      .el-icon.avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 120px;
        height: 120px;
        text-align: center;
        border: 1px dashed #a2a3a3;
      }
      .avatar {
        width: 120px;
        height: 120px;
        text-align: center;
        border: 1px dashed #a2a3a3;
      }
    }
  }

  .user-brief {
    .user-brief-title {
      font-weight: 700;
      font-size: 24px;
      color: rgb(71, 71, 71);
      letter-spacing: 2px;
      text-align: center;
    }

    .user-brief-content {
      margin-top: 10px;
    }
    :deep(.w-e-text-container) {
      height: 550px !important;
    }
  }
  .web-info-btn {
    .el-button {
      margin-top: 35px;
      width: 100%;
      height: 50px;
      font-size: 20px;
    }
  }
  :deep(.el-col) {
    z-index: 0;
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
:deep(#el-drawer__title) {
  margin-bottom: 0;
  span {
    font-size: 26px;
  }
}
.preview-background {
  :deep(.avatar-uploader) {
    .el-icon.avatar-uploader-icon {
      font-size: 28px;
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
      border: 1px dashed #a2a3a3;
    }
    img {
      width: 100px;
      height: 100px;
    }
  }
}
div.preview-background-other {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  p.back-title {
  }

  button.back-btn {
  }
}
.th-from-item {
  padding-left: 100px;
  display: flex;
  > div {
    flex: none;
    width: 100px;
    margin-right: 30px;
    .preview-background-other {
      p.back-title {
      }

      button.back-btn {
        background: #8c939d;
        border: none;
        padding: 5px 10px;
        border-radius: 3px;
        cursor: pointer;
        color: #fff;
      }
    }
  }
  :deep(.el-form-item__content) {
    margin-left: 0 !important;
  }
  :deep(.el-form-item__content) {
    line-height: unset;
  }
}
</style>