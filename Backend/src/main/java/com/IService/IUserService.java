package com.IService;

import com.Pojo.User;

import java.util.List;


public interface IUserService extends IBaseService<User>{
    User login(User user);
    //返回一个用户实体根据用户id
    User GetUserById(Integer userId);
    List<User> GetList(String Id);
    Object[] changepasswd(String code, String oldpassword, String newpassword);
}