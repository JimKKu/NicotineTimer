<template>
  <!-- 功能按钮组 -->
  <div class="button-group">

<!-- 原button（左侧） -->
<!--    <button @click="show_chart"><el-icon><TrendCharts /></el-icon></button>-->
<!--    &lt;!&ndash;    <button @click="show_pic"><el-icon><Camera /></el-icon></button>&ndash;&gt;-->
<!--    <button @click="switch_timer"><el-icon><Switch /></el-icon></button>-->
<!--    &lt;!&ndash;    <button @click="switch_timer"><el-icon><IconTarget /></el-icon></button>&ndash;&gt;-->
<!--    <button @click="handleFullScreen"><el-icon><FullScreen /></el-icon></button>-->

    <el-row>
      <!-- Left -->
      <el-col :span="1">
        <button @click="show_chart"><el-icon><TrendCharts /></el-icon></button>
      </el-col>
      <el-col :span="1">
        <button @click="switch_timer"><el-icon><Switch /></el-icon></button>
      </el-col>
      <el-col :span="1">
        <button @click="handleFullScreen"><el-icon><FullScreen /></el-icon></button>
      </el-col>

      <!-- Right -->
      <el-col :span="1" :offset="20">
        <button @click="handleLogin"><el-icon><IconLogin /></el-icon></button>
      </el-col>
    </el-row>
  </div>
  <div style="width: 100%;">
    <!-- 计时器 -->
    <TimerItem :mode="time_mode" :chart_flag="chart_flag" :pic_flag="pic_flag"/>
    <!-- 目标 -->
    <!--  <TargetsItem  :chart_flag="chart_flag" :pic_flag="pic_flag"/>-->
    <!-- 柱状图 -->
    <ChartItem :hidden="chart_flag" style="z-index: 100"/>
    <!-- 底部文字 -->
    <BottomItem :num="123" style="z-index: 100"/>
    <!-- 右边的图片 -->
    <PictureItem :hidden="pic_flag" :chart_flag="chart_flag" style="z-index: 10"/>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import ChartItem from './components/ChartItem.vue'
import BottomItem from './components/BottomItem.vue'
import TimerItem from './components/TimerItem.vue'
import PictureItem from './components/PictureItem.vue'
import TargetsItem from './components/TargetsItem.vue'
import { useFullscreen } from './utils/ScreenUtils.ts'

// ICON
import { TrendCharts,Camera,Switch,Setting,FullScreen } from '@element-plus/icons-vue'
import IconLogin from './components/icons/IconLogin.vue'
import IconTarget from './components/icons/IconTarget.vue'
import LoginItem from "@/components/LoginItem.vue";


const { isFullscreen, toggleFullscreen } = useFullscreen()
function handleFullScreen() {
  toggleFullscreen()
}

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

const time_mode = ref('big')
function switch_timer() {
  if(time_mode.value === 'big'){
    time_mode.value = 'small'
  } else {
    time_mode.value = 'big'
  }
}

// 设置相关
const setting_flag = ref(false)
function switch_settings() {
  setting_flag.value = !setting_flag.value
}

const login_flag = ref(false)
function handleLogin() {
  setting_flag.value = !setting_flag.value
}

// 目标相关
</script>

<style scoped>
.button-group {
  display: flex inline-block;
  flex-direction: row;
  width: 100%;
  /* box-shadow: 4px 0px 8px gray; */

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
    border-radius: 4px 4px 0 0;
    color: #00bd7e;
  }
}

.setting-button-icon {
  animation: rotate-icon 2s infinite linear;
  transition: all .2s;
}

.setting-button-show {
  margin: -100px 0 10px 20px;
  border-bottom: none;
}

@keyframes rotate-icon {
  0% {
    transform: rotate(0deg);
  }

  100% {
    transform: rotate(360deg);
  }
}
</style>

<!--
<button @click="show_chart">📈</button>
<button @click="show_pic">📷</button>
<button @click="show_pic">🔠</button>
-->
