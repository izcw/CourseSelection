<template>
    <div class="queryCourse-container">
        <el-table :data="tableData" :default-sort="{ prop: 'date', order: 'descending' }" style="width: 100%">
            <el-table-column type="index" width="50" />
            <el-table-column prop="courseName" label="课程名称" />
            <el-table-column prop="courseCode" label="课程编号" width="180" />
            <el-table-column prop="courseType" label="课程类别" width="80">
                <template #default="scope">
                    <el-tag v-if="scope.row.courseType.courseTypeId == '1'" type="primary">{{
                        scope.row.courseType.typeName }}</el-tag>
                    <el-tag v-else-if="scope.row.courseType.courseTypeId == '2'" type="info">{{
                        scope.row.courseType.typeName }}</el-tag>
                    <el-tag v-else type="warning">{{ scope.row.courseType.typeName }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="credits" label="学分" sortable width="180" />
            <el-table-column prop="teacher.teacherName" label="老师" width="100" />
        </el-table>
    </div>
</template>

<script setup>
import { ref } from "vue"
import { useStore } from 'vuex';
import { getViewclassscheduleList } from '@/api/student_enrollment.js';
const tableData = ref()
const info = ref({})
const store = useStore();
async function getUserInfo() {
    info.value = await store.getters['user/info'][0]
    getViewclassscheduleList({ studentCode: info.value.studentCode }).then(res => {
        if (res.code !== 200) {
            console.log("我的选课信息-获取不到数据");
        } else {
            tableData.value = res.data
        }
    });
}

setTimeout(() => {
    getUserInfo()
}, 100)


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