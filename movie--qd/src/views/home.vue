<template>
  <div class="Box">
    <div>
      <Header></Header>
    </div>
    <div class="category">
      <div class="hn">
        <span style="font-size: 1.5rem; margin-right: 100px" @click="category_rightf">Genre of film</span>
        <i class="el-icon-arrow-down" @click="category_rightf" v-if="category_right_status"></i>
        <i class="el-icon-arrow-up" @click="category_rightf" v-else></i>
        <div class="box_fq">
          <div v-if="category_right_status" class="box_son">
            <el-card class="box-card" style="width: 267px">
              <div v-for="o in mtypeArry" :key="o" class="mtype">
                <el-button type="text" @click="mtypef(o)"> {{ o }}</el-button>
              </div>
            </el-card>
          </div>
        </div>
      </div>
      <div class="hn wd">
        <el-button type="text" @click="youlike"> movies you've rated</el-button>
      </div>
    </div>
    <div class="content" v-if="statusl">
      <div style="color: #0CDAEB;font-size: 1.2rem; margin-bottom: 10px;">{{ liketitle }}</div>
      <div class="content_movie" v-for="item in tj" :index="item.id" :key="item.id" @click="omovie(item)">
        <div style="width: 156px; height: 270px">
          <el-image style="width: 156px; height: 228px" :src="item.imageSrc1" :fit="fit"></el-image>
          <div style="width: 156px;">
            <span style="font-size: 1rem">{{ item.title }}</span>
          </div>
          <div style="width: 156px; margin-top: 15px; height: 15px">
            <span style="font-size: 1rem">{{ item.year }}</span>
          </div>
        </div>
      </div>
    </div>
    <div class="content" v-if="statusl">
      <div style="color: #0CDAEB;font-size: 1.2rem; margin-bottom: 10px;">Weekly Trending</div>
      <div class="content_movie" v-for="item in rm" :index="item.id" :key="item.id" @click="omovie(item)">
        <div style="width: 156px;  vertical-align: middle; height: 270px">
          <el-image style="width: 156px; height: 228px" :src="item.imageSrc1" :fit="fit"></el-image>
          <div style="width: 156px; margin-bottom: 10px; ">
            <span style="font-size: 1rem">{{ item.title }}</span>
          </div>
          <div style="width: 156px; margin-top: 15px; height: 15px">
            <span style="font-size: 1rem">{{ item.year }}</span>
          </div>
        </div>
      </div>
    </div>
    <div class="content" v-if="statusl">
      <div style="  color: #0CDAEB;font-size: 1.2rem;margin-bottom: 10px;">Top Rated</div>
      <div class="content_movie" v-for="item in gf" :index="item.id" :key="item.id" @click="omovie(item)">
        <div style="width: 156px;">
          <el-image style="width: 156px; height: 228px" :src="item.imageSrc1" :fit="fit"></el-image>
          <div style="width: 156px; margin-bottom: 10px; ">
            <span style="font-size: 1rem">{{ item.title }}</span>
          </div>
          <div style="width: 156px; margin-top: 15px; height: 15px">
            <span style="font-size: 1rem">{{ item.year }}</span>
          </div>
        </div>
      </div>
    </div>
    <div class="content" v-if="status2">
      <div class="content_movie" v-for="item in lb" :index="item.id" :key="item.id" @click="omovie(item)">
        <div style="width: 156px; height: 320px">
          <el-image style="width: 156px; height: 228px" :src="item.imageSrc1" :fit="fit"></el-image>
          <div style="width: 156px; margin-bottom: 10px; height: 30px">
            <span style="font-size: 1rem">{{ item.title }}</span>
          </div>
          <div style="width: 156px; margin-top: 5px; height: 15px">
            <span style="font-size: 1rem">{{ item.year }}</span>
          </div>
        </div>
      </div>
      <div class="block">
        <span class="demonstration"></span>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                       :current-page.sync="currentPage1" :page-size="21" layout="total, prev, pager, next" :total=total>
        </el-pagination>
      </div>
    </div>

  </div>
</template>
<script>
import bus from "./eventBus";
import Header from "@/views/header.vue";

export default {
  components: {
    Header,
  },
  data() {
    return {
      liketitle: "Recommend",
      cid: "000",
      type: "no",
      sjs: "",
      iconColor: "#ffb24e",
      category_right_status: false,
      movieList: [],
      rm: [],
      gf: [],
      tj: [],
      lb: [],
      pageSize: "",
      total: "",
      lbs: [],
      statusl: true,
      status2: false,
      mtypeArry: ["Animation", "Adventure", "Children", "Fantasy", "Mystery", "Thriller"],
    };
  },
  mounted() {
    this.init();
  },
  beforeDestroy() {
    this.send();
  },
  created() {
    bus.$on("home", (val) => {
      this.type = "no";
      console.log("你的喜欢", val);

      this.statusl = true;
      this.status2 = false;
      // this.init();
    });
  },
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      val = val - 1;
      this.lb = this.lbs[val]
      console.log(`当前页: ${val}`);
    },
    async init() {
      this.cid = window.sessionStorage.getItem("uid");
      const {data: res} = await this.$http.get("/api/ratings/getdata?type=" + this.type + "&cid=" + this.cid);
      this.rm = res.obj.rm;
      this.gf = res.obj.gf;
      this.tj = res.obj.tj;
      this.liketitle = res.obj.title;
      this.type = "no";
      this.status2 = false;
      this.statusl = true;
      console.log(res);
    },
    youlike() {

      const tokenStr = window.sessionStorage.getItem('token')
      console.log(tokenStr);
      if (tokenStr) {
        this.$router.push("/like");
      } else {
        return this.$message.error("请先登录账号后才能看到你的历史打分!");
      }
    },
    send() {
      bus.$emit("xg", this.sjs);
    },
    omovie(val) {
      this.sjs = val;
      this.$router.push("/mdetails");
      //   mdetails
    },
    category_rightf() {
      this.category_right_status = !this.category_right_status;
    },
    async mtypef(o) {
      this.type = o;
      this.sleep(300);
      this.category_rightf();
      this.statusl = false;
      this.status2 = true;
      const {data: res} = await this.$http.get("/api/ratings/classification?type=" + this.type);
      this.lb = res.obj.sj[0];
      this.lbs = res.obj.sj;
      this.total = res.obj.cd
    },
    sleep(numberMillis) {
      var now = new Date();
      var exitTime = now.getTime() + numberMillis;
      while (true) {
        now = new Date();
        if (now.getTime() > exitTime) return true;
      }
    },
  },
};
</script>
<style lang="css" scoped>
.hn {
  display: inline-block;
}

.wd {
  margin-left: 50px;
}

.Box {
  margin: 0;
  padding: 0;
  margin-top: 5px;
}

.category {
  /* background-color: red; */
  margin: 0 auto;
  width: 1385px;
}

.box_fq {
  position: relative;
}

.box_son {
  position: absolute;

  top: 0;
  left: 0;
  z-index: 9;

}

.content {
  /* background-color: green; */
  margin: 0 auto;
  margin-top: 10px;
  width: 1385px;
}

.content_movie {
  display: inline-block;
  margin-left: 21px;
  margin-right: 20px;
}

.content_movie:last-child {
  margin-right: 0px;
}

.content_movie:last-child {
  margin-right: 0px;
}

.mtype {
  display: inline-block;
  margin: 10px;
}
</style>