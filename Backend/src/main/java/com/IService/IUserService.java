package com.IService;

import com.Pojo.Course;
import com.Pojo.User;

public interface IUserService extends IBaseService<User>{
    boolean login(User user);
    User GetUserById(Integer userId);
}
