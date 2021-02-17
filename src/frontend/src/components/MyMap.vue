<template>
  <div>My Gourmand map</div>
</template>

<script>
export default {
    data () {
    return {
      mymap: []
        }
    },
    created () {
    // 컴포넌트가 생성될 때, /api/mymap에 요청을 보냅니다.          
    this.$http.get('/api/mymap/${id}')
        .then((response) => {
          this.mymap = response.data
        })
  }
}
</script>

<style>

</style>
