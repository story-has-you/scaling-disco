package com.re0.disco.service.impl;

import com.re0.disco.common.base.BaseServiceImpl;
import com.re0.disco.common.utils.CollectionUtils;
import com.re0.disco.domain.entity.RoleMenu;
import com.re0.disco.mapper.RoleMenuMapper;
import com.re0.disco.service.RoleMenuService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author fangxi created by 2022/4/6
 */
@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public List<Long> selectMenuIdsByRoleIds(List<Long> roleIds) {
        List<RoleMenu> roleMenuList = lambdaQuery().in(RoleMenu::getRoleId, roleIds).select(RoleMenu::getMenuId).list();
        return CollectionUtils.isEmpty(roleMenuList) ? Collections.emptyList() : CollectionUtils.map(roleMenuList, RoleMenu::getMenuId);
    }
}
