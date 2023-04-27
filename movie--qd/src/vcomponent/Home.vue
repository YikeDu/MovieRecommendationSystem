<template>
  <div class="h-box">
    <div class="header">
      <Header></Header>
    </div>
    <div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import {mapState, mapMutations, mapGetters} from "vuex";
import Header from "@/vcomponent/Header";
export default {
  name: "Login",
  components: {
    Header,
  },

  data() {
    return {
      // searchStatus:false,
    };
  },
  created() {
  },
  mounted() {
  },
  methods: {},
};
</script>
<style lang="less" scoped>
.h-box{
  //background-color: #bd2c00;
  height: 100%;
  .header{
    height: 60px;
  }
}
</style>
