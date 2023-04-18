<template>
  <div class="Box">
    <div class="category">
      <div class="centero"><span>历史评分区</span></div>
    </div>
    <div class="content">
      <div class="content_movie" v-for="(item ,index) of movieList" :index="item" :key="index" >
        <div>
          <div class="category">
            <div class="box_white">
              <div>
                <div class="hn" style="width: 500px; height: 280px">
                  <el-image :src="item.imagesrc2"></el-image>
                </div>
                <div class="hn">
                  <div style="margin-bottom: 10px">
                    <span style="font-size: 3rem">{{ item.name }}</span>
                  </div>
                  <div style="margin-top: 5px">
                    <el-rate v-model="item.star" show-text @change="starf"></el-rate>
                  </div>
                  <div class="category_jj" style="width: 500px">
                    {{ item.jj }}
                  </div>
                </div>
                <div style="float: right;margin-left: 50px">
                  <el-button type="danger" @click="deletM(item)">删除</el-button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      // src="https://image.tmdb.org/t/p/w500/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg"
      iconColor: "#ffb24e",
      category_right_status: false,
      movieList: [],
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    async deletM(v) {
      console.log("deletM", v)
      const {data: res} = await this.$http.get("/api/ratings/deletM?id="+v.id);
      this.init()
    },
    async starf() {
      console.log(this.star);

    },
    async init() {
      console.log(this.star);
      const tokenStr = window.sessionStorage.getItem('uid')
      const {data: res} = await this.$http.post("/api/ratings/like", {
        name: tokenStr,
      });
      this.movieList = res.obj;
      this.movieList.forEach(item=>{
        let a=parseInt(item.star)
        item.star=a;
      })
      console.log(this.movieList);
    },
    youlike() {
      console.log("你的喜欢");
    },
    omovie(val) {
      console.log(val);
      // this.$router.push("/mdetails");
      //   mdetails
    },
    category_rightf() {
      this.category_right_status = !this.category_right_status;
    },
    mtypef() {
      this.sleep(300);
      this.category_rightf();
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
.centero {
  margin: 0 auto;
  width: 1385px;
  /* background-color: red; */
  text-align: center;
}

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

.content {
  /* background-color: green; */
  margin: 0 auto;
  margin-top: 10px;
  width: 1385px;
}

.content_movie {
  /* display: inline-block; */
  margin-left: 5px;
  margin-right: 5px;
  margin-top: 20px;
}
</style>