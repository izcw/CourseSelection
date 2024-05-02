<template>
  <div class="teacherdmin-container">
    <el-space wrap size="large">
      <el-button type="success" @click="centerDialogVisible = true">添加</el-button>
      <el-input v-model="searchName" clearable style="width: 240px" placeholder="请搜索名字" />
    </el-space>
    <br><br>
    <el-table :data="filteredData" border style="width: 100%">
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
      <el-pagination background
      :current-page="currentPage"
      :page-size="pageSize"
      :pager-count="11"
      layout="prev, pager, next"
      :total="tableData.length"
      @current-change="handleCurrentChange"
      />
    </div><!-- 分页 -->

    <el-dialog v-model="centerDialogVisible" title="新增" align-center>
      <span>Open the dialog from the center from the screen</span>
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
import { getStudentList, getAddStudentList ,getDeleteStudentList,getEditorStudentList} from '@/api/student/student.js';

const tableData = ref([]);
const searchName = ref('');
const queryParams = reactive({
  className: '',
  teacherId: 0
});

const currentPage = ref(1);
const pageSize = ref(10);
// 分页改变事件
const handleCurrentChange = (page) => {
  currentPage.value = page;
};


// 获取学生列表
getStudentList().then(res => {
  if (res.code !== 200) {
    console.log("获取不到数据");
  } else {
    console.log("请求：", res);
    tableData.value = res.data;
  }
});

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
  console.log(studentCode);
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
