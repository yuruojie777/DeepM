import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/views/login.vue'
import Register from "../views/Register"
import Admin from "../views/Admin"
import StudentDetail from "../views/StudentDetail"
import Feedback from "../views/Feedback"
import SetEssay from "../views/SetEssay"
import StudentEssay from "../views/StudentEssay"
import EssayDetail from "../views/EssayDetail"
import TeacherHome from "../views/TeacherHome"

Vue.use(Router)

export default new Router({

  routes: [
    {
      redirect: '/login',
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: '/Register',
      component: Register
    },
    {
      path: '/admin',
      name: '/Admin',
      component: Admin
    },
    {
      path: '/studentDetail',
      name: '/StudentDetail',
      component: StudentDetail
    },
    {
      path: '/feedback',
      name: '/Feedback',
      component: Feedback
    },
    {
      path: '/setEssay',
      name: '/SetEssay',
      component: SetEssay
    },
    {
      path: '/studentEssay',
      name: '/StudentEssay',
      component: StudentEssay
    },
    {
      path: '/essayDetail',
      name: '/EssayDetail',
      component: EssayDetail
    },
    {
      path: '/teacherHome',
      name: '/TeacherHome',
      component: TeacherHome
    },
  ]

})
