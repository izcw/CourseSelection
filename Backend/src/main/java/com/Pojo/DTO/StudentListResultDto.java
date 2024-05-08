package com.Pojo.DTO;

import com.Pojo.Student;

import java.util.List;

public class StudentListResultDto {
    public List<Student> list ;
    public PagerInfoDto pagerInfoDto;

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    public PagerInfoDto getPagerInfoDto() {
        return pagerInfoDto;
    }

    public void setPagerInfoDto(PagerInfoDto pagerInfoDto) {
        this.pagerInfoDto = pagerInfoDto;
    }
}
