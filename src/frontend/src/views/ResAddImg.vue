<template>
  <section class="section section-shaped section-lg my-0">
    <div class="text-center">

      <input type="number" v-model="resnum"/>
      {{resnum}}
      가게정보사진
      <input
        type="file"
        ref="imgdata"
        name="imgdata[]"
        accept="image/*"
        multiple="multiple"
      />

      <div>Icons made by <a href="https://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>


      <base-button type="primary" v-on:click="addRes()">가게 등록</base-button>
    </div>
  </section>
</template>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
import BaseButton from "../components/BaseButton.vue";
export default {
  data: function () {
    return {
      resnum: 0
    };
  },
  methods: {
    addRes: function () {
      const formData = new FormData();

      for (let i = 0; i < this.$refs.imgdata.files.length; i++) {
        formData.append("resImg", this.$refs.imgdata.files[i]);
      }
      console.log(this.resnum)
      return this.axios
        .post(`/res/${this.resnum}/update/img`, formData, {
          headers: {
            "Content-Type": `multipart/form-data`,
          },
        })
        .then((res) => {
          alert("가게 등록완료");
        });
    },
  },
  components: { BaseButton },
};
</script>
<style>
</style>
