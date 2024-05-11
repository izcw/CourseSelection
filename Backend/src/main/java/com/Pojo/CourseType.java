package com.Pojo;

public class CourseType {
    public int CourseTypeId;
    public String TypeName;

    public CourseType() {
    }

    public CourseType(int courseTypeId, String typeName) {
        CourseTypeId = courseTypeId;
        TypeName = typeName;
    }

    public int getCourseTypeId() {
        return CourseTypeId;
    }

    public void setCourseTypeId(int courseTypeId) {
        CourseTypeId = courseTypeId;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }
}
