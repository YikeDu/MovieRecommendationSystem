<template>
  <div>
    <div class="tabs-header">
      <el-menu
          :default-active="activeIndex2"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
          background-color="#555657"
          text-color="#fff"
          active-text-color="#ffd04b">

        <el-menu-item index="1" @click="collect" v-if=register>
          <i class="el-icon-collection-tag"></i>
        </el-menu-item>
        <el-menu-item index="1" @click="signup" v-if=!register>Signup</el-menu-item>
<!--        <el-menu-item index="2" @click="login">Login</el-menu-item>-->
        <el-menu-item index="2" @click="login">{{ admin.username }}</el-menu-item>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-s-tools"></i></template>
          <el-menu-item index="3-1" disabled>{{ admin.email }}</el-menu-item>
          <el-menu-item index="3-2" @click="settings">SETTINGS</el-menu-item>
          <el-menu-item index="3-3" @click="signOut">SIGN OUT</el-menu-item>
        </el-submenu>
      </el-menu>
    </div>
  </div>
</template>
<script>
import {mapState} from "vuex";
export default {
  name: "Login",
  components: {},
  computed: {
    ...mapState(['admin','register']),
  },
  data() {
    return {
      email: 'Not logged in',
      activeIndex: '1',
      activeIndex2: '3-1',
      tabsList: [
        {id: "1", title: "Signup", path: "Fl", icon: ""},
        {id: "2", title: "Login", path: "Square", icon: ""},
        // {id: "3", title: "", path: "SharingFb",icon:"el-icon-setting"},

      ],
    };
  },
  mounted() {
    this.init()
  },
  methods: {
    async collect() {
      this.$router.push('/collect');
    },
    async init() {
      let email1 = JSON.parse(window.sessionStorage.getItem("admin"));
      if (email1){
        this.$store.commit('admin', email1)
      }else {
        this.$store.commit('cadmin')
      }
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    signup() {
      this.$router.push("/Signup")
    },
    login() {
      this.$router.push("/Login");
    },
    settings() {
      if (this.admin.id!=null){
        this.$router.push("/Settings");
      }else {
        this.$message.error('After login account can enter the setting!');
      }
    },
    async signOut() {
      window.sessionStorage.clear();
      this.$store.commit('cadmin')
      this.$store.commit('cadmin3')
      this.$router.push("/welcomeHome");
      this.email='Not logged in';
      this.$message.success('The account has been withdrawn.');
    },
  },
};
</script>
<style lang="less" scoped>
.tabs-header {
  //width: 60%;
  //margin: auto;
  height: 100%;

}

.el-menu {
  border-bottom: none;
}

</style>
