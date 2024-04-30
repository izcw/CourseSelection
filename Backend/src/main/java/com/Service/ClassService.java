package com.Service;

import com.IService.IClassService;
import com.IService.ITeacherService;
import com.Pojo.ClassInfo;
import com.Pojo.Teacher;
import com.Tools.DateTimeHelper;

import java.util.List;

public class ClassService extends BaseService<ClassInfo> implements IClassService {
    @Override
    public List<ClassInfo> GetClassList(ClassInfo c) {
        StringBuilder sb = new StringBuilder("select * from classInfo c left join teacher t on c.teacherId = t.teacherId  where c.delFlag = 1 ");
        if(!c.getClassName().equals("")){
            sb.append(String.format(" and c.className like '%%%s%%' ",c.getClassName()));
        }
        if(c.getTeacherId()!=0){
            sb.append(String.format(" and c.teacherId = %d ",c.getTeacherId()));

        }
        return GetList(sb.toString());
    }
    //新增班级实体
    @Override
    public boolean AddClass(ClassInfo c) {
        c.setDelFlag("1");
        c.setNumberOfStudent(0);
        String date = DateTimeHelper.GetCurrentTimeToString();
        c.setCreateTime(date);
        String sql = String.format(" insert into classInfo (className,numberOfStudent,teacherId,createTime,delFlag) values('%s',%d,%d,'%s','%s') ",c.getClassName(),c.getNumberOfStudent(),c.getTeacherId(),c.getCreateTime(),c.getDelFlag());
        return ExecuteUpdate(sql)>0;
    }

    @Override
    public ClassInfo GetClassInfo(int id) {
        String sql = String.format("select * from classInfo where classId = %d limit 1",id);
        return GetFirst(sql);
    }

    @Override
    public String UpdateClass(ClassInfo c) {
        String existCheck = String.format("select * from classInfo where className = '%s' and classId != %d limit 1 ",c.getClassName(),c.getClassId());
        ClassInfo classInfo = GetFirst(existCheck);
        if(classInfo!=null){
            return "班级名称已存在";
        }
        String sql = String.format(" update classInfo set className = '%s',teacherId = %d where classId = %d ",c.getClassName(),c.getTeacherId(),c.getClassId());
        boolean b = ExecuteUpdate(sql) > 0;
        if (b){
            return "修改成功";
        }else {
            return "修改失败";
        }

    }
}
