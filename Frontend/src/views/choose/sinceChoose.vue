<template>
  <div class="sinceChoose-container">
    <countdownTime v-if="parentStatus === false" @status="handleStatusUpdate"></countdownTime>
    <div v-else>
      可以咯
    </div>
  </div>
</template>

<script setup>
import countdownTime from '@/components/Card/CountdownTime.vue';

const parentStatus = ref(false)
const handleStatusUpdate = (val)=>{
  parentStatus.value = val
}
</script>

<style lang="scss" scoped>
.sinceChoose-container {
  padding: $base-main-padding;
  background-color: $base-color-white;

  .row {
    margin: 20px 0;
  }

  .group {
    margin: 0 20px;
  }
}
</style>