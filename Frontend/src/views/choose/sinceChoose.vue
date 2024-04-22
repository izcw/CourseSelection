<template>
  <div class="sinceChoose-container">
    {{ t('route.sinceChooseTxt') }}
    <countdownTime></countdownTime>
  </div>
</template>

<script setup>
import { useI18n } from 'vue-i18n';
const { t } = useI18n();
import countdownTime from '@/components/Card/CountdownTime.vue';
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