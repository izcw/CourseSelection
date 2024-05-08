package com.Controller;
import com.IService.IStudentService;
import com.Pojo.DTO.PagerInfoDto;
import com.Pojo.DTO.StudentListResultDto;
import com.Pojo.Student;
import com.Service.StudentService;
import com.Tools.APIHelper;
import com.Tools.DateTimeHelper;
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
        // 数据
        String classid =req.getParameter("classid");
        if(classid == null || classid.equals("")){
            classid = String.valueOf('1');
        }
        String studentNmae = req.getParameter("studentNmae");
        PagerInfoDto p = null;
        if (req.getParameter("pageNum")!=null){
            p = new PagerInfoDto();
            p.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
            p.setPageSize(Integer.parseInt(req.getParameter("pageSize")));

        }
        // 查询数据，并返回给前端
        StudentListResultDto dto = _StudentService.GetStudentList(classid,studentNmae,p);
        PrintWriter w = resp.getWriter();
        w.println(SUCCESS(dto));
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // data数据
        JSONObject postData = APIHelper.getPostData(req);

        // 初始化对象
        Student student = new Student();
        student.setUserName(postData.getString("name"));
        student.setStudentCode(postData.getString("studentCode"));
        student.setClassId(postData.getInteger("classId"));
        student.setGender(postData.getString("gender"));
        student.setAge(postData.getInteger("age"));
        student.setPhone(postData.getString("phone"));
        student.setEmail(postData.getString("email"));

        // 插入数据，并返回给前端
        String studentLisi = _StudentService.AddStudentList(student);
        PrintWriter writer = resp.getWriter();
        writer.println(SUCCESS(studentLisi));
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 数据
        String studentCode = req.getParameter("studentCode");
        PrintWriter writer = resp.getWriter();

        // 判断“学号”是否为空
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
