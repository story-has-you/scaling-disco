package com.re0.disco.api.config.security;

import com.re0.disco.domain.entity.Role;
import com.re0.disco.domain.entity.User;
import com.re0.disco.service.RoleService;
import com.re0.disco.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author fangxi created by 2022/4/6
 */
@Component("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectByUsername(username);
        List<Role> roles = roleService.selectByUserId(user.getId());
        return new AuthUser(user, roles);
    }
}
