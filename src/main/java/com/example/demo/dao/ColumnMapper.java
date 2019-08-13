package com.example.demo.dao;

import com.example.demo.pojo.Column;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Package： com.example.demo.dao
 * Author:  hujin
 * Date: 2019/8/8 15:59
 * Description:
 * Version：
 */
public interface ColumnMapper {
    Column getColumnByUserId(String userId);
}
