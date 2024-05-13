<template>
  <div class="teacherdmin-container">
    <el-form :model="queryParams" :inline="true">
      <el-form-item label="班级" prop="teacherId">
        <el-select v-model="queryParams.classid" clearable filterable placeholder="请选择要筛选的班级" style="width:300px">
          <el-option v-for="(item, index) in classinfoDate" :key="index" :default="index == 1" :label="item.className"
            :value="item.classId" />
        </el-select>
      </el-form-item>
      <el-form-item label="学生名称" prop="className">
        <el-input v-model.trim="queryParams.studentName" placeholder="请输入学生名称" clearable
          @keyup.enter.native="getData" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" :icon="Search" @click="searchQuery">搜索</el-button>
        <el-button :icon="Refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form><!-- 搜索 -->

    <el-row :gutter="20" style="margin-bottom: 20px;">
      <el-col :span="6">
        <el-button type="success"
          @click="centerDialogVisible = true, dialogboxTitle = 1, clearRuleForm()">添加</el-button>
        <el-popconfirm title="确定要删除吗?" @confirm="deleteMultiple(selectedCodes)">
          <template #reference>
            <el-button type="danger" v-show="MultipleChoice">删除{{ selectedCodes.length }}条数据</el-button>
          </template>
        </el-popconfirm>
      </el-col>
    </el-row><!-- 操作 -->

    <el-divider content-position="left">总{{ dataarrLength }}条数据</el-divider>
    <el-table :data="tableData" border style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="45" />
      <el-table-column fixed prop="userName" label="姓名" width="100" />
      <el-table-column prop="studentCode" label="学号" sortable width="150" />
      <el-table-column prop="gender" label="性别" width="60">
        <template v-slot="scope">
          <el-tag type="primary" v-if="scope.row.gender === '1'">男</el-tag>
          <el-tag type="warning" v-else>女</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="age" label="年龄" sortable width="80" />
      <el-table-column prop="className" label="班级" width="180" />
      <el-table-column prop="phone" label="手机号码" width="120" />
      <el-table-column prop="email" label="电子邮箱" min-width="150" />
      <el-table-column fixed="right" label="操作" width="180">
        <template #default="scope">
          <el-button link type="primary" size="small" @click="handleEdit(scope.row)">
            编辑
          </el-button>
          <el-button link type="warning" size="small" @click="initchangePassword(scope.row.studentCode)">
            修改密码
          </el-button>
          <el-popconfirm title="确定要删除吗?" @confirm="handleDelete(scope.row.studentCode)">
            <template #reference>
              <el-button link type="danger" size="small">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table><!-- 表格 -->
    <br><br>
    <div class="Centermiddle">
      <el-pagination background :current-page="queryParams.pageNum" :page-size="queryParams.pageSize" :pager-count="10"
        layout="prev, pager, next" :total="dataarrLength" @current-change="handleCurrentChange" />
    </div><!-- 分页按钮 -->

    <el-dialog v-model="centerDialogVisible" :title="dialogboxTitle === 1 ? '增加' : (dialogboxTitle === 2 ? '修改' : '')"
      align-center>
      <el-form ref="ruleFormRef" style="max-width: 600px" :model="ruleForm" :rules="rules" label-width="auto"
        class="demo-ruleForm" status-icon>
        <el-form-item label="姓名" prop="name">
          <el-input v-model.trim="ruleForm.name" />
        </el-form-item>
        <el-form-item label="班级" prop="classId">
          <el-select v-model="ruleForm.classId" clearable filterable placeholder="请选择班级"
            :disabled="dialogboxTitle === 2">
            <el-option v-for="(item, index) in classinfoDate" :key="index" :default="index == 1" :label="item.className"
              :value="item.classId" />
          </el-select>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio-group v-model="ruleForm.gender">
            <el-radio :label="1">男</el-radio>
            <el-radio :label="2">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input-number v-model="ruleForm.age" :min="16" :max="60" controls-position="right"></el-input-number>
        </el-form-item>
        <el-form-item label="手机号码" prop="phone">
          <el-input v-model.trim="ruleForm.phone" placeholder="请输入手机号码"></el-input>
        </el-form-item>
        <el-form-item label="电子邮箱" prop="email">
          <el-input v-model.trim="ruleForm.email" placeholder="请输入电子邮箱"></el-input>
        </el-form-item>
      </el-form>
      <el-alert show-icon title="初始密码为学号后六位" type="warning" :closable="false" />
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="centerDialogVisible = false">取消</el-button>
          <el-button v-if="dialogboxTitle == 1" type="primary" @click="addStudent()">
            提交
          </el-button>
          <el-button v-if="dialogboxTitle == 2" type="primary" @click="handleEditSubmit()">
            保存
          </el-button>
        </div>
      </template>
    </el-dialog><!-- 新增窗口 -->
    <changPassword :switch="isSwitched" :code="changePasswordcode" @update:switch="isSwitched = $event"
      @confirmGetData="Modificationcompleted" />
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';
import { getStudentList, getAddStudentList, getDeleteMultipleStudentList, getEditorStudentList } from '@/api/student.js';
import { getList } from '@/api/class';
import changPassword from '@/components/index/changePassword.vue';


const tableData = ref([]); // 数据
let centerDialogVisible = ref(false) // 弹出框
let dialogboxTitle = ref('1')

const ruleForm = reactive({ // 窗口数据
  name: '',
  classId: '',
  gender: 1,
  age: '',
  phone: '',
  email: '',
  studentCode: '',
})

// 初始化ruleForm
const clearRuleForm = () => {
  ruleForm.name = '';
  ruleForm.classId = '';
  ruleForm.gender = 1;
  ruleForm.age = '';
  ruleForm.phone = '';
  ruleForm.email = '';
  ruleForm.studentCode = '';
};

// 表单验证
const rules = reactive({
  name: [
    { required: true, message: '请输入姓名', trigger: 'blur' },
    { min: 2, max: 6, message: '2到6个汉字', trigger: 'blur' },
  ],
  classId: [
    {
      required: true,
      message: '请选择班级',
      trigger: 'change',
    },
  ],
  gender: [
    {
      required: true,
      message: '请选择性别',
      trigger: 'change',
    },
  ],
  age: [
    { required: true, message: '请输入年龄', trigger: 'blur' },
    { type: 'number', min: 16, max: 60, message: '年龄必须在16至60岁之间', trigger: 'blur' },
  ],
  phone: [
    { required: true, message: '请输入手机号码', trigger: 'blur' },
    { pattern: /^1[3456789]\d{9}$/, message: '手机号码格式不正确', trigger: 'blur' }
  ],
  email: [
    { required: true, message: '请输入电子邮箱', trigger: 'blur' },
    { type: 'email', message: '电子邮箱格式不正确', trigger: 'blur' }
  ],
})

// 修改密码
const changePasswordcode = ref() // 传的id
// 父组件的响应式数据
const isSwitched = ref(false);
const initchangePassword = (code) => {
  changePasswordcode.value = code
  isSwitched.value = true
}
const Modificationcompleted = () => { // 修改成功刷新数据
  getData()
}

// 获取班级信息
let classinfoDate = ref()
getList({
  className: '',
  teacherId: 0
}).then(res => {
  if (res.code !== 200) {
    console.log("班级信息-获取不到数据");
  } else {
    classinfoDate.value = res.data
    console.log("班级信息-请求：", res);
  }
});

// 表格多选
const MultipleChoice = ref(false)
const selectedCodes = ref([]); // 初始化为一个数组
const handleSelectionChange = (selection) => {
  MultipleChoice.value = selection && selection.length > 0;
  // 如果是多选状态，打印选中的学号studentCode
  if (MultipleChoice.value) {
    selectedCodes.value = selection.map(item => item.studentCode);
    // console.log("选中的学号数组：", selectedCodes.value);
  }
};


// 分页改变事件
const handleCurrentChange = (page) => {
  queryParams.value.pageNum = page;
  getData()
};


// 搜索
const queryParams = ref({
  classid: "",
  studentName: '',
  pageNum: 1,
  pageSize: 30
})

const searchQuery = () => {
  getData()
  queryParams.value.pageNum = 1
}

// 重置搜索值
const resetQuery = () => {
  queryParams.value.classid = ''
  queryParams.value.studentName = ''
}

// 获取列表
const dataarrLength = ref() // 数据长度
const getData = () => {
  getStudentList(queryParams.value).then(res => {
    if (res.code !== 200) {
      console.log("获取不到数据");
    } else {
      console.log(res, "999");
      tableData.value = res.data.list;
      dataarrLength.value = res.data.pagerInfoDto.totalNum
    }
  });
}
getData()


// 添加
const ruleFormRef = ref(null);
const addStudent = () => {
  ruleFormRef.value.validate((valid) => {
    if (valid) {
      ruleForm.studentCode = studentCodeRule()
      getAddStudentList(ruleForm).then(res => {
        if (res.code != 200) {
          ElMessage.error(res.msg)
        } else {
          ElMessage({
            message: res.msg,
            type: 'success',
          })
          getData()
          centerDialogVisible.value = false;
          clearRuleForm()
        }
      });
    } else {
      console.log("表单验证未通过");
    }
  });
};


// 编辑
const handleEdit = (row) => {
  dialogboxTitle.value = 2
  centerDialogVisible.value = true
  // 赋值
  ruleForm.name = row.userName
  ruleForm.studentCode = row.studentCode
  ruleForm.classId = row.classId
  ruleForm.gender = Number(row.gender)
  ruleForm.age = row.age
  ruleForm.phone = row.phone
  ruleForm.email = row.email
};

// 编辑提交
const handleEditSubmit = () => {
  getEditorStudentList(ruleForm).then(res => {
    if (res.code != 200) {
      ElMessage.error(res.msg)
    } else {
      ElMessage({
        message: res.msg,
        type: 'success',
      })
      getData()
      centerDialogVisible.value = false
    }
  });
}

// 删除
const handleDelete = (studentCode) => {
  deleteMultiple([studentCode])
};

// 多选删除
const deleteMultiple = (data) => {
  getDeleteMultipleStudentList({ data }).then(res => {
    if (res.code != 200) {
      ElMessage.error(res.msg)
    } else {
      ElMessage({
        message: res.msg,
        type: 'success',
      })
      getData()
      centerDialogVisible.value = false
    }
  });
}



// 学号规则（当前年份后两位数+专业班级代码）
const studentCodeRule = () => {
  // 后两位年份
  let now = new Date();
  let year = now.getFullYear();
  let lastTwoDigits = year.toString().slice(-2);

  // 获取专业班级代码
  let classidCode = classinfoDate.value.find(item => item.classId === ruleForm.classId);
  if (!classidCode) {
    console.error('未找到相应班级信息');
    return;
  }

  // 初始化 studentCode
  let studentCode = lastTwoDigits + classidCode.code; // 后两位年份+专业班级代码
  return studentCode;
};
</script>

<style lang="scss" scoped>
.teacherdmin-container {
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
