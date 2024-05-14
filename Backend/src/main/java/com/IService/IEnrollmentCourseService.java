package com.IService;

<<<<<<< HEAD
import com.Pojo.DTO.EnrollmentBindingDto;
=======
>>>>>>> df678de7f559094a31688498c0d5ae18a824a205
import com.Pojo.EnrollmentCourse;

import java.util.List;

public interface IEnrollmentCourseService extends IBaseService<EnrollmentCourse>{
    List<EnrollmentCourse> GetList(EnrollmentCourse e);
<<<<<<< HEAD
    Boolean  BindingCourse(EnrollmentBindingDto dto);

=======
>>>>>>> df678de7f559094a31688498c0d5ae18a824a205
}
