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
                                  <h4>회원 정보 수정</h4>
                                  프로필 사진
                            </div>
                                 <form role="form">
              <center> <img
                v-if=" imgs.length > 0"
                v-lazy="'img/user/' + imgs[0].name"
                alt="Rounded image"
                class="img-fluid rounded shadow"
                style="width: 150px"
              />
              <img
                v-else
                v-lazy="'img/theme/team-4-800x800.jpg'"
                alt="Rounded image"
                class="img-fluid rounded shadow"
                style="width: 150px"
              /><br><br>
              <center><input type="file" ref="imgdata" name="imgdata[]" accept="image/*" multiple="multiple" /></center>
              <br>&nbsp;<br><br>
              </center>
                                <base-input alternative
                                            class="mb-3"
                                            placeholder="아이디" disabled
                                            type="text"
                                            v-model="user.userId"
                                            addon-left-icon="ni ni-email-83">
                                </base-input>

                                <base-input alternative
                                            class="mb-3"
                                            type="password"
                                            placeholder="비밀번호"
                                            v-model="user.pw"
                                            addon-left-icon="ni ni-lock-circle-open">
                                </base-input>
                                 <hr>
                             
                               이름
                                <base-input alternative
                                            class="mb-3"
                                            placeholder="이름" 
                                            type="text"
                                            v-model="user.name"
                                            addon-left-icon="ni ni-single-02"
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
                             <hr>
                             당신의 음식점 선택 기준을 알려주세요!<br>
                             당신 취향에 딱 맞는 음식점을 추천해 드리는 데에만 사용됩니다!<br> 
                             <p>
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
                          
                                <div class="text-center">
                                  
                                    <base-button type="primary" v-on:click="editUser()">회원 정보 변경하기</base-button> <br><br><base-button type="primary" v-on:click="deleteUser()">구르망 탈퇴하기</base-button>
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

export default {
  components: { 
      StarRating, BaseButton, 
  },

  data(){
      return {
        user:{
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
         imgs: [],
         delimgs: [],
      };
  },

    mounted() {
    if (sessionStorage.getItem("user") == null) {
      location.href = "/register";
    }

      this.axios.get(`/user/info`, {}).then((req) => {
      this.user.userId =  req.data.user_id;  
      this.user.name =  req.data.name;
      this.user.pw =  req.data.pw;
      this.user.dob =  req.data.dob; 
      this.user.job =  req.data.job;
      this.user.pageStatus =  req.data.pageStatus;   

      this.userStandard.uflavor =  req.data.user_standard.uflavor;  
      this.userStandard.uclean =  req.data.user_standard.uclean;
      this.userStandard.ucost =  req.data.user_standard.ucost;
      this.userStandard.umood =  req.data.user_standard.umood; 
      this.userStandard.ukindness =  req.data.user_standard.ukindness;
      this.userStandard.uaccess =  req.data.user_standard.uaccess;         

      this.imgs = req.data.user_img;

    });
  },

  methods:{
       editUser() {
          const formData = new FormData(); 
          this.userStandard.uflavor = parseFloat(this.userStandard.uflavor);
          this.userStandard.uclean = parseFloat(this.userStandard.uclean);
          this.userStandard.ucost = parseFloat(this.userStandard.ucost);
          this.userStandard.umood = parseFloat(this.userStandard.umood);
          this.userStandard.ukindness = parseFloat(this.userStandard.ukindness);
          this.userStandard.uaccess = parseFloat(this.userStandard.uaccess);  

          formData.append("userStandard", JSON.stringify(this.userStandard));
          formData.append("user", JSON.stringify(this.user));
          for (let i=0; i<this.$refs.imgdata.files.length; i++){
            formData.append('userImg', this.$refs.imgdata.files[i])

          }
          return this.axios.put(`/user/update`, formData, {
            headers: {
              "Content-Type": 'multipart/form-data'
            }
          })
          .then(user => {
               alert(this.user.userId + "구르망 님의 정보가 수정되었습니다!");

             }).catch((error) => {
               console.error();
              alert("에러");
        });
      },
       delImg(value) {
      this.delimgs.push(this.imgs.user_img_id);
      this.imgs.splice(value, 1);
    },
    deleteUser() {
      this.axios.delete(`/user/`, {})
        .then(req =>{
          window.location.reload();
        })
        .catch(error =>{
          alert("에러");
        });
    },
    }
}
</script>
<style>
</style>