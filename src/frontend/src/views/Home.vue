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
            <b-dropdown :text="dropDownText" class="my-3 col-lg-2 col-sm-2" variant="primary">
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

export default {
  name: "home",
  data() {
    return {
      name: "",
      dropDownText: "음식점",
    };
  },
  components: {
    BCarousel,
    BCarouselSlide,
    BDropdown,
    BDropdownItem,
    myMap,
  },
  methods: {
    search() {
      if (this.name) {
        if (this.dropDownText=="음식점"){
          this.$router.push({ path: "/search", query: { name: this.name } });
        } else{
          this.$router.push({ path: "/search", query: { location: this.name } });
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
};
</script>