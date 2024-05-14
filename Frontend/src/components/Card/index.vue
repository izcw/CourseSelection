<template>
    <div>
        <p>学号：345476567</p>
        <p>姓名：张三</p>
        <p>身份：
            <el-tag type="primary" v-if="true">学生</el-tag>
            <el-tag type="warning" v-else>教师</el-tag>
        </p>
        <p>学院：信息工程学院</p>
        <p>班级：计算机应用工程2302</p>
    </div>
</template>
<script setup>

</script>
<style scoped lang='scss'></style>
