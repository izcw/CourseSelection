package com.Service;

import com.IService.ICourseTypeService;
import com.IService.IEnrollmentClassService;
import com.IService.IEnrollmentCourseService;
import com.IService.IEnrollmentService;
import com.Pojo.*;

import java.util.List;

public class EnrollmentService extends BaseService<Enrollment> implements IEnrollmentService {


    @Override
    public List<Enrollment> GetEnrollmentList(Enrollment e) {
        StringBuilder sb = new StringBuilder("select * from enrollment as e  where e.delFlag = 1 ");

        return GetList(sb.toString());
    }

    @Override
    public String AddEnrollment(Enrollment e) {
        String existCheck = String.format("select * from enrollment where selectionPlanName = '%s'  ",e.getSelectionPlanName());
        Enrollment EnrollmentInfo = GetFirst(existCheck);
        if(EnrollmentInfo!=null){
            return "方案名称已存在";
        }

        String sql = String.format(" insert into enrollment (selectionPlanName,beginTime,endTime,enrollmentMethod,state,createTime,delFlag) values('%s','%s','%s','%s',%d,'%s','%s') ",e.getSelectionPlanName(),e.getBeginTime(),e.getEndTime(),e.getEnrollmentMethod(),e.getState(),e.getCreateTime(),e.getDelFlag());
        int i = ExecuteUpdate(sql);
        if (i > 0) {
            return "新增成功";
        } else {
            return "新增失败";
        }


    }

    @Override
    public String UpdateEnrollment(Enrollment e) {
        String existCheck = String.format("select * from enrollment where selectionPlanName = '%s' and enrollmentId != %d limit 1 ",e.getSelectionPlanName(),e.getEnrollmentId());
        Enrollment enrollment = GetFirst(existCheck);
        if(enrollment!=null){
            return "方案名称已存在";
        }
        String sql = String.format(" update enrollment set selectionPlanName = '%s',beginTime = '%s',endTime = '%s' ,enrollmentMethod = '%s' where enrollmentId = %d ",e.getSelectionPlanName(),e.getBeginTime(),e.getEndTime(),e.getEnrollmentMethod(),e.getEnrollmentId());
        boolean b = ExecuteUpdate(sql) > 0;
        if (b){
            return "修改成功";
        }else {
            return "修改失败";
        }
    }

    @Override
    public boolean DeleteEnrollment(int id) {
        String sql  = String.format(" update enrollment set delFlag = '2'   where enrollmentId = %d ",id);
        return ExecuteUpdate(sql) > 0;
    }

    @Override
    public Enrollment GetEnrollmentInfo(int id) {
        String sql = String.format("select * from enrollment where enrollmentId = %d limit 1",id);
        return GetFirst(sql);
    }

    @Override
    public String Release(int id) {
        String sql = String.format("select * from enrollment where enrollmentId = %d limit 1",id);
        Enrollment enrollment = GetFirst(sql);
        String updateSql =  "";
        if (enrollment.getState()==1){
            updateSql = String.format(" update enrollment set state = 2 where enrollmentId = %d ",id);
        }else {
             IEnrollmentCourseService _enrollmentCourseService = new EnrollmentCourseService();
            EnrollmentCourse enrollmentCourse = new EnrollmentCourse();
            enrollmentCourse.setEnrollmentId(id);
            List<EnrollmentCourse> enrollmentCourses = _enrollmentCourseService.GetList(enrollmentCourse);
            if (enrollmentCourses.isEmpty()){
                return "请先绑定课程再发布";
            }
            IEnrollmentClassService _enrollmentClassService = new EnrollmentClassService();

            EnrollmentClass enrollmentClass = new EnrollmentClass();
            enrollmentClass.setEnrollmentId(id);
            List<EnrollmentClass> enrollmentClasses = _enrollmentClassService.GetList(enrollmentClass);
            if (enrollmentClasses.isEmpty()){
                return "请先绑定班级再发布";
            }
            updateSql = String.format(" update enrollment set state = 1 where enrollmentId = %d ",id);
        }

        boolean b = ExecuteUpdate(updateSql) > 0;
        if (b){
            return "发布成功";
        }else {
            return "发布失败";
        }

    }
}
