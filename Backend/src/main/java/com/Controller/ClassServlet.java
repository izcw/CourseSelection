package com.Controller;

import com.IService.IClassService;
import com.IService.IUserService;
import com.Pojo.ClassInfo;
import com.Pojo.Student;
import com.Service.ClassService;
import com.Service.UserService;
import com.Tools.APIHelper;
import com.Tools.APIResult;
import com.Tools.SixRandomString;
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

    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // data数据
        JSONObject postData = APIHelper.getPostData(req);

        // 初始化对象
        ClassInfo c = new ClassInfo();

        Integer teacherId = postData.getInteger("teacherId");
        if (teacherId != null) {
            c.setTeacherId(teacherId);
        } else {
            c.setTeacherId(1);
        }

        c.setClassName(postData.getString("className"));
        String classnumber = postData.getString("classNumber");
        c.setClassNumber(classnumber);
        String sixnum = SixRandomString.generateRandomString();
        c.setCode(sixnum+classnumber); // 随机生成6位数+班级号

        // 插入数据，并返回给前端
        String classInfos = _classService.AddClass(c);
        PrintWriter writer = resp.getWriter();
        writer.println(SUCCESS(classInfos));
    }
}
