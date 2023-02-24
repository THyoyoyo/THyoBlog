<template>
  <div class="details">
    <!-- 头部 -->
    <div class="details-header">
      <div class="details-title">{{ info.title }}</div>
      <div class="details-other">
        <span><i class="iconfont icon-account" /> {{ info.userName }}</span>
        <span><i class="iconfont icon-clock" /> {{ info.creationTime }}</span>
        <span
          ><i class="iconfont icon-browse" />
          {{ info.viewNum > 999 ? "999+" : info.viewNum }}</span
        >
        <span
          ><i class="iconfont icon-atm" /> {{ info.articleCommentNum }}</span
        >
      </div>
    </div>
    <!-- 内容 -->
    <div class="detalis-content" v-html="info.content"></div>
    <div class="detalis-labels" id="detalis-labels">
      <el-tag
        class="ml-2"
        type="info"
        v-for="(item, key) in info.labelName"
        :key="key"
        >{{ item.name }}</el-tag
      >
    </div>
    <!-- 评论 -->
    <div class="detalis-comment">
      <div :style="{ filter: userQqInfo.qq ? 'none' : 'blur(2px)' }">
        <div class="detalis-comment-title web-font">
          <i class="iconfont icon-atm"></i> 发表评论
        </div>
        <div id="wangEditor"></div>
        <div class="wangEditor-btn">
          <el-button type="primary" class="web-font" @click="userPushComment()"
            >发表评论</el-button
          >
        </div>
        <div class="detalis-comment-title web-font">
          <i class="iconfont icon-atm-away"></i> 精彩评价
        </div>
        <!-- 用户评价 -->
        <div class="detalis-comment-user">
          <el-timeline>
            <el-timeline-item
              :timestamp="item.creationTime"
              placement="top"
              color="#bbcaf0"
              v-for="(item, key) in commentlist"
              :key="key"
            >
              <el-card>
                <h4>
                  {{ item.userName }}
                  <span v-if="item.userId == 2"> ({{ item.ip }})</span>
                </h4>
                <div class="detalis-commment-ctx" v-html="item.content"></div>
                <div class="detalis-commment-other">
                  <el-button type="primary" @click="userReplyComment(item)"
                    >回复评论</el-button
                  >
                </div>
                <div class="reply" v-if="item.replyList.length > 0">
                  <el-timeline>
                    <el-timeline-item
                      :timestamp="item.creationTime"
                      placement="top"
                      color="#bbcaf0"
                      v-for="(item, key) in item.replyList"
                      :key="key"
                    >
                      <el-card>
                        <h4>
                          {{ item.userName }}
                          <span v-if="item.userId == 2"> ({{ item.ip }})</span>
                        </h4>
                        <div
                          class="detalis-commment-ctx"
                          v-html="item.content"
                        ></div>
                      </el-card>
                    </el-timeline-item>
                  </el-timeline>
                </div>
              </el-card>
            </el-timeline-item>
          </el-timeline>
        </div>
      </div>
      <!-- 未登录遮罩层 -->
      <div class="detalis-comment-mark" v-if="!userQqInfo.qq">
        <div class="detalis-comment-mark-ctx">
          <p class="detalis-comment-mark-title">请输入QQ号完成登录</p>
          <el-input v-model="qq" placeholder="QQ号" />
          <div class="detalis-comment-mark-button">
            <el-button type="primary" @click="userLoginQq()">登录</el-button>
            <el-button type="primary" @click="clearLoginQq()"
              >游客访问</el-button
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { onMounted, reactive, toRefs, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import { articInfo, getCommentById, pushComment } from "../../api/article";
import { getQqInfo } from "../../api/expressTools";
import { userLoginFromQq } from "../../api/login";
import { TElMessage } from "../../utils/inform";
import { useStore } from "vuex";
export default {
  setup(props) {
    const route = useRoute();
    const router = useRouter();
    const store = useStore();
    let archiveId = route.query.id;
    let state = reactive({
      info: {},
      commentlist: [],
      brief: "",
      qq: "",
    });
    // 获取文章详情
    articInfo({ id: archiveId }).then((res) => {
      if (res.code == 200) {
        state.info = res.data;
        userGetCommentById(res.data.id);
      }
    });

    let userGetCommentById = (id) => {
      // 获取评论
      getCommentById({ id }).then((res) => {
        state.commentlist = res.data.data;
      });
    };

    // 渲染富文本编辑器
    let editor = null;
    onMounted(() => {
      let E = window.wangEditor;
      editor = new E("#wangEditor");
      editor.customConfig.uploadImgShowBase64 = true;
      editor.customConfig.onchange = (html) => {
        state.brief = html;
      };
      editor.create();
    });

    // 发布评论
    let userPushComment = (parentId) => {
      // 判断提交是否是空
      let div = document.createElement("div");
      div.innerHTML = state.brief;
      //判断回复标签是否存在
      let replyNode = div.querySelector("#replyMag");
      if (replyNode && replyNode.getAttribute("data-id")) {
        parentId = replyNode.getAttribute("data-id");
        replyNode.remove();
      }

      let ctx = div.textContent.trim();
      if (!ctx) {
        return;
      }

      let data = {
        articleId: state.info.id,
        content: div.innerHTML,
        userId: 2,
      };
      if (parentId) {
        data.parentId = parentId;
      }
      pushComment(data).then((res) => {
        if (res.code == 200) {
          editor.txt.html("");
          TElMessage("评论发布成功！");
          userGetCommentById(state.info.id);
        }
      });
    };

    // 回复评论返
    let userReplyComment = (item) => {
      let name =
        item.userId == 2 ? item.userName + `(${item.ip})` : item.userName;
      editor.txt.html(
        `<p style='color:#97aee9' id='replyMag' data-id="${item.id}">正在回复 ${name}：</p><br/>`
      );
      state.brief = `<p style='color:#97aee9' id='replyMag' data-id="${item.id}">正在回复 ${name}：</p><br/>`;

      document.querySelector("#detalis-labels").scrollIntoView(true);
    };

    // 用户QQ登录
    let userLoginQq = () => {
      let data = {
        qq: state.qq,
        time: new Date().getTime(),
      };
      userLoginFromQq(data).then((res) => {
        if (res.code == 200) {
          res.data.qq = data.qq;
          store.commit("setUserQqInfo", res.data);
        } else {
        }
      });
    };

    let clearLoginQq = () => {
      store.commit("setUserQqInfo", {});
    };

    return {
      clearLoginQq,
      userLoginQq,
      userReplyComment,
      userPushComment,
      ...toRefs(state),
      userQqInfo: computed(() => store.state.userQqInfo),
    };
  },
};
</script>
<style lang="less" scoped>
.details {
  .details-header {
    color: #525f7f;
    .details-title {
      text-align: center;
      font-size: 28px;
      font-weight: 600;
    }
    .details-other {
      font-weight: 600;
      margin-top: 15px;
      display: flex;
      justify-content: center;
      align-items: center;
      font-size: 16px;
      span {
        display: flex;
        align-items: center;
        margin-right: 25px;
        i {
          font-size: 20px;
        }
      }
    }
  }
  .detalis-content {
    margin-top: 50px;
    line-height: 30px;
    font-size: 16px;
    letter-spacing: 1px;
  }
  .detalis-labels {
    margin-top: 30px;
    display: flex;
    justify-content: flex-end;
    > span {
      margin-left: 10px;
    }
  }
  .detalis-comment {
    position: relative;
    margin-top: 30px;
    .detalis-comment-title {
      font-weight: 700;
      font-size: 30px;
      padding: 20px 0;
      display: flex;
      align-items: center;
      color: #525f7f;
      .iconfont {
        font-size: 40px;
        margin-right: 4px;
      }
    }
    .wangEditor-btn {
      padding: 10px 0;
      display: flex;
      justify-content: flex-end;
      :deep(.el-button) {
        background: #97aee9;
        color: #fff;
        border: none;
        &:hover {
          background: #7997e2;
        }
      }
    }
    .detalis-comment-mark {
      z-index: 99999;
      position: absolute;
      top: 0;
      width: 100%;
      height: 100%;
    }
  }
  .reply {
    margin-top: 50px;
  }
  .detalis-commment-other {
    display: flex;
    justify-content: flex-end;
    > button {
      background: #97aee9;
      border: none;
    }
  }
  .detalis-comment-mark-ctx {
    width: 250px;
    background: #e9e9e9bb;
    padding: 15px;
    margin: 180px auto 0 auto;
    border-radius: 5px;
    .detalis-comment-mark-title {
      text-align: center;
      font-size: 16px;
    }

    .el-input {
      margin: 10px 0;
    }

    .detalis-comment-mark-button {
      display: flex;
      justify-content: space-around;
      .el-button {
      }
    }
  }
}
</style>

<style lang="less">
.detalis-commment-ctx {
  margin: 10px 0;
  img {
    width: 100px !important;
  }
}

.detalis-content {
  border-radius: 5px;
  padding: 0 10px;
  margin-top: 20px;
  overflow-x: auto;
}

.detalis-content p,
.detalis-content li {
  white-space: pre-wrap; /* 保留空格 */
}

.detalis-content blockquote {
  border-left: 8px solid #d0e5f2;
  padding: 10px 10px;
  margin: 10px 0;
  background-color: #f1f1f1;
}

.detalis-content code {
  font-family: monospace;
  background-color: #eee;
  padding: 3px;
  border-radius: 3px;
}
.detalis-content pre > code {
  display: block;
  padding: 10px;
}

.detalis-content table {
  border-collapse: collapse;
}
.detalis-content td,
.detalis-content th {
  border: 1px solid #ccc;
  min-width: 50px;
  height: 20px;
}
.detalis-content th {
  background-color: #f1f1f1;
}

.detalis-content ul,
.detalis-content ol {
  padding-left: 20px;
}

.detalis-content input[type="checkbox"] {
  margin-right: 5px;
}
</style>