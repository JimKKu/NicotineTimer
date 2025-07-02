<template>
  <div v-show="mode === 'small'" :class="['container-small',chart_flag ? 'container-small-top' : 'container-small-bottom',pic_flag ? 'container-small-left' : 'container-small-right']">
    <WelcomeItem>
      <template #icon>
        <NoSmoke />
      </template>
      <template #heading>
        <span class="days">{{ timePassed.days }}</span><span class="units">天</span>
        <span class="days">{{ timePassed.hours }}</span><span class="units">小时</span>
        <span class="days">{{ timePassed.minutes }}</span><span class="units">分钟</span>
        <span class="days">{{ timePassed.seconds }}</span><span class="units">秒</span>
        <span class="days">{{ timePassed.mills }}</span><span class="units">毫秒</span>
      </template>
      已经克服了<span class="word-small">{{result}}</span>次吸烟的欲望，再多坚持一下吧！
    </WelcomeItem>
  </div>

  <!-- 让数字一跳一跳的 -->
  <div v-show="mode === 'big'" :class="['container-big',chart_flag ? 'container-big-top' : 'container-big-bottom', pic_flag ? 'container-big-left' : 'container-big-right']">
    <div class="big-timer big-mill">次<br/>{{ result }}</div>
    <div class="big-unit">天</div>
    <div class="big-timer big-day">{{ timePassed.days }}</div>
    <div class="big-unit">时</div>
    <div class="big-timer big-hour">{{ timePassed.hours }}</div>
    <div class="big-unit">分</div>
    <div class="big-timer big-min">{{ timePassed.minutes }}</div>
    <div class="big-unit">秒</div>
    <div :class="['big-timer','big-second',isBeating ? 'beating' : '']">
      <div>
        <span>{{ timePassed.seconds }}</span>
        <span>{{ oldTimePassed.seconds }}</span>
      </div>
    </div>
    <span class="word-big">已经克服了<span>{{result}}</span>次吸烟的欲望，再多坚持一下吧！</span>
  </div>
</template>


<script setup lang="ts">
const {chart_flag,pic_flag,mode} = defineProps<{
  chart_flag: boolean,
  pic_flag: boolean,
  mode: string

}>()

import WelcomeItem from "@/components/WelcomeItem.vue";
import NoSmoke from "@/components/icons/IconNoSmoke.vue";
import {onMounted, onUnmounted, ref, watch} from "vue";
import {getData} from "@/api/api.ts";
import {Setting} from '@element-plus/icons-vue'


interface TimePassed {
  days: number
  hours: string
  minutes: string
  seconds: string
  mills: string
}

const timePassed = ref<TimePassed>({
  days: 0,
  hours: '00',
  minutes: '00',
  seconds: '00',
  mills: '000',
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
    mills: pad3(ms),
  }
}

onMounted(() => {
  updateTime()
  timer = setInterval(updateTime, 50)
})

onUnmounted(() => {
  clearInterval(timer)
})

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
  const res = await fetch('/api/iTimer/smoke/count')
  result.value = await res.json()
}, 5000)

// 大字动画效果 - 心跳 | 定义变量
const isBeating = ref(false)
const oldTimePassed = ref<TimePassed>({
  days: 0,
  hours: '00',
  minutes: '00',
  seconds: '00',
  mills: '000',
})
// 初始化第一秒
onMounted(() => {
// 判断当前秒数
  console.log(timePassed.value.seconds)
  if(timePassed.value.seconds === '00') {
    oldTimePassed.value.seconds = '59'
  } else {
    oldTimePassed.value.seconds = timePassed.value.seconds - 1
  }
})

watch(() => timePassed.value.seconds,() => {
  isBeating.value = true
  setTimeout(() => {
    isBeating.value = false
  },200)

  setTimeout(() => {
    oldTimePassed.value = timePassed.value
  },600)

})

</script>


<style scoped>
.word-small {
  font-size: 24px;
  color: #00bd7e;
  font-weight: bold;
  padding: 8px;
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
.container-small {
  position: absolute;
  transform:  translate(-50%, -50%);
  transition: all 0.2s;
}

.container-small-top {
  top: 36%;
}
.container-small-bottom {
  top: 50%
}
.container-small-right {
  left: 30%;
}
.container-small-left {
  left: 28%;
}
.container-big {
  padding: 18px;
  border-radius: 16px;
  width: 900px;
  height: 180px;
  position: absolute;
  transform:  translate(-50%, -50%);
  transition: all 0.2s;
  box-shadow: 2px 2px 6px #8e8e8e,-2px -2px 6px #fff;

  display: flex;
  flex-direction: raw;

  text-align: center;
  color: white;
  .word-big {
    position: absolute;
    left: 50%;
    top: 110%;
    transform: translateX(-50%);
    color: gray;
    border-bottom: 1px solid #cdcdcd;
    span {
      color: #00bd7e;
      font-size: 24px;
      font-weight: bold;
      padding: 8px;
    }
  }

  .settings {
    width: 400px;
    height: 600px;
    position: absolute;
    left: -50%;
    top: 50%;
    transform: translate(-50%,-50%);
    box-shadow: 0px 0px 0px transparent,0px 0px 0px transparent,2px 2px 6px #8e8e8e inset,-2px -2px 6px #ffffff inset;
    border-radius: 26px;
  }
  .big-timer {
    margin: auto;
    width: 144px;
    height: 144px;
    box-sizing: border-box;
    box-shadow: 0px 0px 0px transparent,0px 0px 0px transparent,2px 2px 6px #8e8e8e inset,-2px -2px 6px #ffffff inset;
    border-radius: 20px;
    line-height: 144px;
    color: gray;
    font-size: 100px;
    font-family:
        "Segoe UI",         /* Windows */
        "Helvetica Neue",   /* macOS */
        "Noto Sans",        /* Linux 通用 */
        "Liberation Sans",  /* Linux fallback */
        sans-serif;
    font-weight: bold;
    letter-spacing: 0.5px; /* 稍微拉开一点字间距 */
    background: #e1e1e1;
    transition:  all .2s;

    text-shadow:
        1px 1px 2px #fff,
        -1px -1px 2px #aaa;
  }

  .big-day,
  .big-timer:hover {
    box-shadow: 2px 2px 6px #8e8e8e,-2px -2px 6px #fff,0px 0px 0px transparent inset,0px 0px 0px transparent inset;
    border: 0 solid #fff;
    font-size: 106px;
  }
  .big-unit {
    position: relative;
    top: 116px;
    right: -94px;
    color: rgba(128, 128, 128, 0.489);
  }
  .big-mill{
    font-size: 58px;
    line-height: 66px;
    padding: 6px;
    color: #00bd7e;
    box-shadow: none;
    border: 1px solid;
    box-sizing: border-box;
  }

  .big-mill:hover {
    font-size: 62px;
  }

  /* 时钟跳动动画效果 */
  .beating {
    box-shadow: 2px 2px 6px #8e8e8e,-2px -2px 6px #fff,0px 0px 0px transparent inset,0px 0px 0px transparent inset;
    border: 0 solid #fff;
    font-size: 106px;
  }
  .big-second {
    overflow: hidden;

    div {
      font-weight: bold;
      letter-spacing: 0.5px; /* 稍微拉开一点字间距 */
      transition:  all .2s;

      text-shadow:
          1px 1px 2px #fff,
          -1px -1px 2px #aaa;

      span{
        display: inline-block;
        font-weight: bold;
        letter-spacing: 0.5px; /* 稍微拉开一点字间距 */
        transition:  all .2s;

        text-shadow:
            1px 1px 2px #fff,
            -1px -1px 2px #aaa;
      }
    }
  }

}

.container-big-left {
  left: 40%;
}
.container-big-right {
  left: 50%;
}
.container-big-top {
  top: 40%;
}
.container-big-bottom {
  top: 50%;
}
</style>

<!--  <div class="background">-->
<!--    <i class="set-icon-day"><Setting/></i>-->
<!--    <i class="set-icon-hour"><Setting/></i>-->
<!--    <i class="set-icon-min"><Setting/></i>-->
<!--    <i class="set-icon-second"><Setting/></i>-->
<!--    <i class="set-icon-mill"><Setting/></i>-->
<!--  </div>-->

<!--.background {-->
<!--position: absolute;-->
<!--left: 0;-->
<!--top: 0;-->
<!--width: 100%;-->
<!--height: 100%;-->

<!--i {-->
<!--display: inline-block;-->
<!--position: absolute;-->
<!--color: gray;-->
<!--text-align: center;-->
<!--svg {-->
<!--width: 24px;-->
<!--height: 24px;-->
<!--opacity: 0.16;-->
<!--}-->
<!--}-->

<!--.set-icon-day {-->
<!--position: absolute;-->
<!--left: -50%;-->
<!--top: -50%;-->
<!--animation: rotate-right 520s infinite linear;-->
<!--svg {-->
<!--width: 1600px;-->
<!--height: 1600px;-->
<!--}-->
<!--}-->

<!--.set-icon-hour {-->
<!--left: 24%;-->
<!--top: 30%;-->
<!--animation: rotate-left 360s infinite linear;-->

<!--svg {-->
<!--width: 800px;-->
<!--height: 800px;-->
<!--}-->
<!--}-->
<!--.set-icon-min {-->
<!--left: 55%;-->
<!--top: 5%;-->
<!--animation: rotate-right 30s infinite linear;-->

<!--svg {-->
<!--width: 500px;-->
<!--height: 500px;-->
<!--}-->
<!--}-->

<!--.set-icon-second {-->
<!--left: 76%;-->
<!--top: 43%;-->
<!--animation: rotate-left 6s infinite linear;-->

<!--svg {-->
<!--width: 300px;-->
<!--height: 300px;-->
<!--}-->
<!--}-->
<!--.set-icon-mill {-->
<!--position: absolute;-->
<!--left: 89%;-->
<!--top: 33%;-->
<!--animation: rotate-right 3s infinite linear;-->

<!--svg {-->
<!--width: 200px;-->
<!--height: 200px;-->
<!--}-->
<!--}-->
<!--}-->

<!--@keyframes rotate-right {-->
<!--0% {-->
<!--transform: rotate(0);-->
<!--}-->
<!--100% {-->
<!--transform: rotate(360deg);-->
<!--}-->
<!--}-->

<!--@keyframes rotate-left {-->
<!--0% {-->
<!--transform: rotate(0);-->
<!--}-->
<!--100% {-->
<!--transform: rotate(-360deg);-->
<!--}-->
<!--}-->
