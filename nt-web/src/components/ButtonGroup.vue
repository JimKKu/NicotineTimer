<template>
  <div>
    <!-- 按钮 -->
    <button @click="handleBtn1Click">📈</button>

    <button @click="handleBtn2Click">📷</button>
  </div>
</template>


<script setup lang="ts">
const emit = defineEmits<{
  (e: 'btn1', val: boolean): void
  (e: 'btn2', val: boolean): void
}>()

function handleBtn1Click() {
  emit('btn1', true)
}

function handleBtn2Click() {
  emit('btn2', false)
}
</script>
<style scoped >
div {
  display: flex;
  flex-flow: column wrap;
  width: 2vw;

}


</style>
