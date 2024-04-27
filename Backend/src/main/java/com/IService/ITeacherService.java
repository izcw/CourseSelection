package com.IService;

import com.Pojo.Course;
import com.Pojo.Teacher;

import java.util.List;

public interface ITeacherService extends IBaseService<Teacher>{
    Teacher GetTeacherById (Integer id);
    List<Teacher> GetList(Teacher t);
}
