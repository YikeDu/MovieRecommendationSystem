<template>
  <el-container class="app">
    <el-header>
      <Header />
    </el-header>
    <router-view class="view" />
    <el-footer />
  </el-container>
</template>
<script>
import Header from "./components/header.vue";
export default {
  name: "App",
  components: {
    Header,
  },
};
</script>
<style lang="css" scoped>
.app {
  /* background: #ffffff; */
  background: #f1f1f1;
  font-family: "arial", "times", "courier";
  min-height: 920px;
}

.view {
  padding-top: 30px;
}
</style>