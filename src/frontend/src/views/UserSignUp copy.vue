<template>
  <section class="section section-shaped section-lg my-0">
    <div class="text-center">

        <table border="1">
          <tr>
            <td width="70">유저ID</td>
            <td align="left">
              <input type="text" v-model="newUser.userId" />
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
            userId : "",
            name : "",
            pw :  "",
            dob : "",
            job : "",
            pageStatus : "",
        },

         userStandard :{
            uflavor : "",
            uclean : "",
            ucost :  "",
            umood : "",
            ukindness : "",
            uaccess : "",  
        }
      }
  },
  methods:{
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
          .then(user => {
               alert(this.newUser.userId + "회원 등록완료");
               //location.href="/home"
             });
      },
      addUserStandard: function(){
         
          this.userStandard.uflavor = parseFloat(this.userStandard.uflavor);
          this.userStandard.uclean = parseFloat(this.userStandard.uclean);
          this.userStandard.ucost = parseFloat(this.userStandard.ucost);
          this.userStandard.umood = parseFloat(this.userStandard.umood);
          this.userStandard.ukindness = parseFloat(this.userStandard.ukindness);
          this.userStandard.uaccess = parseFloat(this.userStandard.uaccess);  
          console.log(this.userStandard);
          this.axios.post('/user/regiNewStandard', this.userStandard)         
          .then(res => {
              alert( this.newUser.userId + "회원 기준 등록완료");
              this.addUser();

            });
      }   
  },
  components: { BaseButton },
};
</script>
<style>
</style>