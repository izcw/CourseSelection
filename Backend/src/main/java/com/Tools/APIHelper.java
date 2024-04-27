package com.Tools;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;

public class APIHelper {

    public static JSONObject getPostData(HttpServletRequest request) {
        StringBuilder data = new StringBuilder();
        String line;
        BufferedReader reader;
        try {
            reader = request.getReader();
            while (null != (line = reader.readLine())) {
                data.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return JSON.parseObject(data.toString());
    }

}
