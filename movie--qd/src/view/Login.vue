<template>
  <div class="login-boxma">
    <div class="login-box">
      <el-row type="flex" align="center" justify="center">
        <el-form ref="ruleForm" :model="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="User Name" prop="username">
            <el-input v-model="ruleForm.username"/>
          </el-form-item>
          <el-form-item label="Password" prop="password">
            <el-input v-model="ruleForm.password" type="password"/>
          </el-form-item>
          <el-form-item>

            <el-button type="success" @click="submitForm('ruleForm')" round>Login</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="success" @click="wjma()" round>Forget your password</el-button>
          </el-form-item>
        </el-form>
      </el-row>
    </div>
    <div>
      <el-dialog
          title="Forget your password"
          :visible.sync="dialogVisible"
          width="30%"
          :before-close="handleClose">
        <el-form :model="ruleForm2" status-icon ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="Account" prop="pass">
            <el-input v-model="ruleForm2.zh" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="Email" prop="pass">
            <el-input v-model="ruleForm2.mail" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="Password" prop="pass">
            <el-input v-model="ruleForm2.ma" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="code" prop="pass">
            <el-input v-model="ruleForm2.yzm" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <div style="display: flex">
              <el-button @click="fsyzm()">Verification code</el-button>
              <el-button type="primary" @click="xgma">Change password</el-button>
            </div>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import bus from "@/eventBus/eventBus";

export default {
  name: 'Login',
  data() {
    return {
      email: "",
      dialogVisible: false,
      ruleForm2: {
        zh: '',
        mail: '',
        yzm: '',
        ma: '',

      },
      ruleForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          {required: true, message: 'Please input username', trigger: 'blur'},
          {min: 4, max: 32, message: 'Length limited from 4 to 32 characters', trigger: 'blur'}
        ],
        password: [
          {required: true, message: 'Please input password', trigger: 'blur'},
          {min: 8, max: 32, message: 'Length limited from 8 to 32 characters', trigger: 'blur'}
        ]
      }
    }
  },
  beforeDestroy() {
    this.send();
  },
  methods: {
    send() {
      bus.$emit("email", this.email);
    },
    async fsyzm() {
      if (this.ruleForm2.zh === "" || this.ruleForm2.mail === "") {
        this.$message.error("Please enter your account number and password");
      } else {
        let res = await this.$http.post("./api/user/yzm", this.ruleForm2);
        if (res.data.code == 200) {
          this.$message.success(res.data.message);
        } else {
          this.$message.error(res.data.message);
        }
      }
    },
    async xgma() {
      if (this.ruleForm2.zh === "" || this.ruleForm2.mail === "" || this.ruleForm2.yzm === "" || this.ruleForm2.ma === "") {
        this.$message.error("Please enter your account number and password");
      } else {
        let res = await this.$http.post("./api/user/xgma", this.ruleForm2);
        if (res.data.code == 200) {
          this.$message.success("ok!The verification code is sent successfully. Procedure!");
          this.$message.success(res.data.message);
        } else {
          this.$message.error(res.data.message);
        }
      }

    },
    wjma() {
      this.dialogVisible = true;
    },
    async httpMb() {
      this.tableData = res.obj
    },
    async submitForm(formName) {
      const res = await this.$afuntion.httsPost("/api/user/login", this.ruleForm);
      if (res.code == 200) {
        window.sessionStorage.setItem("token", this.ruleForm.username);
        window.sessionStorage.setItem("admin", JSON.stringify(res.obj));
        window.sessionStorage.setItem("username", res.obj.username);
        window.sessionStorage.setItem("cid", res.obj.id);
        window.sessionStorage.setItem("email", res.obj.email);
        this.$message.success("login successfully");
        bus.$emit("login", res.obj);
        this.$store.commit('admin', res.obj)
        this.$router.push("/HomePage");
        this.email=res.obj.email
        this.send()
        this.$store.commit('cadmin2')

      } else {
        this.$message.error("The account or password is incorrect");
      }
    }
  }
}
</script>

<style lang="css" scoped>
.el-button {
  width: 100%;
}

.login-box {
  margin-top: 40px;
}

.login-boxma {
  margin-right: 70px;
}
</style>  