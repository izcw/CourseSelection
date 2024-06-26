package com.Controller;

import com.IService.IUserService;
import com.Pojo.User;
import com.Service.UserService;
import com.Tools.APIHelper;
import com.Tools.TokenHelper;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpCookie;

@WebServlet("/LoginServlet")
public class LoginServlet extends BaseServlet{
    public IUserService _userService = new UserService();

    public void Login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        System.out.println(req.getContextPath());
        User user = new User();
        JSONObject postData = APIHelper.getPostData(req);
        user.setUserName((String)postData.get("username"));
        user.setPassword((String)postData.get("password"));


        user.setUserType((String) postData.get("userType"));


        PrintWriter w = resp.getWriter();
        /*获取验证码与用户输入的验证码数据*/
        String piccode = (String)req.getSession().getAttribute("piccode");
        String checkCode = postData.getString("validationCode");// 取值
        checkCode = checkCode.toUpperCase(); // 把字符串中的字母全部转换为大写


        User u = _userService.login(user);
        if (!checkCode.equals(piccode)) {
            w.println(ERROR("验证码错误"));
            return;
        }
        if (u!=null){
            String token = TokenHelper.GetToken(u);

            HttpSession session = req.getSession();
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            resp.addCookie(cookie);

            Cookie cookie2 = new Cookie("userType",user.getUserType() );
            cookie2.setPath("/");
            resp.addCookie(cookie2);
            session.setAttribute("token",token);
            w.println(SUCCESS(token));
        }else {

            w.println(ERROR("账号或密码或身份有错误"));
        }


    }
}