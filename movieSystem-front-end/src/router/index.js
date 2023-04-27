import Vue from 'vue'
import Router from 'vue-router'
import afuntion from "../assets/js/afuntion.js";


Vue.use(Router)


const router = new Router({
    // mode: 'history',
    // routes,

    routes: [
        {path: '/', redirect: '/welcomeHome'},
        {path: '/welcomeHome', name: 'NewHome', component: () => import('@/view/NewHome')},
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
                {path: '/UserID', name:"UserID",component: () => import('@/view/UserID')},
                {path: '/Details2', component: () => import('@/view/Details2')},
                {path: '/collect', component: () => import('@/view/Collect')},
            ]
        },
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