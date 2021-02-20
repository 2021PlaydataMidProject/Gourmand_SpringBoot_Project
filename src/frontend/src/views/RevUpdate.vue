<template>
  <section class="section section-shaped section-lg my-0">
    <div class="container pt-lg-md">
      <h2 class="mb-5">
        <span>리뷰 수정하기</span>
        <hr />
      </h2>
      <div>
        <h5 class="mb-2">어떤 음식을 드셨나요?</h5>
        <div class="mb-4">
          <base-input
            placeholder="음식 종류"
            class="w-50"
            v-model="foodType"
          ></base-input>
        </div>
        <h5 class="mb-2">항목별 점수를 매겨주세요.</h5>
        <table>
          <tr>
            <td style="width: 80px">맛 :</td>
            <td>
              <star-rating
                v-model="reviewStandard.rflavor"
                :show-rating="true"
                @hover:rating="mouseOverRating = $event"
                :increment="0.5"
                :starSize="20"
              ></star-rating>
            </td>
          </tr>
          <tr>
            <td>분위기 :</td>
            <td>
              <star-rating
                v-model="reviewStandard.rmood"
                :show-rating="true"
                @hover:rating="mouseOverRating = $event"
                :increment="0.5"
                :starSize="20"
              ></star-rating>
            </td>
          </tr>
          <tr>
            <td>친절함 :</td>
            <td>
              <star-rating
                v-model="reviewStandard.rkindness"
                :show-rating="true"
                @hover:rating="mouseOverRating = $event"
                :increment="0.5"
                :starSize="20"
              ></star-rating>
            </td>
          </tr>
          <tr>
            <td>청결도 :</td>
            <td>
              <star-rating
                v-model="reviewStandard.rclean"
                :show-rating="true"
                @hover:rating="mouseOverRating = $event"
                :increment="0.5"
                :starSize="20"
              ></star-rating>
            </td>
          </tr>
          <tr>
            <td>가성비 :</td>
            <td>
              <star-rating
                v-model="reviewStandard.rcost"
                :show-rating="true"
                @hover:rating="mouseOverRating = $event"
                :increment="0.5"
                :starSize="20"
              ></star-rating>
            </td>
          </tr>
          <tr>
            <td>접근성 :</td>
            <td>
              <star-rating
                v-model="reviewStandard.raccess"
                :show-rating="true"
                @hover:rating="mouseOverRating = $event"
                :increment="0.5"
                :starSize="20"
              ></star-rating>
            </td>
          </tr>
        </table>
        <h5 class="mt-4 mb-3">어떻게 표현할 수 있을까요?</h5>
        <textarea
          class="form-control"
          rows="10"
          placeholder="리뷰 내용"
          style="height: 300px"
          v-model="review"
        />
        <h5 class="mt-4 mb-3">등록된 사진을 삭제할 수 있어요.</h5>
        <div class="row">
          <div class="col-md-3" v-for="(value, key) in imgs" v-bind:key="key">
            <img
              v-lazy="'../img/rev/' + value.name"
              alt="Rounded image"
              class="img-fluid rounded shadow"
              style="width: 150px"
              @click="delImg(key)"
            />
            {{ value.origin_name }}
          </div>
        </div>
        <h5 class="mt-4 mb-3">사진을 추가해주세요!</h5>
        <input
          type="file"
          ref="imgdata"
          name="imgdata[]"
          accept="image/*"
          multiple="multiple"
        />
        <div class="mt-4">
          <base-button
            @click="addRev()"
            type="primary"
            class="my-3 col-lg-2 col-sm-2"
            >수정하기</base-button
          >
        </div>
      </div>
    </div>
  </section>
</template>
<script>
import StarRating from "vue-star-rating";

export default {
  components: {
    StarRating,
  },
  data() {
    return {
      foodType: "",
      reviewStandard: {
        rflavor: 0,
        raccess: 0,
        rcost: 0,
        rkindness: 0,
        rmood: 0,
        rclean: 0,
      },
      review: "",
      imgs: [],
      delimgs: [],
    };
  },
  methods: {
    addRev() {
      const formData = new FormData();
      formData.append("reviewStandard", JSON.stringify(this.reviewStandard));
      formData.append(
        "reviewRegi",
        JSON.stringify({ foodType: this.foodType, review: this.review })
      );
      formData.append("resNum", this.$route.query.name);
      for (let i = 0; i < this.$refs.imgdata.files.length; i++) {
        formData.append("revImg", this.$refs.imgdata.files[i]);
      }

      if (this.delimgs.length > 0) {
        this.axios.post("/rev/delete/img", this.delimgs)
          .catch((error) => console.log("에러"));
      }

      return this.axios
        .put(`/rev/${this.$route.query.rev}/update`, formData, {
          headers: {
            "Content-Type": `multipart/form-data`,
          },
        })
        .then((res) => {
          alert("수정 완료");
          location.href = "/respage?res=" + this.$route.query.name;
        })
        .catch((error) => {
          alert("에러");
        });
    },
    delImg(value) {
      this.delimgs.push(this.imgs[value].review_img_id);
      this.imgs.splice(value, 1);
    },
  },
  mounted() {
    if (sessionStorage.getItem("user") == null) {
      location.href = "/register";
    }

    this.axios.get(`/rev/${this.$route.query.rev}/revinfo`, {}).then((req) => {
      this.reviewStandard.rflavor = req.data.review_standard.rflavor;
      this.reviewStandard.rcost = req.data.review_standard.rcost_value;
      this.reviewStandard.rkindness = req.data.review_standard.rkindness;
      this.reviewStandard.rmood = req.data.review_standard.rmood;
      this.reviewStandard.rclean = req.data.review_standard.rclean;
      this.reviewStandard.raccess = req.data.review_standard.raccess;
      this.foodType = req.data.food_type;
      this.review = req.data.review;
      this.imgs = req.data.review_img;
    });
  },
};
</script>
<style>
td {
  height: 30px;
}
</style>
