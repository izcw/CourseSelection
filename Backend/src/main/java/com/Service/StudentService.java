package com.Service;

import com.IService.IStudentService;
import com.Pojo.DTO.PagerInfoDto;
import com.Pojo.DTO.StudentListResultDto;
import com.Pojo.Student;
import com.Tools.DateTimeHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

//    public List<Student> GetStudentList(String classId, String studentName) {
//        // 构建语句
//        StringBuilder sb = new StringBuilder("SELECT * FROM student_class sc ");
//        sb.append("JOIN student s ON sc.studentId = s.userId ");
//        sb.append("WHERE sc.classId = ? AND s.delFlag = 1");
//
//        List<Object> params = new ArrayList<>();
//        params.add(classId);
//
//        // 模糊查询name
//        if (studentName != null && !studentName.isEmpty()) {
//            sb.append(" AND s.userName LIKE ?");
//            params.add("%" + studentName + "%");
//        }
//
//        // 执行操作
//        return GetListparams(sb.toString(), params);
//    }

    public StudentListResultDto GetStudentList(String classId, String studentName, PagerInfoDto p) {
        StudentListResultDto dto = new StudentListResultDto();

        // 构建语句
        StringBuilder sb = new StringBuilder("SELECT * FROM student s ");
        sb.append("WHERE s.classId = ? AND s.delFlag = 1");

        List<Object> params = new ArrayList<>();
        params.add(classId);

        // 模糊查询name
        if (studentName != null && !studentName.isEmpty()) {
            sb.append(" AND s.userName LIKE ? ");
            params.add("%" + studentName + "%");
        }


        if (p!=null){
            int count = GetListparams(sb.toString(), params).size();
            p.setTotalNum(count);
            sb.append(String.format(" limit %d,%d ",p.getPageNum()-1,p.getPageSize()));
        }
        List<Student> students = GetListparams(sb.toString(), params);
        dto.setList(students);
        dto.setPagerInfoDto(p);
        // 执行操作
        return dto;
    }



    public String AddStudentList(Student student) {
        // 构建语句
        String sql = String.format("INSERT INTO student (studentCode, userName, gender, phone, age, classId, email, createTime) VALUES ('%s', '%s', '%s', '%s', %d, %d, '%s', '%s')",
                student.getStudentCode(), student.getUserName(), student.getGender(), student.getPhone(), student.getAge(), student.getClassId(), student.getEmail(), DateTimeHelper.GetCurrentTimeToString());

        // 执行操作
        int result = ExecuteUpdate(sql);
        if (result > 0) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    public String DeleteStudentList(String code) {
        // 构建语句
        String sql = "UPDATE student SET delFlag = 2 WHERE studentCode = ?";

        // 执行操作
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
