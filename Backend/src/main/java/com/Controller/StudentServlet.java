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
    public void Query(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter w = resp.getWriter();
        List<Student> studentLisi = _StudentService.GetStudentList();
        w.println(SUCCESS(studentLisi));
    }

    public void Add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter w = resp.getWriter();
        List<Student> studentLisi = _StudentService.AddStudentList();
        w.println(SUCCESS(studentLisi));
    }

    public void Delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter w = resp.getWriter();
        List<Student> studentLisi = _StudentService.DeleteStudentList();
        w.println(SUCCESS(studentLisi));
    }

    public void Editor(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter w = resp.getWriter();
        List<Student> studentLisi = _StudentService.EditorStudentList();
        w.println(SUCCESS(studentLisi));
    }
}
