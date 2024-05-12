<template>
  <div class="courseadmin-container">
    <el-row :gutter="20" style="margin-bottom: 20px;">
      <el-col :span="6">
        <el-button type="primary" plain @click="addEnrollmentChange">新增</el-button>
      </el-col>

    </el-row>
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="selectionPlanName" label="选课方案名称">

      </el-table-column>

      <el-table-column prop="beginTime" label="开始时间">

      </el-table-column>


      <el-table-column prop="endTime" label="结束时间">

      </el-table-column>
      <el-table-column prop="enrollmentMethod" label="选课方式">

      </el-table-column>
      <el-table-column prop="state" label="状态" >
        <template  #default="scope">
            <el-tag v-if="scope.row.state===2">{{ formatterState(scope.row.state) }}</el-tag>
            <el-tag type="success" v-if="scope.row.state===1">{{ formatterState(scope.row.state) }}</el-tag>
          </template>
      </el-table-column>


      <el-table-column label="操作" width="450">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
            修改
          </el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">
            删除
          </el-button>
          <el-button size="small" type="primary" @click="release(scope.$index, scope.row)">
            发布/取消发布
          </el-button>
          <el-button :disabled="scope.row.state===1" size="small" type="primary" @click="release(scope.$index, scope.row)">
            绑定课程
          </el-button>
          <el-button :disabled="scope.row.state===1" size="small" type="primary" @click="release(scope.$index, scope.row)">
            绑定班级
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog v-model="formVisible" :title="formTitle" width="30%">
      <el-form :model="form" :rules="rules" ref="ruleFormRef">
        <el-form-item label="选课方案名称" :label-width="formLabelWidth" prop="selectionPlanName">
          <el-input v-model="form.selectionPlanName" autocomplete="off" placeholder="请输入选课方案名称" />
        </el-form-item>
        <el-form-item label="选课时间" :label-width="formLabelWidth" prop="date">
          <el-date-picker :disabled-date="disabledDate" v-model="form.date" type="datetimerange"
            start-placeholder="开始时间" end-placeholder="结束时间" format="YYYY-MM-DD HH:mm:ss"
            value-format="YYYY-MM-DD HH:mm:ss" />
        </el-form-item>
        <el-form-item label="选课方式" :label-width="formLabelWidth">
          <el-select v-model="form.enrollmentMethod" placeholder="选课方式" size="large" style="width: 240px">
            <el-option v-for="item in enrollmentMethod" :key="item.value" :label="item.label" :value="item.value" />
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
import {
  getList as getEnrollmentList,
  AddEnrollment as addEnrollment,
  UpdateEnrollment as updateEnrollment,
  DeleteEnrollment as deleteEnrollment,
  GetEnrollmentById as getEnrollmentById,
  Release 
} from '@/api/enrollment';
import { useI18n } from 'vue-i18n';
const { t } = useI18n();
const tableData = ref([])
const handleEdit = (index, row) => {
  let query = {
    enrollmentId: row.enrollmentId
  }
  getEnrollmentById(query).then(c => {
    reset();

    form.value.enrollmentId = c.data.enrollmentId;
    form.value.enrollmentName = c.data.enrollmentName;
    form.value.date.push(c.data.beginTime)
    form.value.date.push(c.data.endTime)
    form.value.enrollmentMethod = c.data.enrollmentMethod;
    form.value.selectionPlanName = c.data.selectionPlanName;
    formTitle.value = '修改班级'
    formVisible.value = !formVisible.value
  })
}
const formatterState= (state)=>{
  if(state===2){
    return '未发布';
  }else{
    return '已发布'
  }
  console.log();
}
const handleDelete = (index, row) => {
  ElMessageBox.confirm(
    `确定要删除方案名称为${row.selectionPlanName}的数据吗?`,
    '提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      let from = {
        enrollmentId: row.enrollmentId
      }
      deleteEnrollment(from).then(c => {
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
const release = (index,row) => 
{
  var text = '发布'
  if(row.state==1){
    text = '取消发布'
  }
  ElMessageBox.confirm(
    `确定要${text}方案名称为${row.selectionPlanName}的数据吗?`,
    '提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      let from = {
        enrollmentId: row.enrollmentId
      }
      Release(from).then(c => {
        if (c.code === 200) {
          ElMessage({
            type: 'success',
            message: text+'成功',
          })
          getList()
        } else {
          ElMessage.error(c.msg)
        }

      })

    })
    .catch(() => {

    })
  
 }

const formVisible = ref(false)
const formTitle = ref('')
const formLabelWidth = '140px'
const ruleFormRef = ref(null)
const disabledDate = (time) => {

  return time.getTime() < Date.now() - 8.64e7
}
const enrollmentMethod = ref([
  {
    value: '线上',
    label: '线上'
  }
])
const rules = reactive({
  selectionPlanName: [
    { required: true, message: '选课方案名称不能为空', trigger: 'blur' },
  ],
  date: [
    {
      type: 'array', required: true, message: '请选择时间',
      fields: {
        0: { type: 'string', required: true, message: '请选择开始日期' },
        1: { type: 'string', required: true, message: '请选择结束日期' }
      }
    }
  ],
  enrollmentMethod: [
    {
      required: true,
      message: '选课方式不能为空',
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
  enrollmentId: 0,
  selectionPlanName: '',
  date: [],
  enrollmentMethod: '',

})
const submit = () => {
  ruleFormRef.value.validate(valid => {
    if (valid) {
      let formData = {
        enrollmentId: form.value.enrollmentId,
        selectionPlanName: form.value.selectionPlanName,
        beginTime: form.value.date[0],
        endTime: form.value.date[1],
        enrollmentMethod: form.value.enrollmentMethod,
      }
      if (formData.enrollmentId !== 0) {

        updateEnrollment(formData).then(c => {
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
        addEnrollment(formData).then(c => {
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
const addEnrollmentChange = () => {
  reset();
  formTitle.value = '新增选课'
  formVisible.value = !formVisible.value
}
const reset = () => {
  form.value.selectionPlanName = ''
  form.value.date = []

  form.value.enrollmentMethod = ''
  form.value.enrollmentId = 0
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

.demo-datetime-picker {
  display: flex;
  width: 100%;
  padding: 0;
  flex-wrap: wrap;
  justify-content: space-around;
  align-items: stretch;
}

.demo-datetime-picker .block {
  padding: 30px 0;
  text-align: center;
}

.line {
  width: 1px;
  background-color: var(--el-border-color);
}
</style>