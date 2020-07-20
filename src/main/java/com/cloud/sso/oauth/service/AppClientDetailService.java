package com.cloud.sso.oauth.service;

import com.cloud.sso.entity.Application;
import com.cloud.sso.mapper.ApplicationMapper;
import com.cloud.sso.oauth.obj.AppClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class AppClientDetailService implements ClientDetailsService {

    @Autowired
    private ApplicationMapper applicationMapper;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        Application application = applicationMapper.getByClientId(clientId);

        if (application != null) {
            AppClient appClient = new AppClient(application.getClientId(), application.getResourceIds(), application.getSecretRequired(),
                    application.getClientSecret(), null, application.getScope(), application.getGrantType(), application.getRedirectUri(),
                    application.getAutoApprove(), application.getAccessTokenValiditySecond(), application.getRefreshTokenValiditySecond());

            Set<String> appRoleCodeSet = new HashSet<>();

            //appRoleCodeSet.add("USER_LIST");

            appClient.setAuthorities(convertAppRoleGrantedAuthority(appRoleCodeSet));

            return appClient;
        } else {
            return null;
        }

    }

    public Collection<GrantedAuthority> convertAppRoleGrantedAuthority(Set<String> userRoleCodes) {
        if (userRoleCodes == null) {
            return new HashSet<GrantedAuthority>();
        } else {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            userRoleCodes.stream().filter((role) -> {
                return !StringUtils.isEmpty(role);
            }).forEach((role) -> {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.trim()));
            });
            return grantedAuthorities;
        }
    }
}
