package com.cloud.sso.oauth.service;

import com.cloud.sso.entity.User;
import com.cloud.sso.mapper.UserMapper;
import com.cloud.sso.oauth.obj.AppAuthority;
import com.cloud.sso.oauth.obj.AppUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        if (userName == null) {
            return null;
        }

        // 用户认证
        User user = userMapper.getByUserName(userName);

        if (user == null) {
            return null;
        }

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_MANAGE");

        AppUserDetails appUserDetails = new AppUserDetails(user.getUserName(), user.getPassword(),
                user.getRealName(), user.getTelephone(), user.getEmail());

        appUserDetails.setAuthorities(convertRoleToGrantedAuthority(roles));

        return appUserDetails;

        //  return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorityList);
    }


    public Collection<GrantedAuthority> convertRoleToGrantedAuthority(Set<String> userRoleCodes) {
        if (userRoleCodes == null) {
            return new HashSet<GrantedAuthority>();
        } else {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            userRoleCodes.stream().filter((role) -> {
                return !StringUtils.isEmpty(role);
            }).forEach((role) -> {
                role = role.trim();
                if (!role.startsWith("ROLE_")) {
                    role = "ROLE_" + role;
                }

                grantedAuthorities.add(new SimpleGrantedAuthority(role));
            });
            return grantedAuthorities;
        }
    }
}
