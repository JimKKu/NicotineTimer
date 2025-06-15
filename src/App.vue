<template>

  <!-- 按钮组 -->
  <div class="button-group">
    <button @click="show_chart"><el-icon><TrendCharts /></el-icon></button>
    <button @click="show_pic"><el-icon><Camera /></el-icon></button>
    <button @click="switch_timer"><el-icon><Switch /></el-icon></button>
  </div>
  <!-- 计时器 -->
  <TimerItem :mode="time_mode" :chart_flag="chart_flag" :pic_flag="pic_flag"/>
  <!-- 柱状图 -->
  <ChartItem :hidden="chart_flag" style="z-index: 100"/>
  <!-- 底部文字 -->
  <BottomItem :num="123" style="z-index: 100"/>
  <!-- 右边的图片 -->
  <PictureItem :hidden="pic_flag" :chart_flag="chart_flag" style="z-index: 10"/>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import ChartItem from './components/ChartItem.vue'
import BottomItem from './components/BottomItem.vue'
import TimerItem from './components/TimerItem.vue'
import PictureItem from './components/PictureItem.vue'
// ICON
import { TrendCharts,Camera,Switch } from '@element-plus/icons-vue'


// 输出一句话
onMounted(() => {
  console.log('尽早戒烟有益身体健康！！！')
})

// 柱状图相关
const chart_flag = ref(false)

function show_chart() {
  chart_flag.value = ! chart_flag.value
}

// 相册相关
const pic_flag = ref(false)
function show_pic() {
  pic_flag.value = ! pic_flag.value
}

const time_mode = ref('small')
function switch_timer() {
  if(time_mode.value === 'big'){
    time_mode.value = 'small'
  } else {
    time_mode.value = 'big'
  }
}
</script>

<style scoped>
.button-group {
  display: flex;
  flex-direction: row;
}
button {
  background: none;
  border: none;
  cursor: pointer;
  padding: 4px;
  font-size: 18px;
  margin: 10px 0 10px 20px;
  border-bottom: 1px solid gray;
  color: gray;
  transition: all .2s ease;
}
button:hover {
  background-color: #f0f0f0;
  border-radius: 4px;
  color: #00bd7e;
}
</style>

<!--
<button @click="show_chart">📈</button>
<button @click="show_pic">📷</button>
<button @click="show_pic">🔠</button>
-->