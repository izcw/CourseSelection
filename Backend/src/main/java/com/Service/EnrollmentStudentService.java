package com.Service;

import com.Controller.BaseServlet;
import com.IService.IEnrollmentStudentService;
import com.Pojo.ClassInfo;
import com.Pojo.DTO.ListResultDto;
import com.Pojo.DTO.PagerInfoDto;
import com.Pojo.EnrollmentCourse;
import com.Pojo.EnrollmentStudent;

import java.util.List;

public class EnrollmentStudentService extends BaseService<EnrollmentStudent> implements IEnrollmentStudentService {
    @Override
    public ListResultDto<EnrollmentStudent> GetList(EnrollmentStudent e, PagerInfoDto p ) {
        ListResultDto<EnrollmentStudent> dto = new ListResultDto<EnrollmentStudent>();
        StringBuilder sb = new StringBuilder(" select * from enrollment_student es left join course c on es.courseId = c.courseId left join (select s2.userId,s2.studentCode,s2.userName,s2.age,s2.gender,s2.phone,s2.email,c2.className from student s2 left join classinfo c2 on s2.classId = c2.classId  ) s1 on es.studentId = s1.userId where true ");

        //根据学生姓名查询
        if (e.getStudent().getUserName()!=null){
            sb.append(String.format(" and s1.userName = '%s' ",e.getStudent().getUserName()));
        }
        //根据学生姓名学号查询
        if (e.getStudent().getStudentCode()!=null){
            sb.append(String.format(" and s1.studentCode = '%s' ",e.getStudent().getStudentCode()));
        }
        //根据课程名称查询
        if (e.getCourse().getCourseName()!=null){
            sb.append(String.format(" and c.courseName = '%s' ",e.getCourse().getCourseName()));
        }
        //根据课程名称查询
        if (e.getCourse().getCourseCode()!=null){
            sb.append(String.format(" and c.courseCode = '%s' ",e.getCourse().getCourseCode()));
        }
        if (e.getEnrollmentId()!=0){
            sb.append(String.format(" and es.enrollmentId = %d ",e.getEnrollmentId()));
        }
        if (p != null) {
            int count = GetList(sb.toString()).size();
            p.setTotalNum(count);
            sb.append(String.format(" limit %d,%d ",(p.getPageNum()-1)*p.getPageSize(),p.getPageSize()));
        }
        List<EnrollmentStudent> enrollmentStudents = GetList(sb.toString());
        dto.setList(enrollmentStudents);
        dto.setPagerInfoDto(p);
        return dto;
    }
}
