package com.cloud.sso.oauth.provider;

import com.cloud.sso.oauth.service.AppUserDetailService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/*@Component*/
public class AppDaoAuthenticationProvider extends DaoAuthenticationProvider implements InitializingBean {

    @Autowired
    private AppUserDetailService appUserDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return super.authenticate(authentication);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return super.supports(authentication);
    }


    @Override
    protected void doAfterPropertiesSet() {
        super.doAfterPropertiesSet();
        super.setUserDetailsService(appUserDetailService);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
