package com.re0.disco.service.impl;

import com.re0.disco.common.base.BaseServiceImpl;
import com.re0.disco.common.utils.CollectionUtils;
import com.re0.disco.domain.entity.UserRole;
import com.re0.disco.mapper.UserRoleMapper;
import com.re0.disco.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author fangxi created by 2022/4/6
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public List<Long> selectRoleIdsByUserId(Long userId) {
        List<UserRole> userRoles = lambdaQuery().eq(UserRole::getUserId, userId).select(UserRole::getRoleId).list();
        return CollectionUtils.isEmpty(userRoles) ? Collections.emptyList() : CollectionUtils.map(userRoles, UserRole::getRoleId);
    }
}
