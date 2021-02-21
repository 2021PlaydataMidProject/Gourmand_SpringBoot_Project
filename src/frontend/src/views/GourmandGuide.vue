<template>
  <section class="section section-shaped section-lg my-0">
    <div class="container pt-lg-md">
      <h2 class="mb-5">
        <span>구르망 가이드</span>
        <hr />
      </h2>
      <div class="row">
        <base-button
          class="col-sm-2"
          outline
          type="tertiary"
          @click="numChange(0)"
          ><div>
            <img
              src="img/guide/맛있는녀석들.png"
              alt="Rounded image"
              class="img-fluid rounded shadow"
              style="height: 70px"
            />
          </div>
          맛있는 녀석들
        </base-button>
        <base-button
          class="btn-1 col-sm-2"
          outline
          type="tertiary"
          @click="numChange(5)"
        >
          <div>
            <img
              src="img/guide/수요미식회.png"
              alt="Rounded image"
              class="img-fluid rounded shadow"
              style="height: 70px"
            />
          </div>
          수요미식회
        </base-button>
        <base-button
          class="btn-1 col-sm-2"
          outline
          type="tertiary"
          @click="numChange(1)"
        >
          <div>
            <img
              src="img/guide/생활의달인.png"
              alt="Rounded image"
              class="img-fluid rounded shadow"
              style="height: 70px"
            />
          </div>
          생활의 달인
        </base-button>
        <base-button
          class="btn-1 col-sm-2"
          outline
          type="tertiary"
          @click="numChange(2)"
        >
          <div>
            <img
              src="img/guide/이영자.jpg"
              alt="Rounded image"
              class="img-fluid rounded shadow"
              style="height: 70px"
            />
          </div>
          이영자
        </base-button>
        <base-button
          class="btn-1 col-sm-2"
          outline
          type="tertiary"
          @click="numChange(3)"
        >
          <div>
            <img
              src="img/guide/최자.jfif"
              alt="Rounded image"
              class="img-fluid rounded shadow"
              style="height: 70px"
            />
          </div>
          최자
        </base-button>
        <base-button
          class="btn-1 col-sm-2 mt-2"
          outline
          type="tertiary"
          @click="numChange(4)"
        >
          <div>
            <img
              src="img/guide/화사.jfif"
              alt="Rounded image"
              class="img-fluid rounded shadow"
              style="height: 70px"
            />
          </div>
          화사
        </base-button>
        <base-button
          class="btn-1 col-sm-2 mt-2"
          outline
          type="tertiary"
          @click="numChange(6)"
        >
          <div>
            <img
              v-lazy="'img/brand/logo.png'"
              class="Rounded image"
              style="height: 70px"
            />
          </div>
          구르망 <br />베스트 맛집
        </base-button>
        <base-button
          class="btn-1 col-sm-2 mt-2"
          outline
          type="tertiary"
          @click="numChange(7)"
        >
          <div>
            <img
              v-lazy="'img/brand/logo.png'"
              class="Rounded image"
              style="height: 70px"
            />
          </div>
          popular <br />Gourmand
        </base-button>
      </div>
      <div v-if="num >= 0 && num < 6">
        <h3 class="mb-2 mt-5">
          <span>{{ name }} 님의 맛집리스트</span>
          <hr />
        </h3>
        <div class="" v-for="key in listNames" v-bind:key="key">
          <h4 class="mb-2 mt-2">{{ key }}</h4>
          <div class="row">
            <div
              v-for="(value, key) in resList[key]"
              v-bind:key="key"
              class="col-lg-3 col-sm-4"
            >
              <img
                v-if="value.res_img != null"
                :src="'img/res/' + value.res_img.name"
                alt="Rounded image"
                class="img-fluid rounded shadow"
                style="height: 150px"
              />
              <img
                v-else
                v-lazy="'img/theme/team-1-800x800.jpg'"
                alt="Rounded image"
                class="img-fluid rounded shadow"
                style="width: 150px"
              />
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

              <a :href="'/respage?' + value.res_num"
                ><h3 class="heading-title mb-0">{{ value.res_name }}</h3></a
              >
              <h3 class="heading">{{ value.category }}</h3>
              <h6 class="mb-0">{{ value.res_address }}</h6>
              <hr class="mb-1" />
            </div>
          </div>
        </div>
      </div>
      <div v-else-if="num == 6">
        <h3 class="mb-2 mt-5">
          <span>구르망 베스트 맛집</span>
          <hr />
        </h3>
        <div class="row">
          <div
            v-for="(value, key) in resList"
            v-bind:key="key"
            class="col-lg-3 col-sm-4"
          >
            <img
              v-if="value.res_img != null"
              :src="'img/res/' + value.res_img.name"
              alt="Rounded image"
              class="img-fluid rounded shadow"
              style="height: 150px"
            />
            <img
              v-else
              v-lazy="'img/theme/team-1-800x800.jpg'"
              alt="Rounded image"
              class="img-fluid rounded shadow"
              style="width: 150px"
            />
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

            <a :href="'/respage?' + value.res_num"
              ><h3 class="heading-title mb-0">{{ value.res_name }}</h3></a
            >
            <h3 class="heading">{{ value.category }}</h3>
            <h6 class="mb-0">{{ value.res_address }}</h6>
            <hr class="mb-1" />
          </div>
        </div>
      </div>
      <div v-else-if="num == 7" class="row mt-3">
        <div class="col-sm-3 col-lg-4"></div>
        <div  class="col-sm-6 col-lg-4">
        <tabs :fill="false" circle>
          <tab-pane>
            <span slot="title" class="font-weight-bold" @click="popular(0)"
              >1st</span>
          </tab-pane>
          <tab-pane>
            <span slot="title" class="font-weight-bold" @click="popular(1)"
              >2nd</span>
          </tab-pane>
          <tab-pane>
            <span slot="title" class="font-weight-bold" @click="popular(2)"
              >3rd</span>
          </tab-pane>
        </tabs>
        </div>
        <div class="col-sm-3 col-lg-4"></div>
        <div v-if="name != ''">
          <h3 class="mb-2 mt-5">
            <span>{{ name }} 님의 맛집리스트</span>
            <hr />
          </h3>
          <div class="" v-for="key in listNames" v-bind:key="key">
            <h4 class="mb-2 mt-2">{{ key }}</h4>
            <div class="row">
              <div
                v-for="(value, key) in resList[key]"
                v-bind:key="key"
                class="col-lg-3 col-sm-4"
              >
                <img
                  v-if="value.res_img != null"
                  :src="'img/res/' + value.res_img.name"
                  alt="Rounded image"
                  class="img-fluid rounded shadow"
                  style="height: 150px"
                />
                <img
                  v-else
                  v-lazy="'img/theme/team-1-800x800.jpg'"
                  alt="Rounded image"
                  class="img-fluid rounded shadow"
                  style="width: 150px"
                />
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

                <a :href="'/respage?' + value.res_num"
                  ><h3 class="heading-title mb-0">{{ value.res_name }}</h3></a
                >
                <h3 class="heading">{{ value.category }}</h3>
                <h6 class="mb-0">{{ value.res_address }}</h6>
                <hr class="mb-1" />
              </div>
            </div>
          </div>
        </div>
        <div v-else>
          <h3 class="mb-2 mt-5">
            <span>없어요.....</span>
            <hr />
          </h3>
        </div>
      </div>
    </div>
  </section>
</template>
<script>
import StarRating from "vue-star-rating";
import Tabs from "@/components/Tabs/Tabs.vue";
import TabPane from "@/components/Tabs/TabPane.vue";

export default {
  components: {
    StarRating,
    Tabs,
    TabPane,
  },
  data() {
    return {
      num: -1,
      resList: "",
      listNames: [],
      name: "",
      celebs: [
        "맛있는 녀석들",
        "생활의 달인",
        "이영자",
        "최자",
        "화사",
        "수요미식회",
      ],
      userids: null,
      usernames: [],
    };
  },
  methods: {
    numChange(value) {
      this.num = value;

      if (value < 6) {
        this.axios
          .get(`/res/user/${value + 1}/list`)
          .then((req) => {
            this.resList = req.data;
            let names = [];
            for (let key in this.resList) {
              names.push(key);
            }
            this.listNames = names;
            this.name = this.celebs[value];
          })
          .catch((error) => {
            console.log("dhfb");
          });
      } else if (value == 6) {
        ("/res/thumbnail/avgstar/{limit}/{xValue},{yValue}");
        this.axios
          .get(`/res/thumbnail/avgstar/500/37.2822,126.9994`, {})
          .then((res) => {
            this.resList = res.data;
          })
          .catch((error) => {
            alert("서버오류");
          });
      } else {
        this.axios
          .get("/user/popular")
          .then((res) => {
            this.userids = res.data;
            this.popular(0);
          })
          .catch((error) => alert("서버오류"));
      }
    },
    popular(value) {
      if (this.userids.length > value) {
        this.axios
          .get(`/res/user/${this.userids[value].user_num}/list`)
          .then((req) => {
            console.log(req);
            this.resList = req.data;
            let names = [];
            for (let key in this.resList) {
              names.push(key);
            }
            this.listNames = names;
            this.name = this.userids[value].user_name;
          })
          .catch((error) => {
            alert("서버오류");
          });
      } else {
        this.name = "";
      }
    },
  },
};
</script>
<style>
</style>
