package com.Pojo;

public class Enrollment extends BaseModel{
    public int EnrollmentId;
    public String BeginTime;
    public String EndTime;
    public String EnrollmentMethod;

    public Enrollment() {
    }

    public Enrollment(int enrollmentId, String beginTime, String endTime, String enrollmentMethod) {
        EnrollmentId = enrollmentId;
        BeginTime = beginTime;
        EndTime = endTime;
        EnrollmentMethod = enrollmentMethod;
    }

    public int getEnrollmentId() {
        return EnrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        EnrollmentId = enrollmentId;
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
}
