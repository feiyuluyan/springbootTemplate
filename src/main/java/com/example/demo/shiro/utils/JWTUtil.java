package com.example.demo.shiro.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.shiro.JWTConstant;

import java.util.Date;

/**
 * Package： com.example.demo.shiro
 * Author:  hujin
 * Date: 2019/8/7 13:56
 * Description:
 * Version：
 */
public class JWTUtil {

    /**
     * 生成密钥
     * @param userName  用户名
     * @param password  密码
     * @param expireTime  有效时长
     * @return
     */
    public static String getToken (String userName , String password){
        try{
            Date date  = new Date(System.currentTimeMillis()+ JWTConstant.EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(password);
            return JWT.create()
                    .withClaim(JWTConstant.JWT_KEY,userName)
                    .withExpiresAt(date)
                    .sign(algorithm);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验token 是否正确
     * @param token  密钥
     * @param userName 用户名
     * @param password  密码
     * @return
     */
    public static boolean verify (String token,String  userName , String password) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withClaim(JWTConstant.JWT_KEY,userName).build();
            DecodedJWT jwt  = jwtVerifier.verify(token);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 从token 获取用户名
     * @param token
     * @return
     */
    public static String getUserName (String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(JWTConstant.JWT_KEY).asString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
