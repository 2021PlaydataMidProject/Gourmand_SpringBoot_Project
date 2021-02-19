<template>
  <header class="header-global">
    <base-nav
      class="navbar-home"
      transparent
      type="primary"
      effect="light"
      expand
      containerSize="10"
    >
      <router-link slot="brand" class="navbar-brand mr-lg-1" to="/">
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

      <div class="navbar-nav align-items-lg-center col-lg-8">
        <b-dropdown
          :text="dropDownText"
          class="my-3 col-lg-2 col-sm-2"
          variant="primary"
        >
          <b-dropdown-item @click="drop('음식점')">음식점</b-dropdown-item>
          <b-dropdown-item @click="drop('지역')">지역</b-dropdown-item>
        </b-dropdown>
        <base-input
          class="my-3 col-lg-8 col-sm-8"
          :placeholder="dropDownText + ' 검색하기'"
          v-model="name"
        >
        </base-input>
        <base-button
          @click="search()"
          type="primary"
          class="my-3 col-lg-2 col-sm-2"
          >Search</base-button
        >
      </div>
  
      <ul class="navbar-nav align-items-lg-center">
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
            <a href="/mapforall" class="media d-flex align-items-center">
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
        <a slot="title" href="#" class="nav-link">
          <i class="ni ni-ui-04 d-lg-none"></i>
          <span class="nav-link-inner--text " >구르망가이드</span>
        </a>
        <a v-if="!logincheck()" slot="title" href="/login" class="nav-link">
          <i class="ni ni-ui-04 d-lg-none"></i>
          <span class="nav-link-inner--text ">로그인</span>
        </a>
        <a v-if="logincheck()" slot="title" href="#" class="nav-link">
          <i class="ni ni-ui-04 d-lg-none"></i>
          <span class="nav-link-inner--text ">마이페이지</span>
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
import { BCarousel } from "bootstrap-vue/esm/components/carousel/carousel";
import { BCarouselSlide } from "bootstrap-vue/esm/components/carousel/carousel-slide";
import { BDropdown } from "bootstrap-vue";
import { BDropdownItem } from "bootstrap-vue";

export default {
  components: {
    BaseNav,
    CloseButton,
    BaseDropdown,
    BCarousel,
    BCarouselSlide,
    BDropdown,
    BDropdownItem,
  },
  data(){
    return{
      dropDownText:"음식점"
    }
  },
  methods: {
    logincheck: function () {
      if (sessionStorage.getItem("user") == null) {
        return false;
      } else {
        return true;
      }
    },
    logout: function () {
      if (sessionStorage.getItem("user") != null) {
        sessionStorage.removeItem("user");
        location.href = "/";
      }
    },
    mymap: function () {
      if (sessionStorage.getItem("user") != null) {
        location.href = "/resregi";
      } else {
        location.href = "/login";
      }
    },
    search() {
      if (this.name) {
        if (this.dropDownText == "음식점") {
          this.$router.push({ path: "/search", query: { name: this.name } });
        } else {
          this.$router.push({
            path: "/search",
            query: { location: this.name },
          });
        }
      }
    },
    update(value) {
      this.name = value;
    },
    drop(value) {
      this.dropDownText = value;
    },
  },
};
</script>
<style>
</style>
