package com.Controller;

import com.IService.IEnrollmentStudentService;
import com.Pojo.ClassInfo;
import com.Pojo.Course;
import com.Pojo.DTO.ListResultDto;
import com.Pojo.DTO.PagerInfoDto;
import com.Pojo.EnrollmentStudent;
import com.Pojo.Student;
import com.Service.EnrollmentStudentService;
import com.Tools.APIHelper;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/EnrollmentStudentServlet")
public class EnrollmentStudentServlet extends BaseServlet{
    private IEnrollmentStudentService _enrollmentStudentService = new EnrollmentStudentService();
    public void GetListByEnrollmentId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        EnrollmentStudent es = new EnrollmentStudent();
        es.setStudent(new Student());
        es.setCourse(new Course());
        if (req.getParameter("enrollmentId")==null){
            writer.println(ERROR("enrollmentId为空"));
            return;
        }
        if (req.getParameter("studentCode")!=null&&!req.getParameter("studentCode").equals("")){
            es.getStudent().setStudentCode(req.getParameter("studentCode"));
        }
        if (req.getParameter("studentName")!=null&&!req.getParameter("studentName").equals("")){
            es.getStudent().setUserName(req.getParameter("studentName"));
        }
        if (req.getParameter("courseName")!=null&&!req.getParameter("courseName").equals("")){
            es.getCourse().setCourseName(req.getParameter("courseName"));
        }
        if (req.getParameter("courseCode")!=null&&!req.getParameter("courseCode").equals("")){
            es.getCourse().setCourseCode(req.getParameter("courseCode"));
        }
        es.setEnrollmentId(Integer.parseInt(req.getParameter("enrollmentId")));
        PagerInfoDto p = null;
        if (req.getParameter("pageNum")!=null){
            p = new PagerInfoDto();
            p.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
            p.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
        }
        PrintWriter w = resp.getWriter();
        ListResultDto<EnrollmentStudent> dto = _enrollmentStudentService.GetList(es,p);
        w.println(SUCCESS(dto));
    }


    // 查询学生选的课程
    public void mycourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 数据
        String Id = req.getParameter("studentCode");

        List<EnrollmentStudent> Course = _enrollmentStudentService.GetMystudentcourseList(Id);
        PrintWriter w = resp.getWriter();
        w.println(SUCCESS(Course));
    }


    // 选择课程
    public void selectstudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // data数据
        JSONObject postData = APIHelper.getPostData(req);
        System.out.println("jbjbjbbbbb++"+postData.getString("courseCode"));
        System.out.println(Integer.parseInt(postData.getString("enrollmentId")));
        System.out.println(postData.getString("studentCode"));
        // 初始化对象
        EnrollmentStudent enrollmentStudent = new EnrollmentStudent();
        enrollmentStudent.setEnrollmentId(Integer.parseInt(postData.getString("enrollmentId")));
        enrollmentStudent.setCourseCode(postData.getString("courseCode"));
        enrollmentStudent.setStudentCode(postData.getString("studentCode"));

        // 调用service处理，并返回给前端
        Object[] result = _enrollmentStudentService.SelectEnrollmentStudent(enrollmentStudent);
        PrintWriter writer = resp.getWriter();
        processResponse(result, writer);
    }


}
