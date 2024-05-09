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
    StudentListResultDto GetList(String Id, String Name, PagerInfoDto p);
    String AddList(Student student,String passwrod);
    String DeletesArrayList(String[] idArrayData);
    String EditorList(Student student);
}
