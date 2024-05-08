package com.Service;

import com.IService.IStudentService;
import com.Pojo.Student;
import com.Tools.DateTimeHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author：2331020120242张成威
 * @Date： 2024/4/29 下午5:15
 * @ProjectName：Backend
 * @Description：
 */
public class StudentService extends BaseService<Student> implements IStudentService {

    /**
     * 通过传id与姓名查找数据
     * @param Id id
     * @param Name 要查找的姓名（模糊查询）
     * @return 执行状态
     */
    public List<Student> GetList(String Id, String Name) {
        // 构建语句
        StringBuilder sb = new StringBuilder("SELECT * FROM student s ");
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

        // 执行操作
        return GetListparams(sb.toString(), params);
    }


    /**
     * 添加数据
     * @param student 对象
     * @return 执行状态
     */
    public String AddList(Student student) {
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
}
