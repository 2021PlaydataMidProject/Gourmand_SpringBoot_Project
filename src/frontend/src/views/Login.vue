<template>
  <section class="section section-shaped section-lg my-0">
    <div class="shape shape-style-1 bg-gradient-default">
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
    </div>
    <div class="container pt-lg-md">
      <div class="row justify-content-center">
        <div class="col-lg-5">
          <card
            type="secondary"
            shadow
            header-classes="bg-white pb-5"
            body-classes="px-lg-5 py-lg-5"
            class="border-0"
          >
            <template>
              <div class="text-muted text-center mb-3">
               <img src="img/brand/logo.png" alt="logo" width="200" /><br>
                <small>맛잘알들의 집합소,<br>
                     '구르망'에 오신 것을 환영합니다!</small>
              </div>
            </template>
            <template>
              <div class="text-center text-muted mb-4">

              </div>
              <form role="form">
                <base-input
                  alternative
                  class="mb-3"
                  placeholder="아이디"
                  addon-left-icon="ni ni-email-83"
                  v-model="id"
                >
                </base-input>
                <base-input
                  alternative
                  type="password"
                  placeholder="비밀번호"
                  addon-left-icon="ni ni-lock-circle-open"
                  v-model="pw"
                >
                </base-input>
                <base-checkbox> Remember me </base-checkbox>
                <div class="text-center">
                  <base-button type="primary" class="my-4" @click="login()"
                    >Sign In</base-button
                  >
                </div>
              </form>
            </template>
          </card>
          <div class="row mt-3">
            <div class="col-6">
              <a href="#" class="text-light">
                <small>Forgot password?</small>
              </a>
            </div>
            <div class="col-6 text-right">
              <a href="#" class="text-light">
                <small>Create new account</small>
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>

import 
  {
  saveAuthToCookie,
  saveUserIdToCookie,
  saveUserNumToCookie,
  saveUserNameToCookie,

  getAuthFromCookie,
  getUserIdFromCookie,
  getUserNumFromCookie,
  getUserNameFromCookie,
  
  deleteCookie,
} from '../utils/cookies';

export default {
  data: function () {
    return {
      id: "",
      pw: "",
    };
  },
  methods: {
    login: function () {
      return this.axios
        .post("/auth/login", {
          user_id: this.id,
          pw: this.pw,
        })
        .then((res) => {
          if (res.data) {
            console.log(res);
            sessionStorage.setItem("user", true);
            saveUserNumToCookie(res.data.user_num);
            saveUserIdToCookie(res.data.user_id);
            saveUserNameToCookie(res.data.name);

            location.href = "/";
          } else {
            alert("없는 아이디거나 비밀번호가 맞지 않습니다.");
          }
        })
        .catch((error) => {
          alert("서버 오류입니다. 다시 시도해주세요.");
        });
    },
  },
};
</script>
<style>
</style>
