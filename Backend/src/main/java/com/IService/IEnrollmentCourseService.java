package com.IService;


import com.Pojo.DTO.EnrollmentBindingDto;

import com.Pojo.EnrollmentCourse;

import java.util.List;

public interface IEnrollmentCourseService extends IBaseService<EnrollmentCourse>{
    List<EnrollmentCourse> GetList(EnrollmentCourse e);

    Boolean  BindingCourse(EnrollmentBindingDto dto);

    List<EnrollmentCourse> GetNotOptional(int id);
}
