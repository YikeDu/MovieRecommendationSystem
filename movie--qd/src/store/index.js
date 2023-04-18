import Vue from "vue";
import Vuex from 'vuex';
Vue.use(Vuex)
const actions={}
const mutations={
  JJJ(conctext,value){
    console.log(conctext)
    console.log(value)
    state.sum=11111
  },
  Zhang(conctext,value){
    state.sum=value
  },
  osearch(conctext,value){
    if (value!=''){
      state.searchStatus=value
    }
    getters.closeopullDownstatus()
  },
  ohome(conctext,value){
    getters.closeSearchStatus()
    getters.closeopullDownstatus()
  },
  opullDownstatus(){
    state.pullDownstatus=!state.pullDownstatus;
    getters.closeSearchStatus()
  },
  admin(conctext,value){
    console.log("value", value);
    state.admin=value
  },
  cadmin(){
    state.admin= {
      email:'Not logged in'
    }
  },
  mtypes(conctext,value){
    console.log("这里是打印", );
    state.mtypes=value
  },
}
const state={
  mtypes:'',
  admin:{},
  sum:1,
  searchStatus:"",
  pullDownstatus: false,
}
const getters={
  bigSum(state){
    console.log(state)
    state.sum+=1
    return state
  },
  closeSearchStatus(){
    state.searchStatus=false
  },
  closeopullDownstatus(){
    state.pullDownstatus=false;
  },
}

Vue.use(Vuex)
export default new Vuex.Store({
  // modules: {
  //   tab
  // }
  actions,
  mutations,
  state,
  getters
})