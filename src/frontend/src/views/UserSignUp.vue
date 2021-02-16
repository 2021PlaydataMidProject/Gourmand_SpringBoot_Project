<template>
  <section class="section section-shaped section-lg my-0">
    <div class="text-center">
    
    별점기준과 회원정보 함께 받아야 하는데... 어떻게 구현할지
    - 묶어서 전송 - 일단은 보이는 것 한 개인데 실은 테이블 두개
    - 이후 등록 버튼(submit) 어떻게 매칭할지 알아봐야 함
    - 라다오 버튼, 슬라이드 버튼 적용

    - 아마 일반 페이지들 가입시 '다음페이지' 버튼 넘어가면서 넣는 데이터들이 
      회원들이 보기 쉽게 나눈 척 하지만 
      submit 여러번 시키며
      서로 다른 테이블로 가는 게 아닐까 생각

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
          <tr>
            <td colspan="2" align="center">
              <base-button type="primary" v-on:click="addUser()">회원 등록</base-button>
            </td>
          </tr>
        </table>

슬라이드로 바꿀 것
          <tr>
          <td width="70">맛</td>
          <td align="left">
            <input type="text" v-model="userStandard.uflavor" />
          </td>
        </tr>
        <tr>
          <td width="70">위생</td>
          <td align="left">
            <input type="text" v-model="userStandard.uclean" />
          </td>
        </tr>
        <tr>
          <td width="70">가격대</td>
          <td align="left">
            <input type="text" v-model="userStandard.ucost" />
          </td>
        </tr>
        <tr>
          <td>분위기</td>
          <td align="left">
            <input type="text" v-model="userStandard.umood" size="10" />
          </td>
        </tr>
        <tr>
          <td>서비스</td>
          <td align="left">
            <input type="text" v-model="userStandard.ukindness" size="10" />
          </td>
        </tr>
        <tr>
          <td>접근성</td>
          <td align="left">
            <input type="text" v-model="userStandard.uaccess" size="10" />
          </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
              <base-button type="primary" v-on:click="addUserStandard()">회원 기준 등록</base-button>
            </td>
          </tr>

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
        },
         userStandard :{
            uflavor : "",
            uclean : "",
            upost :  "",
            umood : "",
            ukindness : "",
            uaccess : "",
        }
      }
  },
  methods:{
      addUser: function(){
          console.log("userRegister")
          return this.axios.post('/user/regi', this.newUser)
          .then(res => {
              alert(this.newUser.name + "회원 등록완료");
              location.href="/home"
            });
      },
      addUserStandard: function(){
          console.log("userStandardSign")
          return this.axios.post('/user/regiNewStandard', {"userStandard" :this.userStandard})
          .then(res => {
              alert( this.newUser.name + this.newUser.userStandard + "회원 기준 등록완료");
              //location.href="/home"
            });
      }   

  },
  components: { BaseButton },
};
</script>
<style>
</style>
