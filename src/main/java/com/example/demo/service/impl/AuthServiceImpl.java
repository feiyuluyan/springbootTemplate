package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.AuthService;
import com.example.demo.shiro.JWTUtil;
import com.example.demo.vo.AuthVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Package： com.example.demo.service.impl
 * Author:  hujin
 * Date: 2019/8/7 15:20
 * Description:
 * Version：
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(String userName){
        User user = new User();
        user.setUserId("admin");
        user.setUserName("admin");
        user.setPassword("123456");
        return user;
    }

    public String login(AuthVo vo)throws Exception{
        if (StringUtils.isEmpty(vo.getUsername())){
            throw new Exception("the username can not be null!");
        }
        if (StringUtils.isEmpty(vo.getPassword())){
            throw new Exception("the password can not be nll!");
        }

        // User  user = getUser(userName);
        User user = userMapper.selectUserByUserName(vo.getUsername());
        if(user == null) {
            throw new Exception("账户不存在或已注销！");
        }
        if (!vo.getPassword().toUpperCase().equals(user.getPassword())) {
            throw new Exception("账号或密码错误！");
        }
        return JWTUtil.getToken(vo.getUsername(),vo.getPassword());
    }
}
