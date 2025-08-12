<template>
    <div :class="['target-group',pic_flag ?'left' :  'right',chart_flag ? 'show-chart' : 'bottom']">
        <MoonIcon />
        <NoSmokingIcon />
        <ThinIcon />
        <MoneyIcon />
        <FeetIcon />
        <BicycleIcon />
        <DriverIcon class="finished"/>
    </div>
    <!-- <div :class="['guide',pic_flag ?'guide-left' :  'guide-right',show_flag ? 'guide-bottom' : 'guide-top']">
        <div @click="show">2025年计划清单</div>
    </div> -->
</template>

<script lang="ts" setup>
import NoSmokingIcon from './targets/NoSmoke.vue'
import DriverIcon from './targets/DriverIcon.vue'
import BicycleIcon from './targets/BicycleIcon.vue'
import ThinIcon from './targets/ThinIcon.vue'
import MoonIcon from './targets/MoonIcon.vue'
import MoneyIcon from './targets/MoneyIcon.vue'
import FeetIcon from './targets/FeetIcon.vue'

import {onMounted, ref} from 'vue'

const {pic_flag,chart_flag} = defineProps<{
  pic_flag: boolean,
  chart_flag: boolean
}>()

const show_flag = ref(true)
function show () {
    show_flag.value = !show_flag.value
}

onMounted(() => {
    setTimeout(() => {
        show_flag.value = !show_flag.value
    },0)
    setTimeout(() => {
        show_flag.value = !show_flag.value
    },1000)
})




</script>

<style scoped>
.target-group {
  width: 20%;
  height: 40px;
  line-height: 40px;
  display: flex inline-block;
  flex-direction: row;
  padding: 0 10px 10px 0;
  position: absolute;
  text-align: right;
  transition: all .2s;

  svg {
    width: 24px;
    height: 24px;
    margin: 10px 10px 10px 10px;
    transition: all .1s;
    padding: 3px;
    border-radius: 4px;
}

  svg:hover {
    transform: scale(120%);
    background-color: #fff;
  }

  .finished {
    /* background-color: rgba(128, 128, 128, 0.384); */
    border: 1px solid gray;
  }
}

.left {
    right: 600px;
}

.right {
    right: 0
}

.top {
    bottom: -44px;
}

.bottom {
    bottom: 0;
}

.show-chart {
    bottom: 24%;
}

.guide {
    position: absolute;
    width: 136px;
    display: flex;
    flex-direction: row;
    transition: all .2s;

    div {
        display: inline-block;
    }

    /* :nth-child(1), */
    :nth-child(1) {
        content: "";
        display: inline-block;
        width: 100%;
        height: 24px;
        line-height: 24px;
        font-size: 13px;
        border-radius: 4px;
        margin: 4px 8px;
        vertical-align: middle;
        text-align: center;
        padding: 0 6px;
        border: 1px solid rgba(128, 128, 128, 0.5);
}



    /* :nth-child(1) {
        box-shadow: 2px 2px 6px #8e8e8e inset,-2px -2px 6px #fff inset;
    }
    :nth-child(2) {
        border: 1px solid gray;
    } */

}
.guide-top {
    top: 0;
    width: 136px;
}
.guide-bottom {
    top: 44px;
}

.guide-left {
    right: 600px;
    width: 136px;
}

.guide-right {
    right: 0
}

</style>