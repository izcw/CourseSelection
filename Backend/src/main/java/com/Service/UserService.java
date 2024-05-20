package com.Service;


import com.IService.IUserService;
import com.Pojo.User;
import com.Tools.MD5Helper;

import java.util.Collections;
import java.util.List;

public class UserService extends BaseService<User> implements IUserService {
    @Override
    public User login(User user) {
        String md5PWD = MD5Helper.encryptToMD5(user.getPassword());
        String sql = String.format("select * from user where username = '%s' and password = '%s' and userType = '%s' limit 1 ", user.getUserName(), md5PWD, user.getUserType());
        User user1 = GetFirst(sql);
        if (user1 != null&&user1.getUserId()!=0) {
            return user1;
        }
        return null;

    }
    //返回一个用户实体根据用户id
    @Override
    public User GetUserById(Integer userId) {
        User user = GetFirst(String.format("select * from user where user userId = %d limit 1", userId));
        return user;
    }

    /**
     * 通过传入id查找对应的用户信息，并过滤掉敏感信息
     * @param Id 用户id
     * @return 用户列表
     */
    public List<User> GetList(String Id) {
        // 构建语句
        String sql = "SELECT userType,UserName FROM user WHERE userId = ? AND delFlag = 1";
        List<Object> params = Collections.singletonList(Id);
        List<User> userList = GetListparams(sql, params);

        // 如果找到用户
        if (!userList.isEmpty()) {
            User user = userList.get(0);
            String userType = user.getUserType();
            String code = user.getUserName();

            // 根据用户类型获取对应的完整用户信息
            if ("student".equals(userType)) {
                return getStudentInfo(code);
            } else if ("teacher".equals(userType)) {
                return getTeacherInfo(code);
            } else {
                // 如果用户类型不是学生或教师，则直接返回用户信息
                return getUserInfo(Id);
            }
        }
        return Collections.emptyList(); // 如果找不到用户，返回空列表
    }

    /**
     * 从student表中获取学生信息
     * @param code 用户id
     * @return 学生信息列表
     */
    private List<User> getStudentInfo(String code) { // 修改这里的参数类型为 String
        String sql = "SELECT * FROM student WHERE studentCode = ? AND delFlag = 1";
        List<Object> params = Collections.singletonList(code);
        return GetListparams(sql, params);
    }

    /**
     * 从teacher表中获取教师信息
     * @param code 用户id
     * @return 教师信息列表
     */
    private List<User> getTeacherInfo(String code) { // 修改这里的参数类型为 String
        String sql = "SELECT * FROM teacher WHERE teacherCode = ? AND delFlag = 1";
        List<Object> params = Collections.singletonList(code);
        return GetListparams(sql, params);
    }

    /**
     * 获取用户信息（如果用户类型不是学生或教师）
     * @param id 用户id
     * @return 用户信息列表
     */
    private List<User> getUserInfo(String id) {
        // 构建 SQL 查询语句，只选择非敏感字段
        String sql = "SELECT userId, userName, userType, createTime FROM user WHERE userId = ? AND delFlag = 1";
        List<Object> params = Collections.singletonList(id);
        return GetListparams(sql, params);
    }


    /**
     * 修改密码（旧密码与数据库密码匹配）
     * @param code        要查找的id
     * @param oldpassword 旧密码
     * @param newpassword 新密码
     * @return 执行状态
     */
    public Object[] changepasswd(String code, String oldpassword, String newpassword) {
        // 构建查询语句
        String sql = "SELECT password FROM user WHERE userName = ?";
        User user = GetSingleResultsingle(sql, code);
        if (user == null) return new Object[]{400, "用户不存在！请联系管理员处理！"};
        String password = user.getPassword(); // 获取Password字段的值
        if (password == null && password.isEmpty())  return new Object[]{400, "账户密码有有误！请联系管理员处理！"};;

        String MDoldpasswd =  MD5Helper.encryptToMD5(oldpassword);
        if (MDoldpasswd.equals(password)) { // 判断MDoldpasswd和password是否完全一致
            // 构建更新语句
            String usersql = String.format("UPDATE user SET  password = '%s' WHERE userName = '%s'", MD5Helper.encryptToMD5(newpassword), code);

            // 执行更新操作
            int result = ExecuteUpdate(usersql);
            if (result > 0) {
                return new Object[]{200, "修改成功"};
            } else {
                return new Object[]{400, "修改失败"};
            }
        } else {
            return new Object[]{400, "旧密码不正确"};
        }
    }


}