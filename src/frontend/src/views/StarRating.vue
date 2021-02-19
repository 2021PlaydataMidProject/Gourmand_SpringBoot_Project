<template>
  <section class="section section-shaped section-lg my-0">
    <div class="shape shape-style-1 bg-gradient-default">
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
    </div>
    <div>
      <h1>StarRating</h1>
      <h2>0.5씩 커지는 StarRating</h2>
      <div @mouseleave="mouseOverRating = null" style="display: inline-block">
        <star-rating
          :defaultValue="3"
          :show-rating="false"
          @hover:rating="mouseOverRating = $event"
          :increment="0.5"
          :starSize="20"
          :readOnly="false"  
          :rating="3"

        ></star-rating>
      </div>
      <div style="margin-top: 10px; font-weight: bold">
        {{ mouseOverRatingText }}
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
  computed: {
    currentRatingText() {
      return this.rating
        ? "You have selected " + this.rating + " stars"
        : "No rating selected";
    },
    mouseOverRatingText() {
      return this.mouseOverRating
        ? "Click to select " + this.mouseOverRating + " stars"
        : "No rating selected";
    },
  },
  data() {
    return {
      rating: null,
      resetableRating: 2,
      currentRating: "No Rating",
      mouseOverRating: null,
    };
  },
  methods: {
    showCurrentRating(rating) {
      this.currentSelectedRating =
        rating === 0
          ? this.currentSelectedRating
          : "Click to select " + rating + " stars";
    },
    setCurrentSelectedRating(rating) {
      this.currentSelectedRating = "You have Selected: " + rating + " stars";
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
</style>
