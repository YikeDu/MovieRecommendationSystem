<template>
  
  <div class="HomePage-box">
    <div class="tltl1">Watch List</div>
    <div class="geners-box">
      <Picture :rm=mdatas></Picture>
    </div>
    <div class="block">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page=currentPage
          :page-sizes="[14]"
          :page-size=pageSize
          layout="total, sizes, prev, pager, next, jumper"
          :total=total>
      </el-pagination>
    </div>
  </div>


</template>

<script>
import {mapState, mapMutations, mapGetters, osearch} from "vuex";
import Picture from "@/vcomponent/Picture4";

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
      pageSize: 14,

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
    async tz() {
      this.$router.push("/Xh");
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.currentPage = val
      this.init()

    },

    async init() {
      const res = await this.$afuntion.httsGet("/api/ratings/getCollect?currentPage=" + this.currentPage + "&pageSize=" + this.pageSize);
      this.total = res.obj.size
      this.mdatas = res.obj.records
      console.log("这里是打印", this.mdatas);
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

.geners-box {
  margin-top: 20px;
}

.h-title {
  color: #0CDAEB;
  font-size: 1.5rem;
  margin-bottom: 10px;
  margin-top: 20px;
}

.title-box {
  display: flex;
  flex-direction: row;
  //justify-content: center;
  //align-items: center;
  margin-top: 25px;
}

.tltl1 {
  font-size: 50px;
  font-weight: bolder;
  font-family: 楷体;
  margin-right: 20px;
}

.tltl2 {
  font-size: 50px;
  font-weight: bolder;
  font-family: 楷体;
  margin-right: 20px;

  color: red;
}
</style>
