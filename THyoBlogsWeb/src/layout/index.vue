<template>
  <div class="main">
    <AppHaeder />
    <router-view class="appCentent"></router-view>
    <vue-particles
      color="#000000"
      :particleOpacity="0.4"
      :particlesNumber="100"
      shapeType="circle"
      :particleSize="3"
      linesColor="#000000"
      :linesWidth="1"
      :lineLinked="true"
      :lineOpacity="0.4"
      :linesDistance="150"
      :moveSpeed="5"
      :hoverEffect="true"
      hoverMode="grab"
      :clickEffect="true"
      clickMode="push"
    ></vue-particles>
  </div>
</template>
<script>
import AppHaeder from "./appHaeder.vue";
export default {
  components: {
    AppHaeder,
  },
};
</script>
<style lang="less" scoped>
.main {
  height: 100%;
  padding: 0px 0 60px 0;
  box-sizing: border-box;
  .appCentent {
    min-height: -webkit-fill-available;
  }
}
</style>