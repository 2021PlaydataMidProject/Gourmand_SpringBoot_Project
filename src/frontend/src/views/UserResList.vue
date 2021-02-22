<template>
  <section class="col-md-9">
    <div class="col-md-12">
      <b-tabs content-class="mt-3">
        <b-tab title="내 리스트" active>
          <div class="" v-for="key in listNames" v-bind:key="key">
            <h5 class="mb-3 mt-2">{{ key }} 리스트</h5>
            <div class="row">
              <div
                v-for="(value, key) in resList[key]"
                v-bind:key="key"
                class="row col-md-4 mb-4"
              >
                <div class="col-md-12 image-container">
                  <img
                    v-if="value.res_img != null"
                    :src="'img/res/' + value.res_img.name"
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
                <div class="col-lg-12">
                  <small
                  ><a :href="'/respage?res=' + value.res_num">{{
                    value.res_name
                  }}({{ value.category }})</a></small
                >
                <star-rating
                  :value="3"
                  :show-rating="true"
                  @hover:rating="mouseOverRating = $event"
                  :increment="0.5"
                  :starSize="20"
                  :readOnly="true"
                  :rating="value.avg_star"
                ></star-rating>
                <small class="mb-0">{{ value.res_address }}</small>
                <hr class="mt-2 mb-0" />
                </div>
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
      resList: [],
      listNames: [],
    };
  },
  components: {
    StarRating,
    BTabs,
    BTab,
  },
  mounted() {
    this.axios
      .get(`/user/list/${this.$route.query.id}`)
      .then((req) => {
        this.resList = req.data;
        let names = [];
        for (let key in this.resList) {
          names.push(key);
        }
        this.listNames = names;
      })
      .catch((error) => {
        alert("서버오류");
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
</style>