package com.Pojo;

public class Teacher extends BaseModel{
    public Integer teacherId;
    public String teacherCode;
    public String teacherName;
    public String gender;//1:男,2：女
    public String phone;
    public Integer age;
    public String email;
    /**
     * 获取
     * @return teacherId
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * 设置
     * @param teacherId
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * 获取
     * @return teacherNumber
     */
    public String getTeacherCode() {
        return teacherCode;
    }

    /**
     * 设置
     * @param teacherCode
     */
    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    /**
     * 获取
     * @return teacherName
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * 设置
     * @param teacherName
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    /**
     * 获取
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
