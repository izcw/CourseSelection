package com.Service;

import com.IService.IClassService;
import com.IService.IStudentService;
import com.Pojo.Student;

import java.util.List;

/**
 * @Author：2331020120242张成威
 * @Date： 2024/4/29 下午5:15
 * @ProjectName：Backend
 * @Description：
 */
public class StudentService extends BaseService<Student> implements IStudentService {
    public List<Student> GetStudentList() {
        StringBuilder sb = new StringBuilder("select * from student");
        return GetList(sb.toString());
    }

    public List<Student> AddStudentList() {
        StringBuilder sb = new StringBuilder("select * from student");
        return GetList(sb.toString());
    }

    public List<Student> DeleteStudentList() {
        StringBuilder sb = new StringBuilder("select * from student");
        return GetList(sb.toString());
    }

    public List<Student> EditorStudentList() {
        StringBuilder sb = new StringBuilder("select * from student");
        return GetList(sb.toString());
    }
}
