<template>
  <div class="n-box">
    <div class="header-box">
      <div class="k-box1"></div>
      <div class="bj-box">
        <div class="title3">
          <div>
            <el-button plain size="mini" @click="login">Login</el-button>
          </div>
          <div class="or">or</div>
          <div class="Register">
            <el-button type="warning" plain size="mini" @click="register">Register</el-button>
          </div>
        </div>
      </div>
      <div class="k-box2"></div>
    </div>
    <div class="main-box">
      <div class="main-box2">
        <div class="description-box">
          <div class="m-title">
            recommendations
          </div>
          <div class="m-title2">
            Welcome to FilmFusion - the personalized movie recommendation website! Our site utilizes advanced algorithms
            to provide you with personalized movie recommendations based on your viewing history and preferences. Our
            platform offers a seamless user experience, allowing you to easily browse and discover new movies. With the
            help of TMDB API, we not only provide our own movie recommendations, but also show you the latest trending
            movies and top-rated movies recommended by the database. Additionally, when you view a movie's details, we
            use TMDB API to recommend similar movies and other movies you may enjoy. Come join our community of movie
            enthusiasts and discover your next favorite film!
          </div>
        </div>
        <div class="picture-box">

          <el-image class="tx-image" style="width: 1236px; height: 768px"
                    :src="require(`@/assets/images/sy1.png`)" fit="fit"></el-image>
        </div>
      </div>
      <div class="main-box2">
        <div class="description-box">
          <div class="m-title">
            rich data
          </div>
          <div class="m-title2">
            At FilmFusion, we take pride in offering you access to a massive database of movies. With our comprehensive
            database, you can find information on a wide range of movies, from the latest blockbusters to classic films.
            We understand that movie preferences are unique to every individual, and that's why we provide you with
            personalized recommendations based on your viewing history. So whether you're in the mood for action, drama,
            romance, or comedy, we've got you covered!
          </div>
        </div>
        <div class="picture-box">

          <el-image class="tx-image" style="width: 1236px; height: 768px"
                    :src="require(`@/assets/images/sy2.png`)" fit="fit"></el-image>
        </div>
      </div>
      <div class="main-box2">
        <div class="description-box">
          <div class="m-title">
            Acknowledgement
          </div>
          <div class="m-title2">
            I would like to express my heartfelt gratitude to my supervisor, Farrah Higgins, and my second reader, Neil
            O'Connor, for their unwavering support and guidance throughout my project. Despite being a non-native
            English speaker, they patiently guided me every step of the way and provided valuable feedback that helped
            me improve my work. Their dedication and expertise have been a constant source of inspiration, and I feel
            blessed to have had them as my mentors.
          </div>
        </div>
        <div class="picture-box">
          <el-image class="tx-image" style="width: 680px; height: 428px;margin-left: 50px"
                    :src="require(`@/assets/images/sy3.png`)" fit="fit"></el-image>
        </div>
      </div>
      <div class="main-box2">
        <div class="description-box">
          <div class="m-title">
            Science
          </div>
          <div class="m-title2">
            Welcome to FilmFusion, a movie recommendation website created by Yike Du, a Business Computing student of
            Technological University Dublin. My website offers a wide range of movie recommendations based on my
            advanced recommendation algorithms, as well as ratings and reviews from my user community. My goal is to
            help movie lovers discover new and exciting films to enjoy. I hope you enjoy using our website and please
            feel free to provide feedback and suggestions.
          </div>
        </div>
        <div class="picture-box">
          <el-image class="tx-image" style="width: 616px; height: 770px;margin-left: 50px"
                    :src="require(`@/assets/images/sy4.png`)" fit="fit"></el-image>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "Login",
  components: {},
  computed: {},
  data() {
    return {};
  },
  watch: {},
  created() {
  },
  mounted() {
  },
  methods: {
    async login() {
      this.$router.push({path: '/Login'});
    },
    async register() {
      this.$router.push({path: '/Signup'});
      // this.$store.commit('cadmin2')
    },
  },

};
</script>
<style lang="less" scoped>
.n-box {
  width: 100%;
  height: 100%;
  overflow: auto;
  display: flex;
  flex-direction: column;
  //background-color: #ffffff;

  .header-box {
    height: 420px;
    display: flex;
    flex-direction: row;
    background-color: #0a0a0a;

    .bj-box {
      margin: auto;
      background: url("@/assets/images/2.png");
      background-repeat: no-repeat;
      background-size: 100% 100%;
      height: 420px;
      width: 1100px;
      background-color: #323232;
      position: relative;

      .title3 {
        display: flex;
        flex-direction: row;
        position: absolute;
        left: 562px;
        top: 350px;

        .or {
          color: #eaffea;
          margin: 10px;
        }
      }

    }

    .k-box1 {
      flex: 1;
      background-color: #0a0a0a;
      position: relative;
      left: 3px;
      z-index: 9;
    }

    .k-box2 {
      background-color: #323232;
      flex: 1;
    }
  }

  .main-box {
    flex: 1;
    overflow: auto;

    .main-box2 {
      display: flex;
      flex-direction: row;
      width: 1500px;
      margin: auto;
      margin-top: 10px;
      margin-bottom: 30px;
      //border: 4px solid #55a532;

      .description-box {
        //border: 4px solid #55a532;
        width: 400px;

        .m-title {
          font-size: 50px;
          color: #2e86b0;
          width: 400px;
        }

        .m-title2 {
          letter-spacing: 1px;
          line-height: 1.3em;

        }
      }

      .picture-box {
        //border: 4px solid #55a532;

      }
    }


  }
}

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
</style>
