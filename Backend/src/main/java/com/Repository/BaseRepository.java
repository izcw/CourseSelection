package com.Repository;

import com.IRepository.IBaseRepository;
import com.Tools.SqlHelper;
import org.apache.commons.collections4.map.CaseInsensitiveMap;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实现IBaseRepository接口，提供数据库操作的基础功能
 * @param <T> 数据库实体类类型
 */
public class BaseRepository<T> implements IBaseRepository<T> {

    /**
     * 查询数据列表
     * @param sql SQL查询语句
     * @return 数据列表
     */
    @Override
    public List<T> GetList(String sql) {
        List<T> list = new ArrayList<>();
        Connection conn = null;
        Statement sm = null;
        ResultSet rs = null;
        try {
            conn = SqlHelper.GetConnection();
            sm = SqlHelper.GetStatement(conn);
            rs = sm.executeQuery(sql);

            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();

            while (rs.next()) {
                Map<String, Object> rowData = new CaseInsensitiveMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnLabel(i).toUpperCase(), rs.getObject(i));
                }
                T t = initTClass(getTClass(), rowData);
                list.add(t);
            }
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        } finally {
            SqlHelper.CloseResources(conn, sm);
        }
        return list;
    }

    /**
     * 通过参数查询数据
     * @param sql SQL查询语句
     * @param params SQL 查询语句中的参数值
     * @return 数据列表
     */
    public List<T> GetListparams(String sql, List<Object> params) {
        List<T> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = SqlHelper.GetConnection();
            ps = conn.prepareStatement(sql);

            // 设置查询参数
            if (params != null) {
                for (int i = 0; i < params.size(); i++) {
                    ps.setObject(i + 1, params.get(i));
                }
            }

            rs = ps.executeQuery();

            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();

            while (rs.next()) {
                Map<String, Object> rowData = new CaseInsensitiveMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnLabel(i).toUpperCase(), rs.getObject(i));
                }
                T t = initTClass(getTClass(), rowData);
                list.add(t);
            }
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        } finally {
            SqlHelper.CloseResources(conn, ps);
        }
        return list;
    }


    /**
     * 查询第一个结果
     * @param sql SQL查询语句
     * @return 第一个结果
     */
    @Override
    public T GetFirst(String sql) {
        T t = null;
        Connection conn = null;
        Statement sm = null;
        ResultSet rs = null;
        try {
            conn = SqlHelper.GetConnection();
            sm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = sm.executeQuery(sql);

            if (rs.first()) {
                ResultSetMetaData md = rs.getMetaData();
                int columnCount = md.getColumnCount();
                Map<String, Object> rowData = new CaseInsensitiveMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnLabel(i), rs.getObject(i));
                }
                t = initTClass(getTClass(), rowData);
            }
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        } finally {
            SqlHelper.CloseResources(conn, sm);
        }
        return t;
    }

    /**
     * 执行更新操作（插入、更新、删除）
     * @param sql SQL更新语句
     * @return 受影响的行数
     */
    @Override
    public int ExecuteUpdate(String sql) {
        int result = 0;
        Connection conn = null;
        Statement sm = null;
        try {
            conn = SqlHelper.GetConnection();
            sm = conn.createStatement();
            result = sm.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlHelper.CloseResources(conn, sm);
        }
        return result;
    }

    /**
     * 通过传入一个值来删除指定数据
     * @param sql SQL更新语句
     * @param code （要查找的值）
     * @return 受影响的行数
     */
    public int deleteExecuteUpdate(String sql, String code) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = SqlHelper.GetConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, code);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlHelper.CloseResources(conn, pstmt);
        }
        return result;
    }


    /**
     * 查询结果数量
     * @param sql SQL查询语句
     * @return 结果数量
     */
    @Override
    public Integer ExecuteQueryCount(String sql) {
        int count = 0;
        Connection conn = null;
        Statement sm = null;
        ResultSet rs = null;
        try {
            conn = SqlHelper.GetConnection();
            sm = SqlHelper.GetStatement(conn);
            rs = sm.executeQuery(sql);
            while (rs.next()) {
                count++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlHelper.CloseResources(conn, sm);
        }
        return count;
    }

    /**
     * 执行SQL语句
     * @param sql SQL语句
     */
    @Override
    public void ExecuteSql(String sql) {
        Connection conn = null;
        Statement sm = null;
        try {
            conn = SqlHelper.GetConnection();
            sm = SqlHelper.GetStatement(conn);
            sm.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlHelper.CloseResources(conn, sm);
        }
    }

    /**
     * 通过反射动态创建对象并设置属性值
     */
    public static <T> T initTClass(Class<T> cls, Map<String, Object> dataMap) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        T t = cls.getDeclaredConstructor().newInstance();
        Field[] declaredFields = t.getClass().getDeclaredFields();
        for (Field f1 : declaredFields) {
            String fieldName = f1.getName().toUpperCase();
            Object o1 = dataMap.get(fieldName);
            if (o1 != null) {
                f1.setAccessible(true);
                f1.set(t, o1);
            } else {
                try {
                    Class<?> type = f1.getType();
                    String typePackageName = type.getPackageName();
                    if (!"com.Pojo".equals(typePackageName)) {
                        continue;
                    }
                    Object o = f1.getType().getDeclaredConstructor().newInstance();
                    Field[] fields = o.getClass().getDeclaredFields();
                    for (Field f2 : fields) {
                        Object o2 = dataMap.get(f2.getName().toUpperCase());
                        if (o2 != null) {
                            f2.setAccessible(true);
                            f2.set(o, o2);
                        }
                    }
                    f1.setAccessible(true);
                    f1.set(t, o);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        return t;
    }

    /**
     * 获取泛型类型
     */
    public Class<T> getTClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}