package com.Service;

import com.IService.IStudentService;
import com.Pojo.DTO.PagerInfoDto;
import com.Pojo.DTO.StudentListResultDto;
import com.Pojo.Student;
import com.Tools.DateTimeHelper;
import com.Tools.MD5Helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @Author：2331020120242张成威
 * @Date： 2024/4/29 下午5:15
 * @ProjectName：Backend
 * @Description：
 */
public class StudentService extends BaseService<Student> implements IStudentService {

    /**
     * 通过传班级id与姓名查找数据
     * @param Id id（班级id）
     * @param Name 要查找的姓名（模糊查询）
     * @return 执行状态
     */
    public StudentListResultDto GetList(String Id, String Name, PagerInfoDto p) {
        StudentListResultDto dto = new StudentListResultDto();
        // 构建语句
        StringBuilder sb = new StringBuilder("SELECT s.*, c.className FROM student s ");
        sb.append("LEFT JOIN classinfo c ON s.classId = c.classId ");
        sb.append("WHERE s.delFlag = 1");

        List<Object> params = new ArrayList<>();

        // 如果 classId 不为空，则添加 classId 条件
        if (Id != null && !Id.isEmpty()) {
            sb.append(" AND s.classId = ?");
            params.add(Id);
        }

        // 模糊查询 name
        if (Name != null && !Name.isEmpty()) {
            sb.append(" AND s.userName LIKE ?");
            params.add("%" + Name + "%");
        }

        if (p != null) {
            int count = GetListparams(sb.toString(), params).size();
            p.setTotalNum(count);
            sb.append(String.format(" limit %d,%d ",(p.getPageNum()-1)*p.getPageSize(),p.getPageSize()));
        }
        List<Student> students = GetListparams(sb.toString(), params);
        dto.setList(students);
        dto.setPagerInfoDto(p);

        // 执行操作
        return dto;
    }




    /**
     * 添加数据
     * @param student 对象
     * @param passwrod 初始密码
     * @return 执行状态
     */
    public String AddList(Student student, String passwrod) {
        // 构建学生信息插入语句
        String studentSql = String.format("INSERT INTO student (studentCode, userName, gender, phone, age, classId, email, createTime) VALUES ('%s', '%s', '%s', '%s', %d, %d, '%s', '%s')",
                student.getStudentCode(), student.getUserName(), student.getGender(), student.getPhone(), student.getAge(), student.getClassId(), student.getEmail(), DateTimeHelper.GetCurrentTimeToString());

        // 执行学生信息插入操作
        int studentResult = ExecuteUpdate(studentSql);

        // 构建用户信息插入语句
        String userSql = String.format("INSERT INTO user (userName, password, userType, createTime, delFlag) VALUES ('%s', '%s', 'student', '%s', 1)",
                student.getStudentCode(), MD5Helper.encryptToMD5(passwrod), DateTimeHelper.GetCurrentTimeToString());

        // 执行用户信息插入操作
        int userResult = ExecuteUpdate(userSql);

        // 检查是否成功插入学生信息和用户信息
        if (studentResult > 0 && userResult > 0) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    /**
     * 编号生成规则（当前年份后两位数+专业班级代码+当前班级人数自增一位）
     * @param student 对象
     * @param classId 班级id
     * @return 执行状态
     */
    public String NumberingRules(Student student,String classId) {
        // 构建 SQL 查询语句
        String sql = "SELECT s.*, c.className FROM student s LEFT JOIN classinfo c ON s.classId = c.classId WHERE s.delFlag = 1 AND s.classId = ?";
        List<Object> params = new ArrayList<>();
        params.add(classId);

        // 调用 GetListparams 方法获取学生列表
        List<Student> students = GetListparams(sql, params);

        int count = students.size() + 1; // 计算学生数量并加一

        // 循环直到找到一个不重复的学生编号
        String newStudentCode = "";
        while (true) {
            newStudentCode = student.getStudentCode() + count; // 学生编号以前端传来的code开头，后面跟着数字
            if (!isStudentCodeExists(students, newStudentCode)) {
                break;
            }
            count++;
        }
        student.setStudentCode(newStudentCode); // 设置新学生的学生编号
        // 打印最终的自增内容
        System.out.println("zizehngneir：" + newStudentCode);
        return newStudentCode;
    }

    /**
     * 判断学生编号是否已存在于列表中
     * @param students 数据列表
     * @param studentCode 编号
     * @return 执行状态
     */
    private boolean isStudentCodeExists(List<Student> students, String studentCode) {
        for (Student s : students) {
            if (s.getStudentCode().equals(studentCode)) {
                return true;
            }
        }
        return false;
    }



    /**
     * 删除多项数据
     * @param idArrayData 学生学号数组
     * @return 执行状态
     */
    public String DeletesArrayList(String[] idArrayData) {
        int successCount = 0; // 记录成功更新的记录数
        // 遍历学号数组
        for (String studentCode : idArrayData) {
            // 构建 SQL 语句
            String sql = "UPDATE student SET delFlag = 2 WHERE studentCode = ?";
            // 执行操作
            int result = deleteExecuteUpdate(sql, studentCode);
            if (result > 0) {
                successCount++; // 如果更新成功，增加成功计数
            }
        }

        if (successCount == idArrayData.length) {
            // 如果成功计数等于数组长度，说明所有记录都成功更新
            return "删除成功";
        } else {
            // 否则，返回成功更新的记录数
            return "成功删除 " + successCount + " 条记录";
        }
    }


    /**
     * 编辑数据
     * @param student 对象
     * @return 执行状态
     */
    public String EditorList(Student student) {
        // 构建更新语句
        String sql = String.format("UPDATE student SET  userName = '%s', gender = '%s', phone = '%s', age = %d, classId = %d, email = '%s' WHERE studentCode = '%s'",
                 student.getUserName(), student.getGender(), student.getPhone(), student.getAge(), student.getClassId(), student.getEmail(), student.getStudentCode());

        // 执行更新操作
        int result = ExecuteUpdate(sql);
        if (result > 0) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }
    /**
     * 根据班级id获取学生数量
     * @param classId 班级id
     * @return 执行状态
     */
    public int GetStudentCountByClassId(int classId){
        String sql =  String.format(" select * from student where classId = %d",classId);

        return ExecuteQueryCount(sql);
    }

}
