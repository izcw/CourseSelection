package com.Controller;

import com.IService.IEnrollmentService;
import com.Pojo.Course;
import com.Pojo.Enrollment;
import com.Service.EnrollmentService;
import com.Tools.APIHelper;
import com.Tools.DateTimeHelper;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/EnrollmentServlet")
public class EnrollmentServlet extends  BaseServlet{
    private IEnrollmentService _EnrollmentService = new EnrollmentService();
    public void GetList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Enrollment e = new Enrollment();
        PrintWriter w = resp.getWriter();
        List<Enrollment> enrollments = _EnrollmentService.GetEnrollmentList(e);
        w.println(SUCCESS(enrollments));
    }
    public void AddEnrollment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        JSONObject postData = APIHelper.getPostData(req);
        if (postData==null){
            writer.println(ERROR("参数有误"));
            return;
        }
        if (postData.getString("selectionPlanName")==null){
            writer.println(ERROR("选课方案名称不能为空"));
            return;
        }
        if (postData.getString("beginTime")==null){
            writer.println(ERROR("开始时间不能为空"));
            return;
        }
        if (postData.getString("endTime")==null){
            writer.println(ERROR("结束时间不能为空"));
            return;
        }
        if (postData.getString("enrollmentMethod")==null){
            writer.println(ERROR("选课方式不能为空"));
            return;
        }
        // 初始化对象
        Enrollment e = new Enrollment();
        e.setSelectionPlanName(postData.getString("selectionPlanName"));
        e.setBeginTime(postData.getString("beginTime"));
        e.setEndTime(postData.getString("endTime"));
        e.setEnrollmentMethod(postData.getString("enrollmentMethod"));
        e.setState(2);
        String date = DateTimeHelper.GetCurrentTimeToString();
        e.setCreateTime(date);
        e.setDelFlag("1");
        // 插入数据，并返回给前端
        String AddEnrollment = _EnrollmentService.AddEnrollment(e);
        if (AddEnrollment.equals("新增成功")){
            writer.println(SUCCESS("新增成功"));
        }else {
            writer.println(ERROR(AddEnrollment));
        }
    }
    public void UpdateEnrollment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        JSONObject postData = APIHelper.getPostData(req);
        if (postData==null){
            writer.println(ERROR("参数有误"));
            return;
        }
        if (postData.getString("enrollmentId")==null){
            writer.println(ERROR("选课id为空"));
            return;
        }
        if (postData.getString("selectionPlanName")==null){
            writer.println(ERROR("选课方案名称不能为空"));
            return;
        }
        if (postData.getString("beginTime")==null){
            writer.println(ERROR("开始时间不能为空"));
            return;
        }
        if (postData.getString("endTime")==null){
            writer.println(ERROR("结束时间不能为空"));
            return;
        }
        if (postData.getString("enrollmentMethod")==null){
            writer.println(ERROR("选课方式不能为空"));
            return;
        }
        // 初始化对象
        Enrollment e = new Enrollment();
        e.setEnrollmentId(postData.getInteger("enrollmentId"));
        e.setSelectionPlanName(postData.getString("selectionPlanName"));
        e.setBeginTime(postData.getString("beginTime"));
        e.setEndTime(postData.getString("endTime"));
        e.setEnrollmentMethod(postData.getString("enrollmentMethod"));

        String update = _EnrollmentService.UpdateEnrollment(e);
        if (update.equals("修改成功")){
            writer.println(SUCCESS(update));
        }else {
            writer.println(ERROR(update));
        }

    }
    public void DeleteEnrollment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        JSONObject postData = APIHelper.getPostData(req);
        if (postData==null){
            writer.println(ERROR("参数有误"));
            return;
        }
        if (postData.getString("enrollmentId")==null){
            writer.println(ERROR("选课id为空"));
            return;
        }
        Integer enrollmentId = postData.getInteger("enrollmentId");
        boolean b = _EnrollmentService.DeleteEnrollment(enrollmentId);
        if (b){
            writer.println(SUCCESS("删除成功"));

        }else {
            writer.println(ERROR("删除失败"));

        }
    }
    public void GetEnrollmentById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        if (req.getParameter("enrollmentId")==null){
            writer.println(ERROR("enrollmentId为空"));
            return;
        }
        int enrollmentId = Integer.parseInt(req.getParameter("enrollmentId"));
        Enrollment enrollment = _EnrollmentService.GetEnrollmentInfo(enrollmentId);
        writer.println(SUCCESS(enrollment));
    }
    public  void  Release(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        JSONObject postData = APIHelper.getPostData(req);
        if (postData==null){
            writer.println(ERROR("参数有误"));
            return;
        }
        if (postData.getString("enrollmentId")==null){
            writer.println(ERROR("选课id为空"));
            return;
        }
        int enrollmentId = postData.getInteger("enrollmentId");
        String release = _EnrollmentService.Release(enrollmentId);
        if (release.equals("发布成功")){
            writer.println(SUCCESS(release));
        }else {
            writer.println(ERROR(release));
        }

    }
}
