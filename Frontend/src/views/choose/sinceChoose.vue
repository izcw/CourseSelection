<template>
  <div class="sinceChoose-container">
    <div v-if="parentStatus === false" style="padding:100px;">
      <countdownTime @status="handleStatusUpdate" :openness="opennessStatus"></countdownTime>
    </div>
    <div v-else>
      <selectionOperation  ></selectionOperation>
    </div>
  </div>
</template>


<script setup>
import countdownTime from '@/components/Card/CountdownTime.vue';
import selectionOperation from '@/views/choose/selectionOperation.vue';
import { useStore } from 'vuex';
import { getEnrollmentList } from '@/api/student_enrollment.js';
import { ref } from 'vue';

// 
const parentStatus = ref(false)
const handleStatusUpdate = (val) => {
  parentStatus.value = val
}

const opennessStatus = ref(true)


// 
const store = useStore();
const info = ref({})

async function getUserInfo() {
  info.value = await store.getters['user/info'][0]
  getfunList()
}

setTimeout(() => {
  getUserInfo()
}, 100)

// 获取选课
let enrollmentArrData = ref([])
const getfunList = () => {
  getEnrollmentList(info.value).then(res => {
    if (res.code !== 200) {
      console.log("选课信息-获取不到数据");
    } else {
      if (res.data.length == 0) {
        opennessStatus.value = true
      } else {
        opennessStatus.value = false
        enrollmentArrData.value = res.data.map(item => {
          let currentTime = new Date();
          let endTime = new Date(item.endTime);
          return {
            ...item,
            status: currentTime < endTime ? '可选' : '已停止选课'
          };
        });
      }
    }
  });
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