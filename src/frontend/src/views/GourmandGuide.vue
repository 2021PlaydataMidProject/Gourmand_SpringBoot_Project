<template>
  <section class="section section-shaped section-lg my-0">
    <div class="container pt-lg-md">
      <h2 class="mb-5">
        <span>구르망 가이드</span>
        <hr />
      </h2>
      <div>
        <table border="1" class="text-center">
          <tr>
            <td @click="numChange(0)">
              <div>
                <img
                  src="img/guide/맛있는녀석들.png"
                  alt="Rounded image"
                  class="img-fluid rounded shadow"
                  style="height: 50px"
                />
              </div>
              맛있는 녀석들
            </td>
            <td @click="numChange(1)">
              <div>
                <img
                  src="img/guide/수요미식회.png"
                  alt="Rounded image"
                  class="img-fluid rounded shadow"
                  style="width: 70px"
                />
              </div>
              수요미식회
            </td>
            <td @click="numChange(2)">
              <div>
                <img
                  src="img/guide/생활의달인.png"
                  alt="Rounded image"
                  class="img-fluid rounded shadow"
                  style="height: 50px"
                />
              </div>
              생활의 달인
            </td>
          </tr>
          <tr>
            <td @click="numChange(3)">
              <div>
                <img
                  src="img/guide/이영자.jpg"
                  alt="Rounded image"
                  class="img-fluid rounded shadow"
                  style="height: 50px"
                />
              </div>
              이영자
            </td>
            <td @click="numChange(4)">
              <div>
                <img
                  src="img/guide/최자.jfif"
                  alt="Rounded image"
                  class="img-fluid rounded shadow"
                  style="width: 70px"
                />
              </div>
              최자
            </td>
            <td @click="numChange(5)">
              <div>
                <img
                  src="img/guide/화사.jfif"
                  alt="Rounded image"
                  class="img-fluid rounded shadow"
                  style="height: 50px"
                />
              </div>
              화사
            </td>
          </tr>
          <tr>
            <!-- 전체 회원 기준 별점 베스트 -->
            <td @click="numChange(6)">
              <div>
                <img
                  v-lazy="'img/brand/logo.png'"
                  class="Rounded image"
                  style="width: 50px"
                />
              </div>
              구르망 <br />베스트 맛집
            </td>
            <!-- 팔로워 가장 많은 사람 3명? -->
            <td>
              <div>
                <img
                  v-lazy="'img/brand/logo.png'"
                  class="Rounded image"
                  style="width: 50px"
                />
              </div>
              popular <br />Gourmand
            </td>
          </tr>
        </table>
      </div>
      <div v-if="num>=0 && num<6">
        <h3 class="mb-2 mt-5">
          <span>{{name}} 님의 맛집리스트</span>
          <hr>
        </h3>
        <div class="" v-for="key in listNames" v-bind:key="key" >
            <h4 class="mb-2 mt-2">{{key}}</h4>
            <div class="row">
          <div v-for="(value, key) in resList[key]" v-bind:key="key" class="col-lg-3 col-sm-4"
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
    </div>
  </section>
</template>
<script>
import StarRating from "vue-star-rating";

export default {
  components: {
      StarRating
  },
  data() {
      return {
          num:-1,
          resList: "",
          listNames: [],
          name: "",
          celebs: ["맛있는 녀석들", "수요미식회", "생활의 달인", "이영자", "최자", "화사"]
      }
  },
  methods:{
      numChange(value){
        this.num=value;

        this.axios.get(`/res/user/${value+1}/list`)
            .then( req => {
                this.resList = req.data;
                let names=[];
                for(let key in this.resList){
                    names.push(key)
                }
                this.listNames = names;
                this.name = this.celebs[value];
            })
            .catch(error=>{
                console.log("dhfb")
            })
      },
  }
};
</script>
<style>
</style>
