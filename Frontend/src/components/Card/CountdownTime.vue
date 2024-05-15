<template>
    <div class="countdown">
        <div v-if="Notopen">
            <el-empty description="当前不是选课开放时间" :image-size="100" :image="stopimg" />
        </div>
        
        <div v-else>
            <div v-if="statusTime">
                <h3>可以开始选课了</h3>
                <router-link to="/sinceChoose">
                    <el-button size="large"  @click="updateStatus()" type="primary">开始选课</el-button>
                </router-link>
            </div>
            <div v-else>
                <span class="countdown-item">
                    <span>{{ days }}</span>
                    <span>天</span>
                </span>
                <span class="countdown-item">
                    <span>{{ hours }}</span>
                    <span>时</span>
                </span>
                <span class="countdown-item">
                    <span>{{ minutes }}</span>
                    <span>分</span>
                </span>
                <span class="countdown-item">
                    <span>{{ seconds }}</span>
                    <span>秒</span>
                </span>
                <span class="countdown-item">
                    <span>后开放选课</span>
                </span>
                
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed, onUnmounted, watchEffect } from 'vue';
import stopimg from "@/assets/icon/stop.png"


const childStatus = ref(false);
const emits = defineEmits(["status"])
const updateStatus = ()=>{
    childStatus.value = !childStatus.value
    emits("status",childStatus.value)
}




const Notopen = ref(false); // 是否开放选课时间，默认为开放
const statusTime = ref(false); // 倒计时状态
const deadline = new Date('2024-05-13T22:07:00'); // 选课开放时间

const timeRemaining = ref(getTimeRemaining());

function getTimeRemaining() {
    const total = deadline - new Date();
    const seconds = Math.floor((total / 1000) % 60);
    const minutes = Math.floor((total / 1000 / 60) % 60);
    const hours = Math.floor((total / (1000 * 60 * 60)) % 24);
    const days = Math.floor(total / (1000 * 60 * 60 * 24));
    return {
        total,
        days,
        hours,
        minutes,
        seconds
    };
}

const days = computed(() => timeRemaining.value.days);
const hours = computed(() => timeRemaining.value.hours);
const minutes = computed(() => timeRemaining.value.minutes);
const seconds = computed(() => timeRemaining.value.seconds);

let countdownInterval;

watchEffect(() => {
    if (!Notopen.value) { // 只有在Notopen为false时才启动倒计时
        if (!countdownInterval) { // 避免重复启动定时器
            countdownInterval = setInterval(() => {
                timeRemaining.value = getTimeRemaining();
            }, 1000);
        }
    } else { // 如果Notopen为true，则清除定时器
        clearInterval(countdownInterval);
        countdownInterval = null;
    }
});

onUnmounted(() => {
    clearInterval(countdownInterval); // 离开页面时清除定时
});

watchEffect(() => {
    if (timeRemaining.value.total <= 0) {
        clearInterval(countdownInterval); // 清除定时器
        statusTime.value = true;
    } else {
        statusTime.value = false;
    }
});
</script>

<style scoped lang="scss">
.countdown {
    display: flex;
    justify-content: center;
}

.countdown-item {
    margin: 0 10px;
}

span {
    font-size: 24px;
}
</style>
