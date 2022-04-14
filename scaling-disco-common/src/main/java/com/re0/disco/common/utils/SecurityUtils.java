package com.re0.disco.common.utils;

import com.re0.disco.common.exceptions.BusinessException;
import com.re0.disco.domain.model.AuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author fangxi created by 2022/4/7
 */
public class SecurityUtils {

    /**
     * 获取当前登录的用户
     *
     * @return UserDetails
     */
    public static AuthUser getCurrentUser() {
        UserDetailsService userDetailsService = SpringContextHolder.getBean(UserDetailsService.class);
        return (AuthUser) userDetailsService.loadUserByUsername(getCurrentUsername());
    }

    /**
     * 获取系统用户名称
     *
     * @return 系统用户名称
     */
    public static String getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new BusinessException("当前登录状态过期");
        }
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        throw new BusinessException("找不到当前登录的信息");
    }
}
