package com.IService;

import com.Pojo.Course;
import com.Pojo.DTO.ListResultDto;
import com.Pojo.DTO.PagerInfoDto;

public interface ICourseService extends IBaseService<Course>{
    ListResultDto<Course> GetCourseList(Course c, PagerInfoDto p);

    String AddCourse(Course c);
    String UpdateCourse(Course c);
    boolean DeleteCourse(int id);
    Course GetCourseInfo(int id);
}
