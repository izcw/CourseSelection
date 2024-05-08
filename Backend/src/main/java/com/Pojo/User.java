package com.Pojo;

public class User {
    public  Integer UserId;
    public String UserName;
    public String Password;
    public String UserType;

    // 其他
    public String teacherId;
    public String teacherCode;
    public String studentCode;
    public String teacherName;
    public String gender;
    public String phone;
    public Integer age;
    public String email;

    public User() {
    }

    public User(Integer UserId, String UserName, String Password, String UserType, String teacherId, String teacherCode, String studentCode, String teacherName, String gender, String phone, Integer age, String email) {
        this.UserId = UserId;
        this.UserName = UserName;
        this.Password = Password;
        this.UserType = UserType;
        this.teacherId = teacherId;
        this.teacherCode = teacherCode;
        this.studentCode = studentCode;
        this.teacherName = teacherName;
        this.gender = gender;
        this.phone = phone;
        this.age = age;
        this.email = email;
    }

    /**
     * 获取
     * @return UserId
     */
    public Integer getUserId() {
        return UserId;
    }

    /**
     * 设置
     * @param UserId
     */
    public void setUserId(Integer UserId) {
        this.UserId = UserId;
    }

    /**
     * 获取
     * @return UserName
     */
    public String getUserName() {
        return UserName;
    }

    /**
     * 设置
     * @param UserName
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    /**
     * 获取
     * @return Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * 设置
     * @param Password
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     * 获取
     * @return UserType
     */
    public String getUserType() {
        return UserType;
    }

    /**
     * 设置
     * @param UserType
     */
    public void setUserType(String UserType) {
        this.UserType = UserType;
    }

    /**
     * 获取
     * @return teacherId
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * 设置
     * @param teacherId
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * 获取
     * @return teacherCode
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

    public String toString() {
        return "User{UserId = " + UserId + ", UserName = " + UserName + ", Password = " + Password + ", UserType = " + UserType + ", teacherId = " + teacherId + ", teacherCode = " + teacherCode + ", studentCode = " + studentCode + ", teacherName = " + teacherName + ", gender = " + gender + ", phone = " + phone + ", age = " + age + ", email = " + email + "}";
    }
}
