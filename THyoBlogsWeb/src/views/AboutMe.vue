<!--  -->
<template>
  <div class="AboutMe">
    <div class="AboutMe-header top-padding">
      <div class="AboutMe-haeder-title web-font">
        <p>关于我</p>
        <p>About Me</p>
      </div>
    </div>
    <div class="AboutMe-content particles"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  //生命周期 - 创建完成（访问当前this实例）
  created() {},
  //生命周期 - 挂载完成（访问DOM元素）
  mounted() {},
};
</script>
<style lang='less' scoped>
.AboutMe-header {
  position: relative;
  z-index: 2;
  height: 500px;
  background-image: linear-gradient(#000000e5 5%, rgba(27, 27, 27, 0.048) 55%),
    url("../static/images/img-5.jpg");
  background-repeat: no-repeat;
  background-position-x: center;
  background-blend-mode: darken;
  background-size: 1920px;
  .AboutMe-haeder-title {
    margin-top: 100px;
    p {
      font-weight: 700;
      font-size: 35px;
      text-align: center;
      color: #fff;

      &:nth-child(1) {
        letter-spacing: 3px;
      }
      &:nth-child(2) {
      }
    }
  }
}
.AboutMe-content {
  position: relative;
  .AboutMe-content-layout {
  }
}
</style>