package com.Service;

import com.Controller.BaseServlet;
import com.IService.IEnrollmentStudentService;
import com.Pojo.EnrollmentCourse;
import com.Pojo.EnrollmentStudent;

import java.util.List;

public class EnrollmentStudentService extends BaseService<EnrollmentStudent> implements IEnrollmentStudentService {
    @Override
    public List<EnrollmentStudent> GetList(EnrollmentStudent e) {

        StringBuilder sb = new StringBuilder(" select * from enrollment_student where true ");

        if (e.getStudentId()!=0){
            sb.append(String.format(" and studentId = %d ",e.getStudentId()));
        }
        if (e.getEnrollmentId()!=0){
            sb.append(String.format(" and enrollmentId = %d ",e.getEnrollmentId()));
        }
        return GetList(sb.toString());
    }
}
