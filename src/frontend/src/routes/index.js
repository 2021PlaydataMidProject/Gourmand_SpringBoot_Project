import Vue from 'vue';
import VueRouter from 'vue-router';
import store from '@/store';

// import LoginPage from '@/views/LoginPage.vue';
// import SignupPage from '@/views/SignupPage';

Vue.use(VueRouter);

//라우터 네비게이션 사용하기 위해 export default를 const router로 수정
const router = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: '/login',
    },
    {
      path: '/login',
      component: () => import('@/views/LoginPage.vue'),
    },
    {
      path: '/signup',
      component: () => import('@/views/SignupPage.vue'),
    },
    {
      path: '/main',
      component: () => import('@/views/MainPage.vue'),
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
    {
      path: '/mymap',
      component: () => import('@/views/MyMap.vue'),
    },
    {
      path: '*',
      component: () => import('@/views/NotFoundPage'),
    },

  ],
});

// eslint-disable-next-line no-unused-vars
router.beforeEach((to, from, next) => {
  //인증이 필요한 페이지이면서 사용자가 로그인하지 않았을때
  if (to.meta.auth && !store.getters.isLogin) {
    console.log('인증이 필요합니다');
    next('/login');
    return;
  }
  //console.log(to); //로그 출력
  next(); // 다음 페이지로 이동
});

export default router;
