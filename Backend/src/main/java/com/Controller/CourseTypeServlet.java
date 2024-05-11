package com.Controller;

import com.IService.ICourseTypeService;
import com.Service.CourseTypeService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CourseTypeServlet")
public class CourseTypeServlet extends BaseServlet{
    private ICourseTypeService _CourseTypeService = new CourseTypeService();
    public void GetList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter w = resp.getWriter();
        w.println(SUCCESS(_CourseTypeService.GetList()));

    }
}
