package com.Service;

import com.IService.IEnrollmentClassService;
import com.Pojo.DTO.EnrollmentBindingDto;
import com.Pojo.EnrollmentClass;
import com.Pojo.EnrollmentCourse;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentClassService extends BaseService<EnrollmentClass> implements IEnrollmentClassService {
    @Override
    public List<EnrollmentClass> GetList(EnrollmentClass e) {

        StringBuilder sb = new StringBuilder(" select * from enrollment_class where true ");

        if (e.getClassId()!=0){
            sb.append(String.format(" and classId = %d ",e.getClassId()));
        }
        if (e.getEnrollmentId()!=0){
            sb.append(String.format(" and enrollmentId = %d ",e.getEnrollmentId()));
        }
        return GetList(sb.toString());
    }

    @Override
    public Boolean BindingClass(EnrollmentBindingDto dto) {
        List<String> sqls = new ArrayList<>();
        List<Integer> classIds = dto.getClassIds();
        String deleteSql = String.format("delete from enrollment_class where enrollmentId = %d",dto.getEnrollmentId());
        sqls.add(deleteSql);
        for (Integer item : classIds) {
            String sql = String.format(" insert into enrollment_class(enrollmentId,classId) values(%d,%d)",dto.getEnrollmentId(),item);
            sqls.add(sql);
        }
        int i = ExecuteUpdate(sqls);
        return i >0;
    }
}
