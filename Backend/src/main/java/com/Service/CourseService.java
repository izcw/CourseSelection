package com.Service;

import com.IService.ICourseService;
import com.Pojo.ClassInfo;
import com.Pojo.Course;
import com.Pojo.DTO.ListResultDto;
import com.Pojo.DTO.PagerInfoDto;

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
}
