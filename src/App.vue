<template>
  <div class="container">
    <WelcomeItem>
      <template #icon>
        <NoSmoke />
      </template>
      <template #heading>
        <span class="days">{{ timePassed.days }}</span><span class="units">天</span>
        <span class="days">{{ timePassed.hours }}</span><span class="units">小时</span>
        <span class="days">{{ timePassed.minutes }}</span><span class="units">分钟</span>
        <span class="days">{{ timePassed.seconds }}</span><span class="units">秒</span>
        <span class="days">{{ timePassed.milliseconds }}</span><span class="units">毫秒</span>
      </template>
      已经克服了<span class="times">{{result}}</span>次吸烟的欲望，再多坚持一下吧！
    </WelcomeItem>
  </div>

  <div class="floot">
    {{result%2 === 0 ? '烟雾迷人心智，让人意识不清。' : '吸烟有害健康！尽早戒烟有益健康！'}}
  </div>

</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import WelcomeItem from './components/WelcomeItem.vue'
import NoSmoke from './components/icons/IconNoSmoke.vue'
import {getData} from '@/api/api'



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

// 次数
const result = ref<any>(null)

onMounted(async () => {
  try {
    result.value = await getData()
  } catch (err) {
    console.error(err)
  }
})

setInterval(async () => {
  const res = await fetch('/api/get')
  result.value = await res.json()
}, 5000)

// 输出一句话
onMounted(() => {
  console.log('尽早戒烟有益身体健康！！！')
})
</script>

<style scoped >
.times {
  font-size: 24px;
  color: #00bd7e;
  font-weight: bold;
  padding: 8px;
}
.floot {
  position: absolute;
  left: 0;
  bottom: 12px;
  width: 100%;
  height: 20px;
  color: #868686;
  text-align: center;
}
.days {
  position: relative;
  font-size: 24px;
  padding: 5px;
  color: #00bd7e;
}
.units {
  font-size: 12px;
  font-weight: lighter;
}
</style>
