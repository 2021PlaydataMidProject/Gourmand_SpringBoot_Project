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
                <small>Sign in with</small>
              </div>
              <div class="btn-wrapper text-center">
                <base-button type="neutral">
                  <img slot="icon" src="img/icons/common/github.svg" />
                  Github
                </base-button>

                <base-button type="neutral">
                  <img slot="icon" src="img/icons/common/google.svg" />
                  Google
                </base-button>
              </div>
            </template>
            <template>
              <div class="text-center text-muted mb-4">
                <small>Or sign in with credentials</small>
              </div>
              <form role="form">
                <base-input
                  alternative
                  class="mb-3"
                  placeholder="Email"
                  addon-left-icon="ni ni-email-83"
                  v-model="id"
                >
                </base-input>
                <base-input
                  alternative
                  type="password"
                  placeholder="Password"
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
              <p class="log">{{ logMessage }}</p>
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
      //log
      logMessage: "",
    };
  },
  methods: {
    async login() {
      try {
        const userData = {
          user_id: this.id,
          pw: this.pw,
        };
        // response.data = await loginUser(userData);
        // console.log(response.data) 
        //await this.$store.dispatch('LOGIN', userData);
      }catch(error) {
        console.log(error.response.data);
      }finally{
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
      }
    },
  },
};
</script>
<style>
</style>
