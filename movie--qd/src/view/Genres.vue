<template>
  <div class="HomePage-box">
      <div class="geners-box">
        <Picture :rm=mdatas></Picture>
      </div>
    <div class="block">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page=currentPage
          :page-sizes="[21]"
          :page-size=pageSize
          layout="total, sizes, prev, pager, next, jumper"
          :total=total>
      </el-pagination>
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
    ...mapState(['mtypes']),
  },
  data() {
    return {
      lb: [],
      movieList: [],
      mdatas: [],
      rm: [],
      gf: [],
      tj: [],
      total: "",
      lbs: [],
      currentPage: 1,
      pageSize: 21,

    };
  },
  watch: {
    mtypes: function (newval, oldVal) {
      console.log('count 发生了变化', newval);
      console.log('count 发生了变化', oldVal);
      this.getdatas(newval)
    }
  },

  created() {
  },
  mounted() {
    this.init()
  },
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.currentPage=val
      this.init()

    },
    async getdatas(v) {
      const res = await this.$afuntion.httsGet("/api/ratings/classification2?currentPage=" + this.currentPage + "&pageSize=" + this.pageSize + "&type=" + v);
      // this.lb = res.obj.sj[0];
      // this.lbs =  res.obj.sj;
      // this.total = res.obj.cd
      this.total = res.obj.size
      this.mdatas = res.obj.records
      console.log("这里是打印", this.mdatas);

    },
    async init() {
      await this.getdatas(this.mtypes)
    }
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
.geners-box{
  margin-top: 20px;
}
.h-title {
  color: #0CDAEB;
  font-size: 1.5rem;
  margin-bottom: 10px;
  margin-top: 20px;
}
</style>
