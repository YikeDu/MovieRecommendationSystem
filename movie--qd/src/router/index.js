import Vue from 'vue'
import Router from 'vue-router'
import afuntion from "../assets/js/afuntion.js";

import Home from '@/views/home'
import Login from '@/views/login'
import Signup from '@/views/signup'
import Setting from '@/views/setting'
import Logout from '@/views/logout'
import Mdetails from '@/views/Mdetails'
import Mdetails2 from '@/views/Mdetails2'
import Like from '@/views/Like'
import Like2 from '@/views/Like2'
import Change from '@/views/Change'

Vue.use(Router)


const router = new Router({
    // mode: 'history',
    // routes,

    routes: [
        {path: '/', redirect: '/HomePage'},
        { path: '/Home', name: 'Home', component:() => import('@/views/home'),},
        {path: '/Home2', name: 'Home2', component: () => import('@/vcomponent/Home'),
            children: [
                {path: '/HomePage', component: () => import('@/view/HomePage')},
                {path: '/SearchF', component: () => import('@/view/SearchF')},
                {path: '/Details', component: () => import('@/view/Details')},
                {path: '/Login', component: () => import('@/view/Login')},
                {path: '/Signup', component: () => import('@/view/Signup')},
                {path: '/Settings', component: () => import('@/view/Settings')},
                {path: '/Xh', component: () => import('@/view/Xh')},
                {path: '/Ratings', component: () => import('@/view/Ratings')},
                {path: '/Genres', component: () => import('@/view/Genres')},
                {path: '/Favorite', component: () => import('@/view/Favorite')},
                {path: '/SimUserIds', component: () => import('@/view/SimUserIds')},
                {path: '/UserID', component: () => import('@/view/UserID')},
                {path: '/Details2', component: () => import('@/view/Details2')},

            ]
        },
        { path: '/login', name: 'Login', component: Login },
        { path: '/signup', name: 'Signup', component: Signup },
        { path: '/setting', name: 'Setting', component: Setting },
        { path: '/logout', name: 'Logout', component: Logout },
        { path: '/mdetails', name: 'Mdetails', component: Mdetails },
        { path: '/mdetails2', name: 'Mdetails2', component: Mdetails2 },
        { path: '/like', name: 'Like', component: Like },
        { path: '/like2', name: 'Like2', component: Like2 },
        { path: '/change', name: 'Change', component: Change },
    ]

})

// router.beforeEach(async (to, from, next) => {
//     if (to.path === '/login') return next()
//     const username = window.sessionStorage.getItem('dAQT/PTyXLI=')
//     const password = window.sessionStorage.getItem('Yey/O6MS38o=')
//     let loginForm = { username, password }
//     console.log(loginForm)
//     let data = await check(loginForm)


//     // console.log("statusstr", statusstr)
//     // check(tokenStr)
//     // if (!tokenStr) return next('/login')
//     if (!tokenStr || data.code !== 20000) return next('/login')
//     // if (!tokenStr) return next('/login')
//     next()
// })
async function check(loginForm) {
    // const result = await this.$afuntion.httsPost("./yd/login2", loginForm);
    const data = await afuntion.httsPost("./yd/login2", loginForm);
    return data;
}

export default router