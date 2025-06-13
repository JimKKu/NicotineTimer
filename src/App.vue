<template>

  <!-- 按钮 -->
  <button @click="show_chart" class="button-chart">📈</button>
  <!-- 计时器 -->
  <TimerItem :hidden="hidden"/>
  <!-- 柱状图 -->
  <ChartItem :hidden="hidden"/>
  <!-- 底部文字 -->
  <BottomItem :num="123"/>

</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import ChartItem from './components/ChartItem.vue'
import BottomItem from './components/BottomItem.vue'
import TimerItem from './components/TimerItem.vue'

// 输出一句话
onMounted(() => {
  console.log('尽早戒烟有益身体健康！！！')
})

// 柱状图相关
const hidden = ref(false)

function show_chart() {
  hidden.value = ! hidden.value
}
</script>

<style scoped>
button {
  background: none;
  border: none;
  color: #00bd7e;
  cursor: pointer;
  padding: 4px;
  font-size: 18px;
  margin: 10px;
}
button:hover {
  background-color: #f0f0f0;
  border-radius: 4px;
}
</style>

