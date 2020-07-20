package com.cloud.sso.web.oauth;

import com.cloud.sso.oauth.obj.AppUserDetails;
import com.cloud.sso.oauth.redis.AppRedisTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证中心用户处理类
 */
@RestController
@RequestMapping("/oauth/user")
public class Oauth2UserController {

    @Autowired
    private AppRedisTokenStore appRedisTokenStore;

    /**
     * 获取用户信息
     *
     * @return
     */
    @RequestMapping("/info")
    public Map<String, Object> info(HttpServletRequest request) {

        Map<String, Object> userInfo = new HashMap<>();

        String accessToken = request.getParameter("token");
        if (accessToken == null) {
            return null;
        }
        OAuth2Authentication auth2Authentication = appRedisTokenStore.readAuthentication(accessToken);

        Authentication authentication = auth2Authentication.getUserAuthentication();

        AppUserDetails appUserDetails = null;
        if (authentication.getPrincipal() instanceof AppUserDetails) {
            appUserDetails = (AppUserDetails) authentication.getPrincipal();
        }
        if (appUserDetails == null) {
            userInfo.put("message", "获取认证用户信息失败");
            userInfo.put("code", "404");
            return userInfo;
        }

        userInfo.put("userName", appUserDetails.getUsername());
        userInfo.put("realName", appUserDetails.getRealName());
        userInfo.put("telephone", appUserDetails.getTelephone());
        userInfo.put("email", appUserDetails.getEmail());


        return userInfo;
    }
}
