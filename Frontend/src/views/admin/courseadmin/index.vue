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
     


      <el-table-column label="操作" width="450">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
            修改
          </el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">
            删除
          </el-button>
      
          <el-button :disabled="scope.row.state === 1" size="small" type="primary"
            @click="bindingCourseShow(scope.$index, scope.row)">
            绑定课程
          </el-button>
          <el-button :disabled="scope.row.state === 1" size="small" type="primary"
            @click="bindingClassShow(scope.$index, scope.row)">
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
    <el-dialog v-model="bindingCourseVisible" title="绑定课程" width="50%" draggable overflow>
      <el-table ref="courseMultipleTableRef" :data="courseList" style="width: 100%" @select-all="courseHandleAllChange"
        @select="courseHandleSelectionChange" @cell-click="showUnitInput">
        <el-table-column type="selection" width="55" />
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
        <el-table-column label="最大选择人数">
          <template #default="{ row, column }">
            <el-input v-if="
              boundMaxNum.find(c => c.courseId === row.courseId) != undefined" @input="blurValueInput(row, column)"
              @keyup.enter="blurValueInput(row, column)"
              v-model.number="boundMaxNum.find(c => c.courseId === row.courseId).maximumQuantity" />
            <span v-else>{{ boundMaxNum.find(c => c.courseId === row.courseId)!=undefined?boundMaxNum.find(c => c.courseId === row.courseId).maximumQuantity:'' }}</span>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination v-model:current-page="coursePager.pageNum" v-model:page-size="coursePager.pageSize"
        :page-sizes="pageSizes" :small="small" :disabled="disabled" :background="background"
        layout="total, sizes, prev, pager, next, jumper" :total="courseTotal" @size-change="handleSizeChange"
        @current-change="handleCourseCurrentChange" />
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="bindingCourseVisible = false">取消</el-button>
          <el-button type="primary" @click="bindingCourseSubmit">
            确定
          </el-button>
        </div>
      </template>
    </el-dialog>
    <el-dialog v-model="bindingClassVisible" title="绑定班级" width="50%" draggable overflow>
      <el-table ref="classMultipleTableRef" :data="classList" style="width: 100%" @select-all="classHandleAllChange"
        @select="classHandleSelectionChange">
        <el-table-column type="selection" width="55" />
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
      </el-table>
      <el-pagination v-model:current-page="classPager.pageNum" v-model:page-size="classPager.pageSize"
        :page-sizes="pageSizes" :small="small" :disabled="disabled" :background="background"
        layout="total, sizes, prev, pager, next, jumper" :total="classTotal" @size-change="handleSizeChange"
        @current-change="handleClassCurrentChange" />
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="bindingClassVisible = false">取消</el-button>
          <el-button type="primary" @click="bindingClassSubmit">
            确定
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
  Release,
  BindingCourse as bindingCourse,
  BindingClass as bindingClass,
  GetBoundCourse as getBoundCourse,
  GetBoundClass as getBoundClass
} from '@/api/enrollment';
import {
  getList as getClassList,
} from '@/api/class';
import { getList as getCourseList } from '@/api/course';
import { GetStudentCountByClassId } from '@/api/student';
import { useI18n } from 'vue-i18n';
const { t } = useI18n();
const tableData = ref([])
let selectionEnrollmentId = ref(0)
const pageSizes = ref([5, 10, 20, 30, 40, 50])
const courseList = ref([])
const classList = ref([])
const courseMultipleTableRef = ref()
const classMultipleTableRef = ref()
const bindingCourseVisible = ref(false)
const bindingClassVisible = ref(false)

const formVisible = ref(false)

const formTitle = ref('')
const formLabelWidth = '140px'
const ruleFormRef = ref(null)
//选择的数据
const boundCourses = ref([])
const boundMaxNum = ref([])
//选择的数据
const boundClass = ref([])
const coursePager = ref({
  pageNum: 1,
  pageSize: 10
})
const classPager = ref({
  pageNum: 1,
  pageSize: 10
})
const courseTotal = ref(0)
const classTotal = ref(0)
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

let tableRowEditId = ref(null) // 控制可编辑的每一行
let tableColumnEditIndex = ref(null) //控制可编辑的每一列
const showUnitInput = (row, column) => {
  //赋值给定义的变量
  tableRowEditId.value = row.courseId //确定点击的单元格在哪行 如果数据中有ID可以用ID判断，没有可以使用其他值判断，只要能确定是哪一行即可
  tableColumnEditIndex.value = column.courseId //确定点击的单元格在哪列 
}
const blurValueInput = (row, column) => {
  // tableRowEditId.value = null
  // tableColumnEditIndex.value = null
  //在此处调接口传数据
  
}

const bindingCourseSubmit = () => {
  ElMessageBox.confirm(
    '您确定要绑定所选课程吗?',
    '提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'Warning',
    }
  )
    .then(() => {
      let params = {
        enrollmentId: selectionEnrollmentId.value,
        courseIds: boundCourses.value,
        boundMaxNum:boundMaxNum.value
      }
      bindingCourse(params).then(c => {
        if (c.code === 200) {
          ElMessage({
            type: 'success',
            message: '绑定成功',
          })
          bindingCourseVisible.value = false
          getList();
        } else {
          ElMessage.error(c.msg)
        }

      })

    })
    .catch(() => {

    })
}
const bindingClassSubmit = () => {
  ElMessageBox.confirm(
    '您确定要绑定所选班级吗?',
    '提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'Warning',
    }
  )
    .then(() => {
      let params = {
        enrollmentId: selectionEnrollmentId.value,
        classIds: boundClass.value
      }
      bindingClass(params).then(c => {
        if (c.code === 200) {
          ElMessage({
            type: 'success',
            message: '绑定成功',
          })
          bindingClassVisible.value = false
          getList();
        } else {
          ElMessage.error(c.msg)
        }

      })

    })
    .catch(() => {

    })
}

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
const formatterState = (state) => {
  if (state === 2) {
    return '未发布';
  } else {
    return '已发布'
  }
  console.log();
}
const handleCourseCurrentChange = (val) => {
  coursePager.value.pageNum = val

  GetCourseList()
}
const handleClassCurrentChange = (val) => {
  classPager.value.pageNum = val
  GetClassList()
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


const release = (index, row) => {
  var text = '发布'
  if (row.state == 1) {
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
            message: text + '成功',
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
const bindingCourseShow = async (index, row) => {


  coursePager.value.pageNum = 1
  coursePager.value.pageSize = 10
  selectionEnrollmentId.value = row.enrollmentId
  bindingCourseVisible.value = true
  nextTick(() => {
    courseMultipleTableRef.value.clearSelection()
  })

  // 获取回显数据列表
  await GetBoundCourse();
  // 获取分页列表
  await GetCourseList();


}
const GetBoundCourse = async () => {
  let params = {
    enrollmentId: selectionEnrollmentId.value
  }
  await getBoundCourse(params).then(res => {
    boundCourses.value = res.data.map(c => c.courseId)
    boundMaxNum.value = res.data.map(c => {
      return { courseId: c.courseId, maximumQuantity: c.maximumQuantity }
    })
  })
}
const GetCourseList = async () => {

  await getCourseList(coursePager.value).then(c => {

    courseTotal.value = c.data.pagerInfoDto.totalNum
    courseList.value = c.data.list

    nextTick(() => {
      courseList.value.forEach(item => {
        // 重点： 在当前分页列表中查询与回显数据是否有一致的id，一致则勾选数据回显
        // toggleRowSelection(item, true)：设置当前行数据为选中状态
        if (boundCourses.value.includes(item.courseId)) {
          courseMultipleTableRef.value.toggleRowSelection(item, true);
          if (boundMaxNum.value.find(c => c.courseId === item.courseId) === undefined) {
            boundMaxNum.value.push({
              courseId: item.courseId,
              maximumQuantity: 0
            })
          }
        }

      });

    })

  })
}
// 选择关联课程：打勾或取消
const courseHandleSelectionChange = (selecteds, row) => {
  if (!boundCourses.value.includes(row.courseId)) {
    // 回显数据里没有本条，把这条加进来(选中)
    boundCourses.value.push(row.courseId);
  } else {
    // 回显数据里有本条，把这条删除(取消选中)
    boundCourses.value.forEach((id, index) => {
      if (id === row.courseId) {
        boundCourses.value.splice(index, 1);
      }
    });
  }
  if (boundMaxNum.value.find(c => c.courseId === row.courseId) === undefined) {
    // 回显数据里没有本条，把这条加进来(选中)
    boundMaxNum.value.push({
      courseId: row.courseId,
      maximumQuantity: 0
    })
  } else {
    // 回显数据里有本条，把这条删除(取消选中)
    boundMaxNum.value.forEach((item, index) => {
      if (item.courseId === row.courseId) {
        boundMaxNum.value.splice(index, 1);
      }
    });
  }
}
const courseHandleAllChange = (selecteds) => {
  if (selecteds.length > 0) {
    selecteds.forEach(item => {
      if (!boundCourses.value.includes(item.courseId)) {
        boundCourses.value.push(item.courseId);
      }
      if (boundMaxNum.value.find(c => c.courseId === item.courseId) === undefined) {
        // 回显数据里没有本条，把这条加进来(选中)
        boundMaxNum.value.push({
          courseId: item.courseId,
          maximumQuantity: 0
        })
      }
    });
  } else {
    courseList.value.forEach(item => {
      boundCourses.value.forEach((id, index) => {
        if (id === item.courseId) {
          boundCourses.value.splice(index, 1);
          boundMaxNum.value.splice(index, 1);
        }
        
      });
    });

   
  }

}

const bindingClassShow = async (index, row) => {


  classPager.value.pageNum = 1
  classPager.value.pageSize = 10
  selectionEnrollmentId.value = row.enrollmentId
  bindingClassVisible.value = true
  nextTick(() => {
    classMultipleTableRef.value.clearSelection()
  })

  // 获取回显数据列表
  await GetBoundClass();
  // 获取分页列表
  await GetClassList();


}
const GetBoundClass = async () => {
  let params = {
    enrollmentId: selectionEnrollmentId.value
  }
  await getBoundClass(params).then(res => {
    boundClass.value = res.data.map(c => c.classId)

  })
}
const GetClassList = async () => {
  await getClassList(classPager.value).then(c => {

    classTotal.value = c.data.pagerInfoDto.totalNum
    classList.value = c.data.list
    classList.value.forEach(item => {
      let query = {
        classId: item.classId
      }
      GetStudentCountByClassId(query).then(res => {
        item.numberOfStudent = res.data
      })

    })
    nextTick(() => {
      classList.value.forEach(item => {
        // 重点： 在当前分页列表中查询与回显数据是否有一致的id，一致则勾选数据回显
        // toggleRowSelection(item, true)：设置当前行数据为选中状态
        if (boundClass.value.includes(item.classId)) {
          classMultipleTableRef.value.toggleRowSelection(item, true);
        }
      });

    })

  })
}
// 选择关联班级：打勾或取消
const classHandleSelectionChange = (selecteds, row) => {
  if (!boundClass.value.includes(row.classId)) {
    // 回显数据里没有本条，把这条加进来(选中)
    boundClass.value.push(row.classId);
  } else {
    // 回显数据里有本条，把这条删除(取消选中)
    boundClass.value.forEach((id, index) => {
      if (id === row.classId) {
        boundClass.value.splice(index, 1);
      }
    });
  }
}
//全选班级事件
const classHandleAllChange = (selecteds) => {
  if (selecteds.length > 0) {
    selecteds.forEach(item => {
      if (!boundClass.value.includes(item.classId)) {
        boundClass.value.push(item.classId);
      }
    });
  } else {
    classList.value.forEach(item => {
      boundClass.value.forEach((id, index) => {
        if (id === item.classId) {
          boundClass.value.splice(index, 1);
        }
      });
    });
  }

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