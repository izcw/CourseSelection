package com.IService;

import com.Pojo.Course;
import com.Pojo.User;

public interface IUserService extends IBaseService<User>{
    User login(User user);
    //返回一个用户实体根据用户id
    User GetUserById(Integer userId);
}