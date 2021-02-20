<template>
  <section class="section section-shaped section-lg my-0">
    <div class="container pt-lg-md">
          <h2 class="mb-5">
         <span>회원 정보 수정</span>
           <hr />
            </h2>
        <table border="1">
          <tr>
            <td width="70">유저ID</td>
            <td align="left">
              <input type="text" v-model="user.user_id" />
            </td>
          </tr>
          <tr>
            <td width="70">이름</td>
            <td align="left">
              <input type="text" v-model="user.name" />
            </td>
          </tr>
          <tr>
            <td width="70">비밀번호</td>
            <td align="left">
              <input type="text" v-model="user.pw" />
            </td>
          </tr>
          <tr>
            <td>생년월일</td>
            <td align="left">
              <input type="text" v-model="user.dob" size="10" />
            </td>
          </tr>
          <tr>
            <td>직업</td>
            <td align="left">
              <input type="text" v-model="user.job" size="10" />
            </td>
          </tr>
          <tr>            
            <td>회원정보 공개(0,1,2)</td>
            <td align="left">
              <input type="text" v-model="user.pageStatus" size="10" />
            </td>
          </tr>
          <tr>
            <td>사진 업로드</td>
            <td align="left">
  <div>
               <img
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
              />
              <input type="file" ref="imgdata" name="imgdata[]" accept="image/*" multiple="multiple" />
                    <div class="row">
   
            <!-- <img
              v-lazy="'../img/user/'"
              alt="Rounded image"
              class="img-fluid rounded shadow"
              style="width: 150px"
              @click="editImg(key)"
            />
            {{ value.origin_name }} -->
          </div>
         </div>
          </td>
          </tr>
        
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
        <tr>s
            <td colspan="2" align="center">
              <base-button type="primary" v-on:click="editUser()">회원 정보 수정</base-button>
              <base-button type="primary" v-on:click="deleteUser()">회원 탈퇴</base-button>
            </td>
          </tr>
        </table>
    </div>  
  </section>
</template>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
import BaseButton from '../components/BaseButton.vue';
import StarRating from "vue-star-rating";
import { getUserNumFromCookie } from '../utils/cookies';

export default {
  components: {
    StarRating, BaseButton,
  },
  data(){
      return {
        user :{
            user_id : "",
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

      this.axios.get(`/user/${ sessionStorage.getItem("user") }/info`, {}).then((req) => {
      this.user.user_id =  req.data.user_id;  
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
               alert(this.newUser.userId + "회원 정보 수정 완료");
               //location.href="/home"
             }).catch((error) => {
          alert("에러");
        });
      },
       delImg(value) {
      this.delimgs.push(this.imgs.user_img_id);
      this.imgs.splice(value, 1);
    },
  },
};

</script>
<style>
</style>