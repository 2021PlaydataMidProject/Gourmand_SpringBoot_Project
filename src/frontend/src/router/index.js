import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  // {
  //   path: '/',
  //   name: 'Home',
  //   component: Home
  // },
  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // },
  {
    path: '/',
    redirect: '/login',
  },
  {
    path: '/login',
    component: () => import('@/views/Login.vue'),
  },
  // @/views/LoginPage.vue
  {
    path: '/signup',
    component: () => import('@/views/Register.vue'),
  },
  {
    path: '/main',
    component: () => import('@/views/Home.vue'),
    meta: { auth: true },
  },
  {
    path: '/add',
    component: () => import('@/views/PostAddPage.vue'),
    meta: { auth: true },
  },
  {
    path: '/post/:id',
    component: () => import('@/views/PostEditPage.vue'),
    meta: { auth: true },
  },
  // {
  //   path: '/mymap',
  //   component: () => import('@/views/MyMap.vue'),
  // },
  {
    path: '*',
    component: () => import('@/views/NotFoundPage'),
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
