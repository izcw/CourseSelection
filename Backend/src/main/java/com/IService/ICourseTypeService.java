package com.IService;

import com.Pojo.Course;
import com.Pojo.CourseType;

import java.util.List;

public interface ICourseTypeService extends IBaseService<CourseType>{
    List<CourseType> GetList();
}
