package com.example.demo.service;

import com.example.demo.pojo.User;
import com.example.demo.vo.AuthVo;

/**
 * Package： com.example.demo.service
 * Author:  hujin
 * Date: 2019/8/7 15:20
 * Description:
 * Version：
 */
public interface AuthService {

    User getUser(String userName);

    String login(AuthVo vo) throws Exception;
}
