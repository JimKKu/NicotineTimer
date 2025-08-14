<template>
  <!-- 初始化用户名/密码 -->
  <el-form
      :class="['container-init-auth-info',chart_flag ? 'container-init-auth-info-top' : 'container-init-auth-info-bottom']"
      label-width="60"
      label-position="left"
      v-show = "mode == 'init-auth'"
  >
    <span class="title">初始化用户信息</span>
    <el-form-item
        label="用户名"
        style="margin-top: 44px"
    >
      <el-input placeholder="请输入用户名"></el-input>
    </el-form-item>
    <el-form-item
        label="密&emsp;码"
    >
      <el-input placeholder="请输入密码"></el-input>
    </el-form-item>
      <el-row style="width: 100%">
        <el-col :span="6">
          <button><el-icon><IconCancel/></el-icon>&emsp;取&emsp;消</button>
        </el-col>
        <el-col :span="17" :offset="1">
          <button><el-icon><IconSubmit/></el-icon>&emsp;提&emsp;交</button>
        </el-col>
      </el-row>
  </el-form>

  <el-form
    v-show = "mode == 'init-time'"
    :class="['container-init-time-info',chart_flag ? 'container-init-time-info-top' : 'container-init-time-info-bottom']">
      <!-- 日期 -->
    <span class="title">选择起始时间</span>
      <el-form-item label="起始日期" style="margin-top: 54px;">
        <el-date-picker
            style="width: 100%"
            v-model="set_form.date"
            clearable
            type="date"
            placeholder="选择开始日期"
            value-format="YYYYMMDD"
        />
      </el-form-item>
      <!-- 时间 -->
      <el-form-item label="起始时间">
        <el-row style="width: 100%">
          <el-col :span="20">
            <el-time-picker
                style="width: 100%"
                v-model="set_form.time"
                placeholder="请选择起始时间"
                value-format="HHmmss"
            />
          </el-col>
          <el-col :span="1" :offset="1">
            <button type="button" class="now-btn" @click="btn_now"><el-icon><Today/></el-icon></button>
          </el-col>
        </el-row>
      </el-form-item>
    <el-form-item>
      <el-row style="width: 100%">
        <el-col :span="6">
          <button type="button" v-show="!latest_date_is_null"><el-icon><Cancel/></el-icon>&emsp;取&emsp;消</button>
        </el-col>
        <el-col :span="17" :offset="1">
          <button type="button" class="submit_btn" @click="btn_submit"><el-icon><IconSubmit/></el-icon>&emsp;确&emsp;定</button>
        </el-col>
      </el-row>
    </el-form-item>
  </el-form>
</template>


<script setup lang="ts">
import IconCancel from "@/components/icons/IconCancel.vue";
import IconSubmit from "@/components/icons/IconSubmit.vue";
import Start from "@/components/icons/IconStart.vue";
import Cancel from "@/components/icons/IconCancel.vue";
import Today from "@/components/icons/IconToday.vue";
import {reactive, ref} from "vue";
import {fetchLatestDate, setDate} from "@/api/api.ts";


const {chart_flag,pic_flag,mode} = defineProps<{
  chart_flag: boolean,
  pic_flag: boolean,
  mode: string
}>()

let target = new Date('2025-06-02T15:00:00')


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
.title {
  display: inline-block;
  padding: 4px 8px;
  box-shadow: 2px 2px 6px #8e8e8e inset,-2px -2px 6px #ffffff inset;
  font-weight: bold;
  position: absolute;
  right: 24px;
  top: 18px;
}

.container-init-auth-info {
  width: 420px;
  height: 240px;
  position: absolute;
  left: 50%;
  transform: translate(-50%,-50%);
  transition: all 0.2s;
  border-radius: 6px;
  padding: 26px;
  box-shadow: 2px 2px 6px #8e8e8e,-2px -2px 6px #fff;
}

.container-init-auth-info-top {
  top: 40%;
}
.container-init-auth-info-bottom {
  top: 50%;
}

.container-init-time-info {
  width: 420px;
  height: 240px;
  position: absolute;
  left: 50%;
  transform: translate(-50%,-50%);
  transition: all 0.2s;
  border-radius: 6px;
  padding: 16px;
  box-shadow: 2px 2px 6px #8e8e8e,-2px -2px 6px #fff;


  button:hover {
    box-shadow: 2px 2px 6px #8e8e8e,-2px -2px 6px #fff;
    border: 1px solid transparent;
  }
}

.container-init-time-info-top {
  top: 40%;
}
.container-init-time-info-bottom {
  top: 50%;
}

button {
  width: 100%;
  transition: all .2s;
  border-radius: 6px;
  border: none;
  height: 28px;
  box-shadow: 2px 2px 6px #8e8e8e inset,-2px -2px 6px #fff inset;

  svg {
    width: 38px;
    height: 20px;
    position: absolute;
    left: -12px;
    top: -2px;
  }
}
.now-btn {
  width: 44px;
  height: 90%;
  position: relative;
  left: -6px;
  top: 1px;

  svg {
    width: 38px;
    height: 20px;
    position: absolute;
    left: -12px;
    top: -2px;
  }
}



</style>

<!--button {-->
<!--width: 80px;-->
<!--height: 28px;-->
<!--position: relative;-->
<!--text-align: right;-->
<!--border-radius: 4px;-->
<!--color: gray;-->
<!--transition:  all .2s;-->
<!--border: 1px solid gray;-->

<!--svg {-->
<!--width: 20px;-->
<!--height: 20px;-->
<!--position: absolute;-->
<!--left: 8px;-->
<!--}-->

<!--}-->



<!--.submit_btn {-->
<!--width: 80%;-->
<!--}-->