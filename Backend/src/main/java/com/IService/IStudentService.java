package com.IService;

import com.Pojo.DTO.PagerInfoDto;
import com.Pojo.DTO.StudentListResultDto;
import com.Pojo.Student;

import java.util.List;

/**
 * @Author：2331020120242张成威
 * @Date： 2024/4/29 下午5:26
 * @ProjectName：Backend
 * @Description：
 */


public interface IStudentService extends IBaseService<Student>{
    StudentListResultDto GetList(String Id,String Name,String Code,  PagerInfoDto p);
    Object[] AddList(Student student,String passwrod);
    Object[] DeletesArrayList(String[] idArrayData);
    Object[] EditorList(Student student);
    String NumberingRules(Student student,String classId);
     int GetStudentCountByClassId(int classId);
}
