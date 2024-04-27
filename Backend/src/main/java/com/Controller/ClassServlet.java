package com.Controller;

import com.IService.IClassService;
import com.IService.IUserService;
import com.Pojo.ClassInfo;
import com.Service.ClassService;
import com.Service.UserService;
import com.Tools.APIResult;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ClassServlet")
public class ClassServlet extends BaseServlet{
    public IClassService _classService = new ClassService();
    public void GetList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ClassInfo c = new ClassInfo();
        if (!req.getParameter("teacherId").equals("")){
            c.setTeacherId(Integer.parseInt(req.getParameter("teacherId")));
        }
        c.setClassName(req.getParameter("className"));
        PrintWriter w = resp.getWriter();
        List<ClassInfo> classInfos = _classService.GetClassList(c);
        w.println(SUCCESS(classInfos));
    }
}
