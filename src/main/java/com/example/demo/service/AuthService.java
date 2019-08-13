package com.example.demo.service;

import com.example.demo.pojo.User;

/**
 * Package： com.example.demo.service
 * Author:  hujin
 * Date: 2019/8/7 15:20
 * Description:
 * Version：
 */
public interface AuthService {

    User getUser(String userName);

    String login(String userName, String password) throws Exception;
}
