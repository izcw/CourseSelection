<template>
  <div class="teacherdmin-container">
    <!-- <el-space wrap size="large">
      <el-button type="success" @click="centerDialogVisible = true">添加</el-button>
      <el-input v-model="searchName" clearable style="width: 240px" placeholder="请搜索名字" />
    </el-space> -->
    <el-form :model="queryParams" :inline="true">
      <el-form-item label="班级" prop="teacherId">
        <el-select v-model="queryParams.teacherId" clearable filterable placeholder="请选择要筛选的班级">
          <el-option :label="item.teacherName" :value="item.teacherId" v-for="(item, index) in teachers" :key="index" />
        </el-select>
      </el-form-item>
      <el-form-item label="学生名称" prop="className">
        <el-input v-model="queryParams.className" placeholder="请输入学生名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" :icon="Search" @click="handleQuery">搜索</el-button>
        <el-button :icon="Refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="20" style="margin-bottom: 20px;">
      <el-col :span="6">
        <el-button type="success" @click="centerDialogVisible = true">添加</el-button>
        <el-popconfirm title="确定要删除吗?" @confirm="deleteMultiple()">
          <template #reference>
            <el-button type="danger" v-show="MultipleChoice">删除</el-button>
          </template>
        </el-popconfirm>
      </el-col>
    </el-row>
    <el-table :data="filteredData" border style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="45" />
      <el-table-column fixed prop="userName" label="姓名" width="100" />
      <el-table-column prop="studentCode" label="学号" width="150" />
      <el-table-column prop="gender" label="性别" width="60">
        <template v-slot="scope">
          <el-tag type="primary" v-if="scope.row.gender === '1'">男</el-tag>
          <el-tag type="warning" v-else>女</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="age" label="年龄" width="60" />
      <el-table-column prop="phone" label="手机号码" width="120" />
      <el-table-column prop="email" label="电子邮箱" min-width="150" />
      <el-table-column fixed="right" label="操作" width="120">
        <template #default="scope">
          <el-button link type="primary" size="small" @click="handleEdit(scope.row)">
            编辑
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
      <el-pagination background :current-page="currentPage" :page-size="pageSize" :pager-count="11"
        layout="prev, pager, next" :total="tableData.length" @current-change="handleCurrentChange" />
    </div><!-- 分页 -->


    <el-dialog v-model="centerDialogVisible" title="新增" align-center>
      <el-form ref="ruleFormRef" style="max-width: 600px" :model="ruleForm" :rules="rules" label-width="auto"
        class="demo-ruleForm" :size="formSize" status-icon>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="ruleForm.name" />
        </el-form-item>
        <el-form-item label="班级" prop="class">
          <el-select v-model="ruleForm.class" placeholder="请选择班级">
            <el-option label="Zone one" value="shanghai" />
            <el-option label="Zone two" value="beijing" />
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
          <el-input v-model="ruleForm.phone" placeholder="请输入手机号码"></el-input>
        </el-form-item>
        <el-form-item label="电子邮箱" prop="email">
          <el-input v-model="ruleForm.email" placeholder="请输入电子邮箱"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="centerDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="addStudent">
            提交
          </el-button>
        </div>
      </template>
    </el-dialog><!-- 新增 -->
  </div>
</template>

<script setup>
import { ref, reactive, computed } from 'vue';
import { getStudentList, getAddStudentList, getDeleteStudentList, getEditorStudentList } from '@/api/student/student.js';
import { getList } from '@/api/class';
import { getUserInfoData } from '@/api/user';
import { useStore } from 'vuex';
const tableData = ref([]);
const searchName = ref('');

const ruleForm = reactive({
  name: 'Hello',
  gender: 1,
  age: '',
  phone: '',
  email: '',
  class: ''
})

const rules = reactive({
  name: [
    { required: true, message: '请输入姓名', trigger: 'blur' },
    { min: 2, max: 6, message: '2到6个汉字', trigger: 'blur' },
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
  class: [
    {
      required: true,
      message: '请选择班级',
      trigger: 'change',
    },
  ]
})


let classinfoDate = ref()
getList().then(c => {
  classinfoDate.value = c.data
  console.log("班级：" + classinfoDate.value);
})
console.log("执行");
getList()

// 表格多选
const MultipleChoice = ref(false)
const selectedStudentCodes = ref([]); // 初始化为一个数组

const handleSelectionChange = (selection) => {
  // 更新MultipleChoice的值
  MultipleChoice.value = selection && selection.length > 0;
  // 如果是多选状态，打印选中的学号studentCode
  if (MultipleChoice.value) {
    selectedStudentCodes.value = selection.map(item => item.studentCode);
    console.log("选中的学号：", selectedStudentCodes.value);
  }
};

const deleteMultiple = () => {
  console.log(selectedStudentCodes.value);
}

const currentPage = ref(1);
const pageSize = ref(10);
// 分页改变事件
const handleCurrentChange = (page) => {
  currentPage.value = page;
};


const queryParams = reactive({
  classid: "1",
  studentNmae: ''
})

const getData = () => {
  // 获取学生列表
  getStudentList(queryParams).then(res => {
    if (res.code !== 200) {
      console.log("获取不到数据");
    } else {
      console.log("获取请求：", res);
      tableData.value = res.data;
    }
  });
}
getData()


// 根据搜索值过滤数据
const filteredData = computed(() => {
  const searchValue = searchName.value.toLowerCase().trim();

  if (!searchValue) {
    return tableData.value.slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value);
  }

  return tableData.value.filter(student =>
    student.userName.toLowerCase().includes(searchValue)
  ).slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value);
});

let centerDialogVisible = ref(false)


// 添加学生
const addStudent = () => {
  centerDialogVisible = false
  getAddStudentList().then(res => {
    console.log("请求：", res);
  });
};

// 编辑学生
const handleEdit = (row) => {
  console.log(row);
  console.log("编辑");
  getStudentList().then(res => {
    console.log("请求：", res);
  });
};

// 删除学生
const handleDelete = (studentCode) => {
  getDeleteStudentList({ studentCode }).then(res => {
    console.log("删除请求：", res);
    if (res.code != 200) {
      ElMessage.error(res.msg)
    } else {
      ElMessage({
        message: res.msg,
        type: 'success',
      })
      getData()
    }
  });
};

const store = useStore();
getUserInfoData(store.getters['user/accessToken']).then(res => {
  if (res.code !== 200) {
    console.log("获取不到数据");
  } else {
    console.log("用户请求：", res);
  }
});
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
