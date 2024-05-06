<template>
    <div class="MySchedule">
        <el-dropdown size="large">
            <span class="el-dropdown-link">
                第{{ selectedWeek }}周
                <icon-down class="icon-hover refresh" theme="outline" size="16" :fill="color" :strokeWidth="3" />
            </span>
            <template #dropdown>
                <el-dropdown-menu>
                    <el-dropdown-item v-for="week in weeks" :key="week" @click="selectWeek(week)"
                        :disabled="week == selectedWeek">第{{ week
                        }}周</el-dropdown-item>
                </el-dropdown-menu>
            </template>
        </el-dropdown>

        <div class="schedule">
            <div class="time-column">
                <div class="time-slot" v-for="timeSlot in timeSlots" :key="timeSlot"
                    :style="{ top: timeSlot * 50 + 10 + 'px' }">
                    {{
                    timeSlot
                }}</div>
            </div>
            <div class="day" v-for="(day, index) in days" :key="index">
                <h3>{{ day }}</h3>
                <div v-for="curriculumItem in getCurriculum(day)" :key="curriculumItem.day">
                    <div v-for="course in curriculumItem.courses" :key="course.name" class="course"
                        :style="{ top: Courselocation(course.start) + 'px', backgroundColor: CourseColors[course.color], height: 'calc(' + (course.end - course.start + 1) * 50 + 'px - 5px)' }">
                        <div class="course-name">{{ course.name }}</div>
                        <div class="course-info">
                            <div>教室：{{ course.classroom }}</div>
                            <div>老师：{{ course.teacher }}</div>
                            <div>节次：{{ course.start }} - {{ course.end }}</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue';

const weeks = [1, 2, 3, 4, 5]; // 假设总共有五周
const days = ['周一', '周二', '周三', '周四', '周五', '周六', '周日']; // 一周七天
const timeSlots = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12']; // 一天十节课

const CourseColors = ref(["#91C8FF", "#72C871", "#FFB600", "#FAE5F6", "#E392E4", "#D4DADD"]);

const coursesData = [
    // 课程数据示例
    {
        week: 1,
        curriculum: [
            {
                day: '周一',
                courses: [ // 周一的课
                    { name: '数学', classroom: '教室A', teacher: '张老师', start: 1, end: 4, color: 0 },
                    { name: '英语', classroom: '教室B', teacher: '王老师', start: 9, end: 10, color: 1 },
                ]
            },
            {
                day: '周四',
                courses: [ // 周四的课
                    { name: 'python', classroom: '教室B', teacher: '王老师', start: 9, end: 12, color: 2 },
                ]
            },
            {
                day: '周三',
                courses: [//周三的课
                    { name: '化学', classroom: '教室D', teacher: '赵老师', start: 5, end: 6, color: 3 },
                    { name: '历史', classroom: '教室E', teacher: '刘老师', start: 7, end: 8, color: 4 },
                    { name: '计算机', classroom: '教室F', teacher: '张老师', start: 3, end: 4, color: 5 },
                ]
            }
        ]
    },
    {
        week: 2,
        curriculum: [
            {
                day: '周二',
                courses: [ // 周二的课
                    { name: '数学', classroom: '教室A', teacher: '张老师', start: 1, end: 4, color: 2 },
                    { name: '英语', classroom: '教室B', teacher: '王老师', start: 9, end: 10, color: 3 },
                ]
            },
            {
                day: '周五',
                courses: [//周五的课
                    { name: '化学', classroom: '教室D', teacher: '赵老师', start: 5, end: 6, color: 4 },
                    { name: '历史', classroom: '教室E', teacher: '刘老师', start: 7, end: 8, color: 5 },
                    { name: '计算机', classroom: '教室F', teacher: '张老师', start: 3, end: 4, color: 0 },
                ]
            }
        ]
    },
];

const selectedWeek = ref(1);

const getCurriculum = (day) => {
    const weekCourses = coursesData.find(course => course.week === selectedWeek.value);
    const curriculumItem = weekCourses.curriculum.find(curriculum => curriculum.day === day);
    return curriculumItem ? [curriculumItem] : [];
};

const Courselocation = (value) => {
    if (value == 1) {
        return 60;
    } else {
        return (value - 1) * 50 + 60;
    }
};

//   切换显示其它周的课表
const selectWeek = (week) => {
    selectedWeek.value = week;
};
</script>

<style lang="scss" scoped>
.MySchedule {
    overflow: hidden;
    border: 1px solid #eee;
    padding: 10px;
    box-sizing: border-box;
}

.week-selector {
    display: flex;
    justify-content: flex-start;
    margin-bottom: 10px;
}

.week-selector span {
    cursor: pointer;
    margin-right: 10px;
    font-weight: bold;
}

.schedule {
    display: flex;
}

.time-column {
    padding-top: 60px;
    width: 80px;
    position: relative;
    height: 600px;
    z-index: 10;
}

.time-slot {
    width: 100vw;
    height: 50px;
    line-height: 50px;
    text-align: center;
    border-top: 1px solid #f1f1f1;
    text-align: left;

    position: absolute;
    left: 0;
}

.day {
    flex: 1;
    margin-left: 10px;
    padding: 10px;
    background-color: #F9F9F9;
    /* 添加背景色 */
    position: relative;
}

.course {
    width: calc(100% - 20px);
    background-color: #f0f0f0;
    margin-bottom: 5px;
    padding: 10px;
    box-sizing: border-box;
    position: absolute;
    z-index: 20;
    cursor: pointer;

}

.course-name {
    font-weight: bold;
}

.course-info {
    font-size: 0.9em;

    div {
        display: -webkit-box;
        overflow: hidden;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 1;
        line-height: 18px;
    }
}

.el-dropdown {
    border: 1px solid #ccc !important;
    padding: 4px 10px;
    margin-bottom: 10px;
}
</style>