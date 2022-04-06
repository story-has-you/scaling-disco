package com.re0.disco.service;

import com.re0.disco.common.base.BaseService;
import com.re0.disco.domain.entity.User;

/**
 * The interface UserService.
 *
 * @author fangxi created by 2022/4/6
 */
public interface UserService extends BaseService<User> {

    /**
     * 根据用户名查询用户
     *
     * @param username the username
     * @return user
     */
    User selectByUsername(String username);

}
