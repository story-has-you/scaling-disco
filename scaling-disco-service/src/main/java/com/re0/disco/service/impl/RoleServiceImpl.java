package com.re0.disco.service.impl;

import com.re0.disco.common.base.BaseServiceImpl;
import com.re0.disco.common.exceptions.BusinessException;
import com.re0.disco.common.utils.CollectionUtils;
import com.re0.disco.domain.entity.Role;
import com.re0.disco.mapper.RoleMapper;
import com.re0.disco.service.RoleService;
import com.re0.disco.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author fangxi created by 2022/4/6
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public List<Role> selectByUserId(Long userId) {
        List<Long> roleIds = userRoleService.selectRoleIdsByUserId(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return lambdaQuery().in(Role::getId, roleIds).list();
    }

    @Override
    public Role selectByName(String name) {
        return lambdaQuery().eq(Role::getName, name).oneOpt().orElseThrow(() -> new BusinessException("角色不存在"));
    }
}
