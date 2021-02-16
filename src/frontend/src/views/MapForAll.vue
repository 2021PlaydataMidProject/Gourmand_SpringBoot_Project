<template>
   <section class="section section-shaped section-lg my-0">
    <div class="row">
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
        <base-radio name="radio0" class="mb-3" v-model="radio.radio1">
          1km 이내
        </base-radio>
        <base-radio name="radio1" class="mb-3" v-model="radio.radio1">
          3km 이내
        </base-radio>
        <base-radio name="radio2" class="mb-3" v-model="radio.radio1">
          5km 이내
        </base-radio>
        <base-radio name="radio3" class="mb-3" v-model="radio.radio1">
          7km 이내
        </base-radio>
        <base-radio name="radio4" class="mb-3" v-model="radio.radio1">
          9km 이내
        </base-radio>
      </div>
      <div class="col-lg-3 col-sm-6 mt-4 mt-md-0">
        <div class="col-lg-3 col-md-6">
          <!-- Checkboxes -->
          <div class="mb-3">
            <small class="text-uppercase font-weight-bold">음식종류</small>
          </div>
          <base-checkbox name="checkbox1" class="mb-3" v-model="checkboxes">
            한식
          </base-checkbox>
          <base-checkbox
            name="checkbox2"
            class="mb-3"
            v-model="checkboxes.unchecked"
          >
            중식
          </base-checkbox>
          <base-checkbox
            name="checkbox3"
            class="mb-3"
            v-model="checkboxes.unchecked"
          >
            양식
          </base-checkbox>
          <base-checkbox
            name="checkbox4"
            class="mb-3"
            v-model="checkboxes.unchecked"
          >
            일식
          </base-checkbox>          
          <base-checkbox
            name="checkbox6"
            class="mb-3"
            v-model="checkboxes.unchecked"
          >
            분식
          </base-checkbox>
          <base-checkbox
            name="checkbox7"
            class="mb-3"
            v-model="checkboxes.unchecked"
          >
            기타
          </base-checkbox>
        </div>
      </div>
    </div>
          <template slot="footer">
            <base-button type="primary">Save changes</base-button>
            <base-button
              type="link"
              class="ml-auto"
              @click="modals.modal1 = false"
              >Close
            </base-button>
          </template>
        </modal>
      </div>
    </div>

  
 
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
          <a :href="'/respage?' + value.res_num"><h3 class="heading-title mb-0">{{value.res_name}}</h3></a>
          <h3 class="heading">{{value.category}}</h3>
          <h6 class="mb-0">{{value.res_address}}</h6>
          <hr>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import BaseNav from "@/components/BaseNav";
import CloseButton from "@/components/CloseButton";
import Modal from "@/components/Modal.vue";
export default {
  components: {
    BaseNav,
    CloseButton,
    Modal,
  },
  data() {
    return {
      resThumbnails: null,
      modals: {
        modal1: false,
        modal2: false,
        modal3: false,
      },
      checkboxes: {
        unchecked: false,
        checked: true,
      },
      radio: {
        radio1: "radio1",
        radio2: "radio3",
      },
    };
  },
  mounted() {
    var xValue = 37.2822;
    var yValue = 126.9994;
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        xValue = position.coords.latitude;
        yValue = position.coords.longitude;
      });
    }
    this.axios
      .get("/res/thumbnail/" + xValue + "," + yValue, {})
      .then((res) => {
        this.resThumbnails = res;
      })
      .catch((error) => {
        alert("서버오류");
      });
  },
};
</script>
<style>
</style>






