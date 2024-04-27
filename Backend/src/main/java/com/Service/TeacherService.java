package com.Service;

import com.IService.ITeacherService;
import com.Pojo.Teacher;

import java.util.List;

public class TeacherService extends BaseService<Teacher> implements ITeacherService {
    @Override
    public Teacher GetTeacherById(Integer id) {
        Teacher teacher = GetFirst(String.format("select * from teacher where teacherId = %d limit 1", id));
        if (teacher!=null){
            return teacher;
        }
        return null;
    }

    @Override
    public List<Teacher> GetList(Teacher t) {
        StringBuilder sb = new StringBuilder("select * from teacher where delFlag = 1 ");
        if(!t.getTeacherName().equals("")){
            sb.append(String.format(" and TeacherName like '%%%s%%' ",t.getTeacherName()));
        }
        if(!t.getTeacherNumber().equals("")){
            sb.append(String.format(" and TeacherNumber like '%%%s%%' ",t.getTeacherNumber()));
        }
        if(!t.getPhone().equals("")){
            sb.append(String.format(" and Phone like '%%%s%%' ",t.getPhone()));
        }
        if (!t.getGender().equals(""))
        {
            sb.append(String.format(" and Gender = '%s' ",t.getGender()));
        }
        return GetList(sb.toString());
    }
}
