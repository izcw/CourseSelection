package com.Controller;

import com.IService.IClassService;
import com.IService.IUserService;
import com.Pojo.ClassInfo;
import com.Service.ClassService;
import com.Service.UserService;
import com.Tools.APIHelper;
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
    public void AddClass(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject postData = APIHelper.getPostData(req);
        ClassInfo c = new ClassInfo();
        String className = postData.getString("className");
        Integer teacherId = postData.getInteger("teacherId");
        PrintWriter w = resp.getWriter();
        if (className==null||className.equals("")){
            w.println(ERROR("班级名称不能为空"));
            return;
        }
        if (teacherId==null||teacherId==0){
            w.println(ERROR("班主任不能为空"));
            return;
        }
        c.setClassName(className);
        c.setTeacherId(teacherId);
        boolean b = _classService.AddClass(c);
        if (b){
            w.println(SUCCESS("添加成功"));
        }else {
            w.println(ERROR("添加失败"));
        }

    }
    public void GetClassById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter w = resp.getWriter();
        Integer classId = 0;
       if(req.getParameter("classId").equals("")) {
           w.println(ERROR("班级id为空"));
       }else {
           classId = Integer.parseInt(req.getParameter("classId"));
       }
        ClassInfo classInfo = _classService.GetClassInfo(classId);
        w.println(SUCCESS(classInfo));
    }
    public void UpdateClass(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter w = resp.getWriter();
        if (req.getParameter("className")==null||req.getParameter("className").equals("")){
            w.println(ERROR("班级名称为空"));
            return;
        }
        if (req.getParameter("teacherId")==null||Integer.parseInt(req.getParameter("teacherId"))==0){
            w.println(ERROR("班主任为空"));
            return;
        }
        JSONObject postData = APIHelper.getPostData(req);
        ClassInfo c = new ClassInfo();
        String className = "";
        Integer teacherId = 0;
        className = postData.getString("className");
        teacherId = postData.getInteger("teacherId");
        c.setClassName(className);
        c.setTeacherId(teacherId);
        String s = _classService.UpdateClass(c);
        w.println(SUCCESS(s));
    }
}
