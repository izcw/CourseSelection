package com.Service;


import com.IService.IUserService;
import com.Pojo.User;
import com.Tools.MD5Helper;
import com.Tools.TokenHelper;

public class UserService extends BaseService<User> implements IUserService {
    @Override
    public boolean login(User user) {
        String md5PWD = MD5Helper.encryptToMD5(user.getPassword());
        User user1 = GetFirst(String.format("select * from user where username = %s and password = %s and userType = %s ", user.getUserName(), md5PWD, user.getUserType()));
        if (user1 != null) {
           return true;
        }
        return false;

    }
}
