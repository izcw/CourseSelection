package com.Controller;

import com.IService.IUserService;
import com.Pojo.User;
import com.Service.UserService;
import com.Tools.TokenHelper;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/LoginServlet")
public class LoginServlet extends BaseServlet{
    public IUserService _userService = new UserService();
    public void Login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
       User user = new User();
       user.setUserName(req.getParameter("username"));
       user.setUserName(req.getParameter("password"));
       user.setUserName(req.getParameter("usertype"));
        PrintWriter w = resp.getWriter();

        boolean login = _userService.login(user);
        JSONObject json = new JSONObject();
        if (login){
            String token = TokenHelper.GetToken(user);
            json.put("code",200);
            json.put("data",token);
            w.println(json);
        }else {
            json.put("code",401);
            json.put("messge","你没有权限登录本系统");
            json.put("data",null);
            w.println(json);
        }

    }
}
