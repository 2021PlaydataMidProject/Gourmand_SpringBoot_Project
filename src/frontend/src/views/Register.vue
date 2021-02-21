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
                    <card type="secondary" shadow
                          header-classes="bg-white pb-5"
                          body-classes="px-lg-5 py-lg-5"
                          class="border-0">
                        <template>
                            <div class="text-muted text-center mb-3">
                                <small>Sign in with</small>
                            </div>
                            <form role="form">
                                <base-input alternative
                                            class="mb-3"
                                            placeholder="이름" 
                                            addon-left-icon="ni ni-hat-3"
                                            v-model="user.name">
                                </base-input>
                                <base-input alternative
                                            class="mb-3"
                                            placeholder="Email"
                                            addon-left-icon="ni ni-email-83">
                                </base-input>
                                <base-input alternative
                                            type="password"
                                            placeholder="Password"
                                            addon-left-icon="ni ni-lock-circle-open">
                                </base-input>
                                <div class="text-muted font-italic">
                                    <small>password strength:
                                        <span class="text-success font-weight-700">strong</span>
                                    </small>
                                </div>
                                <base-checkbox>
                                    <span>I agree with the
                                        <a href="#">Privacy Policy</a>
                                    </span>
                                </base-checkbox>
                                <div class="text-center">
                                    <base-button type="primary" class="my-4">Create account</base-button>
                                </div>
                            </form>
                        </template>
                    </card>
                </div>
            </div>
        </div>
    </section>
</template>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>


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
            sessionStorage.setItem("user", res.data.user_num);
            console.log(sessionStorage.getItem("user"))

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
