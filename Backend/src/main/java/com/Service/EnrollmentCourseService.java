package com.Service;

import com.IService.IEnrollmentCourseService;

import com.Pojo.DTO.EnrollmentBindingDto;
import com.Pojo.DTO.MaximumQuantityDto;
import com.Pojo.EnrollmentCourse;

import java.util.ArrayList;
import java.util.List;



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

    @Override
    public List<EnrollmentCourse> GetNotOptional(int id) {

        String Sql = String.format("select * from enrollment_course where enrollmentId <> %d ",id);
        return GetList(Sql);
    }


}
