<template>
  <div class="container">
    <div class="timer">
      <h4>距离 2025 年 6 月 2 日 15:00 已过去</h4>
      <span class="time-text">
        {{ timePassed.days }} 天
        {{ timePassed.hours }} 小时
        {{ timePassed.minutes }} 分钟
        {{ timePassed.seconds }} 秒
        {{ timePassed.milliseconds }} 毫秒
      </span>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'

interface TimePassed {
  days: number
  hours: string
  minutes: string
  seconds: string
  milliseconds: string
}

const timePassed = ref<TimePassed>({
  days: 0,
  hours: '00',
  minutes: '00',
  seconds: '00',
  milliseconds: '000',
})

const target = new Date('2025-06-02T15:00:00')

let timer: number

const pad2 = (n: number) => String(n).padStart(2, '0')
const pad3 = (n: number) => String(n).padStart(3, '0')

const updateTime = () => {
  const now = new Date()
  const diff = now.getTime() - target.getTime()

  const totalSeconds = Math.floor(diff / 1000)
  const ms = diff % 1000

  const seconds = totalSeconds % 60
  const minutes = Math.floor(totalSeconds / 60) % 60
  const hours = Math.floor(totalSeconds / 3600) % 24
  const days = Math.floor(totalSeconds / (3600 * 24))

  timePassed.value = {
    days,
    hours: pad2(hours),
    minutes: pad2(minutes),
    seconds: pad2(seconds),
    milliseconds: pad3(ms),
  }
}

onMounted(() => {
  updateTime()
  timer = setInterval(updateTime, 50)
})

onUnmounted(() => {
  clearInterval(timer)
})
</script>

<style scoped>
.container {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  top: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}


.timer {
  text-align: center;
  font-size: 1.5rem;
  color: #783CF9;
  padding: 1.5rem;
  border: 2px solid #783CF9;
  border-radius: 1rem;
  background-color: #783CF9;
  box-shadow: 0 0 10px rgba(16, 185, 129, 0.2);
  min-width: 400px;
}

.timer :hover {
  color: #fff;
}

.time-text {
  font-weight: bold;
  font-size: 1.75rem;
  letter-spacing: 1px;
  font-variant-numeric: tabular-nums; /* 保持数字宽度一致 */
}
</style>
