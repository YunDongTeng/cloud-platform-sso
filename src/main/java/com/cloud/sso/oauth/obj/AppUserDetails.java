package com.cloud.sso.oauth.obj;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class AppUserDetails implements UserDetails {

    private String userName;

    private String password;

    private String realName;

    private String telephone;

    private String email;

    private Collection<GrantedAuthority> authorities = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public AppUserDetails(String userName,String password, String realName, String telephone, String email) {
        this.userName = userName;
        this.password = password;
        this.realName = realName;
        this.telephone = telephone;
        this.email = email;
    }

    public AppUserDetails() {
    }
}
