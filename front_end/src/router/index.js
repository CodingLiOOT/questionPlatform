import Vue from 'vue'
import Router from 'vue-router'
import Home from "../components/Home"
import index from '../components/index'
import store from "../vuex";

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
