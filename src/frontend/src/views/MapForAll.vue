<template>
  <section class="section section-shaped section-lg my-0">
    <div class="container pt-lg-md">
      <h2 class="mt-lg mb-5">
        <span>맛집 지도</span>
        <hr />
      </h2>
      <div class="row">
        <div v-for="value in resThumbnails.data" class="col-sm-4" v-bind:key="value">
          <img
            v-if="value.res_img"
            :src="'img/res/'+value.res_img.name"
            alt="Rounded image"
            class="img-fluid rounded shadow"
            style="height: 150px"
          />
          <img
            v-if="!value.res_img"
            v-lazy="'img/theme/team-1-800x800.jpg'"
            alt="Rounded image"
            class="img-fluid rounded shadow"
            style="width: 150px"
          />
          <h3 class="heading mb-0">★★★★☆  {{value.avg_star}}/5.0</h3>
          <h3 class="heading-title mb-0">{{value.res_name}}</h3>
          <h3 class="heading">{{value.category}}</h3>
          <h6 class="mb-0">{{value.res_address}}</h6>
          <hr>
        </div>
      </div>
    </div>
  </section>
</template>
<script>
export default {
    data: function (){
        return {
            resThumbnails : null,
        }
    },
    mounted(){
        var xValue = 37.28220;
        var yValue = 126.9994;
        if (navigator.geolocation){
            navigator.geolocation.getCurrentPosition((position) => {
                xValue = position.coords.latitude
                yValue = position.coords.longitude
            });
        }
        this.axios
            .get("/res/thumbnail/"+ xValue + "," + yValue, {})
            .then((res) => {
                this.resThumbnails = res;
            })
            .catch((error) => {
                alert("서버오류")
            })
  },
};
</script>
<style>
</style>
