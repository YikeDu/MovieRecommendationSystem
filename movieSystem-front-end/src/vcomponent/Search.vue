<template>
  <div class="s-box">
    <div class="s-title">
      you searched for titles like
    </div>
    <div class="s-details">
     {{searchStatus}}
    </div>
    <div class="s-title">
      found {{mdatascd}} movies showsearch tools
    </div>
    <div>
      <div class="geners-box">
        <Picture :rm=mdatas></Picture>
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
  computed: {
    ...mapState(['searchStatus']),
  },

  data() {
    return {
      mdatas:[],
      mdatascd:"",
    };
  },
  watch: {
    //Favorite genre
    searchStatus: function (newval, oldVal) {
      // console.log('count 发生了变化', newval);
      // console.log('count 发生了变化', oldVal);
      this.init(newval)
    }
  },
  created() {
  },
  mounted() {
    this.init()
  },
  methods: {
    async init() {
      let res = await this.$afuntion.httsGet("./api/ratings/searching?search="+this.searchStatus);
console.log("这里是打印res",res );
        this.mdatas=res.obj
        this.mdatascd=this.mdatas.length
    },
  },
};
</script>
<style lang="less" scoped>
.s-box{
  background-color: #ffffff;
  width: 1550px;
  height: 100%;
  margin: auto;
}
.s-title{
  margin-top: 20px;
}
.s-details{
  margin-top: 20px;
  font-size: 50px;
}

</style>
