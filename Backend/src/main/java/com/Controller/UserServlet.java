package com.Controller;

import com.IService.IUserService;
import com.Pojo.Student;
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
import java.util.List;
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
//        String token = req.getParameter("token");
        String token = req.getHeader("token");
        System.out.println("Token-----"+token);

        Integer Id = TokenHelper.verify(token);
        String strId = Id.toString(); // 将整数id转换为字符串类型
        System.out.println("UserId-----"+strId);


        // 调用service处理，并返回给前端
        List<User> Lisi = _userService.GetList(strId);
        PrintWriter writer = resp.getWriter();
        writer.println(SUCCESS(Lisi));
    }


    // 修改密码
    public void changepassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // data数据
        JSONObject postData = APIHelper.getPostData(req);
        String code = postData.getString("code");
        String oldpassword = postData.getString("oldpassword");
        String newpassword = postData.getString("newpassword");

        // 调用service处理，并返回给前端
        Object[] result = _userService.changepasswd(code,oldpassword,newpassword);
        PrintWriter writer = resp.getWriter();
        processResponse(result, writer);
    }
}
