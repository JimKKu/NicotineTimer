<template>
    <div :class="['target-group',pic_flag ?'left' :  'right',chart_flag ? 'top' : 'bottom']">
        <MoonIcon/>
        <NoSmokingIcon />
        <ThinIcon />
        <MoneyIcon />
        <FeetIcon />
        <BicycleIcon />
        <DriverIcon class="finished"/>
    </div>
    <!-- <div :class="['guide',pic_flag ?'left' :  'right']">
        <div>进行中</div>
        <div>已完成</div>
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

const {chart_flag,pic_flag} = defineProps<{
  chart_flag: boolean,
  pic_flag: boolean,
}>()



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
  border-bottom: 1px solid rgba(211, 209, 209);
  transition: all .2s;
  top: 0;

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

.guide {
    position: absolute;
    width: 20%;
    top: 44px;
    display: flex;
    flex-direction: row;
    transition: all .2s;

    div {
        display: inline-block;
    }

    :nth-child(1),
    :nth-child(2) {
        content: "";
        display: inline-block;
        width: 50%;
        height: 24px;
        line-height: 24px;
        font-size: 13px;
        border-radius: 4px;
        margin: 4px 8px;
        vertical-align: middle;
        text-align: center;
        padding: 0 6px;
    }



    :nth-child(1) {
        box-shadow: 2px 2px 6px #8e8e8e inset,-2px -2px 6px #fff inset;
    }
    :nth-child(2) {
        border: 1px solid gray;
    }



}
</style>