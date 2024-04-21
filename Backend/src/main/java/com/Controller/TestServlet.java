package com.Controller;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/TestServlet")
public class TestServlet extends BaseServlet {

    public void  Add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject jsonObject = new JSONObject();
        ArrayList<Object> objects = new ArrayList<>();
        jsonObject.put("",objects);
        jsonObject.put("data",new Object(){
            public String Name = "111";
        });
        PrintWriter w = resp.getWriter();
        w.println(jsonObject);
    }
    public void  Delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",new Object(){
            public String Name = "111";
        });
        PrintWriter w = resp.getWriter();
        w.println(jsonObject);
    }
    public void  Query(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",new Object(){
            public String Name = "111";
        });
        PrintWriter w = resp.getWriter();
        w.println(jsonObject);
    }
}
