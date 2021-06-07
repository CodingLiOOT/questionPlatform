import Vue from 'vue'
import Router from 'vue-router'
import Home from "../components/Home"
import index from '../components/index'
import store from "../vuex";
import MainPage from "../components/MainPage";
import Upload from "../components/Upload";
import List from "../components/List";
import ReportDetail from "../components/ReportDetail";
import Company from "../components/company/Company";
import Evaluate from "../components/company/Evaluate";
import ExpertsList from "../components/company/ExpertsList";
import Edit from "../components/company/Edit";
import JudgeList from "../components/company/JudgeList";
import AllocateExpert from "../components/company/AllocateExpert";
import InviteExpert from "../components/company/InviteExpert";


Vue.use(Router)

/**
 * 刷新页面时，重新赋值token
 */
if (sessionStorage.getItem('token')) {
  store.commit('login', {
    user: sessionStorage.getItem('user'),
    token: sessionStorage.getItem('token')
  });
}

const router = new Router({
  mode: 'history',
  routes: [
    // {
    //   path: '/try',
    //   name: 'Home',
    //   component: tryFile
    // },
    {
      path: '/',
      name: 'Login',
      component: () => import("../components/login")
    },
    {
      path: '/home',
      name: 'Home',
      component: () => import("../components/Home")
    },
    {
      path: '/index',
      name: 'index',
      meta: {requiresAuth: true},
      component: () => import("../components/index")
    },
    {
      path: '/login',
      name: 'login',
      meta: {requiresAuth: false},
      component: () => import("../components/login")
    },
    {
      path: '/register',
      name: 'register',
      component: () => import("../components/Register")
    },
    {
      path: '/MainPage',
      name: 'MainPage',
      redirect: '/MainPage/Upload',
      component: MainPage,
      children: [
        {
          path: 'Upload',
          name: 'Upload',
          component: Upload,
        },
        {
          path: 'List',
          name: 'List',
          component: List,
        },
        {
          path: 'ReportDetail',
          name: 'ReportDetail',
          component: ReportDetail,
        }
      ]
    },
    {
      path: '/Company',
      name:'Company',
      redirect:'/Company/Evaluate',
      component:Company,
      children:[
        {
          path:'Evaluate',
          name:'Evaluate',
          component:Evaluate,
        },
        {
          path:'ExpertsList',
          name:'ExpertsList',
          component:ExpertsList,
        },
        {
          path:'Edit',
          name:'Edit',
          component:Edit,
        },
        {
          path: 'JudgeList',
          name: 'JudgeList',
          component: () => import("../components/company/JudgeList")
        },
        {

          path: 'JudgeList',
          name: 'JudgeList',
          component: () => import("../components/company/JudgeList")
        },
        {
          path:'AllocateExpert',
          name:'AllocateExpert',
          component:AllocateExpert,
        },
        {
          path: 'ShowJudgement',
          name: 'ShowJudgement',
          component: () => import("../components/company/ShowJudgement")
        },
        {
          path:'AllocateExpert',
          name:'AllocateExpert',
          component:AllocateExpert,
        },
        {
          path:'Invite',
          name:'Invite',
          component:InviteExpert,
        }
      ]
    },
    {
      path: '/fileDemo',
      name: 'fileDemo',
      component: () => import("../components/FileDemo")
    },
    {
      path: '/ExpertInvitation',
      name: 'ExpertInvitation',
      component: () => import("../components/expert/ExpertInvitation")
    },
    {
      path: '/ExpertMainPage',
      name: 'ExpertMainPage',
      redirect: '/ExpertMainPage/ExpertRating',
      component: () => import("../components/expert/ExpertMainPage"),
      children: [
        {
          path: 'ExpertRating',
          name: 'ExpertRating',
          component: () => import("../components/expert/ExpertRating"),
        },
        {
          path: 'ExpertRated',
          name: 'ExpertRated',
          component: () => import("../components/expert/ExpertRated"),
        },
        {
          path: 'ExpertHome',
          name: 'ExpertHome',
          component: () => import("../components/expert/ExpertHome"),
        },
        {

          path: 'ExpertToRate',
          name: 'ExpertToRate',
          component: () => import("../components/expert/ExpertToRate"),
        },

        {
          path: 'ShowScoreDetails',
          name: 'ShowScoreDetails',
          component: () => import("../components/expert/ShowScoreDetails"),
        },
        {
          path: 'EditScore',
          name: 'EditScore',
          component: () => import("../components/expert/EditScore"),
        },

      ]
    },
  ],
})
router.beforeEach((to, from, next) => {
  if (to.matched.some((r) => r.meta.requiresAuth)) {
    if (store.state.token) {
      next();
    } else {
      next({
        path: '/login',
        query: {redirect: to.fullPath}
      });
    }
  } else {
    next();
  }
})

export default router
