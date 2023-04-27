<template>
  <div class="header-box">
    <div class="header-box-left">
      <a href="#" class="picture-box1" @click="ohomeD">
        <div class="picture-box"></div>
      </a>
      <a href="#">
        <div class="pull-down">
          <el-popover v-model="visiblepop" placement="top" width="346" height="500">
            <div class="pull-down-box">
              <div class="lb1-box">
                <div class="type-span">QUICK LINKS</div>
                <a href="#" @click="tz('HomePage')">
                  <div class="type-mx"><span>HOME</span></div>
                </a>
                <a href="#" @click="tzXh('SimUserIds')">
                  <div class="type-mx"><span>Similiar Users</span></div>
                </a>
                <a href="#" @click="tzXh('Favorite')">
                  <div class="type-mx"><span>Favorite genre</span></div>
                </a>
              </div>
              <div class="lb1-box">
                <div class="type-span"><span>YOUR ACTIVITY</span></div>
                <a href="#" @click="tzXh('Ratings')">
                  <div class="type-mx"><span> Ratings</span></div>
                </a>
              </div>
              <div class="lb1-box">
                <div class="type-span">GENRES</div>
                <div class="type-span2">
                  <a href="#" :key="index" v-for="(item, index) in mtypeArry">
                    <div class="type-mx" @click="geners(item)"><span> {{ item }}</span></div>
                  </a>
                </div>
              </div>
            </div>
            <i slot="reference" class="el-icon-s-fold" :style="{ color: iconColor }"></i>
          </el-popover>
        </div>
      </a>
    </div>
    <div>
      <el-input size="mini" placeholder="Search by movie title" v-model="input3" class="input-with-select">
        <el-button size="mini" slot="append" icon="el-icon-search" @click="osearchD(input3)"></el-button>
      </el-input>
    </div>
    <div class="setting">
      <Tabs></Tabs>
    </div>
  </div>
</template>

<script>

import {mapState, mapMutations, mapGetters, osearch} from "vuex";
import Tabs from "@/vcomponent/Tabs";

export default {
  name: "Login",
  components: {
    Tabs,
  },
  computed: {
    ...mapState(['admin']),
  },
  data() {
    return {
      mtypeArry: ["Animation", "Adventure", "Children", "Fantasy", "Mystery", "Thriller"],
      visiblepop: false,
      iconColor: '#ecf5ff',
      input1: '',
      input2: '',
      input3: '',
      select: '',
      activeIndex: '1',
      activeIndex2: '1'
    };
  },
  created() {
  },
  mounted() {
  },
  methods: {
    geners(v) {

      this.$store.commit('mtypes', v)
      if (this.$route.path != '/Genres') {
        this.$router.push("/Genres");
      }
      this.visiblepop = false
    },
    async tzXh(v) {
      if (this.admin.id != null) {
        this.$router.push("/" + v);
        this.visiblepop = false
      } else {
        this.$message.error('Please login account first!');
      }
    },
    async tz(v) {
      this.$router.push("/" + v);
      this.visiblepop = false
    },
    async ohomeD() {
      this.$router.push('HomePage')
    },
    async osearchD(v) {
      this.$router.push('SearchF')
      this.$store.commit('osearch', v)
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    ...mapMutations(['osearch', 'ohome', 'opullDownstatus']),
  },
};
</script>
<style lang="less" scoped>
.header-box {
  background-color: #555657;
  width: 1550px;
  height: 100%;
  margin: auto;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}

.aa {
  background-color: #86bbe4;
  background: url("@/assets/images/1.png");
  background-repeat: no-repeat;
  background-size: 100% 100%;
  height: 100%;
}

.picture-box {
  width: 100px;
  height: 17px;
  display: inline-block;
  background: url("@/assets/images/1.png");
  background-repeat: no-repeat;
  background-size: 100% 100%;
  margin-left: 10px;
}

.pull-down {
  display: inline-block;
  margin-left: 56px;
  //position: relative;
}

.el-select .el-input {
  width: 130px;
}

.input-with-select .el-input-group__prepend {
  background-color: #fff;
}

.pull-down-box {
  //background-color: #fff;
  position: absolute;
  width: 350px;
  height: 200px;
  display: flex;
  flex-direction: row;
  left: 0px;
  z-index: 1;
  //border-radius: 5px 5px 5px 5px ;
  //top: 35px;
  //justify-content: center;
  //align-items: center;
}

.lb1-box {
  display: inline-block;
  background-color: #fff;
  //background-color: #bd2c00;

  //height: 200px;
}

/deep/ .pull-down .el-popover {
  padding: 0px;
  //margin-top: 20px;
}

/deep/ .el-popper[x-placement^=bottom] {
  padding: 0px;
}

.type-span {
  color: #e86e2e;
  margin: 10px;
  vertical-align: top;
  font-family: 楷体;
  font-weight: bold;
}

.type-span2 {
  width: 130px;
  height: 160px;
  //overflow: auto;
  //margin-top: -5px;
}

.type-mx {
  margin: 10px;
  font-size: 12px;
}

.type-mx2 {

}

.setting {
  //width: 252px;
  height: 100%;
  //margin-right: 10px;
}

.header-box-left {
  margin-right: 50px;
}

//==============
/*定义滚动条高宽及背景 高宽分别对应横竖滚动条的尺寸*/
::-webkit-scrollbar {
  width: 1px;
  //height: -5px;
  background-color: #f5f5f5;
}

/*定义滚动条轨道 内阴影+圆角*/
::-webkit-scrollbar-track {
  box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  border-radius: 0px;
  background-color: #f5f5f5;
}

/*定义滑块 内阴影+圆角*/
::-webkit-scrollbar-thumb {
  border-radius: 10px;
  box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.1);
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.1);
  background-color: #c8c8c8;
}

.el-input-group {
  //width: 150%;
  margin-right: 100px;
}
</style>
