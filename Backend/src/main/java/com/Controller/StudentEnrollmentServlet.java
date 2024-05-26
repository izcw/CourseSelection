package com.Controller;

import com.IService.IStudentService;
import com.IService.IUserService;
import com.Pojo.Course;
import com.Pojo.DTO.SelectionOperationDto;
import com.Pojo.Enrollment;
import com.Pojo.Student;
import com.Pojo.User;
import com.Service.CourseService;
import com.Service.EnrollmentService;
import com.Service.StudentService;
import com.Service.UserService;
import com.Tools.TokenHelper;
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
    IUserService _userService = new UserService();

    IStudentService _studentService = new StudentService();
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
//        String Id = req.getParameter("enrollmentId");
        String token = req.getHeader("token");
//        System.out.println("Token-----"+token);

        Integer Id = TokenHelper.verify(token);
        User user = _userService.GetUserById(Id);
        Student student = _studentService.GetStudentByCode(user.getUserName());
        List<SelectionOperationDto> studentenCourse = _courseService.GetstudentshowCourseList(student.getClassId());
        PrintWriter w = resp.getWriter();
        w.println(SUCCESS(studentenCourse));
    }
}
