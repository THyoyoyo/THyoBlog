<template>
  <div class="admin-home">
    <p class="time">{{ time }}</p>
    <!-- <el-tree
      node-key="id"
      ref="treeRef"
      :data="data"
      :props="defaultProps"
      show-checkbox
    />
    <button @click="getData()">获取节点</button> -->
  </div>
</template>
<script>
import { reactive, ref, toRefs } from "vue";
export default {
  setup() {
    let time = ref(new Date());

    setInterval(() => {
      time.value = new Date();
    }, 1000);

    const treeRef = ref(null);
    const defaultProps = {
      children: "children",
      label: "label",
      disabled: "disabled",
    };

    let state = reactive({
      data: [
        {
          id: 1,
          label: "Level one 1",
          children: [
            {
              id: 4,
              label: "Level two 1-1",
              children: [
                {
                  id: 9,
                  label: "Level three 1-1-1",
                },
                {
                  id: 10,
                  label: "Level three 1-1-2",
                },
              ],
            },
          ],
        },
        {
          id: 2,
          label: "Level one 2",
          children: [
            {
              id: 5,
              label: "Level two 2-1",
            },
            {
              id: 6,
              label: "Level two 2-2",
            },
          ],
        },
        {
          id: 3,
          label: "Level one 3",
          children: [
            {
              id: 7,
              label: "Level two 3-1",
            },
            {
              id: 8,
              label: "Level two 3-2",
            },
          ],
        },
      ],
    });
    let getData = () => {
      console.log(treeRef.value.getCheckedNodes(false,true));
    };
    return {
      time,
      defaultProps,
      ...toRefs(state),
      treeRef,
      getData,
    };
  },
};
</script>
<style lang="less" scoped>
.time {
  font-size: 26px;
  line-height: 40px;
  margin-top: 30px;
}
</style>