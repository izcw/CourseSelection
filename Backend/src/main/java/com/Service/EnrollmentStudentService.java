package com.Service;

import com.Controller.BaseServlet;
import com.IService.IEnrollmentStudentService;
import com.Pojo.*;
import com.Pojo.DTO.ListResultDto;
import com.Pojo.DTO.PagerInfoDto;
import com.Tools.DateTimeHelper;
import com.Tools.MD5Helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnrollmentStudentService extends BaseService<EnrollmentStudent> implements IEnrollmentStudentService {
    @Override
    public ListResultDto<EnrollmentStudent> GetList(EnrollmentStudent e, PagerInfoDto p ) {
        ListResultDto<EnrollmentStudent> dto = new ListResultDto<EnrollmentStudent>();
        StringBuilder sb = new StringBuilder(" select * from enrollment_student es left join course c on es.courseCode = c.courseCode left join (select s2.userId,s2.studentCode,s2.userName,s2.age,s2.gender,s2.phone,s2.email,c2.className from student s2 left join classinfo c2 on s2.classId = c2.classId  ) s1 on es.studentCode = s1.studentCode where true ");

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


    /**
     * 通过传学生id查找数据(查询学生选的课程)
     * @param Id id（学生id）
     * @return 执行状态
     */
    public List<EnrollmentStudent> GetMystudentcourseList(String Id) {
        // 构建查询enrollment_class表的语句
        String sql = String.format(" SELECT * from enrollment_student where studentCode = '%s' ",Id);



        List<EnrollmentStudent> enrollmentList = GetList(sql);

        // 返回查询到的enrollment列表
        return enrollmentList;
    }



    /**
     * 添加数据（选择课程）
     * @param enrollmentStudent 对象
     * @return 执行状态
     */
    public Object[] SelectEnrollmentStudent(EnrollmentStudent enrollmentStudent) {
        // 构建学生信息插入语句
        String enrollmentstudentSql = String.format("INSERT INTO enrollment_student ( enrollmentId,courseCode, studentCode, enrollmentTime) VALUES ('%d', '%s', '%s', '%s')",
                enrollmentStudent.getEnrollmentId(),enrollmentStudent.getCourseCode(), enrollmentStudent.getStudentCode(), DateTimeHelper.GetCurrentTimeToString());

        // 执行学生信息插入操作
        int Result = ExecuteUpdate(enrollmentstudentSql);


        // 检查是否成功插入学生信息和用户信息
        if (Result > 0) {
            return new Object[]{200, "选择成功"};
        } else {
            return new Object[]{400, "选择失败"};
        }
    }
}
