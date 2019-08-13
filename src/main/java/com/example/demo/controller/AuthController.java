package com.example.demo.controller;

import com.example.demo.dto.ServerResponse;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Package： com.example.demo.controller
 * Author:  hujin
 * Date: 2019/8/7 15:51
 * Description:
 * Version：
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(value="login")
    public ServerResponse<String> login(@RequestParam String username, @RequestParam String password){
        try{
            String token = authService.login(username,password);
            return  new ServerResponse(200,"",token);
        }catch (Exception e){
            e.printStackTrace();
            return new ServerResponse(-1,"","");
        }
    }
}
