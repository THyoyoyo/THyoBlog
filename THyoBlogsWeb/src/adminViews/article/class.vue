<template>
  <div class="article-calss">
    <div class="query-tool">
      <el-form :inline="true" :model="queryForm" class="demo-form-inline">
        <el-form-item label="ID">
          <el-input
            v-model="queryForm.id"
            placeholder=""
            :input-style="{ width: '50px' }"
          />
        </el-form-item>
        <el-form-item label="标题">
          <el-input
            v-model="queryForm.name"
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
            <el-table :data="props.row.list" border style="width: 100%">
              <el-table-column label="id" prop="id" width="60" align="center" />
              <el-table-column
                prop="name"
                label="图标"
                width="80"
                align="center"
              >
                <template #default="scoped">
                  <i
                    :class="['iconfont', 'icon-' + scoped.row.icon]"
                    style="font-size: 25px"
                  ></i>
                </template>
              </el-table-column>
              <el-table-column label="标题（二级）" prop="name" width="180" />
              <el-table-column prop="oneClassId" label="上级ID" width="180">
                <template #default="scoped">
                  ({{ scoped.row.oneClassId }})-{{ scoped.row.oneName }}
                </template>
              </el-table-column>
              <el-table-column
                label="创建时间"
                prop="creationTime"
                width="180"
              />
              <el-table-column prop="creation_time" label="操作" align="right">
                <template #header>
                  <el-button
                    size="small"
                    type="success"
                    @click="showClassBox(2, null, props.row.id)"
                    >新增二级分类
                  </el-button>
                </template>
                <template #default="scoped">
                  <el-button size="small" @click="showClassBox(2, scoped.row)"
                    >编辑</el-button
                  >
                  <el-popconfirm
                    :title="`是否删除${scoped.row.name}`"
                    @confirm="delClassTwo(scoped.row, props.$index)"
                  >
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
      <el-table-column prop="name" label="图标" width="80" align="center">
        <template #default="scoped">
          <i
            :class="['iconfont', 'icon-' + scoped.row.icon]"
            style="font-size: 25px"
          ></i>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="标题（一级）" width="180" />
      <el-table-column prop="userName" label="所属用户" width="180" />
      <el-table-column prop="creationTime" label="创建时间" width="180" />
      <el-table-column prop="creation_time" label="操作" align="right">
        <template #header>
          <el-button size="small" type="success" @click="showClassBox(1)"
            >新增一级分类</el-button
          >
        </template>
        <template #default="scoped">
          <el-button size="small" @click="showClassBox(1, scoped.row)"
            >编辑</el-button
          >
          <el-popconfirm
            :title="`是否删除${scoped.row.name}`"
            @confirm="delClassOne(scoped.row.id)"
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
      v-model="ClassBox.state"
      :title="ClassBox.title"
      width="30%"
      center
    >
      <el-form
        label-width="100px"
        :model="classForm"
        ref="refClassForm"
        style="max-width: 460px"
      >
        <el-form-item label="分类标题：">
          <el-input v-model="classForm.name" />
        </el-form-item>
        <el-form-item label="图标：">
          <div class="iconBox">
            <el-input v-model="classForm.icon" style="" />
            <i
              :class="['iconfont', 'icon-' + classForm.icon]"
              style="font-size: 25px; margin-left: 10px"
            ></i>
            <button type="button" @click="openDrawerIconBox()">图标库</button>
          </div>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="ClassBox.state = false">取消</el-button>
          <el-button type="primary" @click="onSubmit()">提交保存</el-button>
        </span>
      </template>
    </el-dialog>
    <DrawerIcon ref="DrawerIconComponent" @setDraweIcon="setDraweIcon" />
  </div>
</template>
<script>
import { ref, reactive, toRefs } from "vue";
import {
  articleClassList,
  articleClassAddTwo,
  articleClassAddOne,
  articleClassUpOne,
  articleClassUpTwo,
  articleClassDelTwo,
  articleClassDelOne,
} from "../../api/article";
import { ElNotification } from "element-plus";
import DrawerIcon from "../../components/DrawerIcon";
export default {
  components: {
    DrawerIcon,
  },
  setup() {
    const queryForm = reactive({
      id: "",
      name: "",
    });
    let classFormData = (oneClassId) => {
      return {
        id: "",
        name: "",
        icon: "",
        oneClassId: oneClassId ? oneClassId : null,
      };
    };
    let classForm = reactive(classFormData());
    let inform = (message) => {
      ElNotification({
        title: "系统提示",
        message: message,
        type: "success",
      });
    };
    let ClassBox = reactive({
      state: false,
      title: "",
      type: null,
      methods: "",
    });
    const onSubmit = () => {
      let { oneClassId } = classForm;
      let { methods } = ClassBox;

      if (oneClassId) {
        // 二级
        if (methods == "add") {
          articleClassAddTwo(classForm).then((res) => {
            if (res.code == 200) {
              inform("添加成功！");
              loadList();
            }
          });
        }

        if (methods == "up") {
          articleClassUpTwo(classForm).then((res) => {
            if (res.code == 200) {
              inform("修改成功！");
              loadList();
            }
          });
        }
      } else {
        // 一级
        if (methods == "add") {
          articleClassAddOne(classForm).then((res) => {
            if (res.code == 200) {
              inform("添加成功！");
              loadList();
            }
          });
        }

        if (methods == "up") {
          articleClassUpOne(classForm).then((res) => {
            if (res.code == 200) {
              inform("修改成功！");
              loadList();
            }
          });
        }
      }
      ClassBox.state = false;
    };
    let showClassBox = (type, item, fId) => {
      let arr = ["", "一级", "二级"];
      ClassBox.state = true;
      ClassBox.type = type;

      // 是否编辑
      if (item) {
        console.log("item", item);
        let { id, name, oneClassId, icon } = item;
        ClassBox.title = `正在编辑${arr[type]} ： ${name}`;
        ClassBox.methods = "up";
        classForm.id = id;
        classForm.name = name;
        classForm.icon = icon;
        classForm.oneClassId = oneClassId ? oneClassId : null;
        return;
      }
      ClassBox.title = `新增${arr[type]}分类`;
      ClassBox.methods = "add";
      Object.assign(classForm, classFormData(fId));
    };
    let state = reactive({
      tableData: [],
    });
    //获取文章分类列表
    let loadList = () => {
      articleClassList({ id: 1 }).then((res) => {
        if (res.code == 200) {
          state.tableData = res.data;
        }
      });
    };
    loadList();
    // 删除分类一
    let delClassOne = (id) => {
      articleClassDelOne({ id }).then((res) => {
        if (res.code == 200) {
          inform("删除成功");
          state.tableData = state.tableData.filter((v) => {
            return v.id != id;
          });
        }
      });
    };
    // 删除分类二
    let delClassTwo = (item, index) => {
      let { id } = item;
      articleClassDelTwo({ id }).then((res) => {
        if (res.code == 200) {
          inform("删除成功");
          state.tableData[index].list = state.tableData[index].list.filter(
            (v) => {
              return v.id != id;
            }
          );
        }
      });
    };
    // 打开 DrawerIcon
    let DrawerIconComponent = ref(null);
    let openDrawerIconBox = () => {
      DrawerIconComponent.value.openDrawerIconBox();
    };
    // 设置Icon值
    let setDraweIcon = (val) => {
      classForm.icon = val;
    };
    return {
      queryForm,
      classForm,
      onSubmit,
      ClassBox,
      showClassBox,
      ...toRefs(state),
      delClassOne,
      delClassTwo,
      openDrawerIconBox,
      DrawerIconComponent,
      setDraweIcon,
    };
  },
};
</script>
<style lang="less" scoped>
.article-calss {
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
    width: 900px;
    padding-left: 47px;
  }
  .iconBox {
    display: flex;
    button {
      cursor: pointer;
      width: 80px;
      margin-left: 10px;
      border: none;
      background: rgb(105, 105, 105);
      color: white;
      border-radius: 5px;
    }
  }
}
</style>