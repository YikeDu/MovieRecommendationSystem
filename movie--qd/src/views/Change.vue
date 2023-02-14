<template>
  <div class="category">
    <div class="left_box">
      <div class="banner">
        <div class="w">
          <div class="subnav">
            <ul>
              <a href="#">
                <li :class="{ bg: time === 1 }" @click="gcolor(1)">Account setting</li>
              </a>
              <a href="#">
                <li :class="{ bg: time === 2 }" @click="gcolor(2)">Change password</li>
              </a>
              <a href="#">
                <li :class="{ bg: time === 3 }" @click="gcolor(3)">Delete a ccount</li>
              </a>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="rght_box">
      <div class="rght_box2" v-if="time == 1">
        <div> <span class="title">Account setting</span></div>
        <br>
        <el-form :label-position="labelPosition" label-width="140px" :model="formLabelAlign">
          <el-form-item label="Email">
            <el-input v-model="formLabelAlign.Email"></el-input>
          </el-form-item>
          <el-form-item label="UserName">
            <el-input v-model="formLabelAlign.UserName"></el-input>
          </el-form-item>
          <el-form-item label="">
            <el-button @click="UpdateU">Update</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="rght_box2" v-if="time == 2">
        <div> <span class="title">Change your password</span></div>
        <br>
        <el-form :label-position="labelPosition" label-width="140px" :model="formLabelAlign">
          <el-form-item label="Password">
            <el-input v-model="formLabelAlign.Password"></el-input>
          </el-form-item>
          <el-form-item label="Retype Password" label-width="140px">
            <el-input v-model="formLabelAlign.Retype"></el-input>
          </el-form-item>
          <el-form-item label="">
            <el-button @click="UpdateP">Update</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="rght_box2" v-if="time == 3">
        <div> <span class="title">Delete a ccount</span></div>
        <div> <span>Have you thought about it? What you're going to do will delete your account, irretrievably</span>
        </div>
        <br>
        <el-button type="danger" @click="deleteU">Update</el-button>
      </div>
    </div>
  </div>
</template>
<script>
import bus from "./eventBus";
export default {
  components: {
  },
  data() {
    return {
      time: 1,
      color1: "#5CAFE2",
      labelPosition: 'right',
      formLabelAlign: {
        Email: "",
        UserName: "",
        Password: "",
        Retype: "",
        id: "",

      }
    };
  },
  created() {
  },
  mounted() {
    this.init()
  },
  methods: {
    gcolor(val) {
      this.time = val
      console.log(this.time);
    },
    init() {
      const user = JSON.parse(window.sessionStorage.getItem('user'))
      console.log("user", user);
      this.formLabelAlign.Email = user.email
      this.formLabelAlign.UserName = user.username

      this.formLabelAlign.id = user.id
    },
    async UpdateU() {
      const { data: res } = await this.$http.post("/api/user/UpdateU", this.formLabelAlign);
      this.jumplogin()
    },
    async UpdateP() {
      const { data: res } = await this.$http.post("/api/user/UpdateP", this.formLabelAlign);
      this.jumplogin()
    },
    async deleteU() {
      const { data: res } = await this.$http.post("/api/user/deleteU", this.formLabelAlign);
      this.jumplogin()
    },
    jumplogin() {
      this.$message.success("账号信息更新成功,请重新登录");
      window.sessionStorage.clear();
      bus.$emit("Update", "未登录");
      this.$router.push("/login");
    }
  }
};
</script>
<style lang="less" scoped>
.category {
  margin: 0px;
  padding: 0px;
  margin: 0 auto;
  width: 1513px;
}

ul li {
  margin: 20px;
  padding: 20px;
  list-style: none;
}

li {
  // background-color: bisque;
  // background-color: rgb(12, 197, 89);
  border-radius: 5px;
}

.bg {
  background-color: #54ACE2;
}


.left_box,
.rght_box {
  float: left;
}

.left_box {
  // background-color: aqua;
  // width: 1520px;
}

.rght_box {
  // background-color: rosybrown;
  height: 254px;
  width: 1200px;
  position: relative;
  display: flex;
  align-items: center;

  .rght_box2 {
    margin-top: 10px;
    padding-left: 50px;
  }
}

a {
  text-decoration: none;
}

.title {
  font-size: 50px;
}
</style>