<template>
  <div class="classadmin-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch">
      <el-form-item label="班级名称" prop="className">
        <el-input v-model="queryParams.className" placeholder="请输入班级名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="教师名称" prop="teacherId">
        <el-select v-model="queryParams.teacherId" clearable filterable placeholder="请选择要筛选的老师">
          <el-option :label="item.teacherName" :value="item.teacherId" v-for="(item, index) in teachers" :key="index" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" :icon="Search" @click="handleQuery">搜索</el-button>
        <el-button :icon="Refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="20" style="margin-bottom: 20px;">
      <el-col :span="6">
        <el-button type="primary" plain @click="addClassChange">新增</el-button>
      </el-col>

    </el-row>
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="code" label="班级代码" width="150">

      </el-table-column>
      <el-table-column prop="classNumber" label="班号" width="80">

      </el-table-column>
      <el-table-column prop="className" label="班级名称">

      </el-table-column>

      <el-table-column prop="teacher.teacherName" label="班主任">

      </el-table-column>
      <el-table-column prop="numberOfStudent" label="班级人数" width="100">
      </el-table-column>



      <el-table-column label="操作" width="300">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
            修改
          </el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">
            删除
          </el-button>
          <el-button size="small" type="primary" @click="bindingShow(scope.$index, scope.row)">
            绑定/解绑学生
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog v-model="bindingVisible" title="绑定学生" width="50%" draggable overflow>
      <el-table ref="multipleTableRef" :data="studentList" style="width: 100%"
        @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" />
        <el-table-column fixed prop="userName" label="姓名" width="100" />
        <el-table-column prop="studentCode" label="学号" sortable width="150" />
        <el-table-column prop="gender" label="性别" width="60">
          <template v-slot="scope">
            <el-tag type="primary" v-if="scope.row.gender === '1'">男</el-tag>
            <el-tag type="warning" v-else>女</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="age" label="年龄" sortable width="80" />
        <el-table-column prop="phone" label="手机号码" width="120" />
        <el-table-column prop="email" label="电子邮箱" min-width="150" />
      </el-table>
      <el-pagination v-model:current-page="currentPage4" v-model:page-size="studentPager.pageSize"
        :page-sizes="pageSizes" :small="small" :disabled="disabled" :background="background"
        layout="total, sizes, prev, pager, next, jumper" :total="studentTotal" @size-change="handleSizeChange"
        @current-change="handleCurrentChange" />
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="bindingVisible = false">取消</el-button>
          <el-button type="primary" @click="bindingVisible = false">
            确定
          </el-button>
        </div>
      </template>
    </el-dialog>

    <el-dialog v-model="formVisible" :title="formTitle" width="30%">
      <el-form :model="form" :rules="rules" ref="ruleFormRef">
        <el-form-item label="班级名称" :label-width="formLabelWidth" prop="className">
          <el-input v-model="form.className" autocomplete="off" placeholder="请输入班级名称" />
        </el-form-item>
        <el-form-item label="班主任" :label-width="formLabelWidth" prop="teacherId">
          <el-select v-model="form.teacherId" clearable filterable placeholder="请选择老师">
            <el-option :label="item.teacherName" :value="item.teacherId" v-for="(item, index) in teachers"
              :key="index" />
          </el-select>
        </el-form-item>
        <el-form-item label="班级号" prop="classNumber" :label-width="formLabelWidth">
          <el-select-v2 v-model="form.classNumber" clearable filterable placeholder="请选择班级号" :options="options" />
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
import { Timer } from '@element-plus/icons-vue'
import { ref, reactive, onMounted } from 'vue'
import { useI18n } from 'vue-i18n';

import { useStore } from "vuex"
import { getStudentList } from '@/api/student.js';

import { getList as getClassList, AddClass as addClass, getClassInfo, UpdateClass as updateClass, DeleteClass as deleteClass } from '@/api/class';
import { getTeacherList } from '@/api/teacher';
import {
  Search,
  Refresh
} from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
const tableData = ref([])
const teachers = ref([])
const showSearch = ref(true)
const ruleFormRef = ref(null)
const formVisible = ref(false)
const formLabelWidth = '140px'
const formTitle = ref('')
const bindingVisible = ref(false)
const queryParams = reactive({
  className: '',
  teacherId: undefined,
})
const pageSizes = ref([10, 20, 30, 40, 50])
const currentPage4 = ref(1)
const studentList = ref([])
const small = ref(false)
const background = ref(false)
const disabled = ref(false)
const studentTotal = ref(0)
const options = Array.from({ length: 9 }).map((_, idx) => ({
  value: `0${idx + 1}`,
  label: `0${idx + 1}`,
}))

const form = ref({
  classId: 0,
  className: '',
  teacherId: 0,
  classNumber: ''
})
let studentPager = ref({
  pageNum: 1,
  pageSize: 10,
})
const rules = reactive({
  className: [
    { required: true, message: '班级名称不能为空', trigger: 'blur' },
  ],
  teacherId: [
    {
      required: true,
      message: '班主任不能为空',
      trigger: 'change',
    },
  ],
  classNumber: [
    {
      required: true,
      message: '请选择班级号',
      trigger: 'change',
    },
  ],
})
onMounted(() => {
  getList();
  getTeachers();
})
const bindingShow = async () => {
  await getStudentData();
  bindingVisible.value = !bindingVisible.value

}
const getStudentData = async () => {

  await getStudentList(studentPager.value).then(c => {
    studentTotal.value = c.data.pagerInfoDto.totalNum
    studentList.value = c.data.list
  })  
}

const handleSizeChange = (val) => {
  console.log(`${val} items per page`)
}
const handleCurrentChange = (val) => {
  studentPager.value.pageNum = val
  getStudentData();
}
const handleQuery = () => {
  getList();
}
const addClassChange = () => {
  reset();
  formTitle.value = '新增班级'
  formVisible.value = !formVisible.value

}

const handleEdit = (index, row) => {
  let query = {
    classId: row.classId
  }
  getClassInfo(query).then(c => {
    reset();
    form.value.classId = c.data.classId;
    form.value.className = c.data.className;
    form.value.teacherId = c.data.teacherId;
    form.value.classNumber = c.data.classNumber;
    formTitle.value = '修改班级'
    formVisible.value = !formVisible.value
  })


}
const handleDelete = (index, row) => {
  ElMessageBox.confirm(
    `确定要删除班级名称为${row.className}的数据吗?`,
    '提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      let from = {
        classId: row.classId
      }
      deleteClass(from).then(c => {
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
const submit = () => {
  console.log("form::::+++" + JSON.stringify(form));
  ruleFormRef.value.validate(valid => {
    if (valid) {
      if (form.value.classId !== 0) {

        updateClass(form.value).then(c => {
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
        addClass(form.value).then(c => {
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

  form.value.className = ''
  form.value.teacherId = undefined
  form.value.classId = 0
  form.value.classNumber = ''

}
const getList = () => {
  let query = {
    className: queryParams.className,
    teacherId: queryParams.teacherId === undefined ? 0 : queryParams.teacherId
  }
  if (query.teacherId === '') {
    query.teacherId = 0
  }

  getClassList(query).then(c => {
    tableData.value = c.data
  })
}
const getTeachers = () => {
  getTeacherList().then(c => {
    teachers.value = c.data
  })
}
const resetQuery = () => {

  queryParams.className = ''
  queryParams.teacherId = undefined
  getList();
}
let store = useStore();


const { t } = useI18n();
</script>

<style lang="scss" scoped>
.classadmin-container {
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