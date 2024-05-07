package com.Pojo;


public class ClassInfo extends BaseModel{
    public Integer ClassId;
    public String ClassName;
    public Integer NumberOfStudent;
    public Integer TeacherId;
    public Teacher teacher;
    public String classNumber;
    public String code;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

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

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classnumber) {
        classNumber = classnumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
