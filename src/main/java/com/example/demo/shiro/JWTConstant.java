package com.example.demo.shiro;

/**
 * Package： com.example.demo.shiro
 * Author:  hujin
 * Date: 2019/8/7 14:56
 * Description: JWT 常量池
 * Version：
 */
public class JWTConstant {

    // 过期时长
    public final static long EXPIRE_TIME = 1000 * 60 * 60 * 8;

    // 密钥关键字
    public final static String  JWT_KEY = "user";

    // header中jwt存放的标识符
    public final static String LoginSign = "jwtToken";

    // 无权限
    public static final String CODE_401 = "401";
    // 用户名不存在
    public static final String CODE_4001 = "4001";
    // 用户密码错误
    public static final String CODE_4002 = "4002";
    // 用户状态异常
    public static final String CODE_4003 = "4003";

}
