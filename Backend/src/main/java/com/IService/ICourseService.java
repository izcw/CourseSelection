package com.IService;

import com.Pojo.ClassInfo;
import com.Pojo.Course;

import java.util.List;

public interface ICourseService extends IBaseService<Course>{
    List<Course> GetCourseList(Course c);

    String AddCourse(Course c);
    String UpdateCourse(Course c);
    boolean DeleteCourse(int id);
    Course GetCourseInfo(int id);
}
