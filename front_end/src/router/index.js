import Vue from 'vue'
import Router from 'vue-router'
import Home from "../components/Home"
import index from '../components/index'
import store from "../vuex";
import MainPage from "../components/MainPage";
import Upload from "../components/Upload";
import List from "../components/List";
import ReportDetail from "../components/ReportDetail";

Vue.use(Router)

/**
 * 刷新页面时，重新赋值token
 */
if (sessionStorage.getItem('token')) {
  store.commit('login',{
    user:sessionStorage.getItem('user'),
    token:sessionStorage.getItem('token')
  });
}

const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: () => import("../components/Home")
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
      name:'MainPage',
      redirect:'/MainPage/Upload',
      component:MainPage,
      children:[
        {
          path:'Upload',
          name:'Upload',
          component:Upload,
        },
        {
          path:'List',
          name:'List',
          component:List,
        },
        {
          path:'ReportDetail',
          name:'ReportDetail',
          component:ReportDetail,
        }
      ]
    },
    {
      path: '/fileDemo',
      name:'fileDemo',
      component:()=>import("../components/FileDemo")
    }
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
