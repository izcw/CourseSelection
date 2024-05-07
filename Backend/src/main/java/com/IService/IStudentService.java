package com.IService;

import com.Pojo.Student;

import java.util.List;

/**
 * @Author：2331020120242张成威
 * @Date： 2024/4/29 下午5:26
 * @ProjectName：Backend
 * @Description：
 */

public interface IStudentService extends IBaseService<Student>{
    List<Student> GetStudentList(String classId, String studentName);
    String AddStudentList(Student student);
    String DeleteStudentList(String code);
    List<Student> EditorStudentList();
}
