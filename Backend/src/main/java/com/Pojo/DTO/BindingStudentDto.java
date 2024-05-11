package com.Pojo.DTO;

import java.util.List;

public class BindingStudentDto {
    public int ClassId;
    public List<Integer> StudentIds ;

    public int getClassId() {
        return ClassId;
    }

    public void setClassId(int classId) {
        ClassId = classId;
    }

    public List<Integer> getStudentIds() {
        return StudentIds;
    }

    public void setStudentIds(List<Integer> studentIds) {
        StudentIds = studentIds;
    }
}
