package com.Tools;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebFilter(servletNames = {"TestServlet"})
public class LoginFilter extends HttpFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=UTF-8");
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)res;
        //设置跨域访问
        //允许跨域的主机地址
        response.setHeader("Access-Control-Allow-Origin", "*");
        //允许跨域的请求方法GET, POST, HEAD 等
        response.setHeader("Access-Control-Allow-Methods", "*");
        //重新预检验跨域的缓存时间 (s)
        response.setHeader("Access-Control-Max-Age", "4200");
        //允许跨域的请求头
        response.setHeader("Access-Control-Allow-Headers", "*");
        //是否携带cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String servletPath = request.getServletPath();
        if (servletPath.equals("/LoginServlet")){
            chain.doFilter(request, response);

        }else {
            HttpSession session = request.getSession();

            if(session.getAttribute("flag")==null){

                request.getRequestDispatcher("login.jsp").forward(request,response);
                return;
            }else{
                chain.doFilter(request, response);
            }
        }



    }
    public void init(FilterConfig arg0) throws ServletException {

    }

}
