package com.IService;

import com.Pojo.ClassInfo;
import com.Pojo.DTO.BindingStudentDto;
import com.Pojo.DTO.ListResultDto;
import com.Pojo.DTO.PagerInfoDto;
import com.Pojo.Teacher;

import java.util.List;

public interface IClassService extends IBaseService<ClassInfo>{
    ListResultDto<ClassInfo> GetClassList(ClassInfo c, PagerInfoDto p);
    String AddClass(ClassInfo c);
    ClassInfo GetClassInfo(int id);
    String UpdateClass(ClassInfo c);
    boolean DeleteClass(int id);
    boolean BindingStudent(BindingStudentDto dto);
}
