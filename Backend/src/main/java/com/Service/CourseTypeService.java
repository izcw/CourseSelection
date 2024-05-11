package com.Service;

import com.IService.ICourseService;
import com.IService.ICourseTypeService;
import com.Pojo.Course;
import com.Pojo.CourseType;

import java.util.List;

public class CourseTypeService extends BaseService<CourseType> implements ICourseTypeService {
    @Override
    public List<CourseType> GetList() {
        String sql = "select * from course_type";
        return GetList(sql);
    }
}
