package com.example.demo.pojo;

import lombok.Data;

/**
 * Package： com.example.demo.pojo
 * Author:  hujin
 * Date: 2019/8/7 11:23
 * Description: 个人信息
 * Version：
 */
@Data
public class Person {

    // 账户编号
    private String userId ;
    // 姓名
    private String userName;
    // 邮箱
    private String iEail;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getiEail() {
        return iEail;
    }
}
