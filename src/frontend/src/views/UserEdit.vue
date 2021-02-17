<template>
  <section class="section section-shaped section-lg my-0">
    <div class="text-center">
    
    USEREDIT
 탈퇴버튼 

        <table border="1">
          <tr>
            <td width="70">유저ID</td>
            <td align="left">
              <input type="text" v-bind="newUser.user_id" />
            </td>
          </tr>
          <tr>
            <td width="70">이름</td>
            <td align="left">
              <input type="text" v-bind="newUser.name" />
            </td>
          </tr>
          <tr>
            <td width="70">비밀번호</td>
            <td align="left">
              <input type="text" v-bind="newUser.pw" />
            </td>
          </tr>
          <tr>
            <td>생년월일</td>
            <td align="left">
              <input type="text" v-bind="newUser.dob" size="10" />
            </td>
          </tr>
          <tr>
            <td>직업</td>
            <td align="left">
              <input type="text" v-bind="newUser.job" size="10" />
            </td>
          </tr>
          <tr>            
            <td>회원정보 공개(0,1,2)</td>
            <td align="left">
              <input type="text" v-bind="newUser.pageStatus" size="10" />
  
              <!--
                 <input type="radio" id="male" name="gender" value="male">
                <label for="male">Male</label><br>
                <input type="radio" id="female" name="gender" value="female">
                <label for="female">Female</label><br>
                <input type="radio" id="other" name="gender" value="other">
                <label for="other">Other</label>
               -->
            </td>
          </tr>
          <tr>
            <td>사진 업로드</td>
            <td align="left">
              <input type="file" ref="imgdata" name="imgdata[]" accept="image/*" multiple="multiple" />
          </td>
          </tr>
 <!--     <tr>
          <td colspan="2" align="center">
              <base-button type="primary" v-on:click="addUser()">회원 등록</base-button>
            </td>
          </tr>
        </table>

슬라이드로 바꿀 것
        
        <table border="1"> -->
          <tr>
          <td width="70">맛</td>
          <td align="left">
            <input type="text"  v-bind="userStandard.uFlavor" />
          </td>
        </tr>
        <tr>
          <td width="70">위생</td>
          <td align="left">
            <input type="text"  v-bind="userStandard.uClean" />
          </td>
        </tr>
        <tr>
          <td width="70">가격대</td>
          <td align="left">
            <input type="text"  v-bind="userStandard.uCost" />
          </td>
        </tr>
        <tr>
          <td>분위기</td>
          <td align="left">
            <input type="text"  v-bind="userStandard.uMood" size="10" />
          </td>
        </tr>
        <tr>
          <td>서비스</td>
          <td align="left">
            <input type="text"  v-bind="userStandard.uKindness" size="10" />
          </td>
        </tr>
        <tr>
          <td>접근성</td>
          <td align="left">
            <input type="text"  v-bind="userStandard.uAccess" size="10" />
          </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
              <base-button type="primary" v-on:click="addUserStandard()">회원 가입 완료</base-button>
            </td>
          </tr>
        </table>
    </div>

  </section>
</template>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
import BaseButton from '../components/BaseButton.vue';
import flatPicker from "vue-flatpickr-component";
import "flatpickr/dist/flatpickr.css";

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
        },
         userStandard :{
            uFlavor : "",
            uClean : "",
            uCost :  "",
            uMood : "",
            uKindness : "",
            uAccess : "",
        }
      }
    },

  methods : {
      addUserStandard: function(){
          console.log("userStandardSign")
          return this.axios.post('/user/regiNewStandard', this.userStandard)
          .then(res => {
              this.addUser();
            });
          },
        addUser: function(){
        const formData = new FormData();
           formData.append("user", JSON.stringify(this.newUser));
          for (let i=0; i<this.$refs.imgdata.files.length; i++){
            formData.append('userImg', this.$refs.imgdata.files[i])
          }
          return this.axios.post('/user/regi', formData, {
            headers: {
              "Content-Type": 'multipart/form-data'
            }
          })
          .then(res => {
              console.log(this.newUser.user_id + "회원 등록완료");
             })
             .catch(function(){
              console.log('FAILURE!!');
              location.href="/home"
});
          }
        },
  components: { "BaseButton" : BaseButton }
}

</script>
<style>
</style>