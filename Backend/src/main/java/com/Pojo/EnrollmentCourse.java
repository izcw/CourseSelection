package com.Pojo;

public class EnrollmentCourse {
    public int ECourseId;
    public int EnrollmentId;
    public int CourseId;
<<<<<<< HEAD
    public int MaximumQuantity;
=======
>>>>>>> df678de7f559094a31688498c0d5ae18a824a205

    public EnrollmentCourse() {
    }

<<<<<<< HEAD
    public EnrollmentCourse(int ECourseId, int enrollmentId, int courseId, int maximumQuantity) {
        this.ECourseId = ECourseId;
        EnrollmentId = enrollmentId;
        CourseId = courseId;
        MaximumQuantity = maximumQuantity;
=======
    public EnrollmentCourse(int ECourseId, int enrollmentId, int courseId) {
        this.ECourseId = ECourseId;
        EnrollmentId = enrollmentId;
        CourseId = courseId;
>>>>>>> df678de7f559094a31688498c0d5ae18a824a205
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
<<<<<<< HEAD

    public int getMaximumQuantity() {
        return MaximumQuantity;
    }

    public void setMaximumQuantity(int maximumQuantity) {
        MaximumQuantity = maximumQuantity;
    }
=======
>>>>>>> df678de7f559094a31688498c0d5ae18a824a205
}
