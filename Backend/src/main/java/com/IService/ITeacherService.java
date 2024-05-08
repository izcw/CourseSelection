package com.IService;

import com.Pojo.Course;
import com.Pojo.Student;
import com.Pojo.Teacher;

import java.util.List;

public interface ITeacherService extends IBaseService<Teacher>{
    List<Teacher> GetList(String Name);
    String AddList(Teacher teacher);
    String DeletesArrayList(String[] idArrayData);
    String EditorList(Teacher teacher);
}
