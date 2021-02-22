<template>
  <section class="section section-shaped section-lg my-0">
    <div class="container pt-lg-md">
      <div class="justify-content-center">
        <div class="text-center pt-lg">
          <img
            v-lazy="'img/brand/logo.png'"
            class="Rounded image"
            style="width: 150px"
          />
        </div>
        <div class="row justify-content-center">
          <div class="row col-lg-10">
            <b-dropdown
              :text="dropDownText"
              class="my-3 col-lg-2 col-sm-2"
              variant="primary"
            >
              <b-dropdown-item @click="drop('음식점')">음식점</b-dropdown-item>
              <b-dropdown-item @click="drop('지역')">지역</b-dropdown-item>
            </b-dropdown>
            <base-input
              class="my-3 col-lg-8 col-sm-8"
              :placeholder="dropDownText + ' 검색하기'"
              v-model="name"
            >
            </base-input>
            <base-button
              @click="search()"
              type="primary"
              class="my-3 col-lg-2 col-sm-2"
              >Search</base-button
            >
          </div>
        </div>
      </div>
      <div class="row pt-lg justify-content-center">
        <div class="shadow-lg overflow-hidden col-lg-7">
          <b-carousel id="carousel" controls indicators>
            <!-- Text slides with image -->
            <b-carousel-slide
              img-src="img/theme/img-1-1200x1000.jpg"
            ></b-carousel-slide>
            <b-carousel-slide
              img-src="img/theme/img-2-1200x1000.jpg"
            ></b-carousel-slide>
          </b-carousel>
        </div>
      </div>

      <div class="text-center pt-lg">
        <div class="mt-5">
          <hr />
          <h3 class="h4 font-weight-bold mb-5">Recent Activities</h3>
          <div class="row">
            <div
              v-for="(rev, key) in revs"
              v-bind:key="key"
              class="col-lg-4 col-sm-6 row text-center"
            >
              <hr class="col-lg-8 col-sm-8" />
              <div class="col-lg-12 col-sm-12 text-left row">
                <div class="image-container2">
                  <img
                    v-if="rev.user.user_img != null"
                    v-lazy="'img/user/' + rev.user.user_img.name"
                    alt="Raised circle image"
                    class="img-fluid rounded-circle shadow-lg"
                  />
                  <img
                    v-else
                    v-lazy="'img/theme/team-4-800x800.jpg'"
                    alt="Raised circle image"
                    class="img-fluid rounded-circle shadow-lg"
                  />
                </div>
                <p class="front-weight-bold">
                  {{ rev.user.name }}({{ rev.user.user_id }})
                </p>
                <hr class="col-sm-10 mt-0" />
              </div>
              <div class="col-md-6 image-container">
                <img
                  v-if="rev.review_img != null"
                  v-lazy="'img/rev/' + rev.review_img.name"
                  alt="Rounded image"
                  class="img-fluid rounded shadow"
                />
                <img
                  v-else
                  v-lazy="'img/theme/dish.png'"
                  alt="Rounded image"
                  class="img-fluid rounded shadow"
                />
              </div>
              <div class="text-left">
                <small
                  ><a :href="'/respage?res=' + rev.res_num">{{
                    rev.res_name
                  }}</a></small
                ><br />
                <small>{{ rev.write_date }}</small
                ><br />
                <small>음식종류: {{ rev.food_type }}</small
                ><br /><br />
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

              <div class="col-lg-12 col-sm-12 text-left">
                <div class="mt-4">{{ rev.review }}</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { BCarousel } from "bootstrap-vue/esm/components/carousel/carousel";
import { BCarouselSlide } from "bootstrap-vue/esm/components/carousel/carousel-slide";
import { BDropdown } from "bootstrap-vue";
import { BDropdownItem } from "bootstrap-vue";
import myMap from "./Map";
import Chart from "./components/Chart";
import StarRating from "vue-star-rating";

export default {
  name: "home",
  data() {
    return {
      name: "",
      dropDownText: "음식점",
      revs: "",
    };
  },
  components: {
    BCarousel,
    BCarouselSlide,
    BDropdown,
    BDropdownItem,
    myMap,
    Chart,
    StarRating,
  },
  methods: {
    search() {
      if (this.name) {
        if (this.dropDownText == "음식점") {
          this.$router.push({ path: "/search", query: { name: this.name } });
        } else {
          this.$router.push({
            path: "/search",
            query: { location: this.name },
          });
        }
      }
    },
    update(value) {
      this.name = value;
    },
    drop(value) {
      this.dropDownText = value;
    },
  },
  mounted() {
    this.axios
      .get("/rev/main/nologin", {})
      .then((rev) => {
        this.revs = rev.data;
      })
      .catch((error) => {
        alert("서버 오류");
      });
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
.image-container2 {
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 50px;
  height: 50px;
}
</style>