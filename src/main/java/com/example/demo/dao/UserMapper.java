package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * Package： com.example.demo.dao
 * Author:  hujin
 * Date: 2019/8/7 16:15
 * Description:
 * Version：
 */
public interface UserMapper {

    User selectUserByUserName(@Param("userName") String userName);
}
