package com.Controller;

import com.Pojo.Course;
import com.Pojo.Enrollment;
import com.Service.CourseService;
import com.Service.EnrollmentService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author：2331020120242张成威
 * @Date： 2024/5/18 上午12:32
 * @ProjectName：Backend
 * @Description：
 */

@WebServlet("/StudentEnrollmentServlet")
public class StudentEnrollmentServlet extends BaseServlet{
    public EnrollmentService _enrollmentService = new EnrollmentService();
    public CourseService _courseService = new CourseService();
    // 查询课程类别
    public void query(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 数据
        String Id = req.getParameter("classId");

        List<Enrollment> studentenrollments = _enrollmentService.GetstudentEnrollmentList(Id);
        PrintWriter w = resp.getWriter();
        w.println(SUCCESS(studentenrollments));
    }

    // 查询可选课程
    public void querycourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 数据
        String Id = req.getParameter("enrollmentId");
        List<Course> studentenCourse = _courseService.GetstudentshowCourseList(Id);
        PrintWriter w = resp.getWriter();
        w.println(SUCCESS(studentenCourse));
    }
}
