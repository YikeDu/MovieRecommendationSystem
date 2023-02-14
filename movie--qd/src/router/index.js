import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/home'
import Login from '@/views/login'
import Signup from '@/views/signup'
import Setting from '@/views/setting'
import Logout from '@/views/logout'
import Mdetails from '@/views/Mdetails'
import Like from '@/views/Like'
import Like2 from '@/views/Like2'
import Change from '@/views/Change'
Vue.use(Router)

export default new Router({
    // mode: 'history',
    routes: [
        { path: '/', name: 'Home', component: Home },
        // { path: '/', name: 'mdetails', component: Mdetails },
        { path: '/login', name: 'Login', component: Login },
        { path: '/signup', name: 'Signup', component: Signup },
        { path: '/setting', name: 'Setting', component: Setting },
        { path: '/logout', name: 'Logout', component: Logout },
        { path: '/mdetails', name: 'Mdetails', component: Mdetails },
        { path: '/like', name: 'Like', component: Like },
        { path: '/like2', name: 'Like2', component: Like2 },
        { path: '/change', name: 'Change', component: Change },
    ]
})