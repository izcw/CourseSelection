package com.Tools;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public  class SqlHelper {



    public static Connection GetConnection() {
        Connection con = null;
        try {
            //1、导入jar包
            //2、定义配置文件
            //3、加载配置文件
            Properties pro = new Properties();
            InputStream is = SqlHelper.class.getClassLoader().getResourceAsStream("Druid.properties");

            pro.load(is);
            //4、获取数据库连接池对象
            DataSource ds = null;
            ds = DruidDataSourceFactory.createDataSource(pro);
            //5、获取连接
            con = ds.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return con;
    }

    public static void  CloseConn(Connection conn)  {

        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Statement GetStatement(Connection conn)  {

        Statement statement = null;
        try {
            statement = conn.createStatement();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  statement;
    }


}
