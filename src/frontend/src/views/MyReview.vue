<template>
  <section class="section section-shaped section-lg my-0">
    <div class="col-md-30">
      <b-tabs content-class="mt-3">
        <b-tab title="별점순" active>
        <div class="row">
          <div class="col-md">
            <label>5.0</label>
          </div>
          <div v-for="(rev, key) in revs" v-bind:key="key" class="row">
            <!-- <hr class="col-lg-11 col-sm-11" />-->
            <div class="col-md-6">
              <img
                v-if="rev.review_img != null"
                v-lazy="'img/rev/' + rev.review_img.name"
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
            </div>
            <div class="col-lg-10">
              <small
                ><a :href="'/respage?' + rev.res_num">{{
                  rev.res_name
                }}</a></small
              >
              <br /><br />
              <star-rating
                :show-rating="false"
                @hover:rating="mouseOverRating = $event"
                :increment="0.5"
                :starSize="25"
                :readOnly="true"
                :rating="
                  (rev.review_standard.rflavor +
                    rev.review_standard.rcost_value +
                    rev.review_standard.rkindness +
                    rev.review_standard.rmood +
                    rev.review_standard.raccess +
                    rev.review_standard.rclean) /
                  6
                "
              ></star-rating>
            </div>
          </div>
        </div>
        </b-tab>
        <b-tab title="작성순">
         <div class="row">
          <div class="col-md-1">
            <label>2021.02</label>
          </div>
        <div v-for="(rev, key) in revs" v-bind:key="key" class="row">
            <hr class="col-lg-11 col-sm-11" />
            <div class="col-md-6">
              <img
                v-if="rev.review_img != null"
                v-lazy="'img/rev/' + rev.review_img.name"
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
            </div>
            <div class="col-lg-10">
              <small
                ><a :href="'/respage?' + rev.res_num">{{
                  rev.res_name
                }}</a></small
              >
              <br /><br />
              <star-rating
                :show-rating="false"
                @hover:rating="mouseOverRating = $event"
                :increment="0.5"
                :starSize="25"
                :readOnly="true"
                :rating="
                  (rev.review_standard.rflavor +
                    rev.review_standard.rcost_value +
                    rev.review_standard.rkindness +
                    rev.review_standard.rmood +
                    rev.review_standard.raccess +
                    rev.review_standard.rclean) /
                  6
                "
              ></star-rating>
            </div>
          </div>
         </div>
         </b-tab>
      </b-tabs>
    </div>
  </section>
</template>
<script>
import StarRating from "vue-star-rating";
import { BTabs } from "bootstrap-vue";
import { BTab } from "bootstrap-vue";

export default {
  data() {
    return {
      user_id:"",
      name:"",
      revs: [],
      data: [],
      resnum: 0,
    };
  },
  components: {
    StarRating,
    BTabs,
    BTab,
    
  },
  computed: {
    currentRatingText() {
      return this.rating
        ? "You have selected " + this.rating + " stars"
        : "No rating selected";
    },
    mouseOverRatingText() {
      return this.mouseOverRating
        ? "Click to select " + this.mouseOverRating + " stars"
        : "No rating selected";
    },
  },
  mounted() {
    console.log(sessionStorage.getItem("user"))
     this.axios
      .get("/rev/main/nologin", {})
      .then((rev) => {
        this.revs = rev.data;
      })
      .catch((error) => {
        alert("서버 오류");
      });
    this.axios
      .get(`/rev/res/${str}/standard`, {})
      .then((rs) => {
        var standard = rs.data.split(",");
        this.data = [
          parseFloat(standard[0]),
          parseFloat(standard[2]),
          parseFloat(standard[4]),
          parseFloat(standard[3]),
          parseFloat(standard[5]),
          parseFloat(standard[1]),
        ];
      })
      .catch((error) => {
        alert("서버 오류");
      });

    this.resnum = str;
  },
  methods: {
    //별점순 시간순 카테고리 변경시
    showCurrentRating(rating) {
      this.currentSelectedRating =
        rating === 0
          ? this.currentSelectedRating
          : "Click to select " + rating + " stars";
    },
    setCurrentSelectedRating(rating) {
      this.currentSelectedRating = "You have Selected: " + rating + " stars";
    },
  },
};
</script>
<style>
</style>