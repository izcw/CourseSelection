package com.Service;

import com.IService.IEnrollmentClassService;
import com.Pojo.EnrollmentClass;
import com.Pojo.EnrollmentCourse;

import java.util.List;

public class EnrollmentClassService extends BaseService<EnrollmentClass> implements IEnrollmentClassService {
    @Override
    public List<EnrollmentClass> GetList(EnrollmentClass e) {

        StringBuilder sb = new StringBuilder(" select * from enrollment_class where true ");

        if (e.getClassId()!=0){
            sb.append(String.format(" and classId = %d ",e.getClassId()));
        }
        if (e.getEnrollmentId()!=0){
            sb.append(String.format(" and enrollmentId = %d ",e.getEnrollmentId()));
        }
        return GetList(sb.toString());
    }
}
