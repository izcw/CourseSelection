package com.Tools;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SqlHelper {

    /**
     * 获取数据库连接
     * @return 数据库连接对象
     */
    public static Connection GetConnection() {
        Connection con = null;
        Properties pro = null;
        InputStream is = null;
        try {
            // 加载配置文件
            //1、导入jar包
            //2、定义配置文件
            //3、加载配置文件
            pro =  new Properties();
            is =  SqlHelper.class.getClassLoader().getResourceAsStream("Druid.properties");
            pro.load(is);

            // 获取数据源
            DataSource ds = DruidDataSourceFactory.createDataSource(pro);

            // 获取连接
            con = ds.getConnection();

        } catch (Exception e) {
            // 抛出运行时异常
            throw new RuntimeException(e);
        }
        finally {
            if (pro != null) {
                pro.clone();
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return con;
    }

    /**
     * 关闭数据库连接
     * @param conn 要关闭的数据库连接对象
     */
    public static void CloseConn(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取数据库操作对象
     * @param conn 数据库连接对象
     * @return 数据库操作对象
     */
    public static Statement GetStatement(Connection conn) {
        Statement statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    /**
     * 关闭数据库资源（连接和操作对象）
     * @param conn 数据库连接对象
     * @param statement 数据库操作对象
     */
    public static void CloseResources(Connection conn, Statement statement,ResultSet rs) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
