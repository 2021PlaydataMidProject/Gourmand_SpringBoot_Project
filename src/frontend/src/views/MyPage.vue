<template>
  <section class="section section-shaped section-lg my-0">
   
    <div class="container emp-profile">
      <form method="post">
        <div class="row">
          <div class="col-md-4">
            <div class="profile-img">
              <img
                v-if="userInfo.user_img.length>0"
                :src="'/img/user/' + userInfo.user_img[0].name"
                alt=""
              />
              <img
                v-else
                src="img/theme/dish.png"
                alt=""
              />
            </div>
          </div>
          <div class="col-md-6">
            <div class="profile-head">
              <h5>{{userInfo.user_id}}({{userInfo.name}})</h5>
              <p class="proile-rating">
                평가 <span>{{userInfo.rev_cnt}}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              </p>

              <p class="proile-rating">나의 리스트 <span>{{Object.keys(listInfo).length}}</span></p>
             
            </div>
          </div>
          <div class="col-md-2">
            <input
              type="submit"
              class="profile-edit-btn"
              name="btnAddMore"
              value="Edit Profile"
            />
          </div>
        </div>
        <div class="row">
          <div class="col-md-3">
            <div class="profile-work">
              <p>보관함</p>
              <a href="#" @click="pagenum=0">내 리뷰 확인</a><br />
              <a href="#" @click="pagenum=1">취향 분석</a><br />
              <a href="#" @click="pagenum=2">내 리스트보기</a><br />
              <p>개인정보</p>
              <a href="/userEdit">개인정보 수정</a><br />
            </div>
          </div>
          <!--여기에 my review ,myalanysis둘다 들어와야함 -->
          <my-review v-if="pagenum==0"></my-review>
          <my-analysis v-else-if="pagenum==1"></my-analysis>
          <my-res-list v-else-if="pagenum==2"></my-res-list>
        </div>
        
         
        
      </form>
    </div>
  </section>
</template>
<script>
import MyReview from "@/views/MyReview";
import MyAnalysis from "@/views/MyAnalysis";
import UserEdit from "@/views/UserEdit";
import MyResList from './MyResList.vue';

export default {
   data() {
    return {
      userInfo: {},
      pagenum: 0,
      listInfo: []
    };
  },
  components: {
    MyReview,
    MyAnalysis,
    UserEdit,
    MyResList,
    MyAnalysis,
  },
   mounted() {
    if (sessionStorage.getItem("user") == null) {
      location.href = "/login";
    }

    this.axios.get("user/info",{})
    .then(res => {
      this.userInfo = res.data;
    })

    this.axios.get("user/list",{})
    .then(res => {
      this.listInfo = res.data;
    })
  },
};
</script>
<style>
body {
  background: -webkit-linear-gradient(left, #f3e1dc, #f3553c);
}
.emp-profile {
  padding: 3%;
  margin-top: 3%;
  margin-bottom: 3%;
  border-radius: 0.5rem;
  background: #fff;
}
.profile-img {
  text-align: center;
}
.profile-img img {
  width: 70%;
  height: 100%;
}
.profile-img .file {
  position: relative;
  overflow: hidden;
  margin-top: -20%;
  width: 70%;
  border: none;
  border-radius: 0;
  font-size: 15px;
  background: #212529b8;
}
.profile-img .file input {
  position: absolute;
  opacity: 0;
  right: 0;
  top: 0;
}
.profile-head h5 {
  color: #333;
}
.profile-head h6 {
  color: #dbd4d1;
}
.profile-edit-btn {
  border: none;
  border-radius: 1.5rem;
  width: 70%;
  padding: 2%;
  font-weight: 600;
  color: #6c757d;
  cursor: pointer;
}
.proile-rating {
  float: left;
  font-size: 12px;
  color: #818182;
  margin-top: 5%;
}
.proile-rating span {
  color: #495057;
  font-size: 15px;
  font-weight: 600;
}
.profile-head .nav-tabs {
  margin-bottom: 5%;
}
.profile-head .nav-tabs .nav-link {
  font-weight: 600;
  border: none;
}
.profile-head .nav-tabs .nav-link.active {
  border: none;
  border-bottom: 2px solid #0062cc;
}
.profile-work {
  padding: 14%;
  margin-top: -15%;
}
.profile-work p {
  font-size: 12px;
  color: #818182;
  font-weight: 600;
  margin-top: 10%;
}
.profile-work a {
  text-decoration: none;
  color: #495057;
  font-weight: 600;
  font-size: 14px;
}
.profile-work ul {
  list-style: none;
}
.profile-tab label {
  font-weight: 600;
}
.profile-tab p {
  font-weight: 600;
  color: #0062cc;
}
.custom-text {
  font-weight: bold;
  font-size: 1.9em;
  border: 1px solid #cfcfcf;
  padding-left: 5px;
  padding-right: 5px;
  border-radius: 2px;
  color: #999;
  background: #fff;
}
</style>
