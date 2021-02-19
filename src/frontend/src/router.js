import Vue from "vue";
import Router from "vue-router";
import AppHeader from "./layout/AppHeader";
import AppHeaderOther from "./layout/AppHeaderOther";
import AppFooter from "./layout/AppFooter";
import Components from "./views/Components.vue";
import Landing from "./views/Landing.vue";
import Login from "./views/Login.vue";
import Register from "./views/Register.vue";
import Profile from "./views/Profile.vue";
import Home from "./views/Home.vue";
import ResTest from "./views/ResTest.vue";
import UserSignUp from "./views/UserSignUp.vue";
import MapForAll from "./views/MapForAll.vue";
import ResPage from "./views/ResPage.vue";
import UserEdit from "./views/UserEdit.vue";
import MyPage from "./views/MyPage.vue";
import StarRating from "./views/StarRating.vue";
import MapForAllSearch from "./views/MapForAllSearch.vue"
import GourmandGuide from "./views/GourmandGuide.vue"
import RevRegi from "./views/RevRegi.vue"

Vue.use(Router);

export default new Router({
  linkExactActiveClass: "active",
  mode: 'history',
  routes: [
    {
      path: "/",
      name: "mainpage",
      components: {
        header: AppHeaderOther,
        default: Home,
        footer: AppFooter
      }
    },
    {
      path: "/components",
      name: "components",
      components: {
        header: AppHeaderOther,
        default: Components,
        footer: AppFooter
      }
    },
    {
      path: "/landing",
      name: "landing",
      components: {
        header: AppHeaderOther,
        default: Landing,
        footer: AppFooter
      }
    },
    {
      path: "/login",
      name: "login",
      components: {
        header: AppHeader,
        default: Login,
        footer: AppFooter
      }
    },
    {
      path: "/register",
      name: "register",
      components: {
        header: AppHeader,
        default: Register,
        footer: AppFooter
      }
    },
    {
      path: "/profile",
      name: "profile",
      components: {
        header: AppHeader,
        default: Profile,
        footer: AppFooter
      }
    },
    {
      path: "/resregi",
      name: "resregi",
      components: {
        header: AppHeader,
        default: ResTest,
        footer: AppFooter
      }
    },
    {
      path: "/userregi",
      name: "userregi",
      components: {
        header: AppHeaderOther,
        default: UserSignUp,
        footer: AppFooter
      }
    },
    {
      path: "/mapforall",
      name: "MapForAll",
      components: {
        header: AppHeaderOther,
        default: MapForAll,
        footer: AppFooter
      }
    },
    {
      path: "/search",
      name: "Serch",
      components: {
        header: AppHeaderOther,
        default: MapForAllSearch,
        footer: AppFooter
      }
    },
    {
      path: "/respage",
      name: "ResPage",
      components: {
        header: AppHeaderOther,
        default: ResPage,
        footer: AppFooter
      }
    },
    {
      path: "/respage/review/write",
      name: "RevRegi",
      components: {
        header: AppHeaderOther,
        default: RevRegi,
        footer: AppFooter
      }
    },
    {
      path: "/useredit",
      name: "userEdit",
      components: {
        header: AppHeaderOther,
        default: UserEdit,
        footer: AppFooter
      }
    },
    {
      path: "/mypage",
      name: "myPage",
      components: {
        header: AppHeader,
        default: MyPage,
        footer: AppFooter
      }
    },
    {
      path: "/starrating",
      name: "starRating",
      components: {
        header: AppHeaderOther,
        default: StarRating,
        footer: AppFooter
      }
    },
    {
      path: "/guide",
      name: "guide",
      components: {
        header: AppHeader,
        default: GourmandGuide,
        footer: AppFooter
      }
    }
  ],
  scrollBehavior: to => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  }
})