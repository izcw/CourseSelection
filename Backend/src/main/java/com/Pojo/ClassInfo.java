package com.Pojo;

public class ClassInfo extends BaseModel{
    public Integer ClassId;
    public String ClassName;
    public Integer NumberOfStudent;
    public Integer TeacherId;


    public Integer getClassId() {
        return ClassId;
    }

    public void setClassId(Integer classId) {
        ClassId = classId;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public Integer getNumberOfStudent() {
        return NumberOfStudent;
    }

    public void setNumberOfStudent(Integer numberOfStudent) {
        NumberOfStudent = numberOfStudent;
    }

    public Integer getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(Integer teacherId) {
        TeacherId = teacherId;
    }
}
