// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'

Vue.config.productionTip = false
Vue.use(ElementUI);
var axios = require('axios')
Vue.prototype.$axios = axios
<<<<<<< HEAD
// axios.defaults.baseURL = 'https://7433-119-28-62-29.ngrok.io/deepm'
=======
>>>>>>> 4702fba1f6de20f11d53ddc53cd1c4e5126dc84b
axios.defaults.baseURL = 'https://de87-119-28-62-29.ngrok.io/deepm'
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  render: h => h(App)
})
