<template>
  <div class="personalStudent-container">
    <el-row :gutter="20">
      <el-col :span="16">
        <el-descriptions title="基本信息">
          <el-descriptions-item label="姓名">{{ info.userName }}</el-descriptions-item>
          <el-descriptions-item label="学号">{{ info.studentCode }}</el-descriptions-item>
          <el-descriptions-item label="性别">
            <el-tag type="primary" v-if="info.gender == '1'">男</el-tag>
            <el-tag type="danger" v-else>女</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="年龄">{{ info.age }}</el-descriptions-item>
          <el-descriptions-item label="身份证">- - - -</el-descriptions-item>
          <el-descriptions-item label="出生日期">- - - -</el-descriptions-item>
          <el-descriptions-item label="民族">- - - -</el-descriptions-item>
          <el-descriptions-item label="政治面貌">- - - -</el-descriptions-item>
          <el-descriptions-item label="生源地">- - - -</el-descriptions-item>
          <el-descriptions-item label="学生类型">本科</el-descriptions-item>
        </el-descriptions>
        <el-divider />
        <el-descriptions title="学籍信息">
          <el-descriptions-item label="年级">- - - -</el-descriptions-item>
          <el-descriptions-item label="学院名称">- - - -</el-descriptions-item>
          <el-descriptions-item label="专业名称">- - - -</el-descriptions-item>
          <el-descriptions-item label="班级名称">- - - -</el-descriptions-item>
          <el-descriptions-item label="学制">4</el-descriptions-item>
          <el-descriptions-item label="是否在校">是</el-descriptions-item>
          <el-descriptions-item label="学籍状态">在读</el-descriptions-item>
        </el-descriptions>
        <el-divider />
        <el-descriptions title="联系方式">
          <el-descriptions-item label="电子邮箱">{{ info.email }}</el-descriptions-item>
          <el-descriptions-item label="手机号码">{{ info.phone}}</el-descriptions-item>
          <el-descriptions-item label="地址">- - - -</el-descriptions-item>
          <el-descriptions-item label="邮政编码">- - - -</el-descriptions-item>
        </el-descriptions>
      </el-col>
      <el-col :span="8">
        <el-image style="width: 129px; height: 163px"
          src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg" fit="fill" />
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useStore } from 'vuex';
const store = useStore();

const info = ref({})
async function getUserInfo() {
  info.value = await store.getters['user/info'][0]
  console.log("info----" + JSON.stringify(info.value));
}
setTimeout(() => {
  getUserInfo()
}, 200)
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