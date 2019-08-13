package com.example.demo.pojo;

import lombok.Data;

/**
 * Package： com.example.demo.pojo
 * Author:  hujin
 * Date: 2019/8/8 15:45
 * Description: 栏目分类
 * Version：
 */
@Data
public class Column extends BasePojo{


    // 栏目名称
    private String  columnName;

    public Column(){
        super();
    }

    public Column (String id,String columnName,Boolean status, String createDate,String createUser,String modifyDate, String modifyUser,String deleteDate,String deleteUser){
        super();
        this.setId(id);
        this.columnName = columnName;
        this.setStatus(status);
        this.setCreateDate(createDate);
        this.setCreateUser(createUser);
        this.setModifyDate(modifyDate);
        this.setModifyUser(modifyUser);
        this.setDeleteDate(deleteDate);
        this.setDeleteUser(deleteUser);
    }
}
