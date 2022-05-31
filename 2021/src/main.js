import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import echarts from 'echarts'
//需要挂载到Vue原型上
Vue.prototype.$echarts = echarts

//添入全局样式
import 'element-ui/lib/theme-chalk/index.css';

//导入axios
import axios from "axios"

import './plugins/element.js'
//挂载axios
Vue.prototype.$http = axios
//设置访问根路径
axios.defaults.baseURL = "http://localhost:9000"

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

//全局随机数函数
Vue.prototype.getRandom = function (Min,Max){//getRandom是函数名
    let Range = Max - Min;
    let Rand = Math.random();
    return(Min + Math.round(Rand * Range));
}
