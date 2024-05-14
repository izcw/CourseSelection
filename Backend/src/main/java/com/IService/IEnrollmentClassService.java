package com.IService;

import com.Pojo.DTO.EnrollmentBindingDto;
import com.Pojo.EnrollmentClass;
import com.Pojo.EnrollmentCourse;

import java.util.List;

public interface IEnrollmentClassService extends IBaseService<EnrollmentClass>{
    List<EnrollmentClass> GetList(EnrollmentClass e);
    Boolean BindingClass(EnrollmentBindingDto dto);
}
