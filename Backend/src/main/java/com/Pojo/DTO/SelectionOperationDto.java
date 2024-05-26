package com.Pojo.DTO;

import com.Pojo.Course;
import com.Pojo.CourseType;
import com.Pojo.Enrollment;
import com.Pojo.Teacher;

public class SelectionOperationDto {
    public int EnrollmentId;
    public String SelectionPlanName;
    public String BeginTime;
    public String EndTime;
    public String EnrollmentMethod;
    public Integer CourseId;
    public String CourseCode;
    public String CourseName;
    public Integer  TeacherId;
    public Integer CourseTypeId;

    public Double Credits;
    public com.Pojo.Teacher Teacher;
    public com.Pojo.CourseType CourseType;
    private int maximumQuantity; // 新增字段
    private int selectednumberpeople;
    public SelectionOperationDto() {
    }


    public SelectionOperationDto(int enrollmentId, String selectionPlanName, String beginTime, String endTime, String enrollmentMethod, Integer courseId, String courseCode, String courseName, Integer teacherId, Integer courseTypeId, Double credits, com.Pojo.Teacher teacher, com.Pojo.CourseType courseType, int maximumQuantity, int selectednumberpeople) {
        EnrollmentId = enrollmentId;
        SelectionPlanName = selectionPlanName;
        BeginTime = beginTime;
        EndTime = endTime;
        EnrollmentMethod = enrollmentMethod;
        CourseId = courseId;
        CourseCode = courseCode;
        CourseName = courseName;
        TeacherId = teacherId;
        CourseTypeId = courseTypeId;
        Credits = credits;
        Teacher = teacher;
        CourseType = courseType;
        this.maximumQuantity = maximumQuantity;
        this.selectednumberpeople = selectednumberpeople;
    }

    public int getEnrollmentId() {
        return EnrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        EnrollmentId = enrollmentId;
    }

    public String getSelectionPlanName() {
        return SelectionPlanName;
    }

    public void setSelectionPlanName(String selectionPlanName) {
        SelectionPlanName = selectionPlanName;
    }

    public String getBeginTime() {
        return BeginTime;
    }

    public void setBeginTime(String beginTime) {
        BeginTime = beginTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getEnrollmentMethod() {
        return EnrollmentMethod;
    }

    public void setEnrollmentMethod(String enrollmentMethod) {
        EnrollmentMethod = enrollmentMethod;
    }

    public Integer getCourseId() {
        return CourseId;
    }

    public void setCourseId(Integer courseId) {
        CourseId = courseId;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String courseCode) {
        CourseCode = courseCode;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public Integer getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(Integer teacherId) {
        TeacherId = teacherId;
    }

    public Integer getCourseTypeId() {
        return CourseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        CourseTypeId = courseTypeId;
    }

    public Double getCredits() {
        return Credits;
    }

    public void setCredits(Double credits) {
        Credits = credits;
    }

    public com.Pojo.Teacher getTeacher() {
        return Teacher;
    }

    public void setTeacher(com.Pojo.Teacher teacher) {
        Teacher = teacher;
    }

    public com.Pojo.CourseType getCourseType() {
        return CourseType;
    }

    public void setCourseType(com.Pojo.CourseType courseType) {
        CourseType = courseType;
    }

    public int getMaximumQuantity() {
        return maximumQuantity;
    }

    public void setMaximumQuantity(int maximumQuantity) {
        this.maximumQuantity = maximumQuantity;
    }

    public int getSelectednumberpeople() {
        return selectednumberpeople;
    }

    public void setSelectednumberpeople(int selectednumberpeople) {
        this.selectednumberpeople = selectednumberpeople;
    }
}
