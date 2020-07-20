package com.cloud.sso.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user")
public class User {

    @TableId(value = "id")
    private String id;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "password")
    private String password;

    @TableField(value = "telephone")
    private String telephone;

    @TableField(value = "real_name")
    private String realName;

    @TableField(value = "email")
    private String email;


}
