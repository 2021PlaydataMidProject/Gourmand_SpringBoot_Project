<template>
  <section class="section section-shaped section-lg my-0">
    <div class="text-center">
        <table border="1">
          <tr>
            <td width="70">유저ID</td>
            <td align="left">
              <input type="text" v-model="newUser.user_id" />
            </td>
          </tr>
          <tr>
            <td width="70">이름</td>
            <td align="left">
              <input type="text" v-model="newUser.name" />
            </td>
          </tr>
          <tr>
            <td width="70">비밀번호</td>
            <td align="left">
              <input type="text" v-model="newUser.pw" />
            </td>
          </tr>
          <tr>
            <td>생년월일</td>
            <td align="left">
              <input type="text" v-model="newUser.dob" size="10" />
            </td>
          </tr>
          <tr>
            <td>직업</td>
            <td align="left">
              <input type="text" v-model="newUser.job" size="10" />
            </td>
          </tr>
          <tr>
            <td>회원정보 공개(0,1,2)</td>
            <td align="left">
              <input type="text" v-model="newUser.pageStatus" size="10" />
            </td>
          </tr>
          <tr>
            <td>사진 업로드</td>
            <td align="left">
              <input type="text" size="10" />
            </td>
          </tr>
          <tr>
            <td colspan="2" align="center">
              <base-button type="primary" @click="addUser">회원 등록</base-button>
            </td>
          </tr>
        </table>
    </div>

  </section>
</template>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
import BaseButton from '../components/BaseButton.vue';
export default {
  data: function(){
      return {
        newUser:{
            user_id : "",
            name : "",
            pw :  "",
            dob : "",
            job : "",
            pageStatus : "",

        }
      }
  },
  methods:{
      addUser: function(){
          console.log("userSignUp")
          return this.axios.post('/auth/signup', this.newUser)
          .then(res => {
              alert(this.newUser.user_name + "회원 등록완료");
              location.href="/home"
            });
      }
  },
  components: { BaseButton },
};
</script>
<style>
</style>
