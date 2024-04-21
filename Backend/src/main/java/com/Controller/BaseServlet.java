package com.Controller;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求标记
        String action = req.getParameter("action");
        try {
            //  获取当前类的字节码文件
            Class<? extends BaseServlet> clazz = this.getClass();
            /**
             * clazz.getMethod();
             * 查找当前类，有没有action值所对应的处理方法，它相当于:
             * if(action.equals("add")){}
             */
            Method method = clazz.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            // 判断是否为空！
            if (method != null) {
                //  method.invoke(this, req,resp);是去执行匹配的请求处理方法
                method.invoke(this, req, resp);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
