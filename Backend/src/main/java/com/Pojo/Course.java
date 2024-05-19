package com.Pojo;

public  class Course extends BaseModel{
    public Integer CourseId;
    public String CourseCode;
    public String CourseName;
    public Integer  TeacherId;
    public Integer CourseTypeId;

    public Double Credits;
    public Teacher Teacher;
    public CourseType CourseType;
    private int maximumQuantity; // 新增字段
    private int selectednumberpeople;


    public Course() {
    }

    public Course(Integer courseId, String courseCode, String courseName, Integer teacherId, Integer courseTypeId, Double credits, com.Pojo.Teacher teacher, com.Pojo.CourseType courseType) {
        CourseId = courseId;
        CourseCode = courseCode;
        CourseName = courseName;
        TeacherId = teacherId;
        CourseTypeId = courseTypeId;
        Credits = credits;
        Teacher = teacher;
        CourseType = courseType;
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
