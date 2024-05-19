package com.Service;

import com.IService.ICourseService;
import com.Pojo.ClassInfo;
import com.Pojo.Course;
import com.Pojo.DTO.ListResultDto;
import com.Pojo.DTO.PagerInfoDto;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CourseService extends BaseService<Course> implements ICourseService {
    public ListResultDto<Course> GetCourseList(Course c, PagerInfoDto p) {
        ListResultDto<Course> dto = new ListResultDto<Course>();
        StringBuilder sb = new StringBuilder("select * from course c left join course_type ct on c.courseTypeId = ct.courseTypeId left JOIN teacher t on c.teacherId = t.teacherId  where c.delFlag = 1 ");
        if(c.getCourseName()!=null&&!c.getCourseName().equals("")){
            sb.append(String.format(" and c.CourseName like '%%%s%%' ",c.getCourseName()));
        }
        if(c.getCourseCode()!=null&&!c.getCourseCode().equals("")){
            sb.append(String.format(" and c.CourseCode like '%%%s%%' ",c.getCourseCode()));
        }
        if(c.getTeacherId()!=null&&c.getTeacherId()!=0){
            sb.append(String.format(" and c.teacherId = %d ",c.getTeacherId()));

        }
        if(c.getCourseTypeId()!=null&&c.getCourseTypeId()!=0){
            sb.append(String.format(" and c.CourseTypeId = %d ",c.getCourseTypeId()));

        }
        if (p != null) {
            int count = GetList(sb.toString()).size();
            p.setTotalNum(count);
            sb.append(String.format(" limit %d,%d ",(p.getPageNum()-1)*p.getPageSize(),p.getPageSize()));
        }
        List<Course> courses = GetList(sb.toString());
        dto.setList(courses);
        dto.setPagerInfoDto(p);
        return dto;

    }

    @Override
    public String AddCourse(Course c) {
        String sql = String.format(" insert into course (courseCode,courseName,teacherId,credits,courseTypeId,createTime,delFlag) values('%s','%s',%d,%f,%d,'%s','%s') ",c.getCourseCode(),c.getCourseName(),c.getTeacherId(),c.getCredits(),c.getCourseTypeId(),c.getCreateTime(),c.getDelFlag());

        int result = ExecuteUpdate(sql);
        if (result > 0) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    @Override
    public String UpdateCourse(Course c) {
        String existCheck = String.format("select * from course where courseName = '%s' and courseId != %d limit 1 ",c.getCourseName(),c.getCourseId());
        Course course = GetFirst(existCheck);
        if(course!=null){
            return "课程名称已存在";
        }
        String sql = String.format(" update course set courseName = '%s',teacherId = %d,credits = %f,courseTypeId= %d where courseId = %d ",c.getCourseName(),c.getTeacherId(),c.getCredits(),c.getCourseTypeId(),c.getCourseId());
        boolean b = ExecuteUpdate(sql) > 0;
        if (b){
            return "修改成功";
        }else {
            return "修改失败";
        }
    }

    @Override
    public boolean DeleteCourse(int id) {
        String sql  = String.format(" update course set delFlag = '2'   where courseId = %d ",id);
        return ExecuteUpdate(sql) > 0;
    }

    @Override
    public Course GetCourseInfo(int id) {
        String sql = String.format("select * from course where courseId = %d limit 1",id);
        return GetFirst(sql);
    }


    /**
     * 通过传选课类别id查找数据
     * @param Id id（选课类别id）
     * @return 执行状态
     */
    public List<Course> GetstudentshowCourseList(String Id) {
        // 构建查询的 SQL 语句
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT c.courseId, c.courseCode, c.courseName, c.teacherId, t.teacherName, c.credits, ct.courseTypeId, ct.typeName, c.createTime, c.delFlag, ec.maximumQuantity, ec.enrollmentId ");
        sb.append("FROM enrollment_course ec ");
        sb.append("JOIN enrollment e ON ec.enrollmentId = e.enrollmentId ");
        sb.append("JOIN course c ON ec.courseId = c.courseId ");
        sb.append("JOIN course_type ct ON c.courseTypeId = ct.courseTypeId ");
        sb.append("JOIN teacher t ON c.teacherId = t.teacherId "); // 加入 teacher 表的连接
        sb.append("WHERE ec.enrollmentId = ? ");
        sb.append("AND e.delFlag = 1");

        List<Object> params = new ArrayList<>();
        params.add(Id);

        // 获取查询结果
        List<Course> courseList = GetListparams(sb.toString(), params);

        // 如果没有找到对应的记录，返回空列表
        if (courseList == null || courseList.isEmpty()) {
            return Collections.emptyList();
        }

        // 遍历课程列表，为每个课程计算 selectednumberpeople
        for (Course course : courseList) {
            int selectedNumberPeople = getSelectedNumberPeople(course.getCourseCode());
            course.setSelectednumberpeople(selectedNumberPeople);
        }

        // 返回查询到的课程列表
        return courseList;
    }

    // 根据 enrollmentId 查询 enrollment_student 表的记录数
    public int getSelectedNumberPeople(String Id) {
        // 构建查询的 SQL 语句
        String sql = "SELECT COUNT(*) FROM enrollment_student WHERE courseCode = '" + Id + "'";

        // 使用 ExecuteQueryCount 方法执行查询并返回结果数量
        return ExecuteQueryCount(sql);
    }


    /**
     * 通过传学生id查找数据(我的选课)
     * @param Id id（学生id）
     * @return 执行状态
     */
    public List<Course> GetMyclassstudentcourseList(String studentId) {
        // 构建查询的 SQL 语句
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT c.*, ct.typeName, t.teacherName ");
        sb.append("FROM enrollment_student es ");
        sb.append("JOIN course c ON es.courseCode = c.courseCode ");
        sb.append("JOIN course_type ct ON c.courseTypeId = ct.courseTypeId ");
        sb.append("JOIN teacher t ON c.teacherId = t.teacherId ");
        sb.append("WHERE es.studentCode = ? ");
        sb.append("AND c.delFlag = 1");

        List<Object> params = new ArrayList<>();
        params.add(studentId);

        // 获取查询结果
        List<Course> courseList = GetListparams(sb.toString(), params);

        // 如果没有找到对应的记录，返回空列表
        if (courseList == null || courseList.isEmpty()) {
            return Collections.emptyList();
        }

        // 返回查询到的课程列表
        return courseList;
    }


}
