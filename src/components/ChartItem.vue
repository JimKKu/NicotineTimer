<template>
  <div :class="['container', hidden ? 'container-show' : 'container-hidden']">
    <div ref="chartRef" class="chart"></div>
  </div>
</template>

<script setup lang="ts">
import * as echarts from 'echarts'
import { onMounted, onBeforeUnmount, ref, watch } from 'vue'

// 定义 props
const { hidden } = defineProps<{
  hidden: boolean
}>()

// DOM 节点引用
const chartRef = ref<HTMLDivElement | null>(null)

// 明确类型：ECharts 实例
let chartInstance: echarts.ECharts | null = null

// 监听 hidden 控制图表大小调整
watch(() => hidden, (newVal) => {
  if (newVal && chartInstance) {
    setTimeout(() => {
      chartInstance!.resize()
    }, 100)
  }
})

// 封装今日日期
function getToday(): string {
  const today = new Date()
  const yyyy = today.getFullYear()
  const mm = String(today.getMonth() + 1).padStart(2, '0')
  const dd = String(today.getDate()).padStart(2, '0')
  return `${yyyy}${mm}${dd}`
}

// 自动调整图表尺寸
function resizeChart() {
  chartInstance?.resize()
}

interface ChartData {
  date: string
  count: number
}

onMounted(async () => {
  // 获取数据并解析为固定类型
  const res = await fetch('/api/iTimer/smoke/chart/20250602/' + getToday())
  const data: ChartData[] = await res.json()

  const rawDates = data.map((d: ChartData) => d.date)
  const counts = data.map((d: ChartData) => d.count)

  const xLabels: string[] = []
  let hasMonth1 = false

  for (let i = 0; i < rawDates.length; i++) {
    const dateStr = rawDates[i]
    const day = dateStr.slice(6, 8)
    const month = parseInt(dateStr.slice(4, 6), 10) + '月'

    if (day === '01') {
      xLabels.push(month)
      hasMonth1 = true
    } else {
      xLabels.push(day.replace(/^0/, ''))
    }
  }

  if (!hasMonth1 && xLabels.length > 0) {
    const firstMonth = parseInt(rawDates[0].slice(4, 6), 10) + '月'
    xLabels[0] = firstMonth
  }

  // 初始化图表实例
  if (chartRef.value) {
    chartInstance = echarts.init(chartRef.value)

    const option = {
      title: {
        text: '',
        left: 'center',
        fontSize: '8',
      },
      tooltip: {
        trigger: 'axis'
      },
      xAxis: {
        type: 'category',
        data: xLabels,
        axisTick: {
          alignWithLabel: true
        }
      },
      yAxis: {
        type: 'value',
        name: ''
      },
      series: [
        {
          name: '次数',
          type: 'bar',
          data: counts,
          barWidth: '50%',
        }
      ]
    }

    chartInstance.setOption(option)
    window.addEventListener('resize', resizeChart)
  }
})

onBeforeUnmount(() => {
  chartInstance?.dispose()
  window.removeEventListener('resize', resizeChart)
})
</script>

<style scoped>
.container {
  width: 100%;
  height: 24%;
  transition: all .2s;
  position: absolute;
  left: 0;
  box-sizing: border-box;
  border-top: 1px solid #acacac;
  background: #f0f0f0;
}
.chart {
  margin: 0 auto;
  width: 80%;
  height: 100%;
  transition: all 1s;
}
.container-show {
  bottom: 0;
}
.container-hidden {
  transform: translateY(200%);
  opacity: 0%;
  bottom: -100%;
}
</style>
