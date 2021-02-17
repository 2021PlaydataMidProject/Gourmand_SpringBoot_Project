<template>
  <canvas id="myChart" :width="this.$props.width" :height="this.$props.height"></canvas>
</template>

<script>
export default {
  props: {
    height: {
      type: String,
      default: "300px",
    },
    width: {
      type: String,
      default: "300px",
    },
    data:{
        type: Array
    }
  },
  mounted() {
    var Chart = require("chart.js");
    var ctx = document.getElementById("myChart");
    new Chart(ctx, {
      type: "radar",
      data: {
        labels: ["맛", "가성비", "친절함", "분위기", "접근성", "청결도"],
        datasets: [
          {
            label: "박민영",
            fontSize: 20,
            data: [this.$props.data[0], this.$props.data[1], this.$props.data[2], this.$props.data[3], this.$props.data[4], this.$props.data[5]],
            backgroundColor: ["rgba(255, 181, 60, 0.7)"],
            borderColor: [
              "rgba(255, 181, 60, 1)",
              "rgba(255, 181, 60, 1)",
              "rgba(255, 181, 60, 1)",
              "rgba(255, 181, 60, 1)",
              "rgba(255, 181, 60, 1)",
              "rgba(255, 181, 60, 1)",
            ],
          },
        ],
      },
      options: {
        scale: {
          gridLines:{
              color: "rgba(255, 181, 60, 1)"
          },
          ticks: {
            beginAtZero: true,
            min: 0,
            max: 5,
            stepSize: 1,
          },
          pointLabels: {
            fontSize: 20,
          },
        },
        legend: {
          position: "right",
          display: false
        },
      },
    });
  },
};
</script>