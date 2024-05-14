package com.Service;

import com.IService.IStudentService;
import com.Pojo.DTO.PagerInfoDto;
import com.Pojo.DTO.ListResultDto;
import com.Pojo.Student;
import com.Tools.DateTimeHelper;
import com.Tools.MD5Helper;

import java.util.ArrayList;
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
    public ListResultDto<Student> GetList(String Id, String Name, PagerInfoDto p) {
        ListResultDto<Student> dto = new ListResultDto<Student>();
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
     * @param password 密码（用于初始化密码，格式为学号后6位）
     * @return 执行状态
     */
    public String AddList(Student student, String password) {
        // 构建学生信息插入语句
        String studentSql = String.format("INSERT INTO student (studentCode, userName, gender, phone, age, classId, email, createTime) VALUES ('%s', '%s', '%s', '%s', %d, %d, '%s', '%s')",
                student.getStudentCode(), student.getUserName(), student.getGender(), student.getPhone(), student.getAge(), student.getClassId(), student.getEmail(), DateTimeHelper.GetCurrentTimeToString());

        // 执行学生信息插入操作
        int studentResult = ExecuteUpdate(studentSql);

        // 构建用户信息插入语句
        String userSql = String.format("INSERT INTO user (userName, password, userType, createTime, delFlag) VALUES ('%s', '%s', 'student', '%s', 1)",
                student.getStudentCode(), MD5Helper.encryptToMD5(password), DateTimeHelper.GetCurrentTimeToString());

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
    public List<Student> GetStudentByClassId(int classId){
        String sql =  String.format(" select * from student where classId = %d",classId);

        return GetList(sql);
    }

}
