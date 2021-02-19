<template>
<section class="section section-shaped section-lg my-0">
    <div class="col-md-8">
                        <b-tabs content-class="mt-3">
                            <b-tab title="별점순" active>
                                           <div class="row">
                                            <div class="col-md-6">
                                                <label>5.0</label>
                                            </div>
                                            <div v-for="(rev, key) in revs" v-bind:key="key" class="row">
            <hr class="col-lg-11 col-sm-11" />
            <div class="col-lg-2 col-sm-2">
              <img
                v-if="rev.user.user_img != null"
                v-lazy="'img/user/' + rev.user.user_img.name"
                alt="Raised circle image"
                class="img-fluid rounded-circle shadow-lg"
                style="width: 100px"
              />
              <img
                v-else
                v-lazy="'img/theme/team-4-800x800.jpg'"
                alt="Raised circle image"
                class="img-fluid rounded-circle shadow-lg"
                style="width: 100px"
              />
              <p class="mt-2 front-weight-bold">{{ rev.user.name }}</p>
            </div>
            <div class="col-lg-6 col-sm-6">
              <small>{{ rev.write_date }}</small
              ><br />
              <small>음식종류: {{ rev.food_type }}</small>
              <div class="mt-4">{{ rev.review }}</div>
            </div>
            <div class="col-lg-4 col-sm-4">
              <img
                v-if="rev.review_img != null"
                v-lazy="'img/rev/' + value.review_img.name"
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
              <div class="col-lg-10">
              <chart
                :name="key.toString()"
                :data="[
                  rev.review_standard.rflavor,
                  rev.review_standard.rcost_value,
                  rev.review_standard.rkindness,
                  rev.review_standard.rmood,
                  rev.review_standard.raccess,
                  rev.review_standard.rclean,
                ]"
              ></chart>
              </div>
            </div>
          </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>4.5</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>img</p>
                                                <p>img</p>
                                                <p>img</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>4.0</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>img</p>
                                                <p>img</p>
                                                <p>img</p>
                                            </div>
                                        </div>
                                       <div class="row">
                                            <div class="col-md-6">
                                                <label>3.5</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>img</p>
                                                <p>img</p>
                                                <p>img</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>3.0</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>img</p>
                                                <p>img</p>
                                                <p>img</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>2.5</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>img</p>
                                                <p>img</p>
                                                <p>img</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>2.0</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>img</p>
                                                <p>img</p>
                                                <p>img</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>1.0</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>img</p>
                                                <p>img</p>
                                                <p>img</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>0.5</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>img</p>
                                                <p>img</p>
                                                <p>img</p>
                                            </div>
                                        </div>
                            </b-tab>
                            <b-tab title="작성순">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>2021.2</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>img</p>
                                                <p>img</p>
                                                <p>img</p>
                                            </div>
                                        </div>
                                           <div class="row">
                                            <div class="col-md-6">
                                                <label>2021.1</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>img</p>
                                                <p>img</p>
                                                <p>img</p>
                                            </div>
                                        </div>
                                           <div class="row">
                                            <div class="col-md-6">
                                                <label>2020.12</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>img</p>
                                                <p>img</p>
                                                <p>img</p>
                                            </div>
                                        </div>
                                           <div class="row">
                                            <div class="col-md-6">
                                                <label>2020.11</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>img</p>
                                                <p>img</p>
                                                <p>img</p>
                                            </div>
                                        </div>
                            </b-tab>
                          </b-tabs>
                      </div>
    </section>
    </template>
<script>
import myMap from "./Map";
import { BTabs } from "bootstrap-vue";
import { BTab } from "bootstrap-vue";

export default {
  components: {
    myMap,
    BTabs,
    BTab
  },
 
};
</script>
<style>
body{
    background: -webkit-linear-gradient(left, #f3e1dc, #f3553c);
}
.emp-profile{
    padding: 3%;
    margin-top: 3%;
    margin-bottom: 3%;
    border-radius: 0.5rem;
    background: #fff;
}
.profile-img{
    text-align: center;
}
.profile-img img{
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
.profile-head h5{
    color: #333;
}
.profile-head h6{
    color: #dbd4d1;
}
.profile-edit-btn{
    border: none;
    border-radius: 1.5rem;
    width: 70%;
    padding: 2%;
    font-weight: 600;
    color: #6c757d;
    cursor: pointer;
}
.proile-rating{float:left;
    font-size: 12px;
    color: #818182;
    margin-top: 5%;
}
.proile-rating span{
    color: #495057;
    font-size: 15px;
    font-weight: 600;
}
.profile-head .nav-tabs{
    margin-bottom:5%;
}
.profile-head .nav-tabs .nav-link{
    font-weight:600;
    border: none;
}
.profile-head .nav-tabs .nav-link.active{
    border: none;
    border-bottom:2px solid #0062cc;
}
.profile-work{
    padding: 14%;
    margin-top: -15%;
}
.profile-work p{
    font-size: 12px;
    color: #818182;
    font-weight: 600;
    margin-top: 10%;
}
.profile-work a{
    text-decoration: none;
    color: #495057;
    font-weight: 600;
    font-size: 14px;
}
.profile-work ul{
    list-style: none;
}
.profile-tab label{
    font-weight: 600;
}
.profile-tab p{
    font-weight: 600;
    color: #0062cc;
}
</style>
