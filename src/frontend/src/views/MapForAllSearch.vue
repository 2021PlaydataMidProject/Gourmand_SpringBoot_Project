<template>
  <section class="section section-shaped section-lg my-0">
    <div class="container pt-lg-md">
      <div class="col-md-4">
        <base-button
          block
          type="primary"
          class="mb-3"
          @click="modals.modal1 = true"
        >
          내가 원하는 맛집 고르기
        </base-button>
        <modal :show.sync="modals.modal1">
          <h6 slot="header" class="modal-title" id="modal-title-default">
            filter
          </h6>

          <div class="container">
            <!-- Custom controls -->
            <div class="row">
              <!-- Radio buttons -->
              <div class="mb-3"></div>
              <base-radio name="1" class="mb-3 mr-1" v-model="radio.radio1">
                <small> 1km 이내 </small>
              </base-radio>
              <base-radio name="3" class="mb-3 mr-1" v-model="radio.radio1">
                <small> 3km 이내 </small>
              </base-radio>
              <base-radio name="5" class="mb-3 mr-1" v-model="radio.radio1">
                <small> 5km 이내 </small>
              </base-radio>
              <base-radio name="7" class="mb-3 mr-1" v-model="radio.radio1">
                <small> 7km 이내 </small>
              </base-radio>
              <base-radio name="9" class="mb-3 mr-1" v-model="radio.radio1">
                <small> 9km 이내 </small>
              </base-radio>
              <base-radio name="9999999" class="mb-3 mr-1" v-model="radio.radio1">
                <small> 전체 </small>
              </base-radio>
            </div>
            <div class="mt-4 mt-md-0">
              <div class="mb-3">
                <small class="text-uppercase font-weight-bold">음식종류</small>
              </div>
              <div class="row">
                <!-- Checkboxes -->
                <base-checkbox
                  name="checkbox1"
                  class="mb-3 mr-3"
                  v-model="checkboxes['한식']"
                >
                  한식
                </base-checkbox>
                <base-checkbox
                  name="checkbox2"
                  class="mb-3 mr-3"
                  v-model="checkboxes['중식']"
                >
                  중식
                </base-checkbox>
                <base-checkbox
                  name="checkbox3"
                  class="mb-3 mr-3"
                  v-model="checkboxes['양식']"
                >
                  양식
                </base-checkbox>
                <base-checkbox
                  name="checkbox4"
                  class="mb-3 mr-3"
                  v-model="checkboxes['일식']"
                >
                  일식
                </base-checkbox>
                <base-checkbox
                  name="checkbox6"
                  class="mb-3 mr-3"
                  v-model="checkboxes['분식']"
                >
                  분식
                </base-checkbox>
                <base-checkbox
                  name="checkbox7"
                  class="mb-3"
                  v-model="checkboxes['기타']"
                >
                  기타
                </base-checkbox>
              </div>
            </div>
          </div>
          <template slot="footer">
            <base-button type="primary" @click="applyCategory()"
              >Save changes</base-button
            >
            <base-button
              type="link"
              class="ml-auto"
              @click="modal.modal1 = false"
              >Close
            </base-button>
          </template>
        </modal>
      </div>
    </div>

    <div class="container pt-lg-md">
      <h2 class="mb-5">
        <span>맛집 지도</span>
        <hr />
      </h2> 
      <div class="row" id="pag">
        <!--eslint-disable-next-line vue/no-use-v-if-with-v-for-->
        <div v-for="(value, key) in resThumbnails" v-if="check(value.category)"
          v-bind:key="key"
          class="col-lg-3 col-sm-4"
        >
          <div class="image-container col-md-12">
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
          <h3 class="heading mb-1">
            <star-rating
              :value="3"
              :show-rating="false"
              @hover:rating="mouseOverRating = $event"
              :increment="0.5"
              :starSize="20"
              :readOnly="true"
              :rating="value.avg_star"
            ></star-rating>
          </h3>
          {{ value.avg_star.toFixed(1) }}/5.0
          <a href="#" @click="move(value.res_num)">
            <h3 class="heading-title mb-0">{{ value.res_name }}</h3>
          </a>
          <h3 class="heading">{{ value.category }}</h3>
          <h6 class="mb-0">{{ value.res_address }}</h6>
          <hr />
        </div>
      </div>
      <div class="row">
      <div class="col-md-5"/>
      <b-pagination
        v-model="currentPage"
        :total-rows="row"
        :per-page="perPage"
        aria-controls="pag"
        class=""
      ></b-pagination>
      </div>
    </div>
  </section>
</template>

<script>
import BaseNav from "@/components/BaseNav";
import CloseButton from "@/components/CloseButton";
import Modal from "@/components/Modal.vue";
import StarRating from "vue-star-rating";
import { BPagination } from "bootstrap-vue";

export default {
  components: {
    BaseNav,
    CloseButton,
    Modal,
    StarRating,
    BPagination
  },
  data() {
    return {
      resThumbnails: null,
      modals: {
        modal1: false,
      },
      checkboxes: {
        한식: true,
        중식: true,
        양식: true,
        일식: true,
        분식: true,
        기타: true,
      },
      radio: {
        radio1: "9999999",
        radio2: "",
      },
      xValue: 37.2822,
      yValue: 126.9994,

      perPage: 20,
      currentPage: 1,
    };
  },
  computed:{
    row() {
      return this.resThumbnails.length;
    },
    itemsForList() {
      return this.resThumbnails.slice(
        (this.currentPage - 1) * this.perPage,
        this.currentPage * this.perPage
      );
    },
  },
  mounted() {
    if (this.$route.query.name != null) {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position) => {
          this.xValue = position.coords.latitude;
          this.yValue = position.coords.longitude;
        });
      }
      this.str = this.$route.query.name;
      this.axios
        // default 전체
        .get(
          `/res/thumbnail/search/${this.str}/${this.xValue},${this.yValue}/500`,{}
        )
        .then((res) => {
          this.resThumbnails = res.data;
        })
        .catch((error) => {
          alert("서버오류");
        });
    } else {
      this.str = this.$route.query.location;
      this.axios
        // default 반경 3km
        .get(
          `/res/thumbnail/region/${this.str}/3`,{}
        )
        .then((res) => {
          this.resThumbnails = res.data;
        })
        .catch((error) => {
          alert("서버오류");
        });
    }
  },
  methods: {
    applyCategory() {
      // 거리 카테고리 변경시
      if (this.radio.radio1 != this.radio.radio2) {
        if (this.$route.query.name){
          this.axios
            .get(
              `/res/thumbnail/search/${this.str}/${this.xValue},${this.yValue}/${this.radio.radio1}`,{}
            )
            .then((res) => {
              this.resThumbnails = res.data;
              this.radio.radio2 = this.radio.radio1;
            })
            .catch((error) => {
              alert("서버오류");
            });
        } else{
          this.axios  ``
            .get(
              `/res/thumbnail/region/${this.str}/${this.radio.radio1}`,{}
            )
            .then((res) => {
              this.resThumbnails = res.data;
              this.radio.radio2 = this.radio.radio1;
            })
            .catch((error) => {
              alert("서버오류");
            });
        }
        
      }
      this.modals.modal1 = false;
    },
    move(resnum){
      this.$router.push({ path: "/respage", query: { res: resnum }});
    },
    check(category) {
      return (
        (!(category in this.checkboxes) && this.checkboxes["기타"] == true) ||
        this.checkboxes[category] == true
      );
    },
  },
};
</script>
<style>
body {
  font-family: "Raleway", sans-serif;
}

.custom-text {
  font-weight: bold;
  font-size: 1.9em;
  border: 1px solid #cfcfcf;
  padding-left: 5px;
  padding-right: 5px;
  border-radius: 2px;
  color: #999;
  background: #fff;
}

.image-container {
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 150px;
  height: 150px;
}
</style>






