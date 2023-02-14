<template>
  <el-row type="flex" align="center" justify="center">
    <el-col :span="15">
      <el-menu :default-active="activeIndex" :router="true" class="el-menu-demo" mode="horizontal"
        background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
        <el-menu-item index="/" @click="send"> Home</el-menu-item>
      </el-menu>
    </el-col>
    <el-col :span="3">
      <el-menu :default-active="activeIndex" :router="true" class="el-menu-demo" mode="horizontal"
        background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" v-if="isLogin">

      </el-menu>
      <el-menu :default-active="activeIndex" :router="true" class="el-menu-demo" mode="horizontal"
        background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" v-else>
        <el-menu-item index="/signup">Signup</el-menu-item>
        <el-menu-item index="/login">Login</el-menu-item>

        <el-submenu index="1">
          <template slot="title"> <i class="el-icon-s-tools" style="color: aliceblue;margin-left: -2px;"></i></template>
          <el-menu-item index="1-1" disabled>{{ email }}</el-menu-item>
          <el-menu-item @click="seting">SETTINGS</el-menu-item>
          <el-menu-item @click="exit">SIGN OUT</el-menu-item>
        </el-submenu>
      </el-menu>
      <el-menu>
      </el-menu>
    </el-col>
  </el-row>
</template>
<script>
import bus from "../views/eventBus";

export default {
  data() {
    return {
      email: "Not logged in"
    };
  },
  computed: {
    activeIndex() {
      return this.$route.path;
    },
    isLogin: function () {
      return this.$store.getters.getIsLogin;
    },
    user: function () {
      return this.$store.getters.getUser;
    },
  },
  mounted: function () {
    if (localStorage.getItem("user") !== null) {
      //
    }
  },
  created() {
    bus.$on("login", (val) => {
      console.log("login", val);
      this.email = val.email
    });
    bus.$on("Update", (val) => {
      this.email = "Not logged in"
    });
  },
  methods: {
    seting() {
      // this.$router.push("/change");
      if (this.email != "Not logged in") {
        this.$router.push("/change");
      } else {
        this.$message.error("Please log in your account before you can access the Settings page!");
      }
    },
    exit() {
      this.email = "Not logged in"
      // this.$store.dispatch("token");
      this.$message.success("Your account has been logged out!");
      window.sessionStorage.clear();
      this.$router.push("/login");
    },
    logout() {
      this.$store.dispatch("logout");
      this.$router.push("/login");
    },
    send() {
      bus.$emit("home", "home");
    },
  },
};
</script>

<style lang="css" scoped>
.el-col-3 {
  width: 358px;
}
</style>