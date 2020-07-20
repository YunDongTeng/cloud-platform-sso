package com.cloud.sso.oauth.obj;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

@Data
public class AppClient implements ClientDetails {

    private String clientId;

    private String resourceIds;

    private Integer secretRequired;

    private String clientSecret;

    private Integer scoped;

    private String scope;

    private String grantType;

    private String redirectUri;

    private Integer autoApprove;

    private Integer accessTokenValiditySecond;

    private Integer refreshTokenValiditySecond;

    private Collection<GrantedAuthority> authorities;

    public AppClient() {
    }

    public AppClient(String clientId, String resourceIds, Integer secretRequired, String clientSecret, Integer scoped, String scope, String grantType, String redirectUri, Integer autoApprove, Integer accessTokenValiditySecond, Integer refreshTokenValiditySecond) {
        this.clientId = clientId;
        this.resourceIds = resourceIds;
        this.secretRequired = secretRequired;
        this.clientSecret = clientSecret;
        this.scoped = scoped;
        this.scope = scope;
        this.grantType = grantType;
        this.redirectUri = redirectUri;
        this.autoApprove = autoApprove;
        this.accessTokenValiditySecond = accessTokenValiditySecond;
        this.refreshTokenValiditySecond = refreshTokenValiditySecond;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        if (this.resourceIds != null) {
            return new HashSet<>(Arrays.asList(resourceIds.split(",")));
        }
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return this.secretRequired == 1 ? true : false;
    }

    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    @Override
    public boolean isScoped() {
        return this.scoped == 1 ? true : false;
    }

    @Override
    public Set<String> getScope() {
        if (this.scope != null) {
            return new HashSet<>(Arrays.asList(scope.split(",")));
        }
        return null;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        if (this.grantType != null) {
            return new HashSet<>(Arrays.asList(grantType.split(",")));
        }
        return null;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        if (this.redirectUri != null) {
            return new HashSet<>(Arrays.asList(redirectUri.split(",")));
        }
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValiditySecond;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.refreshTokenValiditySecond;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return this.autoApprove == 1 ? true : false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
