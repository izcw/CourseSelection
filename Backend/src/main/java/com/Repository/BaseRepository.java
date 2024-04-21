package com.Repository;

import com.IRepository.IBaseRepository;
import com.Tools.SqlHelper;
import com.alibaba.fastjson.JSONObject;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseRepository <T> implements IBaseRepository<T> {
    @Override
    public List<T> GetList(String sql) {

        List<T> list =new ArrayList<>();
        Connection conn = SqlHelper.GetConnection();
        Statement sm = SqlHelper.GetStatement(conn);
        try {

            ResultSet rs = sm.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            // 将ResultSet对象的列名和值存到map中，再将map转换为json字符串，最后将json字符串转换为实体类对象
            Map<String, Object> rowData = new HashMap<>();

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnLabel(i).toUpperCase(), rs.getObject(i));

                }

//                String jsonStr = JSONObject.toJSONString(rowData);
//                T t = JSONObject.parseObject(jsonStr,getTClass());
                T t  = null;
                try {
                    t = initTClass(getTClass(),rowData);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
                list.add(t);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            SqlHelper.CloseConn(conn);
            try {
                sm.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return  list;
    }


    @Override
    public T GetFirst(String sql){
        T t = null;
        Connection conn = SqlHelper.GetConnection();
        Statement sm = SqlHelper.GetStatement(conn);
        try {
            conn = SqlHelper.GetConnection();
            sm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = sm.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            // 将ResultSet对象的列名和值存到map中，再将map转换为json字符串，最后将json字符串转换为实体类对象
            Map<String, Object> rowData = new HashMap<>();
            if (rs.first()){
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnLabel(i), rs.getObject(i));
                }
                String jsonStr = JSONObject.toJSONString(rowData);
                t = JSONObject.parseObject(jsonStr,getTClass());
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            SqlHelper.CloseConn(conn);
            try {
                sm.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return  t;
    }

    @Override
    public int ExecuteUpdate(String sql) {
        int result = 0;
        Connection conn = SqlHelper.GetConnection();
        Statement sm = SqlHelper.GetStatement(conn);
        try {
            conn = SqlHelper.GetConnection();
            sm = SqlHelper.GetStatement(conn);
            result = sm.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            SqlHelper.CloseConn(conn);
            try {
                sm.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return  result;
    }

    @Override
    public Integer ExecuteQueryCount(String sql) {
        int i = 0;
        Connection conn = SqlHelper.GetConnection();
        Statement sm = SqlHelper.GetStatement(conn);
        try {
            conn = SqlHelper.GetConnection();
            sm = SqlHelper.GetStatement(conn);
            ResultSet rs= sm.executeQuery(sql);
            while (rs.next()){
                i++;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            SqlHelper.CloseConn(conn);
            try {
                sm.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return i;
    }

    @Override
    public void ExecuteSql(String sql) {
        Connection conn = SqlHelper.GetConnection();
        Statement sm = SqlHelper.GetStatement(conn);
        try {
            conn = SqlHelper.GetConnection();
            sm.execute(sql);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            SqlHelper.CloseConn(conn);
            try {
                sm.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static<T> T initTClass(Class<T> cls,Map<String, Object> dataMap) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        T t  = cls.getDeclaredConstructor().newInstance();
        Field[] declaredFields = t.getClass().getDeclaredFields();

        //遍历map中的键
        for (Field f1 : declaredFields) {
            Object o1 = dataMap.get(f1.getName().toUpperCase());
            if (o1!=null){
                f1.setAccessible(true);
                f1.set(t,o1);
            }else
            {
                try {
                    Class<?> type = f1.getType();
                    String typePackageName = type.getPackageName();
                    if (!"com.Pojo".equals(typePackageName)){
                        continue;
                    }


                    Object o = f1.getType().getDeclaredConstructor().newInstance();
                    Field[] fields = o.getClass().getDeclaredFields();
                    for (Field f2 :fields){
                        Object o2 = dataMap.get(f2.getName().toUpperCase());
                        if (o2!=null){
                            f2.setAccessible(true);
                            f2.set(o,o2);
                        }
                    }
                    f1.setAccessible(true);
                    f1.set(t,o);

                }catch (Exception e){
                    System.out.println(e);
                }
            }


        }


        return t;
    }
    public Class<T> getTClass() {
        return  (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];


    }
}
