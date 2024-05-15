package com.IService;

import com.Pojo.Course;
import com.Pojo.DTO.PagerInfoDto;
import com.Pojo.DTO.TeacherListResultDto;
import com.Pojo.Student;
import com.Pojo.Teacher;

import java.util.List;


public interface ITeacherService extends IBaseService<Teacher>{
    TeacherListResultDto GetList(String Name,String Code, PagerInfoDto p);
    Object[] AddList(Teacher teacher);
    Object[] DeletesArrayList(String[] idArrayData);
    Object[] EditorList(Teacher teacher);
}