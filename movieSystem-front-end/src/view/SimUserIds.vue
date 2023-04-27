<template>
  <div class="HomePage-box">
    <div class="title-box">
      <div class="tltl1">simUserIds </div>
    </div>
    <div class="geners-box">
      <div class="content_movie" v-for="item in mdatas" :index="item.id" :key="item.id" >
        <a href="#"  @click="omovie(item)">
          <el-image class="tx-image" style="width: 130px; height: 130px"
                    :src=item.picturel fit="fit"></el-image>
        </a>
        <div class="title11">User ID: {{ item.id }}</div>
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
    ...mapState(['mtypes']),
  },
  data() {
    return {
      lb: [],
      movieList: [],
      mdatas: [],
      rm: [1,2],
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
    async omovie(v) {
      console.log("这里是打印v",v );
      // this.$router.push("/UserID?key="+v.id);
      // this.$router.push("/UserID");
      this.$router.push({path: '/UserID', query: {key:v.id}});

    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.currentPage = val
      this.init()

    },
    async init() {
      const res = await this.$afuntion.httsGet("/api/ratings/simUserIds?currentPage=" + this.currentPage + "&pageSize=" + this.pageSize);
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

.title11 {
  text-align: center;
  align-content: center;
}
.geners-box{
  display: flex;
  flex-direction: row;
  //justify-content: center;
  align-items: center;
  flex-wrap:wrap;
}
.content_movie{
  margin: 20px;
}
.tx-image{
  border-radius:130px ;
}
</style>
