package com.IRepository;

import java.util.List;

public interface IBaseRepository <T> {
    //获取集合
    List<T> GetList(String sql);
    //获取首行
    T GetFirst(String sql);
    //增删改
    int ExecuteUpdate(String sql);

    Integer ExecuteQueryCount(String sql);

    void ExecuteSql(String sql);
    //使用事务修改多条数据
    int ExecuteUpdate(List<String> sqls);
}
