package com.Service;

import com.IService.ITeacherService;
import com.Pojo.DTO.PagerInfoDto;
import com.Pojo.DTO.StudentListResultDto;
import com.Pojo.DTO.TeacherListResultDto;
import com.Pojo.Student;
import com.Pojo.Teacher;
import com.Tools.DateTimeHelper;
import com.Tools.MD5Helper;
import com.Tools.SixRandomString;

import java.util.ArrayList;
import java.util.List;

public class TeacherService extends BaseService<Teacher> implements ITeacherService {

    /**
     * 通过传姓名查找数据
     * @param Name 要查找的姓名（模糊查询）
     * @return 执行状态
     */
    public TeacherListResultDto GetList(String Name,String Code, PagerInfoDto p) {
        TeacherListResultDto dto = new TeacherListResultDto();
        // 构建语句
        StringBuilder sb = new StringBuilder("SELECT * FROM teacher s ");
        sb.append("WHERE s.delFlag = 1");

        List<Object> params = new ArrayList<>();

        // 如果不为空，则添加条件
        // 模糊查询 name
        if (Name != null && !Name.isEmpty()) {
            sb.append(" AND s.teacherName LIKE ?");
            params.add("%" + Name + "%");
        }

        // 模糊查询 teacherCode
        if (Code != null && !Code.isEmpty()) {
            sb.append(" AND s.teacherCode LIKE ?");
            params.add("%" + Code + "%");
        }

        if (p!=null){
            int count = GetListparams(sb.toString(), params).size();
            p.setTotalNum(count);
            sb.append(String.format(" limit %d,%d ",(p.getPageNum()-1)*p.getPageSize(),p.getPageSize()));
        }
        List<Teacher> teachers = GetListparams(sb.toString(), params);
        dto.setList(teachers);
        dto.setPagerInfoDto(p);

        // 执行操作
        return dto;
    }


    /**
     * 添加数据
     * @param teacher 对象
     * @return 执行状态
     */
    public String AddList(Teacher teacher) {
        String password = SixRandomString.generateRandomString();
        String teacherCode = SixRandomString.generateRandomString()+password;
        // 构建语句
        String sql = String.format("INSERT INTO teacher (teacherCode, teacherName, gender, phone, age, email, createTime) VALUES ('%s', '%s', '%s', '%s', %d,'%s', '%s')",
                teacherCode, teacher.getTeacherName(), teacher.getGender(), teacher.getPhone(), teacher.getAge(),  teacher.getEmail(), DateTimeHelper.GetCurrentTimeToString());
        int teacherResult = ExecuteUpdate(sql);

        // 构建用户信息插入语句
        String userSql = String.format("INSERT INTO user (userName, password, userType, createTime, delFlag) VALUES ('%s', '%s', 'teacher', '%s', 1)",
                teacherCode, MD5Helper.encryptToMD5(password), DateTimeHelper.GetCurrentTimeToString());

        // 执行用户信息插入操作
        int userResult = ExecuteUpdate(userSql);

        // 检查是否成功插入学生信息和用户信息
        if (teacherResult > 0 && userResult > 0) {
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
            String sql = "UPDATE teacher SET delFlag = 2 WHERE teacherCode = ?";
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
     * @param teacher 对象
     * @return 执行状态
     */
    public String EditorList(Teacher teacher) {
        // 构建更新语句
        String sql = String.format("UPDATE teacher SET  teacherName = '%s', gender = '%s', phone = '%s', age = %d,  email = '%s' WHERE teacherCode = '%s'",
                teacher.getTeacherName(), teacher.getGender(), teacher.getPhone(), teacher.getAge(),  teacher.getEmail(), teacher.getTeacherCode());

        // 执行更新操作
        int result = ExecuteUpdate(sql);
        if (result > 0) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }
}
