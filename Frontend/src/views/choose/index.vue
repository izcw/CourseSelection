<template>
  <div class="courseinfo-container">
    <el-card v-for="item in 3" :key="item">
      <template #header>
        <div class="card-header">
          <span> 
            <b>java高级程序设计{{item}}</b></span>
        </div>
      </template>
      <el-descriptions>
        <el-descriptions-item label="学年">2023-2024</el-descriptions-item>
        <el-descriptions-item label="学期">2</el-descriptions-item>
        <el-descriptions-item label="课程代码">D080110041</el-descriptions-item>
        <el-descriptions-item label="学分">3.0</el-descriptions-item>
        <el-descriptions-item label="教学班">软件工程-0002</el-descriptions-item>
        <el-descriptions-item label="选课时间">2024-02-22 13:40:30</el-descriptions-item>
        <el-descriptions-item label="上课时间">星期一第10-12节{15-17周(单),18周}</el-descriptions-item>
        <el-descriptions-item label="教学地点">19栋计算机楼503</el-descriptions-item>
        <el-descriptions-item label="教师">王安石</el-descriptions-item>
        <el-descriptions-item label="选修状态">
          <el-tag size="small" type="primary">必修</el-tag>
          <el-tag size="small" type="success">选修</el-tag>
          <el-tag size="small" type="warning">网络</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="课程介绍">
          No.1188, Wuzhong Avenue, Wuzhong District, Suzhou, Jiangsu Province
        </el-descriptions-item>
      </el-descriptions>
    </el-card>

  </div>
</template>

<script setup>
import { ref } from "vue";

// data = ref([
//   {"name":"java高级程序设计"}
// ])
</script>

<style lang="scss" scoped>
.courseinfo-container {
  padding: $base-main-padding;
  background-color: $base-color-white;

  .row {
    margin: 20px 0;
  }

  .group {
    margin: 0 20px;
  }
  .el-card{
    margin-bottom: 2rem;
  }
}
</style>