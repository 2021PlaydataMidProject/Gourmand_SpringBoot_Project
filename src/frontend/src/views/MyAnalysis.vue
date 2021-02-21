<template>
  <section class="col-md-9">
    <div class="col-md-12">
      <b-tabs content-class="mt-3">
        <b-tab title="선호 입맛" active>
          <div class="row">
            <div class="col-md">
              <label>선호입맛</label>
              <li v-for="(rev, key) in revs" v-bind:key="key" class="row">
                {{ rev }}
              </li>
            </div>
          </div>
        </b-tab>
      </b-tabs>
    </div>
  </section>
</template>
<script>
import { BTabs } from "bootstrap-vue";
import { BTab } from "bootstrap-vue";

export default {
  components: {
    BTabs,
    BTab,
  },
  data() {
    return {
      revs: [],
    };
  },
  mounted() {
    this.axios
      .get("/user/1/userAnalysis/foodType", {})
      .then((res) => {
        this.revs = res.data;
      })
      .catch((error) => {
        alert("서버 오류");
      });
  },
};
</script>
<style>
</style>