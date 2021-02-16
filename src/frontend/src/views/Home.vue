<template>
  <section class="section section-shaped section-lg my-0">
        <div class="container pt-lg-md">
            <div class="justify-content-center">
                <div class="text-center pt-lg">
                  <img v-lazy="'img/brand/logo.png'" class="Rounded image" style="width: 150px;">
                </div>
                  <form role="form" class="row justify-content-center">
                    <div class="row col-lg-8">
                      <base-input class="my-3 col-lg-10"
                                  placeholder="음식점 검색하기">
                      </base-input>
                      <base-button @click="check()" type="primary" class="my-3 col-lg-2">Search</base-button>
                      </div>
                  </form>
            </div>
            <div class="row pt-lg justify-content-center">
                <div class="shadow-lg overflow-hidden col-lg-7">
                        <b-carousel id="carousel"
                                    controls
                                    indicators>
                            <!-- Text slides with image -->
                            <b-carousel-slide img-src="img/theme/img-1-1200x1000.jpg"></b-carousel-slide>
                            <b-carousel-slide img-src="img/theme/img-2-1200x1000.jpg"></b-carousel-slide>
                        </b-carousel>
                    </div>
            </div>

            <div class="text-center pt-lg">
              <div class = "col-lg-7">
                <myMap height="600px"></myMap>
                <!--Xaxis="33.450701" Yaxis="126.570667"-->
              </div>
            </div>
        </div>
  </section>
</template>

<script>
import { BCarousel } from "bootstrap-vue/esm/components/carousel/carousel";
import { BCarouselSlide } from "bootstrap-vue/esm/components/carousel/carousel-slide";
import myMap from "./Map";

export default {
  name: "home",
  components: {
    BCarousel,
    BCarouselSlide,
    myMap,
  }
};
</script>