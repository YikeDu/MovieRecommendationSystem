import Vue from 'vue'
import App from './App.vue'
import router from './router'
// import Excels from './excel/outToExcel.js'
// import vueResource from 'vue-resource'
import store from './store'
import './assets/less/index.less';
import './assets/fonts/iconfont.css';
import * as echarts from 'echarts';
import * as xlsx from "xlsx";
import ElementUI from 'element-ui';
import locale from 'element-ui/lib/locale/lang/en' ;
import 'element-ui/lib/theme-chalk/index.css';
import { Message } from 'element-ui';
// import axios from "axios";

// Vue.prototype.outToExcels = Excels;
import afuntion from "./assets/js/afuntion.js";
// Vue.use(vueResource);
Vue.use(ElementUI,{locale});

// Vue.prototype.$echarts = echarts
// Vue.prototype.$excelxlsx = xlsx
Vue.prototype.$afuntion = afuntion
// axios.interceptors.response.use(undefined, function (error) {
//     if (error) {
//         const originalRequest = error.config;
//         if (error.response.status === 401 && !originalRequest._retry) {
//             originalRequest._retry = true;
//             store.dispatch("logout");
//             return router.push("/login");
//         }
//     }
// });

// Vue.config.productionTip = false;
// Vue.prototype.$http = axios
Vue.prototype.$message = Message
Vue.config.productionTip = false
new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')