import Vue from 'vue'

import Vuex from 'vuex'
Vue.use(Vuex)

const store = new Vuex.Store({
  state:{
    user:undefined,
    token:undefined,
    expert:undefined,
  },
  mutations:{
    login(state,payload){
      state.user=payload.user;
      state.token=payload.token;
      sessionStorage.token = payload.token;
      sessionStorage.user=payload.user;
    },
    expertLogin(state,payload){
      state.expert=payload.expert;
      state.token=payload.token;
      sessionStorage.token = payload.token;
      sessionStorage.expert=payload.expert;
    },
    logout(state){
      state.user=undefined;
      state.token=undefined;
      sessionStorage.removeItem('token');
      sessionStorage.removeItem('user');
    },
    setToken(state,payload){
      state.token=payload;
    },
    removeToken(state){
      state.token='';
    }
  },
  actions:{
    login(context,payload){
      context.commit('login',payload)
    },
    logout(context,payload){
      context.commit('logout',payload)
    },
    setToken(context,payload){
      context.commit('login',payload);
    },
    removeToken(context,payload){
      context.commit('logout',payload);
    }
  }
})

export default store
