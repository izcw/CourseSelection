package com.Service;

import com.IService.IStudentService;
import com.Pojo.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：2331020120242张成威
 * @Date： 2024/4/29 下午5:15
 * @ProjectName：Backend
 * @Description：
 */
public class StudentService extends BaseService<Student> implements IStudentService {
//    public List<Student> GetStudentList() {
//        StringBuilder sb = new StringBuilder("SELECT * FROM student WHERE delFlag = 1");
//        return GetList(sb.toString());
//    }

    public List<Student> GetStudentList(int classId, String studentName) {
        StringBuilder sb = new StringBuilder("SELECT * FROM student_class sc ");
        sb.append("JOIN student s ON sc.studentId = s.userId ");
        sb.append("WHERE sc.classId = ? AND s.delFlag = 1");

        List<Object> params = new ArrayList<>();
        params.add(classId);

        if (studentName != null && !studentName.isEmpty()) {
            sb.append(" AND s.userName LIKE ?");
            params.add("%" + studentName + "%");
        }

        return GetListparams(sb.toString(), params);
    }


    public List<Student> AddStudentList() {
        StringBuilder sb = new StringBuilder("select * from student");
        return GetList(sb.toString());
    }

    public String DeleteStudentList(String code) {
//        String sql = String.format("UPDATE student SET delFlag = 2 WHERE studentCode = '%s'",code);
//        boolean isSuccess = ExecuteUpdate(sql) > 0;
//        if (!isSuccess){
//            return  "删除失败";
//        }
//        return  "删除成功";
        String sql = "UPDATE student SET delFlag = 2 WHERE studentCode = ?";
        int result = deleteExecuteUpdate(sql, code);
        if (result > 0) {
            return "删除成功";
        } else {
            return "删除失败";
        }

    }

    public List<Student> EditorStudentList() {
        StringBuilder sb = new StringBuilder("select * from student");
        return GetList(sb.toString());
    }
}
