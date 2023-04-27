<template>
  <div class="Box">
    <div class="like">
      <div class="like1">Welcome to our movie recommendation website!</div>
      <div class="like2">We provide a feature that allows you to select your favorite movie genres from a list of 20 different genres. This helps us better understand your movie preferences and provide you with more accurate recommendations. So go ahead and choose the genres you love, and get ready for personalized movie recommendations that are tailored just for you!</div>
      <div class="like3">Please choose your favorite genre!</div>
      <div class="like3">
        <div style="display: inline-block">Your favorite genre is:{{ xz }}</div>
        <div style="display: inline-block;margin-left: 20px">
          <el-button type="success" round size="small" @click="bcxq">SAVE</el-button>
        </div>
      </div>
      <div>
        <div>
          <div class="xbox" v-for="item in types" :key="item">
            <el-card class="box-card ">
              <div slot="header" class="clearfix">
                <span>{{ item.title }}</span>
                <el-button style="float: right;" type="text" @click="xqf(item)"><i :style="{ color: item.color }"
                                                                                   class="el-icon-circle-plus-outline"></i>
                </el-button>
              </div>
              <div style="float: left;margin-right: 10px">
                <el-image
                    style="width: 100px; height: 135px"
                    :src="item.url"
                    fit="fit"></el-image>
              </div>
              <div class="title-bt" style=" margin-left: 20px;width: 100px">{{ item.name }}</div>
            </el-card>
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
      url: 'https://image.tmdb.org/t/p/w500/aipiU6YQlClnSkQlbSm7Wm7n7qc.jpg',
      iconColor: "#ffb24e",
      category_right_status: false,
      types: [
        {
          url: 'https://image.tmdb.org/t/p/w500/aipiU6YQlClnSkQlbSm7Wm7n7qc.jpg',
          name: 'Animation',
          title: '忠犬八公',
          color: "#66B1FF"
        },
        {
          url: 'https://image.tmdb.org/t/p/w500/aipiU6YQlClnSkQlbSm7Wm7n7qc.jpg',
          name: 'Animation2',
          title: '忠犬八公2',
          color: "#66B1FF"
        },
      ],
      xz: [],
    };
  },
  mounted() {
    this.getxh()
  },
  methods: {
    async getxh() {
      let cid = window.sessionStorage.getItem("cid");
      const res = await this.$afuntion.httsGet("/api/ratings/getxh?cid=" + cid);
      this.types = res.obj.types
      this.xz = res.obj.xz
    },
    async bcxq() {
      let cid = window.sessionStorage.getItem("cid");
      let obj = {
        xh: this.xz,
        cid: cid
      }
      const res = await this.$afuntion.httsPost("/api/user/xh?", obj);

      return this.$message.success("succeed!");
    },
    xqf(v) {
      if (v.color === 'red') {
        v.color = '#66B1FF'
        let j = v.title;
        this.xz.splice(this.xz.indexOf(j), 1)
        for (let i = 0; i < this.xz.length; i++) {
          if (this.xz[i] === j) {
          }
        }
      } else {
        v.color = 'red'
        let j = v.title;
        if (this.xz.length == 0) {
          this.xz.push(j);
        } else {
          for (let i = 0; i < this.xz.length; i++) {
            let n = this.xz[i]
            console.log("name", this.xz[i])
            if (n === j) {
              break
            } else {
              if (i == this.xz.length - 1) {
                this.xz.push(j);
              }
            }
          }
        }

      }
    },
  }
};
</script>
<style lang="css" scoped>
.like {
  width: 1513px;
  margin: 0 auto;
  /*background-color: #0CDAEB;*/
}

.like1 {
  font-size: 50px;
  margin-bottom: 30px;
}

.like2 {
  /*font-size: 50px;*/
  margin-bottom: 30px;
}

.like3 {
  /*font-size: 50px;*/
  margin-bottom: 30px;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.box-card {
  width: 260px;
}

i {
  font-size: 25px;
  color: #66B1FF;
  /*color: red;*/
}

.xbox {
  display: inline-block;
  margin-left: 20px;
  margin-right: 20px;
  margin-top: 10px;
}

.title-bt {
  width: 156px;
  /*1. 先强制一行内显示文本*/
  white-space: nowrap;
  /*2. 超出的部分隐藏*/
  overflow: hidden;
  /*3. 文字用省略号替代超出的部分*/
  text-overflow: ellipsis;
}
</style>