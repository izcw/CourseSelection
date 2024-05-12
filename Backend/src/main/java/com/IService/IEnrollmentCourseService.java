package com.IService;

import com.Pojo.EnrollmentCourse;

import java.util.List;

public interface IEnrollmentCourseService extends IBaseService<EnrollmentCourse>{
    List<EnrollmentCourse> GetList(EnrollmentCourse e);
}
