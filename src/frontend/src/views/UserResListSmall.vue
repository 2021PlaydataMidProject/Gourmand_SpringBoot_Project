<template>
  <div>
    <base-button block type="primary" class="mb-3" @click="read()">
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
          <base-button type="primary" @click="add(value)" class="ml-4"
          >추가하기</base-button
        >
        </p>
        
        <hr class="mt-0" />
      </div>
      <div class="row mt-3 col-md-12">
      
      </div>
      <template slot="footer">
        <base-input class="col-mb-4 mr-3" placeholder="리스트명" v-model="newname"></base-input>
        <base-button type="primary" @click="add(newname)"
          >추가하기</base-button
        >
        <base-button type="link" class="ml-auto" @click="modals.modal = false"
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
    res: null,
  },
  data() {
    return {
      reslist: [],
      modals: {
        modal: false,
      },
      newname: ""
    };
  },
  methods: {
    read() {
      this.axios
        .get("/user/reslist/", {})
        .then((res) => {
          this.reslist = res.data;
          console.log(res.data)
        })
        .catch((res) => {
          alert("서버 오류");
        });
      this.modals.modal = true;
    },
    add(value) {
      const formdata = new FormData();
      formdata.append("res", this.$props.res);
      formdata.append("listName", value);
      this.axios.post("res/user/insert", formdata)
      .then(res => alert("등록되었습니다."))
      .catch(error=>alert(error));
      this.modals.modal = false;
    },
  },
};
</script>
<style>
</style>
