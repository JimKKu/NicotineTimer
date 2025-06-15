<template>
  <div :class="['container',hidden ? 'container-show' : 'container-hidden',!chart_flag ? 'container-tall' : 'container-short']">
    内容待开发
  </div>
</template>


<script lang="ts" setup>
const { hidden } = defineProps<{
  hidden: boolean,
  chart_flag: boolean
}>()
</script>

<style scoped>
.container {
  position: absolute;
  top: 0;
  right: 0;
  overflow: hidden;
  transition: all .2s ease-in-out;
  border-left: 1px solid gray;
  text-align: center;
  color: #cccccc;
  font-size: 120px;
  writing-mode: vertical-rl;
}

.container-hidden {
  width: 0;
}

.container-show {
  width: 600px;
}

.container-tall {
  height: 100%;
  line-height: 100vh;
}

.container-short {
  height: 76%;
  line-height: 76vh;
}
</style>