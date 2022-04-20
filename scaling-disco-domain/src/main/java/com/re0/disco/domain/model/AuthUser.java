package com.re0.disco.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.re0.disco.domain.entity.Role;
import com.re0.disco.domain.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author fangxi created by 2022/4/6
 */
@Data
public class AuthUser implements UserDetails {

    private Long id;
    private String name;
    private Set<GrantedAuthority> authorities;
    private Boolean enabled;
    private String password;

    private String avatar;


    public AuthUser(User user, List<Role> roles) {
        this.id = user.getId();
        this.name = user.getUsername();
        this.password = user.getPassword();
        this.enabled = user.getEnabled();
        this.authorities = roles.stream().map(Role::getName).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        this.avatar = user.getAvatar();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
