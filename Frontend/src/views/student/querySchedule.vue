<template>
  <div class="querySchedule-container">
    <el-tabs v-model="activeName" type="card" class="demo-tabs" @tab-click="handleClick">
      <el-tab-pane label="表格" name="first">
        <el-table :data="tableData" border style="width: 100%;" :span-method="objectSpanMethod">
          <el-table-column label="时段" width="80">
            <template v-slot="scope">
              <span v-if="scope.$index == 0">上午</span>
              <span v-else-if="scope.$index == 4">下午</span>
              <span v-else>晚上</span>
            </template>
          </el-table-column>
          <el-table-column type="index" label="节次" width="80" />
          <el-table-column label="星期一" width="200">
            <template v-slot="scope">
              {{ scope.row.name1 }}
            </template>
          </el-table-column>
          <el-table-column label="星期二" width="200">
            <template v-slot="scope">
              <el-card shadow="never" class="courseinfo-box" v-if="scope.$index == 0">
                <p class="coursetext"><b>网络爬虫技术{{ scope.row.name2 }}</b></p>
                <p class="coursetext">时间： (1-5节)2-10周</p>
                <p class="coursetext">地点： 本部 （信息）19栋计算机楼503</p>
                <p class="coursetext">教师： 王安石</p>
                <p class="coursetext">课程代码： D080110041</p>
                <p class="coursetext">教学班： 软件工程-0002</p>
                <p class="coursetext">选修状态： 选修</p>
                <p class="coursetext">考试：考察</p>
                <p class="coursetext">学分： 3.0</p>
              </el-card>
            </template>
          </el-table-column>
          <el-table-column label="星期三" width="200">
            <template v-slot="scope">
              {{ scope.row.name3 }}
            </template>
          </el-table-column>
          <el-table-column label="星期四" width="200">
            <template v-slot="scope">
              <el-card shadow="never" class="courseinfo-box" v-if="scope.$index == 4">
                <p class="coursetext"><b>网络爬虫技术{{ scope.row.name4 }}</b></p>
                <p class="coursetext">时间： (1-5节)2-10周</p>
                <p class="coursetext">地点： 本部 （信息）19栋计算机楼503</p>
                <p class="coursetext">教师： 王安石</p>
                <p class="coursetext">课程代码： D080110041</p>
                <p class="coursetext">教学班： 软件工程-0002</p>
                <p class="coursetext">选修状态： 选修</p>
                <p class="coursetext">考试：考察</p>
                <p class="coursetext">学分： 3.0</p>
              </el-card>
            </template>
          </el-table-column>
          <el-table-column label="星期五" width="200">
            <template v-slot="scope">
              {{ scope.row.name5 }}
            </template>
          </el-table-column>
          <el-table-column label="星期六" width="200">
            <template v-slot="scope">
              {{ scope.row.name6 }}
            </template>
          </el-table-column>
          <el-table-column label="星期日" width="200">
            <template v-slot="scope">
              {{ scope.row.name7 }}
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="图形" name="second">
        <MySchedule></MySchedule>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import MySchedule from '@/components/Echarts/MySchedule.vue';
const activeName = ref('first')
const tableData = [
  {
    name1: "1",
    name2: "2",
    name3: "3",
    name4: "4",
    name5: "5",
    name6: "6",
    name7: "7",
    name8: "8",
    name9: "9",
  },
  {
    name1: "1",
    name2: "2",
    name3: "3",
    name4: "4",
    name5: "5",
    name6: "6",
    name7: "7",
    name8: "8",
    name9: "9",
  },
  {
    name1: "1",
    name2: "2",
    name3: "3",
    name4: "4",
    name5: "5",
    name6: "6",
    name7: "7",
    name8: "8",
    name9: "9",
  },
  {
    name1: "1",
    name2: "2",
    name3: "3",
    name4: "4",
    name5: "5",
    name6: "6",
    name7: "7",
    name8: "8",
    name9: "9",
  },
  {
    name1: "1",
    name2: "2",
    name3: "3",
    name4: "4",
    name5: "5",
    name6: "6",
    name7: "7",
    name8: "8",
    name9: "9",
  },
  {
    name1: "1",
    name2: "2",
    name3: "3",
    name4: "4",
    name5: "5",
    name6: "6",
    name7: "7",
    name8: "8",
    name9: "9",
  },
  {
    name1: "1",
    name2: "2",
    name3: "3",
    name4: "4",
    name5: "5",
    name6: "6",
    name7: "7",
    name8: "8",
    name9: "9",
  },
  {
    name1: "1",
    name2: "2",
    name3: "3",
    name4: "4",
    name5: "5",
    name6: "6",
    name7: "7",
    name8: "8",
    name9: "9",
  },
  {
    name1: "1",
    name2: "2",
    name3: "3",
    name4: "4",
    name5: "5",
    name6: "6",
    name7: "7",
    name8: "8",
    name9: "9",
  },
  {
    name1: "1",
    name2: "2",
    name3: "3",
    name4: "4",
    name5: "5",
    name6: "6",
    name7: "7",
    name8: "8",
    name9: "9",
  }
]
const objectSpanMethod = ({ row, column, rowIndex, columnIndex }) => {
  if (columnIndex === 0 && rowIndex < 8) {
    // 每四行合并一次
    if (rowIndex % 4 === 0) {
      return {
        rowspan: 4, // 每四行合并
        colspan: 1 // 不合并列
      };
    } else {
      // 不是每四行的最后一行，不需要渲染
      return {
        rowspan: 0,
        colspan: 0
      };
    }
  }
  // 最后两行合并
  if (columnIndex === 0 && rowIndex >= 8 && rowIndex < 10) {
    if (rowIndex % 2 === 0) {
      return {
        rowspan: 2,
        colspan: 1
      };
    } else {
      return {
        rowspan: 0,
        colspan: 0
      };
    }
  }

  // 合并第三列的第一行和第四行
  if (columnIndex === 3) { // 第三列
    if (rowIndex === 0) { // 第1节至第四节合并
      return {
        rowspan: 4,
        colspan: 1
      };
    } else if (rowIndex === 1 || rowIndex === 2 || rowIndex === 3) { // 因为合并了第2至4节不显示
      return {
        rowspan: 0,
        colspan: 0
      };
    }
  }

  if (columnIndex === 5) {
    if (rowIndex === 4) {
      return {
        rowspan: 2,
        colspan: 1
      };
    } else if (rowIndex === 5) { // 因为合并了第2至4节不显示
      return {
        rowspan: 0,
        colspan: 0
      };
    }
  }
}


</script>

<style lang="scss" scoped>
.querySchedule-container {
  padding: $base-main-padding;
  background-color: $base-color-white;

  /* 如果您想要设置整个表格单元格的内联高度，可以尝试下面的代码 */
  ::v-deep .el-table__body-wrapper {
    .el-table__row {
      height: 100px !important;
    }
  }
  .row {
    margin: 20px 0;
  }

  .group {
    margin: 0 20px;
  }

  .coursetext {
    margin: 6px 0;
  }


}
</style>