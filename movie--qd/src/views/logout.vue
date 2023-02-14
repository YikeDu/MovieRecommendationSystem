<template>
    <el-main>
        <el-row type="flex" align="center" justify="center">
            <el-col :span="18">
                <el-alert title="Are you sure to logout?" type="warning" effect="dark">
                </el-alert>
            </el-col>
        </el-row>

        <el-row type="flex" align="center" justify="center">
            <el-button type="primary" @click="logout()">Logout</el-button>
        </el-row>

    </el-main>
</template>
<script>
export default {
    name: 'Logout',
    methods: {
        logout() {
            this.$store.commit('cleanLoggedin')
        }
    }
}
</script>