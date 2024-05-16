package com.IService;

import com.Pojo.ClassInfo;
import com.Pojo.DTO.ListResultDto;
import com.Pojo.DTO.PagerInfoDto;
import com.Pojo.EnrollmentCourse;
import com.Pojo.EnrollmentStudent;

import java.util.List;

public interface IEnrollmentStudentService extends IBaseService<EnrollmentStudent>{
    ListResultDto<EnrollmentStudent> GetList(EnrollmentStudent e, PagerInfoDto p );
}
