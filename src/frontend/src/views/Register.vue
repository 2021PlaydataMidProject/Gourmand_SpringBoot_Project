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
                               <img src="img/brand/logo.png" alt="logo" width="200" /><br>
                                <small>맛잘알들의 집합소 '구르망',<br>
                                당신을 구르망의 세계로 초대합니다.</small>
                            </div>
                                   <form role="form">

                                <base-input alternative
                                            class="mb-3"
                                            placeholder="아이디"
                                            type="text"
                                            v-model="newUser.userId"
                                            addon-left-icon="ni ni-email-83">
                                </base-input>

                                <base-input alternative
                                            class="mb-3"
                                            type="password"
                                            placeholder="비밀번호"
                                            v-model="newUser.pw"
                                            addon-left-icon="ni ni-lock-circle-open">
                                </base-input>
                                <div class="text-muted font-italic">
                                    <small>password strength:
                                        <span class="text-success font-weight-700">strong</span>
                                    </small>
                                </div>
                                 <hr>
                                
                              <div>
                               프로필 사진
              <input type="file" ref="imgdata" name="imgdata[]" accept="image/*" multiple="multiple" />
         </div>  <br>              
          
                                이름
                                <base-input alternative
                                            class="mb-3"
                                            placeholder="이름" 
                                            type="text"
                                            v-model="newUser.name"
                                            addon-left-icon="ni ni-single-02"
                                            >
                                </base-input>

                               생일
                               <base-input alternative                              
                                            class="mb-3"
                                            type="date"
                                            v-model="newUser.dob"
                                            >
                                </base-input>

                               <base-input class="col-md-20" label="직업">
                               <select id="inputState" class="form-control">
                                      <option selected>직업을 선택하세요</option>
                                      <option>학생</option>
                                      <option>컴퓨터/인터넷</option>
                                      <option>언론</option>
                                      <option>공무원</option>
                                      <option>군인</option>
                                      <option>서비스업</option>
                                      <option>교육</option>
                                      <option>금융/증권/보험업</option>
                                      <option>유통업</option>
                                      <option>예술</option>
                                      <option>의료</option>
                                    </select>
                              </base-input>
                             <p>
                             <br>
                             <hr>
                             당신의 음식점 선택 기준을 알려주세요!<br>
                             당신 취향에 딱 맞는 음식점을 추천해 드리는 데에만 사용됩니다!<br> 
                             <p><p>
                             <table>
                                    <tr>
                                      <td width="70">맛 :</td>
                                      <td align="left">
                                        <star-rating
                                            v-model="userStandard.uflavor"
                                            :show-rating="true"
                                            @hover:rating="mouseOverRating = $event"
                                            :increment="0.5"
                                            :starSize="20"
                                          ></star-rating>
                                      </td>
                                    </tr>
                                    <tr>
                                      <td width="70">위생 :</td>
                                      <td align="left">
                                        <star-rating
                                            v-model="userStandard.uclean"
                                            :show-rating="true"
                                            @hover:rating="mouseOverRating = $event"
                                            :increment="0.5"
                                            :starSize="20"
                                          ></star-rating>
                                      </td>
                                    </tr>
                                    <tr>
                                      <td width="70">가격대 :</td>
                                      <td align="left">
                                      <star-rating
                                            v-model="userStandard.ucost"
                                            :show-rating="true"
                                            @hover:rating="mouseOverRating = $event"
                                            :increment="0.5"
                                            :starSize="20"
                                          ></star-rating>
                                      </td>
                                    </tr>
                                    <tr>
                                      <td>분위기 :</td>
                                      <td align="left">
                                        <star-rating
                                            v-model="userStandard.umood"
                                            :show-rating="true"
                                            @hover:rating="mouseOverRating = $event"
                                            :increment="0.5"
                                            :starSize="20"
                                          ></star-rating>
                                      </td>
                                    </tr>
                                    <tr>
                                      <td>서비스 :</td>
                                      <td align="left">
                                        <star-rating
                                            v-model="userStandard.ukindness"
                                            :show-rating="true"
                                            @hover:rating="mouseOverRating = $event"
                                            :increment="0.5"
                                            :starSize="20"
                                          ></star-rating>
                                      </td>
                                    </tr>
                                    <tr>
                                      <td>접근성 :</td>
                                      <td align="left">
                                        <star-rating
                                            v-model="userStandard.uaccess"
                                            :show-rating="true"
                                            @hover:rating="mouseOverRating = $event"
                                            :increment="0.5"
                                            :starSize="20"
                                          ></star-rating>
                                      </td>
                                      </tr>
                                    </table>
                                     <p>
                                       <hr>
                                <base-checkbox>
                                    <span><i class="ni ni-air-baloon"></i>저는 맛잘알들의 맛집 공유 SNS 구르망의 먹부심 있는 구르망으로서 허위 리뷰나 별점이 아닌 직접 찾아가 맛보고 경험한 바를 토대로 한 리뷰만을 작성할 것을 서약합니다. 
                                        <a href="#">Privacy Policy</a>
                                    </span>
                                </base-checkbox><p>
                                <div class="text-center">
                                  
                                    <base-button type="primary" v-on:click="addUser()">구르망 되기</base-button>
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
import BaseButton from '../components/BaseButton.vue';
import StarRating from "vue-star-rating";
// import {ImageUpload} from 'src/components'
export default {
  components: { 
      StarRating, BaseButton, 
  },

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
            uflavor : 0,
            uclean : 0,
            ucost :  0,
            umood : 0,
            ukindness : 0,
            uaccess : 0,
        },
        imgUrl: null,
        
      }
  },
   
  methods:{
      addUser: function(){
          const formData = new FormData(); 
          this.userStandard.uflavor = parseFloat(this.userStandard.uflavor);
          this.userStandard.uclean = parseFloat(this.userStandard.uclean);
          this.userStandard.ucost = parseFloat(this.userStandard.ucost);
          this.userStandard.umood = parseFloat(this.userStandard.umood);
          this.userStandard.ukindness = parseFloat(this.userStandard.ukindness);
          this.userStandard.uaccess = parseFloat(this.userStandard.uaccess);  
          console.log(this.userStandard);
          formData.append("userStandard", JSON.stringify(this.userStandard));
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
               alert(this.newUser.userId + "님이 구르망이 되셨습니다. 반가워요!");
               location.href="/login"
             }).catch(
               console.error()
             );
      },
      onClickImageUpload() {
    this.$refs.imageInput.click();
},
onChangeImages(e) {
    console.log(e.target.files)
    const file = e.target.files[0]; // Get first index in files
    this.imageUrl = URL.createObjectURL(file); // Create File URL
}
  },
};


</script>
<style>
</style>