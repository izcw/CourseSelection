package com.Service;

import com.IService.IEnrollmentCourseService;
import com.Pojo.EnrollmentCourse;

import java.util.List;

public class EnrollmentCourseService extends BaseService<EnrollmentCourse> implements IEnrollmentCourseService {

    @Override
    public List<EnrollmentCourse> GetList(EnrollmentCourse e) {

       StringBuilder sb = new StringBuilder(" select * from enrollment_course where true ");

        if (e.getCourseId()!=0){
            sb.append(String.format(" and courseId = %d ",e.getCourseId()));
        }
        if (e.getEnrollmentId()!=0){
            sb.append(String.format(" and enrollmentId = %d ",e.getEnrollmentId()));
        }
        return GetList(sb.toString());
    }
}
