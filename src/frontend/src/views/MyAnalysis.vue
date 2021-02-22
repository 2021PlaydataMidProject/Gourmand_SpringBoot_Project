<template>
  <section class="col-md-9">
    <div class="col-md-12">
      <b-tabs content-class="mt-3">
        <b-tab title="선호 입맛" active>
           <h2 class="tagtitle">선호입맛</h2><br/>
          <div class="row">
            <div class="col-md-4"/>
            <div class="col-md-6">

              <h3 class="commentuser">당신은 진정한 미식가!</h3>
              <div class="maincontainer">
                <ul class="list">
              <li v-for="(rev, key) in revs" v-bind:key="key" class="row">
                {{ rev }}
              </li>
              </ul>
              </div>
            </div>
          </div>
        </b-tab>
      </b-tabs>
    </div>
  </section>
</template>
<script>
import { BTabs } from "bootstrap-vue";
import { BTab } from "bootstrap-vue";

export default {
  components: {
    BTabs,
    BTab,
  },
  data() {
    return {
      revs: [],
    };
  },
  mounted() {
    this.axios
      .get("/user/userAnalysis/foodType", {})
      .then((res) => {
        this.revs = res.data;
      })
      .catch((error) => {
        alert("서버 오류");
      });
  },
};
</script>
<style>
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

.tagtitle{
    float: left;
    color: rgb(0, 0, 0);
    font-size: 19px;
    font-weight: 700;
    letter-spacing: -0.7px;
    line-height: 28px;
    margin: 8px 0px;}

    .commentuser {
    color: rgb(255, 47, 110);
    font-size: 13px;
    font-weight: 400;
    letter-spacing: -0.2px;
    text-align: center;
    margin: 8px 0px 0px;
    line-height: normal !important;
}

html,body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn, em, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var, b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas, details, embed, figure, figcaption, footer, header, hgroup, menu, nav, output, ruby, section, summary, time, mark, audio, video, button {
    padding: 0px;
    margin: 0px;
    font-size: 100%;
    box-sizing: border-box;
    border: 0px;
    vertical-align: baseline;
}
.maincontainer{
    padding: 32px;
    margin: 4px;
    font-size: 100%;
    box-sizing: border-box;
    border: 0px;
    vertical-align: baseline;
    float: center;
    color: rgb(0, 0, 0);
    font-size: 19px;
    font-weight: 700;
    letter-spacing: -0.7px;
    line-height: 28px;
    margin: 8px 0px;
    
}
</style>