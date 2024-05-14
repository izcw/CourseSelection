package com.Pojo;

public class EnrollmentStudent {
    public int ESId;
    public int EnrollmentId;
    public int StudentId;
    public String EnrollmentTime;

    public EnrollmentStudent() {
    }

    public EnrollmentStudent(int ESId, int enrollmentId, int studentId, String enrollmentTime) {
        this.ESId = ESId;
        EnrollmentId = enrollmentId;
        StudentId = studentId;
        EnrollmentTime = enrollmentTime;
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
}
