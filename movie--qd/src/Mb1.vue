<template>
  <div>这里是div模板</div>
</template>

<script>
// import bus from "../eventBus/eventBus";
import {mapState, mapMutations, mapGetters, osearch} from "vuex";
import bus from "@/eventBus/eventBus";

export default {
  name: "Login",
  components: {},
  // computed: {
  //   ...mapState(['ssszl','cid','searchdata']),
  // },
  data() {
    return {};
  },
  created() {
  },
  mounted() {
  },
  methods: {
 //           this.$router.push("/like");
 //         let res = await this.$afuntion.httsGet(`./yy/${kcid}/${this.pageSize}/空`);
 // ...mapMutations(['search1']),
 //         this.$store.commit('ssszl',v)
 //         bus.$emit("menu", res.obj);

 //    created() {
 //      bus.$on("menu", (val) => {
 //        console.log("这里是打印", this.tabsList);
 //        this.tabsList = val
 //      });
 //    },
  },
};
</script>
<style lang="less" scoped>

</style>
