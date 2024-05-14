package com.Controller;

import com.IService.ITeacherService;
import com.Pojo.DTO.ListResultDto;
import com.Pojo.DTO.PagerInfoDto;
import com.Pojo.Teacher;
import com.Service.TeacherService;
import com.Tools.APIHelper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/TeacherServlet")
public class TeacherServlet extends BaseServlet{
    private ITeacherService _teacherService = new TeacherService();

    // 查询
    public void query(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 数据
        String Name = req.getParameter("teacherName");

        PagerInfoDto p = null;
        if (req.getParameter("pageNum")!=null){
            p = new PagerInfoDto();
            p.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
            p.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
        }

        // 调用service处理，并返回给前端
        ListResultDto<Teacher> dto = _teacherService.GetList(Name,p);
        PrintWriter w = resp.getWriter();
        w.println(SUCCESS(dto));
    }

    // 添加
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // data数据
        JSONObject postData = APIHelper.getPostData(req);

        // 初始化对象
        Teacher teacher = new Teacher();
        teacher.setTeacherName(postData.getString("name"));
        teacher.setGender(postData.getString("gender"));
        teacher.setAge(postData.getInteger("age"));
        teacher.setPhone(postData.getString("phone"));
        teacher.setEmail(postData.getString("email"));

        // 调用service处理，并返回给前端
        String Lisi = _teacherService.AddList(teacher);
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
            String Lisi = _teacherService.DeletesArrayList(idArrayData);
            writer.println(SUCCESS(Lisi));
        }
    }

    // 编辑
    public void editor(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // data数据
        JSONObject postData = APIHelper.getPostData(req);

        // 初始化对象
        Teacher teacher = new Teacher();
        teacher.setTeacherName(postData.getString("name"));
        teacher.setTeacherCode(postData.getString("teacherCode"));
        teacher.setGender(postData.getString("gender"));
        teacher.setAge(postData.getInteger("age"));
        teacher.setPhone(postData.getString("phone"));
        teacher.setEmail(postData.getString("email"));

        // 调用service处理，并返回给前端
        String Lisi = _teacherService.EditorList(teacher);
        PrintWriter writer = resp.getWriter();
        writer.println(SUCCESS(Lisi));
    }
}
