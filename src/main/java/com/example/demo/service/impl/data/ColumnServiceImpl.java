package com.example.demo.service.impl.data;

import com.example.demo.dao.ColumnMapper;
import com.example.demo.pojo.Column;
import com.example.demo.service.data.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Package： com.example.demo.service.impl.data
 * Author:  hujin
 * Date: 2019/8/8 15:58
 * Description:
 * Version：
 */
@Service
public class ColumnServiceImpl implements ColumnService{
    @Autowired
    private ColumnMapper columnMapper;

    @Override
    public List<Column> getColumnByUserId(String userId){
        // 账户为空
        if(StringUtils.isEmpty(userId)){
            return null;
        }
        return columnMapper.getColumnByUserId(userId);
    }
}
