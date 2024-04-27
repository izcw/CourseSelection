package com.Service;

import com.IService.IClassService;
import com.IService.ITeacherService;
import com.Pojo.ClassInfo;
import com.Pojo.Teacher;

import java.util.List;

public class ClassService extends BaseService<ClassInfo> implements IClassService {
    @Override
    public List<ClassInfo> GetClassList(ClassInfo c) {
        StringBuilder sb = new StringBuilder("select * from classInfo where delFlag = 1 ");
        if(!c.getClassName().equals("")){
            sb.append(String.format(" and className like '%%%s%%' ",c.getClassName()));
        }
        if(c.getTeacherId()!=0){
            sb.append(String.format(" and teacherId = %d ",c.getTeacherId()));

        }
        return GetList(sb.toString());
    }
}
