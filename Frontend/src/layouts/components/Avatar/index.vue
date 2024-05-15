<template>
  <el-dropdown @command="handleCommand">
    <span class="avatar-dropdown" :style="{ color }">
      <!--<el-avatar class="user-avatar" :src="avatar"></el-avatar>-->
      <img class="user-avatar" :src="avatar1" alt="" />
      <div class="user-name">
        <div v-if="userType == 'student'">
          <p>{{info.userName}}</p>
      </div>
      <div v-else-if="userType == 'teacher'">
          <p>{{info.teacherName}}</p>
      </div>
      <div v-else>
          <p>{{info.userName}}</p>
      </div>
        <i class="el-icon-arrow-down el-icon--right"></i>
      </div>
    </span>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item command="github">Github</el-dropdown-item>
        <el-dropdown-item command="logout" divided>{{ t('navbar.logOut') }}</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>


<script>
export default {
  name: 'Avatar',
};
</script>

<script setup>
import { ref } from 'vue';
import { useStore } from 'vuex';
import { ElMessageBox } from 'element-plus';
import { setting } from '@/config/setting';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';

const store = useStore();

const info = ref({})
const userType = ref({})
async function getUserInfo() {
    info.value = await store.getters['user/info'][0]
    userType.value = await store.getters['user/userType']
}
setTimeout(() => {
    getUserInfo()
}, 200)

const { title, recordRoute } = setting;
const { t } = useI18n();
const avatar1 = ref('https://picserver.duoyu.link/picfile/image/202404/22-1713717087790.png'); // 学生头像
const avatar2 = ref('https://picserver.duoyu.link/picfile/image/202404/22-1713717150586.png'); // 管理员头像
const router = useRouter();

defineProps({
  color: {
    type: String,
    default: '#666',
  },
});

const handleCommand = (command) => {
  switch (command) {
    case 'logout':
      handleLogout();
      break;
    case 'github':
      window.open('https://github.com/izcw/CourseSelection');
      break;
    default:
      break;
  }
};

const handleLogout = () => {
  ElMessageBox.confirm(`${t('confirm.msg')}${title}？`, t('confirm.title'), {
    confirmButtonText: t('btn.confirm'),
    cancelButtonText: t('btn.cancel'),
    dangerouslyUseHTMLString: true,
    type: 'warning',
  })
    .then(async () => {
      await store.dispatch('user/logout');
      if (recordRoute) {
        const { fullPath } = router.currentRoute._value;
        location.replace('/login');
      } else {
        router.push('/login');
      }
    })
    .catch(() => { });
};
</script>

<style lang="scss" scoped>
.avatar-dropdown {
  display: flex;
  align-content: center;
  align-items: center;
  justify-content: center;
  justify-items: center;
  height: $base-avatar-dropdown-height;
  padding: $base-padding-10;

  .user-avatar {
    width: $base-avatar-widht;
    height: $base-avatar-height;
    cursor: pointer;
    border-radius: $base-border-radius-circle;
  }

  .user-name {
    position: relative;
    margin-left: $base-margin-5;
    margin-left: $base-margin-5;
    cursor: pointer;
  }
}
</style>