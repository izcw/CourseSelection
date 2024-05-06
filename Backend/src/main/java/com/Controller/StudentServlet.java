package com.Controller;

import com.IService.IClassService;
import com.IService.IStudentService;
import com.Pojo.ClassInfo;
import com.Pojo.Student;
import com.Service.ClassService;
import com.Service.StudentService;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author：2331020120242张成威
 * @Date： 2024/4/29 下午4:57
 * @ProjectName：Backend
 * @Description：
 */

@WebServlet("/StudentServlet")
public class StudentServlet extends BaseServlet{
    public IStudentService _StudentService = new StudentService();
    public void query(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int classid = Integer.parseInt(req.getParameter("classid"));
        String studentNmae = req.getParameter("studentNmae");
        System.out.println(classid+"yuyuyuyu"+studentNmae);
        PrintWriter w = resp.getWriter();
        List<Student> studentLisi = _StudentService.GetStudentList(classid,studentNmae);
        w.println(SUCCESS(studentLisi));
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter w = resp.getWriter();
        List<Student> studentLisi = _StudentService.AddStudentList();
        w.println(SUCCESS(studentLisi));
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String studentCode = req.getParameter("studentCode");
        PrintWriter writer = resp.getWriter();

        if (studentCode != null && !studentCode.isEmpty()) {
            String studentLisi = _StudentService.DeleteStudentList(studentCode);
            writer.println(SUCCESS(studentLisi));
        } else {
            writer.println(ERROR("学生学号不能为空"));
        }
    }

    public void editor(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter w = resp.getWriter();
        List<Student> studentLisi = _StudentService.EditorStudentList();
        w.println(SUCCESS(studentLisi));
    }
}
