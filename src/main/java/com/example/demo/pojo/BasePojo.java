package com.example.demo.pojo;

import lombok.Data;

/**
 * Package： com.example.demo.pojo
 * Author:  hujin
 * Date: 2019/8/8 15:47
 * Description:公共实体类
 * Version：
 */
@Data
public class BasePojo {

    //  主键id
    private String id;
    // 数据状态 1 - 正常 0 - 逻辑删除
    private Boolean status;
    // 创建人
    private String createUser;
    // 创建时间
    private String createDate;
    // 修改人
    private String modifyUser;
    // 修改时间
    private String modifyDate;
    // 删除人
    private String deleteUser;
    // 删除时间
    private String deleteDate;

}
