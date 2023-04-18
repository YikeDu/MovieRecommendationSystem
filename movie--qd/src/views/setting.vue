<template>
    <el-row type="flex" align="center" justify="center">
        <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px" class="demo-ruleForm">
            <el-form-item label="Email" prop="email">
                <el-input v-model="ruleForm.email" placeholder="Please input new email" />
            </el-form-item>
            <el-form-item label="Password" prop="password">
                <el-input v-model="ruleForm.password" type="password" placeholder="Input new password"/>
            </el-form-item>
            <el-form-item>
                <el-button type="success" @click="submitForm('ruleForm')" round>Update</el-button>
            </el-form-item>
        </el-form>
    </el-row>
</template>
  
<script>
import axios from 'axios'
export default {
    name: 'Setting',
    data() {
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
                id: 0,
                email: '',
                password: ''
            },
            rules: {
                email: [
                    { validator: validateEmail, trigger: 'blur' },
                    { required: true, message: 'Please input email', trigger: 'blur' },
                    { min: 4, max: 32, message: 'Length limited from 4 to 64 characters', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: 'Please input password', trigger: 'blur' },
                    { min: 8, max: 32, message: 'Length limited from 8 to 32 characters', trigger: 'blur' }
                ]
            }
        }
    },
    methods: {
        submitForm(formName) {
            console.log(formName);
            var vm = this
            var form = this.ruleForm
            form.id = this.$store.getters.getUserId

            this.$refs[formName].validate((valid) => {
                if (valid) {
                    axios
                        .post('/api/user/setting', form)
                        .then(function (response) {
                            console.log(response);
                            if (response.data.code == 0) {
                                vm.$message({
                                    message: 'Update Success!',
                                    type: 'success'
                                })
                                vm.$store.dispatch('login', response.data.data)
                                vm.$router.push({ path: '/' })
                            } else {
                                vm.$message({
                                    message: 'Update Error!',
                                    type: 'error'
                                })
                            }
                        })
                }
            })
        }
    }
}
</script>
  
<style lang="css" scoped>
.el-button {
    width: 100%;
}
</style>
  