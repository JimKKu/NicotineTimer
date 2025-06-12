<template>
  <div ref="chartRef" style="width: 100%; height: 400px"></div>
</template>

<script setup>
import * as echarts from 'echarts'
import { onMounted, onBeforeUnmount, ref } from 'vue'

const chartRef = ref(null)
let chartInstance = null

const daysOfWeek = ['周日', '周一', '周二', '周三', '周四', '周五', '周六','周日', '周一', '周二', '周三', '周四', '周五', '周六','周日', '周一', '周二', '周三', '周四', '周五', '周六','周日', '周一', '周二', '周三', '周四', '周五', '周六']
const smokeCount = [24, 23, 22, 24, 23, 21, 22,23,19, 17, 18,17 , 16, 14,15, 13, 12, 15, 12, 11, 10,11,10, 10, 9,8 , 7, 8,] // 模拟数据：对应每一天的次数

onMounted(() => {
  chartInstance = echarts.init(chartRef.value)

  const option = {
    title: {
      text: '一周戒烟次数记录',
      left: 'center'
    },
    tooltip: {
      trigger: 'axis'
    },
    xAxis: {
      type: 'category',
      data: daysOfWeek,
      axisTick: {
        alignWithLabel: true
      }
    },
    yAxis: {
      type: 'value',
      name: '次数'
    },
    series: [
      {
        name: '次数',
        type: 'bar',
        data: smokeCount,
        barWidth: '50%',
        itemStyle: {
          color: '#00bd7e'
        }
      }
    ]
  }

  chartInstance.setOption(option)

  window.addEventListener('resize', resizeChart)
})

onBeforeUnmount(() => {
  if (chartInstance) {
    chartInstance.dispose()
  }
  window.removeEventListener('resize', resizeChart)
})

function resizeChart() {
  if (chartInstance) {
    chartInstance.resize()
  }
}
</script>
