<template>
    <div class="queryCourse-container">
        <el-space :size="100">
            <div>
                学年
                <el-select v-model="select1" placeholder="全部" style="width: 240px">
                    <el-option v-for="item in 4" :key="item" :label="item" :value="item" />
                </el-select>
            </div>

            <div>
                学期
                <el-select v-model="select1" placeholder="全部" style="width: 240px">
                    <el-option v-for="item in 4" :key="item" :label="item" :value="item" />
                </el-select>
            </div>
        </el-space>

        <el-table :data="tableData" :default-sort="{ prop: 'date', order: 'descending' }" style="width: 100%">
            <el-table-column prop="date" label="学年" sortable width="100" />
            <el-table-column prop="name" label="学期" width="60" />
            <el-table-column prop="address" label="课程名称" />
            <el-table-column prop="name" label="开课学院" width="180" />
            <el-table-column prop="name" label="课程类别" width="180" />
            <el-table-column prop="name" label="学分" width="180" />
            <el-table-column prop="name" label="教学班名称" width="180" />
            <el-table-column prop="name" label="教师" width="180" />
            <el-table-column prop="name" label="上课时间" width="180" />
            <el-table-column prop="name" label="上课地点" width="180" />
        </el-table>
    </div>
</template>

<script setup>
import { ref } from "vue"
const select1 = ref('')
const select2 = ref('')
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