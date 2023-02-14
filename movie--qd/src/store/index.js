import Vue from 'vue'
import vuex from 'vuex'
import axios from 'axios'
Vue.use(vuex)
export default new vuex.Store({
    state: {
        isLogin: false,
        user: {},
    },
    getters: {
        getIsLogin: state => {
            return state.isLogin || localStorage.getItem('user') != null
        },

        getUser: state => {
            return JSON.parse(localStorage.getItem('user'));
        },

        getUserId: state => {
            return JSON.parse(localStorage.getItem('user')).id;
        }
    },
    mutations: {        
        logIn: (state, usr) => {
            state.isLogin = true
            localStorage.setItem('user', JSON.stringify({id:usr.id, name:usr.username, email:usr.email}))
        },

        logOut: state => {
            state.isLogin = false
            state.user = {}
            localStorage.removeItem('user')
        }
    },
    actions: {
        login ({ commit }, usr) {
            commit('logIn', usr)
        },

        logout ({ commit }) {
            commit('logOut')
        }
    }
})
