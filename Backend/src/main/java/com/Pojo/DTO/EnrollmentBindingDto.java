package com.Pojo.DTO;

import java.util.List;

//选课通用
public class EnrollmentBindingDto {
    public int EnrollmentId;
    public List<Integer> ClassIds;

    public List<Integer>CourseIds;
    public List<Integer>StudentIds;
    public List<MaximumQuantityDto> MaximumQuantity;

    public EnrollmentBindingDto() {
    }

    public EnrollmentBindingDto(int enrollmentId, List<Integer> classIds, List<Integer> courseIds, List<Integer> studentIds, List<MaximumQuantityDto> maximumQuantity) {
        EnrollmentId = enrollmentId;
        ClassIds = classIds;
        CourseIds = courseIds;
        StudentIds = studentIds;
        MaximumQuantity = maximumQuantity;
    }

    public int getEnrollmentId() {
        return EnrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        EnrollmentId = enrollmentId;
    }

    public List<Integer> getClassIds() {
        return ClassIds;
    }

    public void setClassIds(List<Integer> classIds) {
        ClassIds = classIds;
    }

    public List<Integer> getCourseIds() {
        return CourseIds;
    }

    public void setCourseIds(List<Integer> courseIds) {
        CourseIds = courseIds;
    }

    public List<Integer> getStudentIds() {
        return StudentIds;
    }

    public void setStudentIds(List<Integer> studentIds) {
        StudentIds = studentIds;
    }

    public List<MaximumQuantityDto> getMaximumQuantity() {
        return MaximumQuantity;
    }

    public void setMaximumQuantity(List<MaximumQuantityDto> maximumQuantity) {
        MaximumQuantity = maximumQuantity;
    }
}
