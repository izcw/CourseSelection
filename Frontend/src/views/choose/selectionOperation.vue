<template>
    <div class="queryCourse-container">
        <el-table :data="tableData" :default-sort="{ prop: 'date', order: 'descending' }" style="width: 100%">
            <el-table-column prop="address" label="课程名称"  width="180" />
            <el-table-column prop="name" label="开课学院" width="180" />
            <el-table-column prop="name" label="课程类别" width="180" />
            <el-table-column prop="name" label="学分" width="180" />
            <el-table-column prop="name" label="教学班名称" width="180" />
            <el-table-column prop="name" label="教师" width="180" />
            <el-table-column prop="name" label="上课时间" width="180" />
        </el-table>
    </div>
</template>

<script setup>
import { ref } from "vue"
import { useStore } from 'vuex';

const store = useStore();
const info = ref({})
async function getUserInfo() {
    info.value = await store.getters['user/info'][0]
    console.log("info----" + JSON.stringify(info.value));
}
setTimeout(()=>{
    getUserInfo()
},200)



</script>

<style lang="scss" scoped>
.queryCourse-container {
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