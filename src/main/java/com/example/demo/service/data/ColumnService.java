package com.example.demo.service.data;

import com.example.demo.pojo.Column;

import java.util.List;

/**
 * Package： com.example.demo.service.data
 * Author:  hujin
 * Date: 2019/8/8 15:58
 * Description:
 * Version：
 */
public interface ColumnService {

    List<Column> getColumnByUserId(String userId);
}
