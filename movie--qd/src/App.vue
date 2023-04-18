<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>
<script>
export default {
  name: "HomeApp",
  components: {
  },
  mounted () {
    this.init();
  },
  methods: {
    init () {
      this.initContentSize();
    },
    initContentSize () {
      this.$nextTick(() => {
        this.setSize();
        window.onresize = () => {
          this.setSize();
        };
      });
    },
    setSize () {
      let app = document.getElementById("app");
      app.style.height = window.innerHeight + "px";
    },
  },
};
</script>

<style lang="less" >
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  // text-align: center;
  color: #2c3e50;
  height: 100%;
}

 html,
 body {
   margin: 0;
   padding: 0;
   height: 100%;
   height: 100%;
 }

.myhome {
  height: 100%;
  width: 100%;
}
.el-menu--collapse .el-menu .el-submenu, .el-menu--popup{
  min-width: 130px!important;
  //background-color: #bd2c00!important;
  position: relative;
  left: -34px;
}
/deep/ .el-popover{
  padding: 0px!important;
  //margin-top: 20px;
}
</style>

