<template>
    <div class="classadmin-container">
        {{ t('route.classadminTxt') }}
    </div>
  </template>
  
  <script setup>
    import { useI18n } from 'vue-i18n';
    import { onMounted } from 'vue';
    import {useStore} from "vuex"
    
    let store = useStore();
    let token = computed(() => store.state.user.accessToken).value
    let userType = computed(() => store.state.user.userType).value
    onMounted(() => {
        console.log(userType)
    })
    const { t } = useI18n();
  </script>
  
  <style lang="scss" scoped>
    .classadmin-container {
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
  