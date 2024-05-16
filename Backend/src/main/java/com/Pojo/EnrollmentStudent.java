package com.Pojo;

public class EnrollmentStudent {
    public int ESId;
    public int EnrollmentId;
    public int StudentId;
    public String EnrollmentTime;
    public Course Course;
    public Student Student;

    public EnrollmentStudent() {
    }

    public EnrollmentStudent(int ESId, int enrollmentId, int studentId, String enrollmentTime, com.Pojo.Course course, com.Pojo.Student student) {
        this.ESId = ESId;
        EnrollmentId = enrollmentId;
        StudentId = studentId;
        EnrollmentTime = enrollmentTime;
        Course = course;
        Student = student;
    }

    public int getESId() {
        return ESId;
    }

    public void setESId(int ESId) {
        this.ESId = ESId;
    }

    public int getEnrollmentId() {
        return EnrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        EnrollmentId = enrollmentId;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public String getEnrollmentTime() {
        return EnrollmentTime;
    }

    public void setEnrollmentTime(String enrollmentTime) {
        EnrollmentTime = enrollmentTime;
    }

    public com.Pojo.Course getCourse() {
        return Course;
    }

    public void setCourse(com.Pojo.Course course) {
        Course = course;
    }

    public com.Pojo.Student getStudent() {
        return Student;
    }

    public void setStudent(com.Pojo.Student student) {
        Student = student;
    }
}
