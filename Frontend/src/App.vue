<template>
  <el-config-provider :locale="localLanguage">
    <el-scrollbar height="100vh" ref="scroll">
      <router-view></router-view>
    </el-scrollbar>
  </el-config-provider>
</template>

<script setup>
import { onMounted, computed, ref, watch } from 'vue';
import { useStore } from 'vuex';
import i18n from '@/locales';
import { useRouter } from 'vue-router';
const locale = i18n.global.locale;
import { getUserInfoData } from '@/api/user';
const store = useStore();

const localLanguage = computed(() => {
  const isDev = process.env.NODE_ENV === 'development';
  if (isDev) return i18n.global.messages.value[locale.value];
  else return i18n.global.messages[locale];
});

const scroll = ref(null);

const router = useRouter();

onMounted(() => {
  changeBodyWidth();
  window.addEventListener('resize', changeResize);
 
});

watch(
  () => router.currentRoute.value,
  () => {
    scroll.value.setScrollTop(0);
  }
);

const changeBodyWidth = () => {
  const flag = document.body.getBoundingClientRect().width - 1 < 992;
  store.dispatch('setting/changeMobile', flag);
};

const changeResize = () => {
  changeBodyWidth();
};

// 获取用户信息
// async function setUserInfo() {
   getUserInfoData({
    token: store.getters['user/accessToken']
  }).then(res => {
    if (res.code !== 200) {
    } else {
      try {
        const userInfo = { name: "张三", age: "18" };
        store.commit('user/setUserInfo', res.data); // 调用 setUserInfo mutation 来设置用户信息到 Vuex
        console.log("vuex存储的内容:", JSON.parse(JSON.stringify(store.state)));
      } catch (error) {
        console.error('设置用户信息出错：', error);
      }
    }
  });
// }
// setUserInfo()

</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  font-size: $base-font-size-default;
  color: #2c3e50;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
