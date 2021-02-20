import Vue from 'vue';
import Vuex from 'vuex';
import {
  saveAuthToCookie,
  saveUserIdToCookie,
  saveUserNumToCookie,
  saveUserNameToCookie,

  getAuthFromCookie,
  getUserIdFromCookie,
  getUserNumFromCookie,
  getUserNameFromCookie,
  deleteCookie,
  } 
from '@/utils/cookies';
import { loginUser } from '@/auth/login';


Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    username: getUserFromCookie() || '',
    token: getAuthFromCookie() || '',
  },
  getters: {
    isLogin(state) {
      return state.username !== '';
    },
  },
  mutations: {
    setUsername(state, username) {
      state.username = username;
    },
    clearUsername(state) {
      state.username = '';
    },
    setToken(state, token) {
      state.token = token;
    },
    clearToken(state) {
      state.token = '';
    },
  },
  actions: {
    async LOGIN({ commit }, userData) {
      Response.data = await loginUser(userData);
      console.log(data);
      // commit('setToken', data.token);
      // commit('setUsername', data.user.username);
      // saveAuthToCookie(data.token);
      // saveUserToCookie(data.user.username);
      return data;
    },
  },
});
