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
          <star-rating
            :value="3"
            :show-rating="false"
            @hover:rating="mouseOverRating = $event"
            :increment="0.5"
            :starSize="40"
            :readOnly="true"
            :rating="resInfo.avg_star"
          ></star-rating>
          <h2 class="display-3 mt-2 mb-1">{{ (resInfo.avg_star).toFixed(1) }}/ 5.0</h2>

          <div class="col-lg-8 col-sm-8">
            <chart v-if="data.length > 0" :data="data" name="mainchart"></chart>
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
        <h3 class="h4 font-weight-bold mb-5">평가 ({{ revs.length }})</h3>
        <div>
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
      </div>
    </div>
  </section>
</template>
<script>
import myMap from "./Map";
import Chart from "./components/Chart.vue";
import StarRating from "vue-star-rating";

export default {
  data() {
    return {
      resInfo: "",
      resUser: "",
      revs: [],
      data: [],
    };
  },
  components: {
    myMap,
    Chart,
    StarRating,
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

    this.axios
      .get(`/rev/res/${str}`, {})
      .then((rs) => {
        this.revs = rs.data;
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
  },
};
</script>
<style>
</style>
