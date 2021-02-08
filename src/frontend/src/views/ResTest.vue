<template>
  <section class="section section-shaped section-lg my-0">
    <div class="text-center">
        <table border="1">
          <tr>
            <td width="70">가게이름</td>
            <td align="left">
              <input type="text" v-model="newRes.resName" />
            </td>
          </tr>
          <tr>
            <td width="70">xValue</td>
            <td align="left">
              <input type="text" v-model="newRes.xValue" />
            </td>
          </tr>
          <tr>
            <td width="70">yValue</td>
            <td align="left">
              <input type="text" v-model="newRes.yValue" />
            </td>
          </tr>
          <tr>
            <td>운영시간</td>
            <td align="left">
              <input type="text" v-model="newRes.resHour" size="10" />
            </td>
          </tr>
          <tr>
            <td>전화번호</td>
            <td align="left">
              <input type="text" v-model="newRes.tel" size="10" />
            </td>
          </tr>
          <tr>
            <td>카테고리</td>
            <td align="left">
              <input type="text" v-model="newRes.category" size="10" />
            </td>
          </tr>
          <tr>
            <td colspan="2" align="center">
              <base-button type="primary" v-on:click="addRes()">가게 등록</base-button>
            </td>
          </tr>
        </table>
    </div>

  </section>
</template>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
import BaseButton from '../components/BaseButton.vue';
export default {
  data: function(){
      return {
        newRes:{
            resName:"",
            xValue:"",
            yValue:"",
            resHour:"",
            tel:"",
            category:""
        }
      }
  },
  methods:{
      addRes: function(){
          return 
      }
  },
  components: { BaseButton },
};
</script>
<style>
</style>
