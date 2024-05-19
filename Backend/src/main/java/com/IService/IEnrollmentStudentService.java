package com.IService;

import com.Pojo.*;
import com.Pojo.DTO.ListResultDto;
import com.Pojo.DTO.PagerInfoDto;

import java.util.List;

public interface IEnrollmentStudentService extends IBaseService<EnrollmentStudent>{
    ListResultDto<EnrollmentStudent> GetList(EnrollmentStudent e, PagerInfoDto p );

    List<EnrollmentStudent> GetMystudentcourseList(String Id);

    Object[] SelectEnrollmentStudent(EnrollmentStudent enrollmentStudent);
}
