package com.Pojo;

public class EnrollmentCourse {
    public int ECourseId;
    public int EnrollmentId;
    public int CourseId;
    public int MaximumQuantity;

    public EnrollmentCourse() {
    }

    public EnrollmentCourse(int ECourseId, int enrollmentId, int courseId, int maximumQuantity) {
        this.ECourseId = ECourseId;
        EnrollmentId = enrollmentId;
        CourseId = courseId;
        MaximumQuantity = maximumQuantity;
    }

    public int getECourseId() {
        return ECourseId;
    }

    public void setECourseId(int ECourseId) {
        this.ECourseId = ECourseId;
    }

    public int getEnrollmentId() {
        return EnrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        EnrollmentId = enrollmentId;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int courseId) {
        CourseId = courseId;
    }

    public int getMaximumQuantity() {
        return MaximumQuantity;
    }

    public void setMaximumQuantity(int maximumQuantity) {
        MaximumQuantity = maximumQuantity;
    }
}