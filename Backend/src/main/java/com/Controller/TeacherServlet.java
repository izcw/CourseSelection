package com.Controller;

import com.IService.ITeacherService;
import com.Pojo.Teacher;
import com.Service.TeacherService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/TeacherServlet")
public class TeacherServlet extends BaseServlet{
    private ITeacherService _teacherService = new TeacherService();
    public void GetList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        Teacher teacher = new Teacher();

        List<Teacher> teachers = _teacherService.GetList(teacher);
        PrintWriter writer = resp.getWriter();
        writer.println(SUCCESS(teachers));
    }
}
