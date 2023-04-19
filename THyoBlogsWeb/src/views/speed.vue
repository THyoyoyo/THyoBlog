<template>
  <div class="speed">
    <div class="speed-header top-padding" ref="homeHeader"></div>
    <div class="speed-ctx">
      <div class="tool">
        <el-button type="primary" size="large" color="#000000"
          >就 现 在 , 立 刻 加 入 ！</el-button
        >
        <el-button type="primary" size="large" color="#000000"
          >这个有什么用？</el-button
        >
      </div>
    </div>
  </div>
</template>
<script>
export default {
  setup() {},
};
</script>
<style lang="less" scoped>
.speed {
  .speed-header {
    background-image: linear-gradient(
      to left,
      black,
      rgba(0, 0, 0, 0.459),
      black
    );
    background-size: 300%;
    animation: backMove 10s linear infinite;
  }
  @keyframes backMove {
    to {
      background-position-x: 0;
    }
    from {
      background-position-x: 300%;
    }
  }
  .tool {
    margin-top: 20px;
    position: relative;
    z-index: 2;
    padding: 0 30px;
    display: flex;
    align-items: center;
    opacity: 0.9;
  }
}
</style>