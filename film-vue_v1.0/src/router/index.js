import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/home'
import Login from '@/views/login'
import Signup from '@/views/signup'
import Setting from '@/views/setting'
import Logout from '@/views/logout'
Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        { path: '/', name: 'Home', component: Home },
        { path: '/login', name: 'Login', component: Login },
        { path: '/signup', name: 'Signup', component: Signup },
        { path: '/setting', name: 'Setting', component: Setting },
        { path: '/logout', name: 'Logout', component: Logout }
    ]
})