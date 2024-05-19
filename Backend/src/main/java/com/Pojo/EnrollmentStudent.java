package com.Pojo;

public class EnrollmentStudent {
    public int ESId;
    public int EnrollmentId;
    public String courseCode;
    public String StudentCode;
    public String EnrollmentTime;
    public Course Course;
    public Student Student;


    public EnrollmentStudent() {
    }

    public EnrollmentStudent(int ESId, int EnrollmentId, String courseCode, String StudentCode, String EnrollmentTime, Course Course, Student Student) {
        this.ESId = ESId;
        this.EnrollmentId = EnrollmentId;
        this.courseCode = courseCode;
        this.StudentCode = StudentCode;
        this.EnrollmentTime = EnrollmentTime;
        this.Course = Course;
        this.Student = Student;
    }

    /**
     * 获取
     * @return ESId
     */
    public int getESId() {
        return ESId;
    }

    /**
     * 设置
     * @param ESId
     */
    public void setESId(int ESId) {
        this.ESId = ESId;
    }

    /**
     * 获取
     * @return EnrollmentId
     */
    public int getEnrollmentId() {
        return EnrollmentId;
    }

    /**
     * 设置
     * @param EnrollmentId
     */
    public void setEnrollmentId(int EnrollmentId) {
        this.EnrollmentId = EnrollmentId;
    }

    /**
     * 获取
     * @return courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * 设置
     * @param courseCode
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * 获取
     * @return StudentCode
     */
    public String getStudentCode() {
        return StudentCode;
    }

    /**
     * 设置
     * @param StudentCode
     */
    public void setStudentCode(String StudentCode) {
        this.StudentCode = StudentCode;
    }

    /**
     * 获取
     * @return EnrollmentTime
     */
    public String getEnrollmentTime() {
        return EnrollmentTime;
    }

    /**
     * 设置
     * @param EnrollmentTime
     */
    public void setEnrollmentTime(String EnrollmentTime) {
        this.EnrollmentTime = EnrollmentTime;
    }

    /**
     * 获取
     * @return Course
     */
    public Course getCourse() {
        return Course;
    }

    /**
     * 设置
     * @param Course
     */
    public void setCourse(Course Course) {
        this.Course = Course;
    }

    /**
     * 获取
     * @return Student
     */
    public Student getStudent() {
        return Student;
    }

    /**
     * 设置
     * @param Student
     */
    public void setStudent(Student Student) {
        this.Student = Student;
    }

}
