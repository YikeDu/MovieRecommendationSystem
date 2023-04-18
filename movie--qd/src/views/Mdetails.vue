<template>
  <div class="Box">
    <div class="category">
      <div class="box_white">
        <div>
          <div class="hn">
            <a :href=hearfs target="_blank">
              <el-image :src="sj.imageSrc2"
                        style="width: 500px; height: 280px"></el-image>
            </a>
          </div>
          <div class="hn">
            <div style="margin-bottom: 10px">
              <span style="font-size: 2rem">{{ sj.name }}</span>
            </div>
            <div style="margin-top: 5px">
              <el-rate v-model="star" @change="starf"></el-rate>
            </div>
            <div>
              <div style="display: inline-block; margin-right: 80px">
                <div style="margin-top: 5px">
                  <span style="font-size: 1rem; color: #efa485">runtime</span>
                </div>
                <div style="margin-top: 10px">
                  <span style="font-size: 1rem">{{ sj.runtime }}</span>
                </div>
              </div>
              <div style="display: inline-block; margin-right: 80px">
                <div style="margin-top: 5px">
                  <span style="font-size: 1rem; color: #efa485">category</span>
                </div>
                <div style="margin-top: 10px">
                  <!-- <span style="font-size: 1rem; color: #77b8e8"
                    >运动, 喜剧</span
                  > -->
                  <span style="font-size: 0.9rem; color: #77b8e8" v-for="itemList in sj.mtypeLis" :key="itemList"
                        :index="itemList">{{ itemList }}
                  </span>
                </div>
              </div>
            </div>
            <div style="margin-top: 15px">
              <div style="display: inline-block; margin-right: 80px">
                <div style="margin-top: 5px">
                  <span style="font-size: 1rem; color: #efa485">vote count</span>
                </div>
                <div style="margin-top: 10px">
                  <span style="font-size: 1rem">{{ sj.voteCount }}</span>
                </div>
              </div>
              <div style="display: inline-block; margin-right: 80px">
                <div style="margin-top: 5px">
                  <span style="font-size: 1rem; color: #efa485">source</span>
                </div>
                <div style="margin-top: 10px">
                  <span style="font-size: 1rem; color: #77b8e8">themoviedb</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="category_jj">{{ sj.jj }}</div>
    <div class="category_jj">
      <div class="head">
        <div style="font-size:20px;position: absolute;top: 15px;left: 2px;">{{ this.cid }}:</div>
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

    <div class="category_jj">
      <div style=" color: #0CDAEB;font-size: 1.2rem;margin-bottom: 10px;"> Similar Movies</div>
      <div class="content" v-if="statusl">
        <div class="content_movie" v-for="item in tj" :index="item" :key="item" @click="omovie(item)">
          <div style="width: 156px; height: 320px" >
            <el-image style="width: 156px; height: 228px" :src="item.imageSrc1" :fit="fit"></el-image>
            <div style="width: 156px; margin-bottom: 10px; height: 15px">
              <span style="font-size: 1rem">{{ item.title }}</span>
            </div>
            <div style="width: 156px; margin-top: 5px; height: 15px">
              <span style="font-size: 1rem">{{ item.year }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="category_jj">
      <div style=" color: #0CDAEB;font-size: 1.2rem;margin-bottom: 10px;"> Recommendation movie</div>
      <div class="content" v-if="statusl">
        <div class="content_movie" v-for="item in xs" :index="item" :key="item" @click="omovie(item)">
          <div style="width: 156px; height: 320px">
            <el-image style="width: 156px; height: 228px" :src="item.imageSrc1" :fit="fit"></el-image>
            <div style="width: 156px; margin-bottom: 10px; height: 15px">
              <span style="font-size: 1rem">{{ item.title }}</span>
            </div>
            <div style="width: 156px; margin-top: 5px; height: 15px">
              <span style="font-size: 1rem">{{ item.year }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import bus from "./eventBus";

export default {
  components: {},
  data() {
    return {
      hearfs: "",
      firstComments: "",
      sj: null,
      xs: null,
      cid: "",
      tj: null,
      star: 0,
      statusl: true,
      // comment: [{ cid: "zhangsan", cTimeStr: "2022-11-07 11:80", cStr: "评论内容" }, { cid: "李四", cTimeStr: "2022-11-07 11:80", cStr: "评论内容" }],
      comment: [],
      movieid: "",
      sjs:''

    };
  },
  created() {
    bus.$on("xg", (val) => {
      this.sj = val;
      // this.tj = val.tj;
      // this.xs = val.xs;
      this.hearfs = "https://www.youtube.com/watch?v=" + val.hearf;
      this.comment = val.comment;
      this.movieid = val.movieid;
      console.log(this.comment);
      let tokenStr = this.gettoken();
      if (tokenStr) {
        this.cid = tokenStr
      } else {
        this.cid = "Not logged in"
      }
    });
  },
  beforeDestroy() {
    this.send();
  },
  mounted() {
    this.gettjxs()

  },
  methods: {
    omovie(v){
      console.log("这里是打印", v);
      // this.sj=v
      // this.comment = v.comment;
      // this.movieid = v.movieid;
      // this.star = v.star;
      this.sjs = v;
      this.sjs.names = this.cid;
      console.log("这里是打印",this.sjs );

      this.$router.push("/mdetails2");
    },
    send() {
      bus.$emit("xgg", this.sjs);
    },
    async gettjxs() {
      let uid = window.sessionStorage.getItem('uid');
      const {data: res} = await this.$http.get("/api/ratings/lb?mid="+this.movieid+"&uid="+uid);
      console.log('123',res)
      this.tj = res.obj.tj;
      this.xs = res.obj.xs;
      this.star = res.obj.star;

    },
    async sumbit() {
      console.log(this.firstComments);

      let tokenStr = this.gettoken();
      if (tokenStr) {
        if (this.firstComments != "") {
          let obj = {
            cid: tokenStr,
            ctimeStr: new Date(),
            cstr: this.firstComments
          }
          this.comment.unshift(obj);
          obj.mid = this.movieid
          this.$http.post("/api/ratings/sumbit", obj);
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
      console.log(this.star);
      let tokenStr = this.gettoken();
      console.log(tokenStr);
      if (tokenStr) {
        this.sj.star = this.star;
        this.sj.cid = tokenStr;
        this.sj.uid = window.sessionStorage.getItem('uid');
        const {data: res} = await this.$http.post("/api/ratings/jsData", this.sj);
        this.$message.success("Thank you for your participation. Score success:" + this.star + "Mark");
      } else {
        return this.$message.error("Please log in your account first before you can score!");
      }
    },
    gettoken() {
      return window.sessionStorage.getItem('token')
    }
  },
};
</script>
<style lang="css" scoped>
.Box {
  margin: 0;
  padding: 0;
  margin-top: 5px;
}

.category {
  /* background-color: red; */
  margin: 0 auto;
  width: 1385px;
  /* width: 1385px; */
}

.box_white {
  background-color: #feffff;
  width: 1300px;
  /* height: 500px; */
  margin: 0 auto;
  padding: 18px;
}

.hn {
  display: inline-block;
  margin-right: 30px;
}

.el-rate__icon {
  font-size: 30px;
}

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