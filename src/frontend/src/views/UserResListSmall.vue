<template>
  <div>
    <base-button block type="primary" class="mb-3" @click="modals.modal = true">
      내 맛집 리스트에 등록하기
    </base-button>
    <modal :show.sync="modals.modal">
      <h5 class="mb-5">추가할 리스트를 선택하세요.</h5>
      <div v-if="reslist.length == 0">
        <h5 class="h5 font-weight-bold">아직 등록된 리스트가 없어요.</h5>
      </div>
      <div v-else class="col-md-12">
        <p
          v-for="(value, key) in reslist"
          v-bind:key="key"
          class="font-weight-bold"
        >
          {{ value }}
        </p>
        <hr class="mt-0" />
      </div>
      <div class="row mt-3 col-md-12">
        <p class="text-bold font-weight-bold col-md-3">새로 추가</p>
        <base-input class="col-mb-4 mr-3" placeholder="리스트명"></base-input>
        <base-button>추가</base-button>
      </div>
      <template slot="footer">
        <base-button type="primary" @click="applyCategory()"
          >추가하기</base-button
        >
        <base-button type="link" class="ml-auto" @click="modal.modal1 = false"
          >Close
        </base-button>
      </template>
    </modal>
  </div>
</template>
<script>
import Modal from "@/components/Modal.vue";
import BaseButton from "../components/BaseButton.vue";

export default {
  components: {
    Modal,
    BaseButton,
  },
  props: {
    user: null,
  },
  data() {
    return {
      reslist: [],
      modals: {
        modal: false,
      },
    };
  },
  mounted() {
    this.axios
      .get("/user/reslist/" + this.$props.user, {})
      .then((res) => {
        this.reslist = res.data;
      })
      .catch((res) => {
        alert("서버 오류");
      });
  },
};
</script>
<style>
</style>
