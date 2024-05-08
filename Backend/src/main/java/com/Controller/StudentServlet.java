package com.Controller;
import com.IService.IStudentService;
import com.Pojo.DTO.PagerInfoDto;
import com.Pojo.DTO.StudentListResultDto;
import com.Pojo.Student;
import com.Service.StudentService;
import com.Tools.APIHelper;
import com.alibaba.fastjson.JSONArray;
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
 * @Description：student
 */

@WebServlet("/StudentServlet")
public class StudentServlet extends BaseServlet{
    public IStudentService _StudentService = new StudentService();
    // 查询
    public void query(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 数据
        String Id =req.getParameter("classid");
        String Name = req.getParameter("studentName");

        PagerInfoDto p = null;
        if (req.getParameter("pageNum")!=null){
            p = new PagerInfoDto();
            p.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
            p.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
        }
        StudentListResultDto dto = _StudentService.GetList(Id,Name,p);
        PrintWriter w = resp.getWriter();
        w.println(SUCCESS(dto));
    }

    // 添加
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

        // 调用service处理，并返回给前端
        String Lisi = _StudentService.AddList(student);
        PrintWriter writer = resp.getWriter();
        writer.println(SUCCESS(Lisi));
    }


    // 删除多项
    public void deletes(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 数据
        JSONObject postData = APIHelper.getPostData(req);
        JSONArray data = postData.getJSONArray("data"); // 解析数组
        PrintWriter writer = resp.getWriter();
        // 检查 idArray 是否为空
        if (data == null || data.isEmpty()) {
            writer.println(SUCCESS("不能为空数组！"));
        }else {
            // 创建字符串数组并存储
            String[] idArrayData = new String[data.size()];
            for (int i = 0; i < data.size(); i++) {
                idArrayData[i] = data.getString(i);
            }

            // 调用service处理，并返回给前端
            String Lisi = _StudentService.DeletesArrayList(idArrayData);
            writer.println(SUCCESS(Lisi));
        }
    }

    // 编辑
    public void editor(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

        // 调用service处理，并返回给前端
        String Lisi = _StudentService.EditorList(student);
        PrintWriter writer = resp.getWriter();
        writer.println(SUCCESS(Lisi));
    }
}
