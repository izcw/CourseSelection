package com.Pojo;

public class Enrollment extends BaseModel{
    public int EnrollmentId;
    public String SelectionPlanName;
    public String BeginTime;
    public String EndTime;
    public String EnrollmentMethod;
    public int State;

    public Enrollment() {
    }

    public Enrollment(int enrollmentId, String selectionPlanName, String beginTime, String endTime, String enrollmentMethod, int state) {
        EnrollmentId = enrollmentId;
        SelectionPlanName = selectionPlanName;
        BeginTime = beginTime;
        EndTime = endTime;
        EnrollmentMethod = enrollmentMethod;
        State = state;
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

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }
}
