<template>
  <div class="courseadmin-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true">
      <el-form-item label="课程编号" prop="courseCode">
        <el-input v-model="queryParams.courseCode" placeholder="请输入课程编号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="课程名称" prop="courseName">
        <el-input v-model="queryParams.courseName" placeholder="请输入课程名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="教师名称" prop="teacherId">
        <el-select v-model="queryParams.teacherId" clearable filterable placeholder="请选择要筛选的老师">
          <el-option :label="item.teacherName" :value="item.teacherId" v-for="(item, index) in teachers" :key="index" />
        </el-select>
      </el-form-item>
      <el-form-item label="课程类型" prop="teacherId">
        <el-select v-model="queryParams.courseTypeId" clearable filterable placeholder="请选择要筛选的课程类型">
          <el-option :label="item.typeName" :value="item.courseTypeId" v-for="(item, index) in types" :key="index" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" :icon="Search" @click="handleQuery">搜索</el-button>
        <el-button :icon="Refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="20" style="margin-bottom: 20px;">
      <el-col :span="6">
        <el-button type="primary" plain @click="addCourseChange">新增</el-button>
      </el-col>

    </el-row>
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="courseCode" label="课程代码" width="150">

      </el-table-column>

      <el-table-column prop="courseName" label="课程名称">

      </el-table-column>


      <el-table-column prop="teacher.teacherName" label="任课教师">

      </el-table-column>
      <el-table-column prop="courseType.typeName" label="课程类型">

      </el-table-column>
      <el-table-column prop="credits" label="学分">

      </el-table-column>


      <el-table-column label="操作" width="300">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
            修改
          </el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">
            删除
          </el-button>

        </template>
      </el-table-column>
    </el-table>
    <el-pagination v-model:current-page="queryParams.pageNum" v-model:page-size="queryParams.pageSize"
        :page-sizes="pageSizes" :small="small" :disabled="disabled" :background="background"
        layout="total, sizes, prev, pager, next, jumper" :total="courseTotal" @size-change="handleSizeChange"
        @current-change="handleCurrentChange" />
    <el-dialog v-model="formVisible" :title="formTitle" width="30%">
      <el-form :model="form" :rules="rules" ref="ruleFormRef">
        <el-form-item label="课程名称" :label-width="formLabelWidth" prop="courseName">
          <el-input v-model="form.courseName" autocomplete="off" placeholder="请输入课程名称" />
        </el-form-item>
        <el-form-item label="任课教师" :label-width="formLabelWidth" prop="teacherId">
          <el-select v-model="form.teacherId" clearable filterable placeholder="请选择老师">
            <el-option :label="item.teacherName" :value="item.teacherId" v-for="(item, index) in teachers"
              :key="index" />
          </el-select>
        </el-form-item>
        <el-form-item label="学分" :label-width="formLabelWidth" prop="credits">
          <el-input type="number" step="0.01" v-model="form.credits" autocomplete="off" placeholder="请输入学分" />
        </el-form-item>
        <el-form-item label="课程类型" :label-width="formLabelWidth" prop="courseTypeId">
          <el-select v-model="form.courseTypeId" clearable filterable placeholder="请选择课程类型">
            <el-option :label="item.typeName" :value="item.courseTypeId" v-for="(item, index) in types" :key="index" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="formVisible = false">取消</el-button>
          <el-button type="primary" @click="submit">
            提交
          </el-button>
        </div>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
import { ref, onMounted, reactive, nextTick } from 'vue'
import { getTeacherList as getTeacherList } from '@/api/teacher';
import { getList as getCourseList, AddCourse as addCourse,UpdateCourse as updateCourse,DeleteCourse as deleteCourse,GetCourseById as getCourseById} from '@/api/course';
import { getList as getCourseTypeList } from '@/api/courseType';

const queryParams = ref({
  courseCode: '',
  courseName: '',
  teacherId: undefined,
  courseTypeId: undefined,
  pageNum: 1,
  pageSize: 10,
})
const ruleFormRef = ref(null)
const tableData = ref([])
const courseTotal =ref(0)
const teachers = ref([])
const types = ref([])
const formTitle = ref('')
const formVisible = ref(false)
const rules = reactive({
  courseName: [
    { required: true, message: '课程名称不能为空', trigger: 'blur' },
  ],
  credits: [
    { required: true, message: '学分不能为空', trigger: 'blur' },
  ],
  teacherId: [
    {
      required: true,
      message: '任课教师不能为空',
      trigger: 'change',
    },
  ],
  courseTypeId: [
    {
      required: true,
      message: '课程类型不能为空',
      trigger: 'change',
    },
  ],

})
const form = ref({
  courseId: 0,
  courseName: '',
  teacherId: 0,
  credits: 0.0,
  courseTypeId: 0
})
const formLabelWidth = '140px'
const handleQuery = () => {
  getList();
}
const handleCurrentChange = (val)=>{
  queryParams.value.pageNum = val
  getList()
}
const handleEdit = (index, row) => 
{
  let query = {
    courseId: row.courseId
  }
  getCourseById(query).then(c => {
    reset();
    form.value.courseId = c.data.courseId;
    form.value.courseName = c.data.courseName;
    form.value.credits = c.data.credits;
    form.value.teacherId = c.data.teacherId;
    form.value.courseTypeId = c.data.courseTypeId;
    formTitle.value = '修改班级'
    formVisible.value = !formVisible.value
  })
 }
const submit = () => {
  ruleFormRef.value.validate(valid => {
    if (valid) {
      if (form.value.courseId !== 0) {

        updateCourse(form.value).then(c => {
          if (c.code === 200) {
            ElMessage({
              message: c.msg,
              type: 'success',
            })
            formVisible.value = false
            reset()
            getList()
          } else {
            ElMessage.error(c.msg)
          }
        })
      } else {
        addCourse(form.value).then(c => {
          if (c.code === 200) {
            ElMessage({
              message: c.msg,
              type: 'success',
            })
            formVisible.value = false
            reset()
            getList()
          } else {
            ElMessage.error(c.msg)
          }
        })
      }

    } else {
      ElMessage.error('表单校验失败')
    }

  })
}
const reset = () => {

  form.value.courseName = ''
  form.value.teacherId = undefined
  form.value.courseId = 0
  form.value.credits = 0.0
  form.value.courseTypeId = undefined


}
const resetQuery = () => {
  queryParams.value.courseName = ''
  queryParams.value.courseCode = ''
  queryParams.value.teacherId = undefined
  queryParams.value.courseTypeId = undefined
  queryParams.value.pageNum = 1,
  queryParams.value.pageSize = 10,
  getList();
}
const handleDelete =  (index, row) => 
{
  ElMessageBox.confirm(
    `确定要删除课程名称为${row.courseName}的数据吗?`,
    '提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      let from = {
        courseId: row.courseId
      }
      deleteCourse(from).then(c => {
        if (c.code === 200) {
          ElMessage({
            type: 'success',
            message: '删除成功',
          })
          getList()
        } else {
          ElMessage.error(c.msg)
        }

      })

    })
    .catch(() => {

    })

  console.log(index, row)
}
const addCourseChange = () => {
  reset();
  formTitle.value = '新增课程'
  formVisible.value = !formVisible.value
}
const getList = () => {

  let query = {
    courseCode: queryParams.value.courseCode,
    courseName: queryParams.value.courseName,
    teacherId: queryParams.value.teacherId === undefined ? 0 : queryParams.value.teacherId,
    courseTypeId: queryParams.value.courseTypeId === undefined ? 0 : queryParams.value.courseTypeId,
    pageNum: queryParams.value.pageNum,
  pageSize: queryParams.value.pageSize,
  }
  if (query.teacherId === '') {
    query.teacherId = 0
  }
  if (query.courseTypeId === '') {
    query.courseTypeId = 0
  }

  getCourseList(query).then(c => {
    courseTotal.value = c.data.pagerInfoDto.totalNum
    tableData.value = c.data.list

  })
}
const getTeachers = () => {
  getTeacherList().then(c => {
    teachers.value = c.data.list
  })
}
const getCourseTypes = () => {
  getCourseTypeList().then(c => {
    types.value = c.data
  })
}
onMounted(() => {
  console.log(11111);
  getList();
  getTeachers();
  getCourseTypes();
})
</script>

<style lang="scss" scoped>
.courseadmin-container {
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