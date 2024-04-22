<template>
    <div class="querySchedule-container">
        {{ t('route.queryScheduleTxt') }}
    </div>
  </template>
  
  <script setup>
    import { useI18n } from 'vue-i18n';
    const { t } = useI18n();
  </script>
  
  <style lang="scss" scoped>
    .querySchedule-container {
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
  