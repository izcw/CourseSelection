package com.Tools;

import com.Pojo.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Calendar;
import java.util.HashMap;

public class TokenHelper {
    public static String GetToken(User user){
        HashMap<String,Object> map = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(5,1);
        String token = JWT.create()
                .withHeader(map) //可以不设定，就是使用默认的
                .withClaim("userId",user.getUserId())//payload  //自定义用户名
                .withClaim("username",user.getUserName())
                .withExpiresAt(instance.getTime()) //指定令牌过期时间
                .sign(Algorithm.HMAC256("fdahuifeuw78921"));//签名
        return token;
    }
}
