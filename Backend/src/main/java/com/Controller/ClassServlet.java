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
        if (req.getParameter("teacherId")!=null&&!req.getParameter("teacherId").equals("")){
            c.setTeacherId(Integer.parseInt(req.getParameter("teacherId")));
        }
        if (req.getParameter("className")!=null&&!req.getParameter("className").equals("")){
            c.setClassName(req.getParameter("className"));
        }

        PrintWriter w = resp.getWriter();
        List<ClassInfo> classInfos = _classService.GetClassList(c);
        w.println(SUCCESS(classInfos));
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        JSONObject postData = APIHelper.getPostData(req);
        if (postData==null){
            writer.println(ERROR("参数有误"));
            return;
        }

        if (postData.getString("teacherId")==null){
            writer.println(ERROR("班主任不能为空"));
            return;
        }
        if (postData.getString("className")==null){
            writer.println(ERROR("班级名称不能为空"));
            return;
        }
        if (postData.getString("classNumber")==null){
            writer.println(ERROR("班号不能为空"));
            return;
        }



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

        writer.println(SUCCESS(classInfos));
    }
    public void GetClassById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        if (req.getParameter("classId")==null||req.getParameter("classId").equals("")){
            writer.println(ERROR("classId为空"));
            return;
        }
        int classId = Integer.parseInt(req.getParameter("classId"));
        ClassInfo classInfo = _classService.GetClassInfo(classId);
        writer.println(SUCCESS(classInfo));
    }
    public void UpdateClass(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        JSONObject postData = APIHelper.getPostData(req);
        if (postData==null){
            writer.println(ERROR("参数有误"));
            return;
        }
        if (postData.getString("classId")==null){
            writer.println(ERROR("班级id为空"));
            return;
        }
        if (postData.getString("teacherId")==null){
            writer.println(ERROR("班主任不能为空"));
            return;
        }
        if (postData.getString("className")==null){
            writer.println(ERROR("班级名称不能为空"));
            return;
        }
        if (postData.getString("classNumber")==null){
            writer.println(ERROR("班号不能为空"));
            return;
        }



        // 初始化对象
        ClassInfo c = new ClassInfo();

        Integer teacherId = postData.getInteger("teacherId");
        if (teacherId != null) {
            c.setTeacherId(teacherId);
        } else {
            c.setTeacherId(1);
        }
        Integer classId = postData.getInteger("classId");
        c.setClassId(classId);
        c.setClassName(postData.getString("className"));
        String classNumber = postData.getString("classNumber");
        c.setClassNumber(classNumber);
        String code = postData.getString("code");
        c.setCode(code); // 随机生成6位数+班级号
        String s = _classService.UpdateClass(c);
        if (s.equals("修改成功")){
            writer.println(SUCCESS(s));
            return;
        }
        writer.println(ERROR(s));
    }
    public void DeleteClass(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        JSONObject postData = APIHelper.getPostData(req);
        if (postData==null){
            writer.println(ERROR("参数有误"));
            return;
        }
        if (postData.getString("classId")==null){
            writer.println(ERROR("班级id为空"));
            return;
        }
        Integer classId = postData.getInteger("classId");
        boolean b = _classService.DeleteClass(classId);
        if (b){
            writer.println(SUCCESS("删除成功"));

        }else {
            writer.println(ERROR("删除失败"));

        }
    }
}
