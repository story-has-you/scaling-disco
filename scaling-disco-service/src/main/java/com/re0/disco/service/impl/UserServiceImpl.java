package com.re0.disco.service.impl;

import com.re0.disco.common.base.BaseServiceImpl;
import com.re0.disco.common.exceptions.BusinessException;
import com.re0.disco.domain.entity.User;
import com.re0.disco.mapper.UserMapper;
import com.re0.disco.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author fangxi created by 2022/4/6
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User selectByUsername(String username) {
        return lambdaQuery().eq(User::getUsername, username).oneOpt().orElseThrow(() -> new BusinessException("用户不存在"));
    }

    @Override
    public boolean save(User entity) {
        if (exists(User::getUsername, entity.getUsername())) {
            throw new BusinessException("用户已存在");
        }
        return super.save(entity);
    }
}
