/*
package com.cloud.sso.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class RedisAuthorizationCodeService implements AuthorizationCodeServices {
    */
/**
     * redis存储accessKey的前缀
     *//*

    private static final String ACCESS_PREFIX = "oauth2:access:tmp:";

    */
/**
     * oauth2认证的accesskey的失效时间为30秒
     *//*

    private Long accessKeyTimeout  = 300L ;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public String getRedisAccessKey(String code){
        return ACCESS_PREFIX + code ;
    }

    @Override
    public String createAuthorizationCode(OAuth2Authentication authentication) {
        String code = UUID.randomUUID().getNextId().toString() ;

        stringKeyRedisTemplate.opsForValue().set( getRedisAccessKey(code) , authentication , accessKeyTimeout , TimeUnit.SECONDS) ;

        return code;
    }

    @Override
    public OAuth2Authentication consumeAuthorizationCode(String code)
            throws InvalidGrantException {
        // 由redis中读取认证的信息
        OAuth2Authentication auth2Authentication = (OAuth2Authentication)stringKeyRedisTemplate.opsForValue().get( getRedisAccessKey(code) );

        if (auth2Authentication == null) {
            throw new InvalidGrantException("Invalid authorization code: " + code);
        }

        stringKeyRedisTemplate.delete(code);

        return auth2Authentication;
    }
}
*/
