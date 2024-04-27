package com.Tools;

import com.Pojo.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;

public class TokenHelper {
    public static String GetToken(User user){
        HashMap<String,Object> map = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(5,1);
        String token = JWT.create()
                .withHeader(map) //可以不设定，就是使用默认的
                .withClaim("userId",user.getUserId())//设置userId
                .withExpiresAt(instance.getTime()) //指定令牌过期时间
                .sign(Algorithm.HMAC256("fdahuifeuw78921"));//签名
        return token;
    }
    //验证token并返回用户id
    public static Integer verify(String token){

        try {
            Algorithm algorithm = Algorithm.HMAC256("fdahuifeuw78921");
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Integer userId = jwt.getClaim("userId").asInt();

            return userId;
        }catch (Exception e){
            e.printStackTrace();
            return  0;
        }
    }
}
