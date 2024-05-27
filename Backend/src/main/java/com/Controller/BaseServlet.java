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

        if (!servletPath.equals("/LoginServlet")&&!servletPath.equals("/UserServlet")&&!method1.equals("OPTIONS")){
            String token = req.getHeader("token");

            if(token!=null){
                if (TokenHelper.verify(token)==0)
                {
                    resp.sendRedirect("login.jsp");
                    return;
                }
            }else {
                resp.sendRedirect("login.jsp");
                return;
            }
        }
        // 获取请求标记
        try {
            Class<? extends BaseServlet> clazz = this.getClass();
            Method method = null;
            String action = req.getParameter("action");

            if (action != null) {
                method = clazz.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            }

            if (method != null) {
                method.invoke(this, req, resp);
            } else {
                // 如果没有找到对应的方法，可以返回错误信息或者进行其他处理
                resp.getWriter().write("没有这样的方法: " + action);

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
    public static Object SUCCESS(String msg){
        return JSONObject.toJSON(new APIResult(200,msg));
    };
    public Object ERROR(String msg){
        return JSONObject.toJSON(new APIResult(401,msg));
    }
    public static Object ERROR(int code, String msg){
        return JSONObject.toJSON(new APIResult(code,msg));
    }


    /**
     * 传入状态码和消息
     * @param result Object[]{200, "添加成功"}
     * @param writer resp.getWriter();
     */
    public static void processResponse(Object[] result, PrintWriter writer) {
        if (result != null && result.length == 2) {
            // 如果数组不为空且长度为2，则提取状态码和消息
            int statusCode = (int) result[0];
            String message = (String) result[1];

            if (statusCode == 200) {
                writer.println(SUCCESS(message));
            } else {
                writer.println(ERROR(statusCode, message));
            }
        } else {
            // 如果数组为空或者长度不为2，则给出默认的状态码和消息
            int defaultStatusCode = 400;
            String defaultMessage = "未知错误";
            writer.println(ERROR(defaultStatusCode, "请求失败，状态码: " + defaultStatusCode + "，消息: " + defaultMessage));
        }
    }
}