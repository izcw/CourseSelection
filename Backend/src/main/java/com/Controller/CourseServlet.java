package com.Controller;

import com.IService.ICourseService;
import com.Pojo.ClassInfo;
import com.Pojo.Course;
import com.Pojo.DTO.ListResultDto;
import com.Pojo.DTO.PagerInfoDto;
import com.Pojo.EnrollmentStudent;
import com.Service.CourseService;
import com.Tools.APIHelper;
import com.Tools.DateTimeHelper;
import com.Tools.SixRandomString;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/CourseServlet")
public class CourseServlet extends BaseServlet{
    private ICourseService _courseServise = new CourseService();
    public void GetList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Course c = new Course();
        if (req.getParameter("teacherId")!=null&&!req.getParameter("teacherId").equals("")){
            c.setTeacherId(Integer.parseInt(req.getParameter("teacherId")));
        }
        if (req.getParameter("courseTypeId")!=null&&!req.getParameter("courseTypeId").equals("")){
            c.setCourseTypeId(Integer.parseInt(req.getParameter("courseTypeId")));
        }
        if (req.getParameter("courseName")!=null&&!req.getParameter("courseName").equals("")){
            c.setCourseName(req.getParameter("courseName"));
        }
        if (req.getParameter("courseCode")!=null&&!req.getParameter("courseCode").equals("")){
            c.setCourseCode(req.getParameter("courseCode"));
        }
        PagerInfoDto p = null;
        if (req.getParameter("pageNum")!=null){
            p = new PagerInfoDto();
            p.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
            p.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
        }
        PrintWriter w = resp.getWriter();
        ListResultDto<Course> dto = _courseServise.GetCourseList(c, p);
        w.println(SUCCESS(dto));
    }
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        JSONObject postData = APIHelper.getPostData(req);
        if (postData==null){
            writer.println(ERROR("参数有误"));
            return;
        }

        if (postData.getString("teacherId")==null){
            writer.println(ERROR("任课教师不能为空"));
            return;
        }
        if (postData.getString("courseName")==null){
            writer.println(ERROR("课程名称不能为空"));
            return;
        }
        if (postData.getString("credits")==null){
            writer.println(ERROR("学分不能为空"));
            return;
        }
        if (postData.getString("courseTypeId")==null){
            writer.println(ERROR("课程类型不能为空"));
            return;
        }



        // 初始化对象
        Course c = new Course();
        String sixnum = SixRandomString.generateRandomString();
        c.setCourseCode(sixnum); // 随机生成6位数
        c.setCourseName(postData.getString("courseName"));
        Integer teacherId = postData.getInteger("teacherId");
        if (teacherId != null) {
            c.setTeacherId(teacherId);
        } else {
            c.setTeacherId(1);
        }
        Integer courseTypeId = postData.getInteger("courseTypeId");
        if (courseTypeId != null) {
            c.setCourseTypeId(courseTypeId);
        } else {
            c.setCourseTypeId(1);
        }
        Double credits = postData.getDouble("credits");
        if (credits != null) {
            c.setCredits(credits);
        } else {
            c.setCredits(0.0);
        }

        c.setDelFlag("1");
        String date = DateTimeHelper.GetCurrentTimeToString();
        c.setCreateTime(date);

        // 插入数据，并返回给前端
        String addCourse = _courseServise.AddCourse(c);
        if (addCourse.equals("添加成功")){
            writer.println(SUCCESS(addCourse));
        }else {
            writer.println(ERROR(addCourse));
        }

    }
    public void UpdateCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        JSONObject postData = APIHelper.getPostData(req);
        if (postData==null){
            writer.println(ERROR("参数有误"));
            return;
        }
        if (postData.getString("courseId")==null){
            writer.println(ERROR("课程id为空"));
            return;
        }
        if (postData.getString("teacherId")==null){
            writer.println(ERROR("任课教师不能为空"));
            return;
        }
        if (postData.getString("courseName")==null){
            writer.println(ERROR("课程名称不能为空"));
            return;
        }
        if (postData.getString("credits")==null){
            writer.println(ERROR("学分不能为空"));
            return;
        }
        if (postData.getString("courseTypeId")==null){
            writer.println(ERROR("课程类型不能为空"));
            return;
        }
        // 初始化对象
        Course c = new Course();
        c.setCourseId(Integer.parseInt(postData.getString("courseId")));
        c.setCourseName(postData.getString("courseName"));
        Integer teacherId = postData.getInteger("teacherId");
        if (teacherId != null) {
            c.setTeacherId(teacherId);
        } else {
            c.setTeacherId(1);
        }
        Integer courseTypeId = postData.getInteger("courseTypeId");
        if (courseTypeId != null) {
            c.setCourseTypeId(courseTypeId);
        } else {
            c.setCourseTypeId(1);
        }
        Double credits = postData.getDouble("credits");
        if (credits != null) {
            c.setCredits(credits);
        } else {
            c.setCredits(0.0);
        }
        // 插入数据，并返回给前端
        String update = _courseServise.UpdateCourse(c);
        if (update.equals("修改成功")){
            writer.println(SUCCESS(update));
        }else {
            writer.println(ERROR(update));
        }

    }
    public void DeleteCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        JSONObject postData = APIHelper.getPostData(req);
        if (postData==null){
            writer.println(ERROR("参数有误"));
            return;
        }
        if (postData.getString("courseId")==null){
            writer.println(ERROR("课程id为空"));
            return;
        }
        Integer courseId = postData.getInteger("courseId");
        boolean b = _courseServise.DeleteCourse(courseId);
        if (b){
            writer.println(SUCCESS("删除成功"));

        }else {
            writer.println(ERROR("删除失败"));

        }
    }
    public void GetCourseById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        if (req.getParameter("courseId")==null){
            writer.println(ERROR("courseId为空"));
            return;
        }
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        Course course = _courseServise.GetCourseInfo(courseId);
        writer.println(SUCCESS(course));
    }

    // 查询我的选课
    public void myclassscourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 数据
        String Id = req.getParameter("studentCode");
        List<Course> Course = _courseServise.GetMyclassstudentcourseList(Id);
        PrintWriter w = resp.getWriter();
        w.println(SUCCESS(Course));
    }
}

