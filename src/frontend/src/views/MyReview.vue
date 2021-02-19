<template>
  <section class="section section-shaped section-lg my-0">
    <div class="col-md-30">
       <b-tabs content-class="mt-3">
         <b-tab title="별점순" active></b-tab>
         <div class="row">
            <div class="col-md-6">
              <label>5.0</label>
            </div>
             <div v-for="(rev, key) in revs" v-bind:key="key" class="row">
            <hr class="col-lg-11 col-sm-11" />
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
         <b-tab title="작성순"></b-tab>
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
      resInfo: "",
      resUser: "",
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
   mounted() {
    var str = this.$route.query.res;
    if (str==null){
    sessionStorage.setItem("user_num", res.data.user_num);
      location.href="/";
    }
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

      this.resnum = str;
  },
};
</script>
<style>
</style>