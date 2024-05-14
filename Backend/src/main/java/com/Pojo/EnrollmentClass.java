package com.Pojo;

public class EnrollmentClass {
    public int ECId;
    public int EnrollmentId;
    public int ClassId;

    public EnrollmentClass() {
    }

    public EnrollmentClass(int ECId, int enrollmentId, int classId) {
        this.ECId = ECId;
        EnrollmentId = enrollmentId;
        ClassId = classId;
    }

    public int getECId() {
        return ECId;
    }

    public void setECId(int ECId) {
        this.ECId = ECId;
    }

    public int getEnrollmentId() {
        return EnrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        EnrollmentId = enrollmentId;
    }

    public int getClassId() {
        return ClassId;
    }

    public void setClassId(int classId) {
        ClassId = classId;
    }
}
