<template>
  <div class="sinceChoose-container">
    <div v-if="parentStatus === false" style="padding:100px;">
      <countdownTime  @status="handleStatusUpdate"></countdownTime>
    </div>
    <div v-else>
      <selectionOperation></selectionOperation>
    </div>
  </div>
</template>


<script setup>
import countdownTime from '@/components/Card/CountdownTime.vue';
import selectionOperation from '@/views/choose/selectionOperation.vue';

const parentStatus = ref(true)
const handleStatusUpdate = (val)=>{
  parentStatus.value = val
}
</script>

<style lang="scss" scoped>
.sinceChoose-container {
  background-color: $base-color-white;

  .row {
    margin: 20px 0;
  }

  .group {
    margin: 0 20px;
  }
}
</style>