<template>
  <section class="section section-shaped section-lg my-0">
        <div class="container pt-lg-md">
            <div class="justify-content-center">
                <div class="text-center pt-lg">
                  <img v-lazy="'img/brand/logo.png'" class="Rounded image" style="width: 150px;">
                </div>
                  <form role="form" class="row">
                      <div class="col-lg-2 col-sm-1"></div>
                      <base-input class="my-3 col-lg-6 col-sm-8"
                                  placeholder="음식점 검색하기">
                      </base-input>
                      <div class="col-lg-4 col-sm-2">
                      <base-button type="primary" class="my-3">Search</base-button>
                      </div>
                  </form>
            </div>
            <div class="justify-content-center">
              <div class="text-center pt-lg">
                <div class="shadow-lg overflow-hidden transform-perspective-right">
                        <b-carousel id="carousel1"
                                    controls
                                    indicators>
                            <!-- Text slides with image -->
                            <b-carousel-slide img-src="img/theme/img-1-1200x1000.jpg"></b-carousel-slide>
                            <b-carousel-slide img-src="img/theme/img-2-1200x1000.jpg"></b-carousel-slide>
                        </b-carousel>
                    </div>
              </div>
            </div>
        </div>
  </section>
</template>

<script>
import { BCarousel } from "bootstrap-vue/esm/components/carousel/carousel";
import { BCarouselSlide } from "bootstrap-vue/esm/components/carousel/carousel-slide";

export default {
  name: "home",
  components: {
    BCarousel,
    BCarouselSlide
  }
};
</script>