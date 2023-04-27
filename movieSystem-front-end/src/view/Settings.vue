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
        <div><span class="title">Account setting</span></div>
        <br>
        <div>
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
        <div class="tx-box">
          <div>
            <el-image class="tx-box1"
                      style="width: 100px; height: 100px"
                      :src="url"
                      fit="fit"></el-image>
          </div>
          <div class="gh-box">
            <el-upload class="upload-demo" :auto-upload="false" action="" :on-change="fileChange"
                       :on-exceed="exceedFile"
                       :show-file-list="false"
                       :file-list="fileList" list-type="picture">
              <el-button type="success" plain>Change profile photo</el-button>
            </el-upload>
          </div>
        </div>
      </div>

      <div class="rght_box2" v-if="time == 2">
        <div><span class="title">Change your password</span></div>
        <br>
        <el-form :label-position="labelPosition" label-width="140px" :model="formLabelAlign">
          <el-form-item label="Original password">
            <el-input v-model="formLabelAlign.oPassword"></el-input>
          </el-form-item>
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
        <div><span class="title">Delete a ccount</span></div>
        <div><span>Have you thought about it? What you're going to do will delete your account, irretrievably</span>
        </div>
        <br>
        <el-button type="danger" @click="deleteU">Update</el-button>
      </div>
    </div>
  </div>
</template>
<script>
import bus from "@/eventBus/eventBus";
import {mapState, mapMutations, mapGetters, osearch} from "vuex";

export default {
  components: {},
  data() {
    return {
      limitNum: 1,
      fileList: [],
      fits: ['fill', 'contain', 'cover', 'none', 'scale-down'],
      url: require(`@/assets/images/1.jpg`),
      time: 1,
      color1: "#5CAFE2",
      labelPosition: 'right',
      formLabelAlign: {
        Email: "",
        UserName: "",
        Password: "",
        oPassword: "",
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
    async daqd() {
      // var user = JSON.stringify(this.form);
      var formData = new FormData();
      formData.append("file", this.files)
      // formData.append("name", user)
      if (!this.files) {
        return this.$message.error("Please select a picture to upload!");
      }
      let data = await this.$afuntion.httsPost("./api/ratings/upload", formData)
      if (data.code == 200) {
        this.$message.success(data.message);
        this.url = data.obj
        let user = JSON.parse(window.sessionStorage.getItem('admin'))
        user.picturel = data.obj;
        window.sessionStorage.setItem("admin", JSON.stringify(user));
      } else {
        this.$message.error(data.message);
      }
    },
    // 文件状态改变时的钩子
    async fileChange(file, fileList) {
      let suffix = file.name.split(".");
      if (suffix[1] != "png") {
        this.$message.error("The image format is incorrect. Please upload the png format!");
        this.fileList = []
        return
      }
      this.files = file.raw
      this.daqd()
      fileList = []
      // let data = await this.$afuntion.httsPost("upload", formData)
    },
    // 文件超出个数限制时的钩子
    exceedFile(files, fileList) {
      this.$message.warning(`只能选择 ${this.limitNum} 个文件，当前共选择了 ${files.length + fileList.length} 个`);
    },
    gcolor(val) {
      this.time = val
      console.log(this.time);
    },
    init() {
      const user = JSON.parse(window.sessionStorage.getItem('admin'))
      this.formLabelAlign.Email = user.email
      this.formLabelAlign.UserName = user.username
      this.formLabelAlign.id = user.id
      this.url = user.picturel
    },

    async UpdateU() {
      const res = await this.$afuntion.httsPost("/api/user/UpdateU", this.formLabelAlign);
      if (res.code == 200) {
        this.jumplogin()
      } else {
        this.$message.error(res.message);
      }
    },
    async UpdateP() {
      if (this.formLabelAlign.Password != this.formLabelAlign.Retype) {
        this.$message.error("The password is wrong. The password entered twice is different");
        return;
      }
      const res = await this.$afuntion.httsPost("/api/user/UpdateP", this.formLabelAlign);
      if (res.code == 200) {
        this.jumplogin()
      } else {
        this.$message.error(res.message);
      }
    },
    async deleteU() {
      const res = await this.$afuntion.httsPost("/api/user/deleteU", this.formLabelAlign);
      this.jumplogin()
    },
    jumplogin() {
      this.$message.success("The account information has been updated successfully. Please log in again!");
      window.sessionStorage.clear();
      // bus.$emit("Update", "未登录");
      this.$store.commit('cadmin')
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
    margin-top: 60px;
    padding-left: 20px;
  }
}

a {
  text-decoration: none;
}

.title {
  font-size: 50px;
  margin-top: 20px;
  position: relative;
}

.tx-box {
  position: absolute;
  //width: 100%;
  //height: 100%;
  //border: 4px solid #55a532;
  left: -20px;
  top: 100px;
  display: flex;
  flex-direction: column;
  //justify-content: center;
  //align-items: center;
  //flex-flow: wrap;
  .tx-box1 {
    border-radius: 100px;
  }

  .gh-box {
    margin-top: 20px;
  }

}
</style>