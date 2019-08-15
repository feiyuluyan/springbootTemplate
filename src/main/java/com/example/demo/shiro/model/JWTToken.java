package com.example.demo.shiro.model;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * Package： com.example.demo.shiro
 * Author:  hujin
 * Date: 2019/8/7 13:49
 * Description: jwt token  实体类
 * Version：
 */
@Data
public class JWTToken implements AuthenticationToken{

    // token
    private String token;
    // 有效时长
    private Long expireTime;

    public JWTToken(String token,  Long expireTime) {
        this.token = token;
        this.expireTime = expireTime;
    }
    @Override
    public Object getPrincipal(){return token;}

    @Override
    public Object getCredentials(){return token;}

    public String getToken() {
        return token;
    }

    public Long getExpireTime() {
        return expireTime;
    }
}
