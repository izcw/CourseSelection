<template>
    <div class="personalStudent-container">
        <el-row :gutter="20">
          <el-col :span="16">
            <el-descriptions title="基本信息">
              <el-descriptions-item label="姓名">张三</el-descriptions-item>
              <el-descriptions-item label="学号">23300185934</el-descriptions-item>
              <el-descriptions-item label="身份证">4509812003832721</el-descriptions-item>
              <el-descriptions-item label="出生日期">2000-01-01</el-descriptions-item>
              <el-descriptions-item label="民族">汉</el-descriptions-item>
              <el-descriptions-item label="政治面貌">群众</el-descriptions-item>
              <el-descriptions-item label="生源地">广西</el-descriptions-item>
              <el-descriptions-item label="学生类型">本科</el-descriptions-item>
              <el-descriptions-item label="学生类型">本科</el-descriptions-item>
            </el-descriptions>
            <el-divider />
            <el-descriptions title="学籍信息">
              <el-descriptions-item label="年纪">2023</el-descriptions-item>
              <el-descriptions-item label="学院名称">信息工程学院</el-descriptions-item>
              <el-descriptions-item label="专业名称">计算机应用工程</el-descriptions-item>
              <el-descriptions-item label="班级名称">计算机应用工程（专升本）2302</el-descriptions-item>
              <el-descriptions-item label="学制">2</el-descriptions-item>
              <el-descriptions-item label="是否在校">是</el-descriptions-item>
              <el-descriptions-item label="学籍状态">在读</el-descriptions-item>
            </el-descriptions>
            <el-divider />
            <el-descriptions title="联系方式">
              <el-descriptions-item label="电子邮箱">12345@qq.com</el-descriptions-item>
              <el-descriptions-item label="手机号码">12345678910</el-descriptions-item>
              <el-descriptions-item label="地址">广西南宁</el-descriptions-item>
              <el-descriptions-item label="邮政编码">530000</el-descriptions-item>
            </el-descriptions>
          </el-col>
          <el-col :span="8">
            <el-image style="width: 129px; height: 163px" src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg" fit="fill" />
          </el-col>
        </el-row>
    </div>
  </template>
  
  <script setup>
  </script>
  
  <style lang="scss" scoped>
    .personalStudent-container {
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
  