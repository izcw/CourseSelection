package com.Pojo;

public  class Course {
    Integer CourseId;
    String CourseNo;
    String CourseName;
    String Curriculum;
    Integer LearningTime;
    Integer TheoryTime;
    Integer ExperimentTime;
    Double Credit;
    String OpeningSemester;

    public Course() {
    }

    public Course(Integer courseId, String courseNo, String courseName, String curriculum, Integer learningTime, Integer theoryTime, Integer experimentTime, Double credit, String openingSemester) {
        CourseId = courseId;
        CourseNo = courseNo;
        CourseName = courseName;
        Curriculum = curriculum;
        LearningTime = learningTime;
        TheoryTime = theoryTime;
        ExperimentTime = experimentTime;
        Credit = credit;
        OpeningSemester = openingSemester;
    }

    public Integer getCourseId() {
        return CourseId;
    }

    public void setCourseId(Integer courseId) {
        CourseId = courseId;
    }

    public String getCourseNo() {
        return CourseNo;
    }

    public void setCourseNo(String courseNo) {
        CourseNo = courseNo;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCurriculum() {
        return Curriculum;
    }

    public void setCurriculum(String curriculum) {
        Curriculum = curriculum;
    }

    public Integer getLearningTime() {
        return LearningTime;
    }

    public void setLearningTime(Integer learningTime) {
        LearningTime = learningTime;
    }

    public Integer getTheoryTime() {
        return TheoryTime;
    }

    public void setTheoryTime(Integer theoryTime) {
        TheoryTime = theoryTime;
    }

    public Integer getExperimentTime() {
        return ExperimentTime;
    }

    public void setExperimentTime(Integer experimentTime) {
        ExperimentTime = experimentTime;
    }

    public Double getCredit() {
        return Credit;
    }

    public void setCredit(Double credit) {
        Credit = credit;
    }

    public String getOpeningSemester() {
        return OpeningSemester;
    }

    public void setOpeningSemester(String openingSemester) {
        OpeningSemester = openingSemester;
    }
}
