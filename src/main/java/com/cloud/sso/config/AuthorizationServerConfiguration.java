package com.cloud.sso.config;

import com.cloud.sso.oauth.redis.AppRedisTokenStore;
import com.cloud.sso.oauth.service.AppClientDetailService;
import com.cloud.sso.oauth.service.AppUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Order(2)
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppRedisTokenStore appRedisTokenStore;

    @Autowired
    private AppUserDetailService appUserDetailService;

    @Autowired
    private AppClientDetailService appClientDetailService;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
       /* clients.inMemory()
                .withClient("app01")
                .scopes("all")
                .secret(passwordEncoder.encode("app01"))
                .redirectUris("http://127.0.0.1:8868/callback/")
                .accessTokenValiditySeconds(300)
                .autoApprove(true)
                .authorizedGrantTypes("authorization_code");*/
        clients.withClientDetails(appClientDetailService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        /*
            如果配置支持allowFormAuthenticationForClients的，且url中有client_id和client_secret的会走ClientCredentialsTokenEndpointFilter来保护
            如果没有支持allowFormAuthenticationForClients或者有支持但是url中没有client_id和client_secret的，走basic认证保护
         */
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();

        // oauth2密码匹配方式
        security.passwordEncoder(passwordEncoder);

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(appRedisTokenStore);
        authenticationManagerBuilder.userDetailsService(appUserDetailService).passwordEncoder(passwordEncoder);
        endpoints.authenticationManager(authenticationManagerBuilder.build());
    }


}
