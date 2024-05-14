package com.Service;

import com.IService.IEnrollmentCourseService;
<<<<<<< HEAD
import com.Pojo.DTO.EnrollmentBindingDto;
import com.Pojo.DTO.MaximumQuantityDto;
import com.Pojo.EnrollmentCourse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
=======
import com.Pojo.EnrollmentCourse;

import java.util.List;
>>>>>>> df678de7f559094a31688498c0d5ae18a824a205

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
<<<<<<< HEAD

    @Override
    public Boolean BindingCourse(EnrollmentBindingDto dto) {
        List<String> sqls = new ArrayList<>();
        List<Integer> courseIds = dto.getCourseIds();
        String deleteSql = String.format("delete from enrollment_course where enrollmentId = %d",dto.getEnrollmentId());
        sqls.add(deleteSql);
        for (Integer item : courseIds) {
            MaximumQuantityDto maximumQuantityDto = dto.getMaximumQuantity().stream().filter(c -> c.getCourseId() == item).findFirst().get();
            String sql = String.format(" insert into enrollment_course(enrollmentId,courseId,maximumQuantity) values(%d,%d,%d)",dto.getEnrollmentId(),item,maximumQuantityDto.getMaximumQuantity());
            sqls.add(sql);
        }
        int i = ExecuteUpdate(sqls);
        return i >0;
    }


=======
>>>>>>> df678de7f559094a31688498c0d5ae18a824a205
}
