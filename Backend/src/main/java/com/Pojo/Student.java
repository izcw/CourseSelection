package com.Pojo;

import java.time.LocalDateTime;

/**
 * @Author：2331020120242张成威
 * @Date： 2024/4/29 下午5:18
 * @ProjectName：Backend
 * @Description：
 */
public class Student  extends BaseModel{
    public Integer userId;
    public String studentCode;
    public String userName;
    public String gender;
    public String phone;
    public Integer age;
    public Integer classId;
    public String email;

    /**
     * 获取
     * @return userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取
     * @return studentCode
     */
    public String getStudentCode() {
        return studentCode;
    }

    /**
     * 设置
     * @param studentCode
     */
    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    /**
     * 获取
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * @return classId
     */
    public Integer getClassId() {
        return classId;
    }

    /**
     * 设置
     * @param classId
     */
    public void setClassId(Integer classId) {
        this.classId = classId;
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
