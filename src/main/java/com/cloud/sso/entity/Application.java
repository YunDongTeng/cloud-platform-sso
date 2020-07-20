package com.cloud.sso.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 应用实体类
 */
@Data
@TableName("sys_application")
public class Application {

    @TableId
    private String id;

    @TableField("app_name")
    private String appName;

    @TableField("client_id")
    private String clientId;

    @TableField("client_secret")
    private String clientSecret;

    @TableField("resource_ids")
    private String resourceIds;

    @TableField("secret_required")
    private Integer secretRequired;

    @TableField("scope")
    private String scope;

    @TableField("grant_type")
    private String grantType;

    @TableField("redirect_uri")
    private String redirectUri;

    @TableField("auto_approve")
    private Integer autoApprove;

    @TableField("access_token_validity_second")
    private Integer accessTokenValiditySecond;

    @TableField("refresh_token_validity_second")
    private Integer refreshTokenValiditySecond;
}
