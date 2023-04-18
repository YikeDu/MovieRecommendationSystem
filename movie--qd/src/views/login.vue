<template>
  <div>
    <div>
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
      <el-form :model="ruleForm2" status-icon  ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="Account" prop="pass">
          <el-input  v-model="ruleForm2.zh" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Email" prop="pass">
          <el-input  v-model="ruleForm2.mail" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="pass">
          <el-input  v-model="ruleForm2.ma" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="code" prop="pass">
          <el-input  v-model="ruleForm2.yzm" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <div style="display: flex">
            <el-button  @click="fsyzm()">Verification code</el-button>
            <el-button type="primary" @click="xgma">Change password</el-button>
          </div>
        </el-form-item>
      </el-form>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import bus from "./eventBus";

export default {
  name: 'Login',
  data() {
    return {
      dialogVisible:false,
      ruleForm2: {
        zh:'',
        mail:'',
        yzm:'',
        ma:'',

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
  methods: {
    async fsyzm() {

        if (this.ruleForm2.zh===""||this.ruleForm2.mail===""){
          this.$message.error("Please enter your account number and password");
        }else {
          let res = await this.$http.post("./api/user/yzm", this.ruleForm2);
          if (res.data.code==200){
            this.$message.success(res.data.message);
          }else {
            this.$message.error(res.data.message);
          }
        }

    },
    async xgma() {
      if (this.ruleForm2.zh===""||this.ruleForm2.mail===""||this.ruleForm2.yzm===""||this.ruleForm2.ma===""){
        this.$message.error("Please enter your account number and password");
      }else {
        let res = await this.$http.post("./api/user/xgma", this.ruleForm2);
        console.log("这里是打印", res);
        if (res.data.code==200){
          this.$message.success("ok!The verification code is sent successfully. Procedure!");
          this.$message.success(res.data.message);
        }else {
          this.$message.error(res.data.message);
        }
      }

    },
    wjma(){
      this.dialogVisible=true;
    },
    async submitForm(formName) {
      var vm = this
      console.log(this.ruleForm)
      const {data: res} = await this.$http.post("/api/user/login", this.ruleForm);
      // this.movieList = res.obj;
      console.log("res", res)
      if (res.code == 200) {
        window.sessionStorage.setItem("token", this.ruleForm.username);
        window.sessionStorage.setItem("user", JSON.stringify(res.obj));
        window.sessionStorage.setItem("uid", JSON.stringify(res.obj.id));
        window.sessionStorage.setItem("email", JSON.stringify(res.obj.email));
        this.$message.success("login successfully");
        bus.$emit("login", res.obj);
        this.$router.push("/");

      } else {
        this.$message.error("The account or password is incorrect");
      }

      // this.$refs[formName].validate(async valid => {
      //   console.log("valid", valid);
      //     // if (valid) {
      //     //     console.log(this.ruleForm)
      //     //     const { data: res } = await this.$http.post("/api/user/login" ,this.ruleForm);
      //     //     // this.movieList = res.obj;
      //     //     console.log("res",res)
      //     /*    if(res.code==200){*/
      /*    !*        window.sessionStorage.setItem("token", this.ruleForm.username);*!*/
      /*    !*        this.$message.success("登录成功");*!*/
      /*    !*        this.$router.push("/");*!*/
      /*    !*    }else{*!*/
      /*    !*        this.$message.error("账号密码错误");*!*/
      /*    !*    }*!*/
      /*    !*    // var form = this.ruleForm*!*/
      /*    //     // axios*/
      //     //     //     .post('/api/user/login', form)
      //     //     //     .then(function (response) {
      //     //     //         console.log(response)
      //     //
      //     //     //         if (response.data.code == 0) {
      //     //     //             vm.$message({
      //     //     //                 message: 'Login Success',
      //     //     //                 type: 'success'
      //     //     //             })
      //     //     //             vm.$store.dispatch('login', response.data.data)
      //     //     //             vm.$router.push('/')
      //     //     //         } else {
      //     //     //             vm.$message({
      //     //     //                 message: 'Username or password is incorrect',
      //     //     //                 type: 'error'
      //     //     //             })
      //     //     //         }
      //     //     //     })
      //     // }
      // })
    }
  }
}
</script>

<style lang="css" scoped>
.el-button {
  width: 100%;
}
</style>  