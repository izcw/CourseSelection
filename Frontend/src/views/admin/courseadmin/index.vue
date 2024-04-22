<template>
    <div class="courseadmin-container">
        {{ t('route.courseadminTxt') }}
    </div>
  </template>
  
  <script setup>
    import { useI18n } from 'vue-i18n';
    const { t } = useI18n();
  </script>
  
  <style lang="scss" scoped>
    .courseadmin-container {
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
  