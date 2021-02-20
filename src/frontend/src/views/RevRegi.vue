<template>
  <section class="section section-shaped section-lg my-0">
    <div class="container pt-lg-md">
      <h2 class="mb-5">
        <span>리뷰 등록하기</span>
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
        <h5 class="mt-4 mb-3">찍으신 사진이 있나요?</h5>
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
            >등록하기</base-button
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
        rmood: 0,
        rkindness: 0,
        rclean: 0,
        raccess: 0,
        rcost: 0,
      },
      review: "",
    };
  },
  methods: {
    addRev() {
      const formData = new FormData();
      formData.append("reviewStandard", JSON.stringify(this.reviewStandard));
      formData.append("reviewRegi", JSON.stringify({ foodType: this.foodType, review: this.review }));
      formData.append("resNum", this.$route.query.name);

      console.log(sessionStorage)
      for (let i = 0; i < this.$refs.imgdata.files.length; i++) {
        formData.append("revImg", this.$refs.imgdata.files[i]);
      }
      return this.axios
        .post("/rev/regi", formData, {
          headers: {
            "Content-Type": `multipart/form-data`
          }
        })
        .then((res) => {
          alert("등록 완료");
          location.href = "/respage?res=" + this.$route.query.name;
        })
        .catch((error) =>{
            alert("에러");
        });
    },
  },
  mounted() {
    if (sessionStorage.getItem("user") == null) {
      location.href = "/register";
    }
  },
};
</script>
<style>
td {
  height: 30px;
}
</style>
