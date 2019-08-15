package com.example.demo.controller;

import com.example.demo.dto.ServerResponse;
import com.example.demo.service.AuthService;
import com.example.demo.vo.AuthVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value="/login")
    public ServerResponse<String> login(@RequestBody AuthVo vo){
        try{
            String token = authService.login(vo);
            return  new ServerResponse(200,"",token);
        }catch (Exception e){
            e.printStackTrace();
            return new ServerResponse(-1,"","");
        }
    }
}
