<template>
    <el-row type="flex" align="center" justify="center">
        <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px" class="demo-ruleForm">
            <el-form-item label="User Name" prop="username">
                <el-input v-model="ruleForm.username" />
            </el-form-item>
            <el-form-item label="Password" prop="password">
                <el-input v-model="ruleForm.password" type="password" />
            </el-form-item>
            <el-form-item>
                <el-button type="success" @click="submitForm('ruleForm')" round>Login</el-button>
            </el-form-item>
        </el-form>
    </el-row>
</template>
  
<script>
import bus from "./eventBus";
export default {
    name: 'Login',
    data() {
        return {
            ruleForm: {
                username: '',
                password: ''
            },
            rules: {
                username: [
                    { required: true, message: 'Please input username', trigger: 'blur' },
                    { min: 4, max: 32, message: 'Length limited from 4 to 32 characters', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: 'Please input password', trigger: 'blur' },
                    { min: 8, max: 32, message: 'Length limited from 8 to 32 characters', trigger: 'blur' }
                ]
            }
        }
    },
    methods: {
        async submitForm(formName) {
            var vm = this
            console.log(this.ruleForm)
            const { data: res } = await this.$http.post("/api/user/login", this.ruleForm);
            // this.movieList = res.obj;
            console.log("res", res)
            if (res.code == 200) {
                window.sessionStorage.setItem("token", this.ruleForm.username);
                window.sessionStorage.setItem("user", JSON.stringify(res.obj));
                this.$message.success("login successfully");
                bus.$emit("login", res.obj);
                this.$router.push("/");
            } else {
                this.$message.error("The account or password is incorrect");
            }
            // this.$refs[formName].validate(async valid => {
            //     if (valid) {
            //         console.log(this.ruleForm)
            //         const { data: res } = await this.$http.post("/api/user/login" ,this.ruleForm);
            //         // this.movieList = res.obj;
            //         console.log("res",res)
            //         if(res.code==200){
            //             window.sessionStorage.setItem("token", this.ruleForm.username);
            //             this.$message.success("登录成功");
            //             this.$router.push("/");
            //         }else{
            //             this.$message.error("账号密码错误");
            //         }
            //         // var form = this.ruleForm
            //         // axios
            //         //     .post('/api/user/login', form)
            //         //     .then(function (response) {
            //         //         console.log(response)

            //         //         if (response.data.code == 0) {
            //         //             vm.$message({
            //         //                 message: 'Login Success',
            //         //                 type: 'success'
            //         //             })
            //         //             vm.$store.dispatch('login', response.data.data)
            //         //             vm.$router.push('/')
            //         //         } else {
            //         //             vm.$message({
            //         //                 message: 'Username or password is incorrect',
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
</style>  