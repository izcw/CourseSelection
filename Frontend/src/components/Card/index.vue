<template>
    <div>
        <div v-if="userType == 'student'">
            <p>姓名：{{info.userName}}</p>
            <p>学号：{{info.studentCode}}</p>
        </div>
        <div v-else-if="userType == 'teacher'">
            <p>姓名：{{info.teacherName}}</p>
            <p>编号：{{info.teacherCode}}</p>
        </div>
        <div v-else>
            <p>姓名：{{info.userName}}</p>
        </div>
        
        <p>身份：
            <el-tag type="primary" v-if="userType == 'student'">学生</el-tag>
            <el-tag type="warning"  v-else-if="userType == 'teacher'">教师</el-tag>
            <el-tag type="info" v-else>管理员</el-tag>
        </p>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { useStore } from 'vuex';
const store = useStore();

const info = ref({})
const userType = ref({})
async function getUserInfo() {
    info.value = await store.getters['user/info'][0]
    userType.value = await store.getters['user/userType']
    console.log("info----" + JSON.stringify(info.value));
}
setTimeout(()=>{
    getUserInfo()
},200)

</script>
<style scoped lang='scss'></style>