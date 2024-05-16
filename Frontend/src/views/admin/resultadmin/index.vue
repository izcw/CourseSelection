<template>
    <div class="classadmin-container">
        <el-table :data="tableData" border style="width: 100%">
            <el-table-column prop="selectionPlanName" label="选课方案名称">

            </el-table-column>

            <el-table-column prop="beginTime" label="开始时间">

            </el-table-column>


            <el-table-column prop="endTime" label="结束时间">

            </el-table-column>
            <el-table-column prop="enrollmentMethod" label="选课方式">

            </el-table-column>

            <el-table-column label="操作">
                <template #default="scope">

                    <el-button size="small" type="primary" @click="resultShow(scope.$index, scope.row)">
                        查看结果
                    </el-button>

                </template>
            </el-table-column>
        </el-table>
        <el-dialog v-model="resultVisible" title="选课结果" width="70%" draggable overflow>
            <el-form :model="queryParams" ref="queryForm" :inline="true" >
                <el-form-item label="学号" prop="studentCode">
                    <el-input v-model="queryParams.studentCode" placeholder="请输入学生学号" clearable
                        @keyup.enter.native="handleQuery" />
                </el-form-item>
                <el-form-item label="姓名" prop="studentName">
                    <el-input v-model="queryParams.studentName" placeholder="请输入学生姓名" clearable
                        @keyup.enter.native="handleQuery" />
                </el-form-item>
                <el-form-item label="课程编号" prop="courseCode">
                    <el-input v-model="queryParams.courseCode" placeholder="请输入课程编号" clearable
                        @keyup.enter.native="handleQuery" />
                </el-form-item>
                <el-form-item label="课程名称" prop="courseName">
                    <el-input v-model="queryParams.courseName" placeholder="请输入课程名称" clearable
                        @keyup.enter.native="handleQuery" />
                </el-form-item>
             
                <el-form-item>
                    <el-button type="primary" :icon="Search" @click="handleQuery">搜索</el-button>
                    <el-button :icon="Refresh" size="mini" @click="resetQuery">重置</el-button>
                </el-form-item>
            </el-form>
         
            <el-table :data="resultData" border style="width: 100%">
                <el-table-column prop="student.studentCode" label="学号">

                </el-table-column>
                <el-table-column prop="student.userName" label="姓名">

                </el-table-column>
                <el-table-column prop="student.phone" label="电话">

                </el-table-column>
                <el-table-column prop="student.className" label="班级名称">

                </el-table-column>
                <el-table-column prop="course.courseCode" label="课程编号">

                </el-table-column>
                <el-table-column prop="course.courseName" label="课程名称">

                </el-table-column>
                <el-table-column prop="enrollmentTime" label="选课时间">

                </el-table-column>
            </el-table>
            <el-pagination v-model:current-page="queryParams.pageNum" v-model:page-size="queryParams.pageSize"
                :page-sizes="pageSizes" :small="small" :disabled="disabled" :background="background"
                layout="total, sizes, prev, pager, next, jumper" :total="resultTotal" @size-change="handleSizeChange"
                @current-change="handleCurrentChange" />
        </el-dialog>
    </div>
</template>

<script setup>
import {
  Search,
  Refresh
} from '@element-plus/icons-vue'
import {
    getList as getEnrollmentList,

} from '@/api/enrollment';
import {
    GetListByEnrollmentId as getListByEnrollmentId,

} from '@/api/enrollmentstudent';
import { ref } from 'vue'
const tableData = ref([])
const resultData = ref([])
const resultVisible = ref(false)
const pageSizes = ref([1,10, 20, 30, 40, 50])
const small = ref(false)
const disabled = ref(false)
const background = ref(false)
const resultTotal = ref(0)
const queryParams = ref({
    enrollmentId: 0,
    studentCode: '',
    studentName: '',
    courseName: '',
    courseCode: '',
    pageNum: 1,
    pageSize: 10,
})
const handleSizeChange = (val) => {
    console.log(`${val} items per page`)
}
const handleCurrentChange = (val) => {
    queryParams.value.pageNum = val
    getResultList()
}
const handleQuery = () => {
    getResultList()
}
const resetQuery = () => {
    queryParams.value.studentCode = ''
    queryParams.value.studentName = ''
    queryParams.value.courseName = ''
    queryParams.value.courseCode = ''
    queryParams.value.pageNum = 1
    queryParams.value.pageSize = 10
    getResultList();
}
const resultShow = async (index,row) => {
    queryParams.value.enrollmentId = row.enrollmentId
    await getResultList();
    resultVisible.value = true
}
const getResultList = async () => {
    await getListByEnrollmentId(queryParams.value).then(c => {
        resultData.value = c.data.list
        resultTotal.value = c.data.pagerInfoDto.totalNum

    })
}

const getList = () => {
    getEnrollmentList().then(c => {
        tableData.value = c.data
    })
}
onMounted(() => {
    getList()
})
</script>

<style lang="scss" scoped></style>