<template>
  <div class="Box">
    <div class="details-picture">
      <div class="">
        <a :href='"https://www.youtube.com/watch?v="+sj.hearf' target="_blank">
          <img :src='"https://image.tmdb.org/t/p/w500"+sj.imageSrc1' alt="">
          <!--          <el-image style="width: 500px; height: 280px"-->
          <!--                    :src='"https://image.tmdb.org/t/p/w500"+sj.imageSrc1' fit="fit"></el-image>-->
        </a>
      </div>
      <div class="picture-mx">
        <div class="mtitle">
          <div class="bj-box1"> {{ sj.title }}</div>
          <div class="bj-box1">
            <el-rate v-model=sj.star @change="starf"></el-rate>
          </div>
          <a href="#">
            <div class="bj-box1" @click="collect"><i class="el-icon-view"></i></div>
          </a>

        </div>
        <div class="bq-box">
          <div class="bq-box1">
            <div class="title-bq">runtime</div>
            <div> {{ sj.runtime }}</div>
          </div>
          <div class="bq-box1">
            <div class="title-bq">Genre</div>
            <div>{{ sj.genres }}</div>
          </div>
        </div>
        <div class="bq-box">
          <div class="bq-box1">
            <div class="title-bq">rating</div>
            <div>{{ sj.fz }}</div>
          </div>
          <div class="bq-box1">
            <div class="title-bq">voteCount</div>
            <div>-</div>
          </div>
        </div>
      </div>
    </div>
    <div>&ensp;&ensp;&ensp;{{ sj.jj }}</div>
    <div class="category_jj">
      <div class="head">
        <div style="font-size:20px;position: absolute;top: 15px;left: 2px;">{{ username }}:</div>
        <input type="text" placeholder="Please enter a comment.." v-model="firstComments" style="margin-left: 25px;"/>
        <button @click="sumbit">add a comment</button>
      </div>
      <div class="content">
        <div class="first" v-for="(item, index) in comment" :key="index">
          <div class="first-img"></div>
          <div class="first-content">
            <span>{{ item.cid }}: </span>
            <span class="first-time">{{ item.ctimeStr }}</span>
            <p class="first-comment">{{ item.cstr }}</p>
          </div>
        </div>
      </div>
    </div>

    <div class="h-title">
      Similar Movies
    </div>
    <Picture3 :rm=rm></Picture3>
    <div class="h-title">
      Recommendation movie
    </div>
    <Picture3 :rm=tj></Picture3>
  </div>
</template>
<script>
import bus from "../eventBus/eventBus";
import Picture3 from "@/vcomponent/Picture3";

export default {
  name: "Login",
  components: {
    Picture3,
  },
  data() {
    return {
      sj: {},
      mid: '',
      username: 'Not logged in',
      firstComments: '',
      lbList: [1, 2],
      rm: [],
      tj: [],
      comment: [],
      hearfs: 'https://fanyi.youdao.com/index.html#/',
    };
  },
  created() {

  },
  beforeCreate() {

  },
  beforeDestroy() {
  },
  mounted() {
    this.getmid()
  },
  methods: {
    async collect() {
      console.log(this.sj.star);
      // let tokenStr = this.gettoken();
      // console.log(tokenStr);
      if (this.username != 'Not logged in') {
        // this.sj.star =this.sj.star
        // this.sj.cid = tokenStr;
        // this.sj.cid = this.mid;
        // this.sj.uid = window.sessionStorage.getItem('uid');
        this.sj.uid = window.sessionStorage.getItem('cid');
        const res = await this.$afuntion.httsPost("/api/ratings/collect", this.sj);
        this.$message.success("You have collected successfully!");
      } else {
        return this.$message.error("!");
      }
    },
    async sumbit() {
      let tokenStr = window.sessionStorage.getItem('cid')
      let username = window.sessionStorage.getItem('username')
      if (tokenStr) {
        if (this.firstComments != "") {
          let obj = {
            cid: username,
            uid: tokenStr,
            ctimeStr: new Date(),
            cstr: this.firstComments
          }
          this.comment.unshift(obj);
          obj.mid = this.sj.movieid
          this.$afuntion.httsPost("/api/ratings/sumbit", obj);
          this.$message.success("Thanks for participating, comment success!");
          this.firstComments = "";
        } else {
          this.$message.error("You cannot enter an empty comment!");
        }
      } else {
        return this.$message.error("Please login to your account before Posting comments!");
      }
    },
    async starf() {
      console.log(this.sj.star);
      // let tokenStr = this.gettoken();
      // console.log(tokenStr);
      if (this.username != 'Not logged in') {
        // this.sj.star =this.sj.star
        // this.sj.cid = tokenStr;
        // this.sj.cid = this.mid;
        // this.sj.uid = window.sessionStorage.getItem('uid');
        this.sj.uid = window.sessionStorage.getItem('cid');
        const res = await this.$afuntion.httsPost("/api/ratings/jsData", this.sj);
        this.$message.success("Thank you for your participation. Score success:" + this.star + "Mark");
      } else {
        return this.$message.error("Please log in your account first before you can score!");
      }
    },
    async getmid() {
      this.mid = window.sessionStorage.getItem("mid");
      let iname = window.sessionStorage.getItem("username");
      if (iname) {
        this.username = iname;
      }

      this.gettjxs()
      this.getLb()
    },
    async gettjxs() {
      let res = await this.$afuntion.httsGet("./api/ratings/mid?mid=" + this.mid);
      this.sj = res.obj
      this.comment = res.obj.comment
    },
    async getLb() {
      let res = await this.$afuntion.httsGet("./api/ratings/getLb?mid=" + this.mid);
      this.rm = res.xs
      this.tj = res.tj
    }
  },
};
</script>
<style lang="css" scoped>
.Box {
  width: 1385px;
  margin: auto;
}

.h-title {
  width: 1350px;
  margin: auto;
  color: #0CDAEB;
  font-size: 1.5rem;
  margin-bottom: 10px;
  margin-top: 20px;
}

.details-picture {
  display: flex;
  flex-direction: row;
  /*justify-content: center;*/
  /*align-items: center;*/
  margin: 20px;
}

.picture-mx {
  flex: 1;
  margin-left: 20px;
}

.mtitle {
  font-size: 2rem;
  margin-top: 15px;
  margin-bottom: 10px;
  display: flex;
  flex-direction: row;
}
.bj-box1 {
  margin-left: 20px;
}
.title-bq {
  font-size: 2rem;
  color: #efa485;
  margin-top: 20px;
  margin-bottom: 10px;
}

.bq-box {
  display: flex;
  flex-direction: row;
  /*justify-content: center;*/
  /*align-items: center;*/
}

.bq-box1 {
  margin-right: 30px;
  width: 200px;
}

/*==================================*/
.category_jj {
  width: 1385px;
  margin: 0 auto;
  margin-top: 20px;
  font-size: 30px;
}

.content_movie {
  display: inline-block;
  margin-left: 20px;
  margin-right: 21px;
}

/* .content_movie:last-child {
  margin-right: 0px;
}

.content_movie:last-child {
  margin-right: 0px;
} */
.head {
  background-color: rgb(248, 248, 248);
  position: relative;
  height: 75px;
  border-radius: 5px;
}

.head img {
  width: 55px;
  height: 55px;
  border-radius: 50%;
  position: absolute;
  top: 10px;
  left: 13px;
}

/* 评论框 */
.head input {
  position: absolute;
  top: 13px;
  left: 120px;
  height: 45px;
  border-radius: 5px;
  outline: none;
  width: 65%;
  font-size: 20px;
  padding: 0 20px;
  border: 2px solid #f8f8f8;
}

/* 发布评论按钮 */
.head button {
  position: absolute;
  top: 13px;
  right: 20px;
  width: 120px;
  height: 48px;
  border: 0;
  border-radius: 5px;
  font-size: 20px;
  font-weight: 500;
  color: #fff;
  background-color: rgb(118, 211, 248);
  cursor: pointer;
  letter-spacing: 2px;
}

/* 鼠标经过字体加粗 */
.head button:hover {
  font-weight: 600;
}

/* 评论内容区域 */
.content .first {
  display: flex;
  position: relative;
  padding: 10px 10px 0px 0;
  text-align: left;
}

.first .first-img {
  flex: 1;
  text-align: center;
}

.first img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}

.first-username,
.second-username {
  color: #504f4f;
}

.first-username {
  margin-bottom: 2px;
}

.first-content {
  flex: 9;
}

.first-time,
.second-time {
  color: #767575;
}

.first-time {
  font-size: 3px;
}

.first-comment,
.second-comment {
  margin-top: 2px;
}

.first-comment {
  color: #D3322C;
  font-size: 20px;
}

.first-right span,
.second-right span {
  margin-right: 20px;
  cursor: pointer;
}

/* 删除评论 */
.delete:hover {
  color: red;
}

/* 评论字体图标 */
.comments::before {
  /* 想使用的icon的十六制编码，去掉&#x之后的 */
  content: "\e8b9";
  /* 必须加 */
  font-family: "iconfont";
  margin-right: 4px;
  font-size: 16px;
}

/* 点赞字体图标 */
.praise::before {
  /* 想使用的icon的十六制编码，去掉&#x之后的 */
  content: "\ec7f";
  /* 必须加 */
  font-family: "iconfont";
  margin-right: 4px;
  font-size: 19px;
}

.second {
  background-color: #f3f3f3;
  margin-top: 0px;
}

.second li {
  padding: 10px 10px 10px 0;
  border-bottom: 1px solid rgb(237, 237, 237);
}

.second .top {
  display: flex;
  position: relative;
}

.second-img {
  flex: 1;
  text-align: center;
}

.to_reply {
  color: rgb(106, 106, 106);
}

.second-content {
  flex: 9;
}

.second .reply_li {
  margin-left: 70px;
}

/* 评论框 */
.reply-comment {
  margin: 10px 0 0 0;
}

.reply-comment input {
  height: 40px;
  border-radius: 5px;
  outline: none;
  width: 70%;
  font-size: 18px;
  padding: 0 20px;
  /* border: 2px solid #f8f8f8; */
  border: 2px solid skyblue;
}

/* 发布评论按钮 */
.reply-comment button {
  width: 15%;
  height: 43px;
  border: 0;
  border-radius: 5px;
  font-size: 18px;
  font-weight: 500;
  color: #fff;
  background-color: rgb(118, 211, 248);
  cursor: pointer;
  letter-spacing: 2px;
  margin-left: 20px;
}

/* 鼠标经过字体加粗 */
.reply-comment button:hover {
  font-weight: 600;
}

/* 评论点赞颜色 */
.comment-like {
  color: red;
}
</style>