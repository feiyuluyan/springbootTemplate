package com.example.demo.pojo;

import lombok.Data;

/**
 * Package： com.example.demo.pojo
 * Author:  hujin
 * Date: 2019/8/7 11:19
 * Description: 账户信息
 * Version：
 */
@Data
public class User {

    private String id;
    // 账户id
    private String userId;
    // 用户名
    private String userName;
    // 用户密码
    private String password;
    // 登录状态
    private Boolean isLogin;

    public User(){
        super();
    }
    public User(String id,String  userId,String userName,String password,Boolean isLogin) {
        super();
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.isLogin = isLogin;
    }

//    public String getId() {
//        return id;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public boolean getLogin() {
//        return isLogin;
//    }
}
