import axios from 'axios'

axios.interceptors.request.use(
  config => {
      const tokenStr = window.sessionStorage.getItem("cid")
   if (tokenStr) {
     config.headers.common['Authorization'] = "Bearer " + tokenStr
   }
   return config
   })
// axios.defaults.baseURL = 'http://localhost:20016/'


async function httsGet(strUrl, prams) {
  let { data } = await axios.get(strUrl, { params: prams });
  return data;
}
async function httsPost(strUrl, prams) {
  let { data } = await axios.post(strUrl, prams);
  return data;
}

function sdf(e) {
  var year = new Date(e).getFullYear();
  var month = new Date(e).getMonth() + 1;
  var day = new Date(e).getDate();
  var yue = "";
  var days = "";
  if (month < 10) {
    yue = "0" + month;
  } else {
    yue = month;
  }
  if (day < 10) {
    days = "0" + day;
  } else {
    days = day;
  }
  var years = year + "-" + yue + "-" + days;
  return years;
}
function sdf2(time) {
  // var time= new Date();
  var newTime = time.getFullYear() + '-' + (time.getMonth() + 1) + '-' + time.getDate() + ' ' + time.getHours() + ':' + time.getMinutes() + ':' + time.getSeconds();
  return newTime;
}
function readFile(file) {
  return new Promise(resolve => {
    let reader = new FileReader();
    reader.readAsBinaryString(file);
    reader.onload = ev => {
      resolve(ev.target.result);
    }
  })
}
let chaaracter = {}

export default { sdf, httsGet, httsPost, readFile, chaaracter } 