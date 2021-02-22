<template>
  <header class="header-global">
    <base-nav
      class="navbar-main"
      transparent
      type="primary"
      effect="light"
      expand
    >
      <router-link slot="brand" class="navbar-brand mr-lg-5" to="/">
        <img src="img/brand/logo.png" alt="logo" />
        구르망
      </router-link>

      <div class="row" slot="content-header" slot-scope="{ closeMenu }">
        <div class="col-6 collapse-brand">
          <img src="img/brand/logo.png" />
          구르망
        </div>
        <div class="col-6 collapse-close">
          <close-button @click="closeMenu"></close-button>
        </div>
      </div>

      <ul class="navbar-nav align-items-lg-center ml-lg-auto">
        <base-dropdown class="nav-item" menu-classes="dropdown-menu-xl">
          <a
            slot="title"
            href="#"
            class="nav-link"
            data-toggle="dropdown"
            role="button"
          >
            <i class="ni ni-ui-04 d-lg-none"></i>
            <span class="nav-link-inner--text">맛집지도</span>
          </a>
          <div class="dropdown-menu-inner">
            <a
              href="/mapforall"
              class="media d-flex align-items-center"
            >
              <div class="media-body ml-3">
                <h6 class="heading text-primary mb-md-1">전체 맛집 지도</h6>
                <p class="description d-none d-md-inline-block mb-0">
                  주변의 맛집을 확인해보세요!
                </p>
              </div>
            </a>
            <a
              href="#"
              @click="mymap()"
              class="media d-flex align-items-center"
            >
              <div class="media-body ml-3">
                <h5 class="heading text-warning mb-md-1">내 맛집 지도</h5>
                <p class="description d-none d-md-inline-block mb-0">
                  자신만의 맛집을 만들어보세요.
                </p>
              </div>
            </a>
          </div>
        </base-dropdown>
          <a
            slot="title"
            href="/guide"
            class="nav-link"
          >
            <i class="ni ni-ui-04 d-lg-none"></i>
            <span class="nav-link-inner--text">구르망가이드</span>
          </a>
          <a
            v-if="!logincheck()"
            slot="title"
            href="/login"
            class="nav-link"
          >
            <i class="ni ni-ui-04 d-lg-none"></i>
            <span class="nav-link-inner--text">로그인</span>
          </a>
          <a
            v-if="logincheck()"
            slot="title"
            href="/mypage"
            class="nav-link"
          >
            <i class="ni ni-ui-04 d-lg-none"></i>
            <span class="nav-link-inner--text">마이페이지</span>
          </a>
        <a
            v-if="logincheck()"
            slot="title"
            href="#"
            @click="logout()"
            class="nav-link"
          >
            <i class="ni ni-ui-04 d-lg-none"></i>
            <span class="nav-link-inner--text">로그아웃</span>
          </a>
      </ul>
    </base-nav>
  </header>
</template>
<script>
import BaseNav from "@/components/BaseNav";
import BaseDropdown from "@/components/BaseDropdown";
import CloseButton from "@/components/CloseButton";
import {deleteCookie} from '../utils/cookies.js';

export default {
  components: {
    BaseNav,
    CloseButton,
    BaseDropdown,
  },
  methods: {
    logincheck: function () {
      if (sessionStorage.getItem("user") == null) {
        return false;
      } else {
        return true;
      }
    },
    logout: function(){
        if (sessionStorage.getItem("user")!=null){
            sessionStorage.removeItem("user");
            deleteCookie();
            this.axios.post("/auth/logout",{})
            location.href="/"
        }
    },
    mymap: function(){
        if (sessionStorage.getItem("user")!=null){
            location.href="http://localhost:3000/mymap";
        //  resregi
        } else{
            location.href="/login";
        }
    }
  },
};
</script>
<style>
</style>
