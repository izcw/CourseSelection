package com.IService;

import com.Pojo.Course;
import com.Pojo.DTO.PagerInfoDto;
import com.Pojo.DTO.TeacherListResultDto;
import com.Pojo.Student;
import com.Pojo.Teacher;

import java.util.List;

public interface ITeacherService extends IBaseService<Teacher>{
    TeacherListResultDto GetList(String Name,String Code, PagerInfoDto p);
    String AddList(Teacher teacher);
    String DeletesArrayList(String[] idArrayData);
    String EditorList(Teacher teacher);
}
