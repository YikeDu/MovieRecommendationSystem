<template>
  <div class="HomePage-box">
    <div class="title-box">
      <div class="tltl1">User ID: {{userID}}</div>
    </div>
    <div class="geners-box">
      <Picture2 :rm=mdatas></Picture2>
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
import Picture2 from "@/vcomponent/Picture2";

export default {
  name: "UserID",
  components: {
    Picture2,
  },
  computed: {
    ...mapState(['mtypes']),
  },
  data() {
    return {
      userID: '',
      lb: [],
      movieList: [],
      mdatas: [],
      rm: [],
      gf: [],
      tj: [],
      total: null,
      lbs: [],
      currentPage: 1,
      pageSize: 14,

    };
  },
  watch: {
    // mtypes: function (newval, oldVal) {
    //   console.log('count 发生了变化', newval);
    //   console.log('count 发生了变化', oldVal);
    //   this.getdatas(newval)
    // }
  },

  created() {
  },
  mounted() {
    this.init()
  },
  methods: {
    async tz() {
      // this.$router.push("/Xh");
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.currentPage = val
      this.init()

    },

    async init() {
     this.userID= this.$route.query.key;
     // this.userID= 307;
      const res = await this.$afuntion.httsGet("/api/ratings/userID?currentPage=" + this.currentPage + "&pageSize=" + this.pageSize + "&userid=" + this.userID);
      this.total = res.obj.size
      res.obj.records.forEach(i=>{
        i.star=parseInt(i.star);
      })
      this.mdatas = res.obj.records
      console.log("this", res);
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
