<template>
    <el-row type="flex" align="center" justify="center">
        <el-col :span="15">
            <el-menu :default-active="activeIndex" :router="true" class="el-menu-demo" mode="horizontal"
                background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
                <el-menu-item index="/">Home</el-menu-item>
            </el-menu>
        </el-col>
        <el-col :span="3">
            <el-menu :default-active="activeIndex" :router="true" class="el-menu-demo" mode="horizontal"
                background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" v-if="isLogin">
                <el-submenu index="1">
                    <template slot="title">
                        <i class="el-icon-user"></i>
                        <span>{{user.name}}</span>
                    </template>
                    <el-menu-item index="1-1" disabled>{{user.email}}</el-menu-item>
                    <el-menu-item index="/setting">SETTING</el-menu-item>
                    <el-menu-item @click="logout">SIGN OUT</el-menu-item>
                </el-submenu>
            </el-menu>
            <el-menu :default-active="activeIndex" :router="true" class="el-menu-demo" mode="horizontal"
                background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" v-else>
                <el-menu-item index="/signup">Signup</el-menu-item>
                <el-menu-item index="/login">Login</el-menu-item>
            </el-menu>
        </el-col>
    </el-row>
</template>
<script>
export default {
    computed: {
        activeIndex() {
            return this.$route.path
        },
        isLogin: function(){ 
            return this.$store.getters.getIsLogin
        },
        user: function() {
            return this.$store.getters.getUser
        }
    },
    mounted: function () {
        if (localStorage.getItem('user') !== null) {
            //
        }
    },
    methods: {
        logout() {
            this.$store.dispatch('logout')
            this.$router.push('/login')
        }
    }
}
</script>