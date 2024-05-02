package com.IService;

import com.Pojo.ClassInfo;
import com.Pojo.Course;
import com.Pojo.Student;

import java.util.List;

/**
 * @Author：2331020120242张成威
 * @Date： 2024/4/29 下午5:26
 * @ProjectName：Backend
 * @Description：
 */

public interface IStudentService extends IBaseService<Student>{
    List<Student> GetStudentList();
    List<Student> AddStudentList();
    List<Student> DeleteStudentList();
    List<Student> EditorStudentList();
}
