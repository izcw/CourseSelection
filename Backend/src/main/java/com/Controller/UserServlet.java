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

        // 判断返回的数组是否为空
        if (result != null && result.length == 2) {
            // 如果数组不为空且长度为2，则提取状态码和消息
            int statusCode = (int) result[0];
            String message = (String) result[1];

            if (statusCode == 200) {
                writer.println(SUCCESS(message));
            } else {
                writer.println(ERROR(message));
            }
        } else {
            // 如果数组为空或者长度不为2，则给出默认的状态码和消息
            int statusCode = 400;
            String message = "未知错误";
            writer.println(ERROR(statusCode,"请求失败，状态码: " + statusCode + "，消息: " + message));
        }

    }
}
