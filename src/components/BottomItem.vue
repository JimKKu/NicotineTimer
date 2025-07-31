<script setup lang="ts">
const {num} = defineProps<{
  num: number
}>()

// 在这里使用num这个变量
function getWord() {
  return num%2 === 0 ? '吸烟有害健康！尽早戒烟有益健康！' : '烟雾迷人心智，让人意识不清。'
}

</script>

<template>
  <div>
    <span>{{getWord()}}</span>
  </div>
</template>

<style scoped>
div {
  position: absolute;
  bottom: 12px;
  left: 0;
  width: 100%;
  height: 20px;
  color: #868686;
  text-align: center;
}
</style>
