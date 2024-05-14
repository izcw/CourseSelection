package com.IService;

import com.Pojo.DTO.ListResultDto;
import com.Pojo.DTO.PagerInfoDto;
import com.Pojo.Teacher;

public interface ITeacherService extends IBaseService<Teacher>{
    ListResultDto<Teacher> GetList(String Name, PagerInfoDto p);
    String AddList(Teacher teacher);
    String DeletesArrayList(String[] idArrayData);
    String EditorList(Teacher teacher);
}
