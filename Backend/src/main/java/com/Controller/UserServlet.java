package com.Controller;

import com.IService.IUserService;
import com.Pojo.User;
import com.Service.UserService;
import com.Tools.APIHelper;
import com.Tools.TokenHelper;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet{
    IUserService _userService = new UserService();

    public void GetUserInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject postData = APIHelper.getPostData(req);
        String token = (String) postData.get("token");
        Integer userId = TokenHelper.verify(token);

        PrintWriter writer = resp.getWriter();


//        User user = _userService.GetUserById(userId);

        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("admin");
        Map<String,Object> map = new HashMap<>();
        map.put("permissions",permissions);
        map.put("avatar","");
        map.put("username","admin");
        writer.println(SUCCESS(map));

    }

    public void GetUserinfodata(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject postData = APIHelper.getPostData(req);
        String token = (String) postData.get("token");
        Integer userId = TokenHelper.verify(token);

        System.out.println("tytytyTYTY:"+userId);

        PrintWriter writer = resp.getWriter();
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("username","admin");
        writer.println(SUCCESS(map));

    }
}
