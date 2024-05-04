package com.IService;

import com.Pojo.Course;
import com.Pojo.Teacher;

import java.util.List;

public interface ITeacherService extends IBaseService<Teacher>{
    //返回一个教师实体根据教师id查询
    Teacher GetTeacherById (Integer id);
    //返回教师的数据集合 t:代表搜索条件可以不传也可以传不传则全部查询，传的话根据传的值进行模糊查询
    List<Teacher> GetList(Teacher t);
}
