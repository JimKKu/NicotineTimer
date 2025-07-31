<template>
  <div v-show="mode === 'small' && !show_set_form" :class="['container-small',chart_flag ? 'container-small-top' : 'container-small-bottom',pic_flag ? 'container-small-left' : 'container-small-right']">
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
  <div v-show="mode === 'big' && !show_set_form" :class="['container-big',chart_flag ? 'container-big-top' : 'container-big-bottom', pic_flag ? 'container-big-left' : 'container-big-right']">
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

  <!-- 设置起始日期 -->
  <el-form
      :class="['container-set',chart_flag ? 'container-set-top' : 'container-set-bottom']"
      label-width="auto"
      v-if="show_set_form"
  >

    <el-row :gutter="20">
      <!-- 日期 -->
      <el-col :span="12"><div class="grid-content ep-bg-purple" />
        <el-form-item label="起始日期">
          <el-date-picker
              v-model="set_form.date"
              clearable
              type="date"
              placeholder="选择开始日期"
              value-format="YYYYMMDD"
          />
        </el-form-item>
      </el-col>
      <!-- 时间 -->
      <el-col :span="12"><div class="grid-content ep-bg-purple" />
        <el-form-item label="起始时间">
          <el-time-picker
              v-model="set_form.time"
              placeholder="请选择起始时间"
              value-format="HHmmss"
          />
        </el-form-item>
      </el-col>
    </el-row>
    <!-- 按钮 -->
    <el-row :gutter="20">
      <el-col :span="4"><div class="grid-content ep-bg-purple" />
<!--        <el-button @click="" type="primary" plain :icon="Today">此刻</el-button>-->
        <button type="button" @click="btn_now"><Today/>此刻</button>
      </el-col>
      <el-col :offset="12" :span="4"><div class="grid-content ep-bg-purple" />
<!--        <el-button @click="" type="default" plain :icon="Cancel">取消</el-button>-->
        <button type="button" v-show="!latest_date_is_null"><Cancel/>取消</button>
      </el-col>
      <el-col :span="4"><div class="grid-content ep-bg-purple"/>
<!--        <el-button @click="" type="primary" :icon="Start">确定</el-button>-->
        <button type="button" @click="btn_submit"><Start/>确定</button>
      </el-col>
    </el-row>
  </el-form>
</template>


<script setup lang="ts">
const {chart_flag,pic_flag,mode} = defineProps<{
  chart_flag: boolean,
  pic_flag: boolean,
  mode: string

}>()

import WelcomeItem from "@/components/WelcomeItem.vue";
import NoSmoke from "@/components/icons/IconNoSmoke.vue";
import Today from "@/components/icons/IconToday.vue";
import Start from "@/components/icons/IconStart.vue";
import Cancel from "@/components/icons/IconCancel.vue";
import {onMounted, onUnmounted, ref,reactive, watch} from "vue";
import {getData, fetchLatestDate, setDate} from "@/api/api.ts";
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

let target = new Date('2025-06-02T15:00:00')


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
  getLatestDate()
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
  const res = await getData()
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
  if(timePassed.value.seconds === '00') {
    oldTimePassed.value.seconds = '59'
  } else {
    oldTimePassed.value.seconds = String(Number(timePassed.value.seconds) - 1);
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

const latestDate = ref('')
const show_set_form = ref(false)
const latest_date_is_null = ref(false)
const formLabelWidth = '140px'

const getLatestDate = async () => {
  try {
    // 获取最新的日期信息 - 这里调用的是API函数，不是自身
    let response = await fetchLatestDate(); // 确保这是你的API调用函数

    if(response.code === 200) {
      // 获取成功，从今天开始计时
      const dateStr = response.data.datetime;

      // 解析日期字符串
      const year = parseInt(dateStr.substring(0, 4));
      const month = parseInt(dateStr.substring(4, 6)) - 1; // 月份从0开始
      const day = parseInt(dateStr.substring(6, 8));
      const hours = parseInt(dateStr.substring(8, 10));
      const minutes = parseInt(dateStr.substring(10, 12));
      const seconds = parseInt(dateStr.substring(12, 14));

      // 更新target
      target = new Date(year, month, day, hours, minutes, seconds);
      latest_date_is_null.value = false;
      return target; // 返回新的Date对象
    } else {
      show_set_form.value = true;
      latest_date_is_null.value = true;
      return null; // 或者抛出错误
    }
  } catch (err) {
    console.error("获取最新日期失败:", err);
    show_set_form.value = true;
    throw err; // 可以选择重新抛出错误或返回null
  }
}

// 自定义日期信息
const set_form = reactive({
  date: '',
  time: ''
})


// 日期与时间 | 按钮
const btn_now = () => {
  const now = new Date();

  const year = now.getFullYear();
  const month = String(now.getMonth() + 1).padStart(2, '0'); // 月份从0开始，所以要+1
  const day = String(now.getDate()).padStart(2, '0');
  const date = `${year}${month}${day}`; // 格式：yyyyMMdd

  const hours = String(now.getHours()).padStart(2, '0');
  const minutes = String(now.getMinutes()).padStart(2, '0');
  const seconds = String(now.getSeconds()).padStart(2, '0');
  const time = `${hours}${minutes}${seconds}`; // 格式：HHmm

  set_form.date = date;
  set_form.time = time;
}

// 提交 | 按钮
const loading = ref(false)
const btn_submit = async () => {
  loading.value = true
  let rsp = await setDate(set_form.date, set_form.time);

  if (rsp.code === 200) {
    // 更新成功 获取最新时间日期 返回显示
    getLatestDate()
    show_set_form.value = false
    return
  }

  // 更新失败
  console.log('更新失败')
}

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

.container-set {
  width: 620px;
  height: 130px;
  position: absolute;
  left: 50%;
  transform: translate(-50%,-50%);
  transition: all 0.2s;
  border-radius: 6px;
  padding: 26px;
  box-shadow: 2px 2px 6px #8e8e8e,-2px -2px 6px #fff;

  button {
    width: 80px;
    height: 28px;
    position: relative;
    text-align: right;
    border-radius: 4px;
    color: gray;
    transition:  all .2s;
    border: 1px solid gray;

    svg {
      width: 20px;
      height: 20px;
      position: absolute;
      left: 8px;
    }

  }

  button:hover {
    box-shadow: 2px 2px 6px #8e8e8e,-2px -2px 6px #fff;
    border: 1px solid transparent;
  }
}

.container-set-top {
  top: 40%;
}
.container-set-bottom {
  top: 50%;
}

</style>
