package com.Pojo.DTO;

import java.util.List;

public class ListResultDto<T> {
    public List<T> list ;
    public PagerInfoDto pagerInfoDto;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public PagerInfoDto getPagerInfoDto() {
        return pagerInfoDto;
    }

    public void setPagerInfoDto(PagerInfoDto pagerInfoDto) {
        this.pagerInfoDto = pagerInfoDto;
    }
}
