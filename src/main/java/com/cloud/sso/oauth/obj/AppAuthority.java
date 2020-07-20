package com.cloud.sso.oauth.obj;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class AppAuthority implements GrantedAuthority {

    private String authCode;

    public AppAuthority() {
    }

    public AppAuthority(String authCode) {
        this.authCode = authCode;
    }

    @Override
    public String getAuthority() {
        return authCode;
    }
}
