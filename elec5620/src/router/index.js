import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/views/login.vue'
import Register from "../views/Register";

Vue.use(Router)

export default new Router({

  routes: [
    {
      redirect:'/login',
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path:'/login',
      name:'Login',
      component:Login
    },
    {
      path:'/register',
      name:'/Register',
      component:Register
    },
  ]
})
