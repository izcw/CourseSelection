<template>
    <div class="countdown">
        <div v-if="days == 0 && hours == 0 && minutes == 0 && seconds == 0">
            0
        </div>
        <div v-else>
            {{ days }}天{{ hours }}时 {{ minutes }}:{{ seconds }}
        </div>
    </div>
</template>

<script setup>
import { ref, computed, onMounted, onUnmounted } from 'vue';

const props = defineProps({
    endTime: String
});

const timeRemaining = ref(getTimeRemaining());

function getTimeRemaining() {
    const total = Date.parse(props.endTime) - Date.parse(new Date());
    if (total <= 0) {
        return {
            total: 0,
            days: 0,
            hours: 0,
            minutes: 0,
            seconds: 0
        };
    }
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

const updateRemainingTime = () => {
    timeRemaining.value = getTimeRemaining();
};

onMounted(() => {
    countdownInterval = setInterval(updateRemainingTime, 1000);
});

onUnmounted(() => {
    clearInterval(countdownInterval);
});
</script>

<style scoped></style>