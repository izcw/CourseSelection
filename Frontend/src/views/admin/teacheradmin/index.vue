<template>
  <div class="teacherdmin-container">
    <el-table :data="filterTableData" style="width: 100%">
      <el-table-column type="index" width="50" />
      <el-table-column label="Date" prop="date" />
      <el-table-column label="Name" prop="name" />
      <el-table-column align="right">
        <template #header>
          <el-input v-model="search" size="small" placeholder="请搜索" />
        </template>
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
            Edit
          </el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">
            Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
const search = ref('')

const filterTableData = ref([
  {
    date: "你好", name: "张三"
  }
])

const handleEdit = (row) => {
  console.log(row)
}
const handleDelete = (row) => {
  console.log(row)
}

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