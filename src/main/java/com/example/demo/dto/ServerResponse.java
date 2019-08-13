package com.example.demo.dto;

import lombok.Data;

/**
 * Package： com.example.demo.dto
 * Author:  hujin
 * Date: 2019/8/7 11:05
 * Description: 通用返回数据类型
 * Version：
 */
@Data
public class ServerResponse<T> {

    // 编码
    private Integer code;

    // 信息内容
    private  String msg;

    // 数据
    private T data;

    // 重载
    public  ServerResponse() {
    }

    public ServerResponse(Integer code ,String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
