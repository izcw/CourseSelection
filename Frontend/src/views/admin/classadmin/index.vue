<template>
  <div class="classadmin-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch">
      <el-form-item label="班级名称" prop="className">
        <el-input v-model="queryParams.className" placeholder="请输入班级名称" clearable size="small"
          @keyup.enter.native="handleQuery" />
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

      <el-table-column prop="className" label="班级名称">

      </el-table-column>
      <el-table-column prop="teacher.teacherName" label="班主任">

      </el-table-column>
      <el-table-column prop="numberOfStudent" label="班级人数">

      </el-table-column>

      <el-table-column label="Operations" width="180">
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
    <el-dialog v-model="addVisible" :title="formTitle" width="30%">
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
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="addVisible = false">取消</el-button>
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
import { getList as getClassList, AddClass as addClass, getClassInfo } from '@/api/class';
import { getList as getTeacherList } from '@/api/teacher';
import {
  Search,
  Refresh
} from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
const tableData = ref([])
const teachers = ref([])
const showSearch = ref(true)
const ruleFormRef = ref(null)
const addVisible = ref(false)
const formLabelWidth = '140px'
const formTitle = ref('')
const queryParams = reactive({
  className: '',
  teacherId: undefined

})
const form = ref({
  classId: 0,
  className: '',
  teacherId: 0,
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
  ]
})
onMounted(() => {
  getList();
  getTeachers();
})
const handleQuery = () => {
  getList();
}
const addClassChange = () => {
  formTitle.value = '新增班级'
  addVisible.value = !addVisible.value

}

const handleEdit = (index, row) => {
  let query = {
    classId: row.classId
  }
  getClassInfo(query).then(c => {
    
    form.value.classId = c.data.classId;
    form.value.className = c.data.className;
    form.value.teacherId = c.data.teacherId;
    formTitle.value = '修改班级'
    addVisible.value = !addVisible.value
  })


}
const handleDelete = (index, row) => {
  console.log(index, row)
}
const submit = () => {
  ruleFormRef.value.validate(valid => {
    if (valid) {

      addClass(form).then(c => {
        if (c.code === 200) {
          ElMessage({
            message: c.msg,
            type: 'success',
          })
          addVisible.value = false
          reset()
          getList()
        } else {
          ElMessage.error(c.msg)
        }
      })
    } else {
      ElMessage.error('表单校验失败')
    }

  })
}
const reset = () => {
  form.value.className = ''
  form.value.teacherId = 0
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