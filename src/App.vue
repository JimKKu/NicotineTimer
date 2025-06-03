<template>
  <WelcomeItem>
    <template #icon>
      <NoSmoke />
    </template>
    <template #heading>{{getStr(timePassed)}}</template>
    <a href="https://www.chinatca.cn/" target="_blank" rel="noopener">Smoke</a>
     clouds the mind. Clarity begins when you quit.<br/>
    烟雾遮蔽心智，清醒始于戒烟。
  </WelcomeItem>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import WelcomeItem from './components/WelcomeItem.vue'
import NoSmoke from './components/icons/IconNoSmoke.vue'

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

function getStr(timePassed:TimePassed):string  {
  return timePassed.days + '天' +
  timePassed.hours  + ' 小时' +
  timePassed.minutes  + ' 分钟' +
  timePassed.seconds  + ' 秒' +
  timePassed.milliseconds  + ' 毫秒'
}
</script>

