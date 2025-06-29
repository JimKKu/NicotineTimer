<template>
  <div :class="['container', hidden ? 'container-show' : 'container-hidden']">
    <div ref="chartRef" class="chart"></div>
    <div class="guide"></div>
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

function getDateRange(start: string, end: string): string[] {
  const result: string[] = []
  const current = new Date(
    parseInt(start.slice(0, 4)),
    parseInt(start.slice(4, 6)) - 1,
    parseInt(start.slice(6, 8))
  )
  const endDate = new Date(
    parseInt(end.slice(0, 4)),
    parseInt(end.slice(4, 6)) - 1,
    parseInt(end.slice(6, 8))
  )

  while (current <= endDate) {
    const yyyy = current.getFullYear()
    const mm = String(current.getMonth() + 1).padStart(2, '0')
    const dd = String(current.getDate()).padStart(2, '0')
    result.push(`${yyyy}${mm}${dd}`)
    current.setDate(current.getDate() + 1)
  }
  return result
}

onMounted(async () => {
  const today = getToday()
  const startDate = '20250602'

  const res = await fetch(`/api/iTimer/smoke/chart/${startDate}/${today}`)
  const originalData: ChartData[] = await res.json()

  const dateMap = new Map<string, number>()
  originalData.forEach(d => {
    dateMap.set(d.date, d.count)
  })

  const fullDates = getDateRange(startDate, today)

  const xLabels: string[] = []
  const counts: number[] = []

  let hasMonth1 = false

  for (let i = 0; i < fullDates.length; i++) {
    const dateStr = fullDates[i]
    const count = dateMap.get(dateStr) ?? 0
    counts.push(count)

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
    const firstMonth = parseInt(fullDates[0].slice(4, 6), 10) + '月'
    xLabels[0] = firstMonth
  }

  // 初始化 echarts（不变）
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

.guide::after {
  content: "尼古丁的回响（次/天）";
  display: inline-block;
  background-color: #5470C6;
  width: 178px;
  height: 26px;
  position: absolute;
  top: -14px;
  left: 50%;
  transform: translateX(-50%);
  border-radius: 4px;
  text-align: center;
  line-height: 26px;
  color: #fff;
}
</style>
