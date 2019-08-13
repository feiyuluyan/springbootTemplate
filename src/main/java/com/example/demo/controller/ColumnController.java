package com.example.demo.controller;

import com.example.demo.dto.ServerResponse;
import com.example.demo.pojo.Column;
import com.example.demo.service.data.ColumnService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Package： com.example.demo.controller
 * Author:  hujin
 * Date: 2019/8/8 16:45
 * Description:
 * Version：
 */
@RestController
@RequestMapping("/column")
public class ColumnController {

    @Autowired
    private ColumnService columnService;

    @PostMapping(value="/getColumn")
    public ServerResponse<Column> getColumn(@RequestParam String userId){
        return new ServerResponse<>(200,"",columnService.getColumnByUserId(userId));
    }
}
