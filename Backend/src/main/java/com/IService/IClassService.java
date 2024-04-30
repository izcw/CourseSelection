package com.IService;

import com.Pojo.ClassInfo;

import java.util.List;

public interface IClassService extends IBaseService<ClassInfo>{
    List<ClassInfo> GetClassList(ClassInfo c);
    boolean AddClass(ClassInfo c);
    ClassInfo GetClassInfo(int id);
    String UpdateClass(ClassInfo c);
}
