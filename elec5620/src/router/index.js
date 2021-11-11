import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/views/login.vue'
import Register from "../views/Register"
import Admin from "../views/Admin"
import EssayDetail from "../views/EssayDetail";
import StudentDetail from "../views/StudentDetail";
import Feedback from "../views/Feedback";
import SetEssay from "../views/SetEssay";
import StudentEssay from "../views/StudentEssay";
import TeacherHome from "../views/TeacherHome";
import SubmissionList from "../views/SubmissionList";
import SubmitEssay from "../views/SubmitEssay";
import UpcomingEssay from "../views/UpcomingEssay";
import StudentHome from "../views/StudentHome";

Vue.use(Router)

const router = new Router({
  mode: 'history',
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
    {
      path:'/admin',
      name:'/Admin',
      meta: {
        requireAuth: true
      },
      component:Admin
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
      meta: {
        requireAuth: true
      },
      component: TeacherHome
    },
    {
      path: '/submissionList',
      name: '/SubmissionList',

      component: SubmissionList
    },
    {
      path: '/submitEssay',
      name: '/SubmitEssay',
      component: SubmitEssay
    },
    {
      path: '/upcomingEssay',
      name: '/UpcomingEssay',
      component: UpcomingEssay
    },
    {
      path: '/studentHome',
      name: '/StudentHome',
      component: StudentHome
    }
    ]

})

router.beforeEach((to, from, next) => {
  var role = localStorage.getItem('role')
  if (to.meta.requireAuth) {
    if (role == 2) {
      if (to.path !== "/admin") {
        alert("You cannot go these page!")
      } else {
        next()
      }
    } else if (role == 1) {
      if (to.path === "/admin") {
        alert("You cannot go these page!")
      } else {
        next()
      }
    } else if (role == 0) {
      if (to.path === "/admin") {
        alert("You cannot go these page!")
      } else {
        next()
      }
    } else {
      alert("Please log in first!!!")
      next({
        path: '/login'
      })
    }
  } else {
    next()
  }
})

export default router;
