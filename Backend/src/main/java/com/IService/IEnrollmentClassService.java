package com.IService;

<<<<<<< HEAD
import com.Pojo.DTO.EnrollmentBindingDto;
=======
>>>>>>> df678de7f559094a31688498c0d5ae18a824a205
import com.Pojo.EnrollmentClass;
import com.Pojo.EnrollmentCourse;

import java.util.List;

public interface IEnrollmentClassService extends IBaseService<EnrollmentClass>{
    List<EnrollmentClass> GetList(EnrollmentClass e);
<<<<<<< HEAD
    Boolean BindingClass(EnrollmentBindingDto dto);
=======
>>>>>>> df678de7f559094a31688498c0d5ae18a824a205
}
