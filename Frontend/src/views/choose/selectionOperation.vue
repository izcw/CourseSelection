<template>
    <div class="queryCourse-container">
        <el-table :data="arrData" :default-sort="{ prop: 'date', order: 'descending' }" style="width: 100%">
            <el-table-column prop="selectionPlanName" label="课程名称" />
            <el-table-column prop="enrollmentMethod" label="课程类别" width="80" />
            <el-table-column prop="beginTime" label="选课开放时间" width="180" />
            <el-table-column prop="endTime" label="选课结束时间" width="180" />
            <el-table-column prop="time" label="剩余时间" width="180">
                <template v-slot="scope">
                    <Countdown :endTime="scope.row.endTime" />
                </template>
            </el-table-column>
            <el-table-column prop="status" label="状态" width="120">
                <template v-slot="scope">
                    <el-tag :type="scope.row.status === '可选' ? 'primary' : 'info'">
                        {{ scope.row.status }}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="180">
                <template #default="scope">
                    <el-button :disabled="scope.row.status === '已停止选课'" link type="success" size="small"
                        @click="showCourse(scope.row)">
                        查看课程
                    </el-button>
                </template>
            </el-table-column>
        </el-table>


        <el-dialog v-model="centerDialogVisible" title="选课" align-center style="width: 100px !important">
            <el-table :data="showarrData" :default-sort="{ prop: 'date', order: 'descending' }" style="width: 100%">
                <el-table-column prop="courseName" fixed="left" label="课程名称" width="180" />
                <el-table-column prop="credits" label="学分" width="80">
                    <template #default="scope">
                        <el-button type="primary" link>
                            {{ scope.row.credits }}
                        </el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="courseCode" label="课程编号" width="100" />
                <el-table-column prop="teacher.teacherName" label="老师" width="100" />
                <el-table-column prop="courseType" label="是否必修" width="80">
                    <template #default="scope">
                        <el-tag v-if="scope.row.courseType.courseTypeId == '1'" type="primary">{{
                            scope.row.courseType.typeName }}</el-tag>
                        <el-tag v-else-if="scope.row.courseType.courseTypeId == '2'" type="info">{{
                            scope.row.courseType.typeName }}</el-tag>
                        <el-tag v-else type="warning">{{ scope.row.courseType.typeName }}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="maximumQuantity" label="最大可选人数/已选人数" width="200">
                    <template #default="scope">
                        {{ scope.row.maximumQuantity }}/{{ scope.row.selectednumberpeople }}
                    </template>
                </el-table-column>
                <el-table-column fixed="right" label="操作" width="100">
                    <template #default="scope">
                        <el-popconfirm title="确定选择此课程吗？" @confirm="selectMultiple(scope.row)">
                            <template #reference>
                                <el-button
                                    v-if="scope.row.maximumQuantity > scope.row.selectednumberpeople && !isSelected[scope.$index]"
                                    type="warning" size="small">选择</el-button>
                                <el-button v-else-if="isSelected[scope.$index]" disabled plain type="info" size="small"
                                    round>已选</el-button>
                                <el-button v-else type="info" size="small" round disabled plain>已满</el-button>
                            </template>
                        </el-popconfirm>
                    </template>
                </el-table-column>

            </el-table>
            <br><br>
            <el-alert show-icon title="可在：学生信息》查询选课信息模块中查看已经选择的课程" type="warning" :closable="false" />

            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="centerDialogVisible = false">关闭</el-button>
                </div>
            </template>
        </el-dialog>
    </div>
</template>

<script setup>
import { ref, defineProps, computed } from 'vue';
import Countdown from '@/components/Card/Countdown.vue'; // 导入子组件
import { getviewcoursetList, getSelectStudentcourses, getSelectcoursesList } from '@/api/student_enrollment.js';

const centerDialogVisible = ref(false)

const props = defineProps({
    data: Object,
    studentinfo: Object
});

const arrData = ref(props.data)
const studentInfo = ref(props.studentinfo)
const enrollmentId = ref()
const rowdata = ref()
// 查看选课
const showarrData = ref(props.data)
const showCourse = (row) => {
    rowdata.value = row
    centerDialogVisible.value = true
    enrollmentId.value = rowdata.value.enrollmentId
    // 实现查看课程的逻辑
    getviewcoursetList({ enrollmentId: enrollmentId.value }).then(res => {
        if (res.code !== 200) {
            console.log("课程信息-获取不到数据");
        } else {
            showarrData.value = res.data
        }
    });
}

// 选择课程
const selectMultiple = (data) => {
    getSelectStudentcourses({
        courseCode: data.courseCode,
        enrollmentId: enrollmentId.value,
        studentCode: studentInfo.value.studentCode
    }).then(res => {
        if (res.code != 200) {
            ElMessage.error(res.msg)
        } else {
            ElMessage({
                message: res.msg,
                type: 'success',
            })
            showCourse(rowdata.value)
            showMyselect()
        }
    });
}

// 查看我的选课
const myselectArr = ref()
const showMyselect = () => {
    getSelectcoursesList({ studentCode: studentInfo.value.studentCode }).then(res => {
        if (res.code !== 200) {
            console.log("我的选课信息-获取不到数据");
        } else {
            myselectArr.value = res.data
        }
    });
}
showMyselect()


const isSelected = computed(() => {
    const selectedCourses = myselectArr.value.map(course => course.courseCode);
    return showarrData.value.map(course => selectedCourses.includes(course.courseCode));
});
</script>

<style lang="scss" scoped>
.queryCourse-container {
    padding: 20px;
    background-color: #ffffff;
}
</style>