import Vue from "vue";
import App from "./App";
import router from "./router";
import store from "./store";
import axios from "axios";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";

axios.interceptors.response.use(undefined, function (error) {
    if (error) {
        const originalRequest = error.config;
        if (error.response.status === 401 && !originalRequest._retry) {
            originalRequest._retry = true;
            store.dispatch("logout");
            return router.push("/login");
        }
    }
});

Vue.config.productionTip = false;
Vue.use(ElementUI);
Vue.prototype.$http = axios
new Vue({
    router,
    store,
    render: h => h(App)
}).$mount("#app");