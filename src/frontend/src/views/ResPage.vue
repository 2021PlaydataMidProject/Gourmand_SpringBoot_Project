<template>
  <section class="section section-shaped section-lg my-0">
    <div class="mb-5 row">
      <div class="col-lg-3 col-sm-4">
        <img
          v-if="resInfo.res_img != null"
          :src="'img/res/' + resInfo.res_img[0].name"
          style="width: 100%"
        />
        <img
          v-else
          v-lazy="'img/theme/team-1-800x800.jpg'"
          style="width: 100%"
        />
      </div>
    </div>

    <div class="container pt-lg-md">
      <div class="row">
        <div class="col-lg-7 col-sm-7">
          <h2 class="display-2 mb-1">{{ resInfo.res_name }}</h2>
          <h2 class="display-2 mb-1">★★★★☆ {{ resInfo.avg_star }}</h2>

          <div class="col-lg-8 col-sm-8">
            <chart :data="data"></chart>
          </div>
          <hr />

          <table>
            <tr>
              <td style="width: 120px">
                <h3 class="h5 text-primary font-weight-bold">주소:</h3>
              </td>
              <td>{{ resInfo.res_address }}</td>
            </tr>
            <tr>
              <td>
                <h3 class="h5 text-primary font-weight-bold">전화번호:</h3>
              </td>
              <td>{{ resInfo.tel }}</td>
            </tr>
            <tr>
              <td>
                <h3 class="h5 text-primary font-weight-bold">음식종류:</h3>
              </td>
              <td>{{ resInfo.category }}</td>
            </tr>
          </table>
        </div>
        <div class="col-lg-5 col-sm-5">
          <my-map
            height="400px"
            :Xaxis="resInfo.xvalue"
            :Yaxis="resInfo.yvalue"
          ></my-map>
        </div>
      </div>
      <div class="mt-5">
        <hr />
        <h3 class="h5 font-weight-bold">이 가게를 맛집으로 등록한 구르망들</h3>
        <div class="mt-4 row">

          <div v-for="(value, key) in resUser" v-bind:key="key">
            <img
              v-if="value.user_img != null"
              v-lazy="'img/user/' + value.user_img.name"
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
          </div>
        </div>
        <hr />
      </div>

      <div class="mt-5">
        <h3 class="h4 font-weight-bold">평가 (평가수)</h3>
      </div>
    </div>
  </section>
</template>
<script>
import myMap from "./Map";
import Chart from './components/Chart.vue';

export default {
  data() {
    return {
      resInfo: "",
      resUser: "",
      data:[1,1,1,1,1,1]
    };
  },
  components: {
    myMap,
    Chart,
  },
  mounted() {
    var str = location.href;
    var index = str.indexOf("?") + 1;
    var lastIndex = str.indexOf("#") > -1 ? str.indexOf("#") + 1 : str.length;
    if (index == 0) {
      location.href = "/";
    }
    str = str.substring(index, lastIndex);

    this.axios
      .get("/res/" + str + "/resinfo", {})
      .then((req) => {
        this.resInfo = req.data;
      })
      .catch((error) => {
        alert("서버 오류");
      });

    this.axios
      .get("/res/" + str + "/user", {})
      .then((us) => {
        this.resUser = us.data;
      })
      .catch((error) => {
        alert("서버 오류");
      });
    
  },
};
</script>
<style>
</style>
