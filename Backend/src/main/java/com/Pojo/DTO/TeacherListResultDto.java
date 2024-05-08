package com.Pojo.DTO;

import com.Pojo.Student;
import com.Pojo.Teacher;

import java.util.List;

/**
 * @Author：2331020120242张成威
 * @Date： 2024/5/8 下午11:08
 * @ProjectName：Backend
 * @Description：
 */
public class TeacherListResultDto {
    public List<Teacher> list ;
    public PagerInfoDto pagerInfoDto;

    public List<Teacher> getList() {
        return list;
    }

    public void setList(List<Teacher> list) {
        this.list = list;
    }

    public PagerInfoDto getPagerInfoDto() {
        return pagerInfoDto;
    }

    public void setPagerInfoDto(PagerInfoDto pagerInfoDto) {
        this.pagerInfoDto = pagerInfoDto;
    }
}
