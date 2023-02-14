<template>
  <div>
    <div>
      <el-row type="flex" align="center" justify="center">
        <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px" class="demo-ruleForm">
          <el-form-item label="User Name" prop="username">
            <el-input v-model="ruleForm.username"/>
          </el-form-item>

          <el-form-item label="Password" prop="password">
            <el-input v-model="ruleForm.password" type="password"/>
          </el-form-item>
          <el-form-item label="Email" prop="email">
            <el-input v-model="ruleForm.email"/>
          </el-form-item>
          <el-form-item>
            <el-button type="success" @click="submitForm('ruleForm')" round>Signup</el-button>
          </el-form-item>
        </el-form>
      </el-row>
    </div>
<!--    <div class="like">喜好</div>-->
  </div>
</template>

<script>
import axios from 'axios'
import bus from "@/views/eventBus";

export default {
  name: 'Sign',
  data() {
    var validateUsername = (rule, value, callback) => {
      axios
          .get('/api/user/checkUsername?username=' + value)
          .then(function (response) {
            if (response.data.code == 0) {
              console.log('username validation success')
              callback()
            } else {
              callback(new Error('Username already existed'))
            }
          })
    }
    var validateEmail = (rule, value, callback) => {
      if (/^\w{1,64}@[a-z0-9\-]{1,256}(\.[a-z]{2,6}){1,2}$/i.test(value) == false) {
        callback(new Error("Email format error"));
      }
      axios
          .post('/api/user/checkEmail?email=' + value)
          .then(function (response) {
            if (response.data.code == 0) {
              callback()
            } else {
              callback(new Error('Email already existed'))
            }
          })
    }
    return {
      ruleForm: {
        username: '',
        email: '',
        password: ''
      },
      rules: {
        username: [
          {validator: validateUsername, trigger: 'blur'},
          {required: true, message: 'Please input username', trigger: 'blur'},
          {min: 4, max: 32, message: 'Length limited from 4 to 32 characters', trigger: 'blur'}
        ],
        email: [
          {validator: validateEmail, trigger: 'blur'},
          {required: true, message: 'Please input email', trigger: 'blur'},
          {min: 4, max: 32, message: 'Length limited from 4 to 64 characters', trigger: 'blur'}
        ],
        password: [
          {required: true, message: 'Please input password', trigger: 'blur'},
          {min: 8, max: 32, message: 'Length limited from 8 to 32 characters', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    async submitForm(formName) {
      var vm = this
      var form = this.ruleForm
      console.log(form);
      const {data: res} = await this.$http.post("/api/user/checkUsername", this.ruleForm);
      // this.movieList = res.obj;
      console.log("res", res)
      if (res.code == 200) {
        // this.$router.push("/login");
        const { data: res2 } = await this.$http.post("/api/user/login", this.ruleForm);
        window.sessionStorage.setItem("token", this.ruleForm.username);
        window.sessionStorage.setItem("user", JSON.stringify(res2.obj));
        bus.$emit("login", res2.obj);
        this.$router.push("/like2");
        return this.$message.success(res.message);
      } else {
        this.$message.error(res.message);
      }


      // this.$refs[formName].validate((valid) => {
      //     if (valid) {

      //         // axios
      //         //     .post('/api/user/signup', form)
      //         //     .then(function (response) {
      //         //         console.log(response);
      //         //         if (response.data.code == 0) {
      //         //             vm.$message({
      //         //                 message: 'Signup Success!',
      //         //                 type: 'success'
      //         //             })
      //         //             vm.$router.push({ path: '/login' })
      //         //         } else {
      //         //             vm.$message({
      //         //                 message: 'Signup Error!',
      //         //                 type: 'error'
      //         //             })
      //         //         }
      //         //     })
      //     }
      // })
    }
  }
}
</script>

<style lang="css" scoped>
.el-button {
  width: 100%;
}

.like {
  width: 1513px;
  margin: 0 auto;
  background-color: #0CDAEB;
}
</style>
  