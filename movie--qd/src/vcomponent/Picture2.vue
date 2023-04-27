<template>
  <div>
    <div class="flex-box">
      <div class="content_movie" v-for="(item ,index) in rm" :index="index" :key="item.id">
        <a href="#" @click="omovie(item)">
          <!--          <img :src=item.imagesrc1  alt="">-->
          <el-image style="width: 156px; height: 228px"
                    :src="'https://image.tmdb.org/t/p/w500'+item.imageSrc2" fit="fit"></el-image>
        </a>
        <div class="title1">{{ item.title }}</div>
        <div class="mid">
          <el-rate v-model=item.star></el-rate>
        </div>
        <div class="mid">{{ item.movieid }}</div>
      </div>
    </div>
  </div>
</template>
<script>
import {mapState, mapMutations, mapGetters, osearch} from "vuex";

export default {
  name: "Login",
  props: ['rm'],
  components: {},
  // computed: {
  //   ...mapState(['ssszl','cid','searchdata']),
  // },
  data() {
    return {
      rm2: [],
      starValue: 3,
    };
  },
  created() {
  },
  mounted() {
  },
  methods: {
    async omovie(v) {
      window.sessionStorage.setItem("mid", v.movieid);
      this.$router.push("/Details");
    },
  },
};
</script>
<style lang="less" scoped>
.flex-box {
  display: flex;
  flex-direction: row;
  //justify-content: center;
  align-items: center;
  flex-wrap: wrap;
}

.content_movie {
  margin-left: 17px;
  margin-right: 17px;
}

.title1, .mid {
  margin-top: 5px;
  width: 156px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;


}

.el-image {
  border-radius: 6px;
}
</style>
