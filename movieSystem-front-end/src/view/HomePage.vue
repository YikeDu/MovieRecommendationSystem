<template>
  <div class="HomePage-box">
    <div :key="index" v-for="(item, index) in mdatas">
      <div class="h-title">
        {{item.title}}
      </div>
      <div>
        <Picture :rm=item.data></Picture>
      </div>
    </div>
  </div>
</template>
<script>
import {mapState, mapMutations, mapGetters, osearch} from "vuex";
import Picture from "@/vcomponent/Picture";

export default {
  name: "Login",
  components: {
    Picture,
  },
  // computed: {
  //   ...mapState(['ssszl','cid','searchdata']),
  // },
  data() {
    return {
      movisList: [1, 2, 3],
      rm: [1, 2, 3, 4, 5, 6, 7],
      mdatas: [],
    };
  },
  created() {
  },
  mounted() {
    this.init();
  },
  methods: {
    async init() {
      let res = await this.$afuntion.httsGet("./api/ratings/HomePage");
      this.mdatas = res.obj;
    },
  },
};
</script>
<style lang="less" scoped>
.HomePage-box {
  //background-color: #bd2c00;
  width: 1350px;
  height: 100%;
  margin: auto;
}

.h-title {
  color: #0CDAEB;
  font-size: 1.5rem;
  margin-bottom: 10px;
  margin-top: 20px;
}
</style>
