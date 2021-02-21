<template>
  <section class="col-md-9">
    <div class="col-md-12">
      <b-tabs content-class="mt-3">
        <b-tab title="별점순" active>
          <div class="row">
            <div class="col-md-12">
              <label>5.0</label>
            </div>
            <div v-for="(rev, key) in revs_star" v-bind:key="key" class="row col-md-4 mb-5">
              <!-- <hr class="col-lg-11 col-sm-11" />-->
              <div class="col-md-12 image-container">
                <img
                  v-if="rev.review_img != null"
                  v-lazy="'img/rev/' + rev.review_img.name"
                  alt="Rounded image"
                  class="img-fluid rounded shadow"
                />
                <img
                  v-else
                  v-lazy="'img/theme/team-4-800x800.jpg'"
                  alt="Rounded image"
                  class="img-fluid rounded shadow"
                />
              </div>
              <div class="col-lg-12">
                <small class="mr-4"
                  ><a :href="'/respage?res=' + rev.res_num">{{
                    rev.res_name
                  }}</a></small
                >
                <p class="mb-0"><small>{{rev.write_date}}</small></p>
                <star-rating
                  :show-rating="true"
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
                <hr class="mb-0"/>
              </div>
            </div>
          </div>
        </b-tab>
        <b-tab title="작성순">
          <div class="row">
            <div class="col-md-12">
              <label>2021.02</label>
            </div>
            <div v-for="(rev, key) in revs_date" v-bind:key="key" class="row col-md-4 mb-5">
                <div class="col-md-12 image-container">
                  <img
                    v-if="rev.review_img != null"
                    v-lazy="'img/rev/' + rev.review_img.name"
                    alt="Rounded image"
                    class="img-fluid rounded shadow"
                  />
                  <img
                    v-else
                    v-lazy="'img/theme/team-4-800x800.jpg'"
                    alt="Rounded image"
                    class="img-fluid rounded shadow"
                  />
                </div>
              <div class="col-lg-12">
                <small
                  ><a :href="'/respage?res=' + rev.res_num">{{
                    rev.res_name
                  }}</a></small
                >
                <p class="mb-0"><small>{{rev.write_date}}</small></p>
                <star-rating
                  :show-rating="true"
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
                <hr class="mb-0"/>
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
      user_id: "",
      name: "",
      revs_date: [],
      revs_star: [],
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
    this.axios
      .get("/res/user/review/Star", {})
      .then((rev) => {
        this.revs_star = rev.data;
      })
      .catch((error) => {
        alert("서버 오류");
      });
    this.axios
      .get("/res/user/review/writeDate", {})
      .then((rev) => {
        this.revs_date = rev.data;
      })
      .catch((error) => {
        alert("서버 오류");
      });
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
.image-container {
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100px;
  height: 100px;
}
</style>