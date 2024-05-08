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
    StudentListResultDto GetStudentList(String classId, String studentName, PagerInfoDto p);
    String AddStudentList(Student student);
    String DeleteStudentList(String code);
    List<Student> EditorStudentList();
}
