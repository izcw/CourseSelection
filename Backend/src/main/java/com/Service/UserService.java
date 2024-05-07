package com.Service;


import com.IService.IUserService;
import com.Pojo.User;
import com.Tools.MD5Helper;
import com.Tools.TokenHelper;

public class UserService extends BaseService<User> implements IUserService {
    @Override
    public User login(User user) {
        String md5PWD = MD5Helper.encryptToMD5(user.getPassword());
        String sql = String.format("select * from user where username = '%s' and password = '%s' and userType = '%s' limit 1 ", user.getUserName(), md5PWD, user.getUserType());
        User user1 = GetFirst(sql);
        if (user1.getUserId() != null&&user1.getUserId()!=0) {
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
}