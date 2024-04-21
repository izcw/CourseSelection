package com.Tools;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

//tomcat初始化
public class InitListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("监听器运行");
        System.out.println("ServletContext创建");

//        HibernateUtil.init();

    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext销毁");
        System.out.println("监听器销毁");

    }

}

