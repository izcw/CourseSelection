package com.Controller;

import com.Tools.APIResult;
import com.Tools.TokenHelper;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        //设置跨域访问
        //允许跨域的主机地址
        resp.setHeader("Access-Control-Allow-Origin", "*");
        //允许跨域的请求方法GET, POST, HEAD 等
        resp.setHeader("Access-Control-Allow-Methods", "*");
        //重新预检验跨域的缓存时间 (s)
        resp.setHeader("Access-Control-Max-Age", "4200");
        //允许跨域的请求头
        resp.setHeader("Access-Control-Allow-Headers", "*");
        //是否携带cookie
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        String servletPath = req.getServletPath();
        //判断请求地址如果不是登录接口则验证有没有token以及token是否合法
        String method1 = req.getMethod();

        if (!servletPath.equals("/LoginServlet")&&!method1.equals("OPTIONS")){
            String token = req.getHeader("token");

            if(token!=null){
//                if (TokenHelper.verify(token)==0)
//                {
//                    resp.sendRedirect("login.jsp");
//                    return;
//                }
            }else {
                resp.sendRedirect("login.jsp");
                return;
            }
        }
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
                System.out.println(resp);
                //  method.invoke(this, req,resp);是去执行匹配的请求处理方法
                method.invoke(this, req, resp);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public Object SUCCESS(String msg,Object data){
        return JSONObject.toJSON(new APIResult(200,msg,data));
    };
    public Object SUCCESS(Object data){
        return JSONObject.toJSON(new APIResult(200,"success",data));
    };
    public Object SUCCESS(String msg){
        return JSONObject.toJSON(new APIResult(200,msg));
    };
    public Object ERROR(String msg){
        return JSONObject.toJSON(new APIResult(401,msg));
    }
    public Object ERROR(int code,String msg){
        return JSONObject.toJSON(new APIResult(code,msg));
    }
}
