package com.IService;

import com.Pojo.EnrollmentCourse;
import com.Pojo.EnrollmentStudent;

import java.util.List;

public interface IEnrollmentStudentService extends IBaseService<EnrollmentStudent>{
    List<EnrollmentStudent> GetList(EnrollmentStudent e);
}
