package com.Service;

import com.IService.ITeacherService;
import com.Pojo.Teacher;

import java.util.List;

public class TeacherService extends BaseService<Teacher> implements ITeacherService {
    //返回一个教师实体根据教师id查询
    @Override
    public Teacher GetTeacherById(Integer id) {
        Teacher teacher = GetFirst(String.format("select * from teacher where teacherId = %d limit 1", id));
        if (teacher!=null){
            return teacher;
        }
        return null;
    }
    //返回教师的数据集合 t:代表搜索条件可以不传也可以传不传则全部查询，传的话根据传的值进行模糊查询
    @Override
    public List<Teacher> GetList(Teacher t) {
        StringBuilder sb = new StringBuilder("select * from teacher where delFlag = 1 ");
        if(t.getTeacherName()!=null&&!t.getTeacherName().equals("")){
            sb.append(String.format(" and TeacherName like '%%%s%%' ",t.getTeacherName()));
        }
        if(t.getTeacherNumber()!=null&&!t.getTeacherNumber().equals("")){
            sb.append(String.format(" and TeacherNumber like '%%%s%%' ",t.getTeacherNumber()));
        }
        if(t.getPhone()!=null&&!t.getPhone().equals("")){
            sb.append(String.format(" and Phone like '%%%s%%' ",t.getPhone()));
        }
        if (t.getGender()!=null&&!t.getGender().equals(""))
        {
            sb.append(String.format(" and Gender = '%s' ",t.getGender()));
        }
        return GetList(sb.toString());
    }
}
