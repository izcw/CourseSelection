package com.Controller;

import com.IService.IEnrollmentStudentService;
import com.Pojo.ClassInfo;
import com.Pojo.Course;
import com.Pojo.DTO.ListResultDto;
import com.Pojo.DTO.PagerInfoDto;
import com.Pojo.EnrollmentStudent;
import com.Pojo.Student;
import com.Service.EnrollmentStudentService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
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
}
