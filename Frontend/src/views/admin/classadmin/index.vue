<template>
  <div class="classadmin-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch"  >
      <el-form-item label="班级名称" prop="className">
        <el-input v-model="queryParams.className" placeholder="请输入班级名称" clearable size="small"
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="教师名称" prop="teacherId">
        <el-input v-model="queryParams.teacherId" placeholder="请输入教师名称" clearable size="small"
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item >     
        <el-button type="primary" :icon="Search" >搜索</el-button>
        <el-button :icon="Refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { ref ,reactive,onMounted} from 'vue'
import { useI18n } from 'vue-i18n';

import { useStore } from "vuex"
import {getList as getClassList} from '@/api/class';

import {
  Search,
  Refresh
} from '@element-plus/icons-vue'

onMounted(()=>{
  getList();
})

const queryParams = reactive({
  className:'',
  teacherId:0

})
const showSearch = ref(true)
const handleQuery = () => {

}
const getList = ()=>{
  
  getClassList(queryParams).then(c=>{
    console.log(c)
  })
}
const resetQuery=()=>{
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