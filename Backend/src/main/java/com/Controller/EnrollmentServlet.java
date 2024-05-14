package com.Controller;

import com.IService.IEnrollmentClassService;
import com.IService.IEnrollmentCourseService;
import com.IService.IEnrollmentService;
import com.Pojo.Course;
import com.Pojo.DTO.EnrollmentBindingDto;
import com.Pojo.DTO.MaximumQuantityDto;
import com.Pojo.Enrollment;
import com.Pojo.EnrollmentClass;
import com.Pojo.EnrollmentCourse;
import com.Service.EnrollmentClassService;
import com.Service.EnrollmentCourseService;
import com.Service.EnrollmentService;
import com.Tools.APIHelper;
import com.Tools.DateTimeHelper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet("/EnrollmentServlet")
public class EnrollmentServlet extends  BaseServlet{
    private IEnrollmentService _EnrollmentService = new EnrollmentService();
    private IEnrollmentCourseService _enrollmentCourseService = new EnrollmentCourseService();
    private IEnrollmentClassService _enrollmentClassService = new EnrollmentClassService();
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
    public void BindingCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
        if (postData.getString("courseIds")==null){
            writer.println(ERROR("请选择课程"));
            return;
        }
        try {
            JSONArray jsonArray = postData.getJSONArray("courseIds");
            JSONArray boundMaxNum = postData.getJSONArray("boundMaxNum");

            List<Integer> courseIds = jsonArray.toJavaList(Integer.class);
            String jsonString = boundMaxNum.toJSONString();

            List<MaximumQuantityDto> list = JSONArray.parseArray(jsonString, MaximumQuantityDto.class);

            EnrollmentBindingDto dto = new EnrollmentBindingDto();
            dto.setEnrollmentId(postData.getInteger("enrollmentId"));
            dto.setCourseIds(courseIds);
            for (Integer courseId : courseIds) {
                Optional<MaximumQuantityDto> first = list.stream().filter(c -> c.getCourseId() == courseId).findFirst();
                if (first.isEmpty()){
                    writer.println(ERROR("请给选择了的课程填写最大上课人数"));
                    return;
                }
                MaximumQuantityDto maximumQuantityDto = first.get();
                if (maximumQuantityDto.getMaximumQuantity()==0){
                    writer.println(ERROR("最大上课人数不能为0"));
                    return;
                }
            }
            dto.setMaximumQuantity(list);

            Boolean b = _enrollmentCourseService.BindingCourse(dto);
            if (b){
                writer.println(SUCCESS("绑定成功"));
            }else {
                writer.println(ERROR("绑定失败"));
            }

        }catch (Exception e){
            writer.println(ERROR("json转换报错"));
            System.out.println(e.getMessage());
        }

    }
    public void BindingClass(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
        if (postData.getString("classIds")==null){
            writer.println(ERROR("请选择班级"));
            return;
        }
        try {
            JSONArray jsonArray = postData.getJSONArray("classIds");

            List<Integer> classIds = jsonArray.toJavaList(Integer.class);
            EnrollmentBindingDto dto = new EnrollmentBindingDto();
            dto.setEnrollmentId(postData.getInteger("enrollmentId"));
            dto.setClassIds(classIds);
            Boolean b = _enrollmentClassService.BindingClass(dto);
            if (b){
                writer.println(SUCCESS("绑定成功"));
            }else {
                writer.println(ERROR("绑定失败"));
            }

        }catch (Exception e){
            writer.println(ERROR("json转换报错"));
            System.out.println(e.getMessage());
        }

    }
    public void GetBoundCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        if (req.getParameter("enrollmentId")==null){
            writer.println(ERROR("选课id为空"));
            return;
        }
        Integer enrollmentId =Integer.parseInt(req.getParameter("enrollmentId"));
        EnrollmentCourse enrollmentCourse = new EnrollmentCourse();
        enrollmentCourse.setEnrollmentId(enrollmentId);
        List<EnrollmentCourse> list = _enrollmentCourseService.GetList(enrollmentCourse);
        writer.println(SUCCESS(list));
    }
    public void GetBoundClass(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        if (req.getParameter("enrollmentId")==null){
            writer.println(ERROR("选课id为空"));
            return;
        }
        Integer enrollmentId =Integer.parseInt(req.getParameter("enrollmentId"));
        EnrollmentClass enrollmentClass = new EnrollmentClass();
        enrollmentClass.setEnrollmentId(enrollmentId);
        List<EnrollmentClass> list = _enrollmentClassService.GetList(enrollmentClass);
        writer.println(SUCCESS(list));
    }
}
